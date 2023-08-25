package pd.example.demo.major.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    /**
     * geting error handling page
     * @param model
     * @return
     */
    @RequestMapping("/quiz-error")
    public String handleError(Model model) {
        String errorMessage = (String) model.getAttribute("errorMessageQuiz");
        if (errorMessage == null) {
            errorMessage = "An error occurred.";
        }
        model.addAttribute("errorMessageQuiz", errorMessage);
        return "quiz-error.html";
    }
}
