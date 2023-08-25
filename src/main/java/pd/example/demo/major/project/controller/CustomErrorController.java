package pd.example.demo.major.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pd.example.demo.major.project.exception.ForbiddenException;

@Controller
public class CustomErrorController {
    /**
     * handling ecxeption
     * @param ex
     * @return
     */
    @ExceptionHandler(ForbiddenException.class)
    public ModelAndView handleForbiddenException(ForbiddenException ex) {
        ModelAndView modelAndView = new ModelAndView("403"); // Path to your custom error HTML page
        modelAndView.addObject("errorMessage", "Custom Forbidden Error Message"); // You can customize the error message here
        return modelAndView;
    }
}
