package pd.example.demo.major.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pd.example.demo.major.project.exception.InvalidNameException;
import pd.example.demo.major.project.model.Proleap;
import pd.example.demo.major.project.repository.ProfileRepository;
import pd.example.demo.major.project.repository.ProleapRepository;
import pd.example.demo.major.project.service.CourseService;
import pd.example.demo.major.project.service.ProleapService;
import pd.example.demo.major.project.utility.Course;

import java.util.List;

@Controller
public class ProleapController {
    private final ProleapService proleapService;
    private final CourseService courseService;
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    public ProleapController(ProleapService proleapService, CourseService courseService) {
        this.proleapService = proleapService;
        this.courseService = courseService;
    }
    /**
     * Implemented using dropdown method instead of option tag in html
     * @param model
     * @return
     */
    @GetMapping("/proleap-register")
    public String showRegistrationForm(Model model) {
        List<Course> coursesList= courseService.getDropdown();

        model.addAttribute("courseList",coursesList);
        model.addAttribute("proleapRegistration", new Proleap());
        return "proleap-register";
    }
    /**
     * If the proleap and profile registration validation will be tru then it will save the details
     * in the proleap database and start sending a mail to the registered gmail id.
     *
     * @param :proleap
     * @param :model
     * @param :course
     * @param :ra
     * @return
     */
    @PostMapping("/proleap-register")
    public String registerCandidate(@ModelAttribute("proleapRegistration")
                                    Proleap proleap, Model model, Course course, RedirectAttributes ra) {
        if(profileRepository.findAll().stream().filter(profile->profile.getName().equalsIgnoreCase(proleap.getName())).findFirst().isEmpty()) {
//            model.addAttribute("error", "Invalid Name");
//            return "redirect:/proleap-register";
            throw new InvalidNameException("Name was not matched as the time of registration");
        }
        proleapService.save(proleap);
        //dropdown
        course.setCourseRegistered(course.getCourseRegistered());//java
        //courseService.save(course);
        model.addAttribute("data1",course.toString());
        //java mail sender
        Proleap registration = new Proleap();
        registration.setEmail(proleap.getEmail());
        registration.setName("Welcome to "+ proleap.getName()+" Mail id");
        registration.setUid(proleap.getUid());
        registration.setPassword(proleap.getPassword());

        proleapService.sendEmail(registration);

        return "redirect:/exam-register";
    }

    /**
     * Handling the exception
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(InvalidNameException.class)
    public String handleInvalidNameException(InvalidNameException ex, Model model) {
        model.addAttribute("errorMessageName", ex.getMessage());
        return "invalid-name";
    }
}
