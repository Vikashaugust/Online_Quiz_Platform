package pd.example.demo.major.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pd.example.demo.major.project.model.User;
import pd.example.demo.major.project.service.UserDetailsServiceImpl;

@Controller

public class UserController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public UserController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    /**
     * Getting quiz-registration page
     * @param model
     * @return
     */

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "quiz-registration";
    }

    /**
     * it will save detail of new registration then it will go for registration successpage
     * @param user
     * @return
     */
    @PostMapping("/registration")
    public String processRegistrationForm(User user) {
        userDetailsServiceImpl.register(user);
        return "redirect:/login";
    }
}
