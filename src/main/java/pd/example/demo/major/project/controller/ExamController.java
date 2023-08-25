package pd.example.demo.major.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pd.example.demo.major.project.exception.InvalidCredentialsException;
import pd.example.demo.major.project.model.Exam;
import pd.example.demo.major.project.model.Proleap;
import pd.example.demo.major.project.model.Result;
import pd.example.demo.major.project.repository.ProleapRepository;
import pd.example.demo.major.project.service.ExamService;
import pd.example.demo.major.project.service.ProleapService;
import pd.example.demo.major.project.service.TimeslotService;
import pd.example.demo.major.project.utility.Timeslot;

import java.util.List;

@Controller

public class ExamController {
    @Autowired
    Result result;
    @Autowired
    ProleapService proleapService;


    private final ExamService examService;
    private final TimeslotService timeslotService;

    @Autowired
    public ExamController(ExamService examService, TimeslotService timeslotService) {
        this.examService = examService;
        this.timeslotService = timeslotService;
    }

    /**
     * Implemented using dropdown method instead of option tag in html
     * @param model
     * @return
     */
    @GetMapping("/exam-register")

    public String showRegistrationForm(Model model) {
        List<Timeslot> timeslotList = timeslotService.getDropDown();
        model.addAttribute("timeslotList", timeslotList);
        model.addAttribute("candidate", new Exam());
        return "exam-register";
    }

    /**
     * comparing UID and Password by using findByUidAndPassword method it is same or not
     * @param exam
     * @param model
     * @param timeslot
     * @param ra
     * @return
     */
    @PostMapping("/exam-register")
    public String registerCandidate(@ModelAttribute("candidate") Exam exam, Model model, Timeslot timeslot, RedirectAttributes ra) {
        String uid = exam.getUid();
        String password = exam.getPassword();
        Proleap proleap = proleapService.findByUidAndPassword(uid, password);

        if (proleap != null) {
            examService.register(exam);
            timeslot.setSlot(timeslot.getSlot());
            //timeslotService.save(timeslot);
            Exam registration1 = new Exam();
            registration1.setEmail(exam.getEmail());
            registration1.setName(exam.getName());
            registration1.setExamId(exam.getExamId());
            registration1.setSlot(exam.getSlot());
            registration1.setPhone(exam.getPhone());

            examService.sendEmail(registration1);
            return "quiz-index";
        } else {
            model.addAttribute("error", "Invalid UID or Password");
            List<Timeslot> timeslotList = timeslotService.getDropDown();
            model.addAttribute("timeslotList", timeslotList);
            return "error";
        }
    }

    /**
     * handling the exception
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleInvalidCredentialsException(InvalidCredentialsException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}

