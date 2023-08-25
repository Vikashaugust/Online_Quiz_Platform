package pd.example.demo.major.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pd.example.demo.major.project.model.Exam;
import pd.example.demo.major.project.model.QuestionForm;
import pd.example.demo.major.project.model.Result;
import pd.example.demo.major.project.repository.ExamRepository;
import pd.example.demo.major.project.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QuizController {
@Autowired
ExamRepository examRepository;
    @Autowired
    Result result;
    @Autowired
    QuizService qService;

    Boolean submitted = false;

    /**
     * get result
     * @return
     */
    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    /**
     * getting quiz-index page
     * @return
     */
    @RequestMapping(value = "/quiz-register",method = RequestMethod.GET)
    public String home() {
        return "quiz-index";
    }

    /**
     * Here the name of the Exam registration page is validation if the validation will true then will it will go for quiz otherwise it stay in validation page only.
     * @param: username
     * @param: m
     * @param: ra
     * @return
     */

    @RequestMapping(value = "/quiz",method = RequestMethod.GET)
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
        if((username.equals(null)) || examRepository.findAll().stream().filter(exam->exam.getName().equalsIgnoreCase(username)).findFirst().isEmpty()) {
            ra.addFlashAttribute("errorMessage", "You must enter a valid name.");
            return "redirect:/quiz-error";
        }

        submitted = false;
        result.setUsername(username);

        QuestionForm qForm = qService.getQuestion();
        m.addAttribute("qForm", qForm);

        return "quiz";
    }
    /**
     * After submitting answer it will give total correct answer,skipped question and total false answer and saved to qService
     * @param qForm
     * @param m
     * @return
     */

    @RequestMapping(value = "/submit",method = RequestMethod.GET)
    public String submit(@ModelAttribute QuestionForm qForm, Model m, HttpSession session, SessionStatus status) {
        if(!submitted) {
            result.setTotalCorrect(qService.getResult(qForm));
            result.setTotalSkipped(qService.getResult1(qForm));

            qService.saveScore(result);
            submitted = true;
        }
        status.setComplete();
        session.removeAttribute("qForm");

        return "result";
    }
    /**
     * Here admin can see top score scored by candidate
     * @param m
     * @return
     */
    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public String score(Model m) {
        List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);


        return "scoreboard";
    }
}
