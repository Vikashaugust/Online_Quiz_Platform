package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.model.Question;
import pd.example.demo.major.project.model.QuestionForm;
import pd.example.demo.major.project.model.Result;
import pd.example.demo.major.project.repository.QuestionRepository;
import pd.example.demo.major.project.repository.ResultRepository;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuestionRepository qRepo;
    @Autowired
    private ResultRepository rRepo;
    /**
     * It will get all the question  from database.
     * @return
     */
    public QuestionForm getQuestion() {
        List<Question> allQues = qRepo.findAll();
        QuestionForm qForm = new QuestionForm();
        qForm.setQuestion(allQues);
        return qForm;
    }
    /**
     * After submitting answer of question will calculate total correct answer score and display.
     * @param qForm
     * @return
     */
    public int getResult(QuestionForm qForm) {
        int correct = 0;
        for (Question q : qForm.getQuestion()) {
            if (q.getAns() == q.getChose()) {
                correct++;
            }

        }
        return correct;
    }
    /**
     * After submitting answer of question will calculate total skipped question score and display.
     * @param qForm
     * @return
     */
    public int getResult1(QuestionForm qForm) {
        int skipped = 0;
        for (Question q : qForm.getQuestion()) {
            if (q.getChose()==0) {
                skipped++;
            }
        }
        return skipped;
    }
    /**
     * It will save the all the score in database
     * @param result
     */
    public void saveScore(Result result) {
        rRepo.save(result);
    }
    /**
     * It will find all the top score and display assending order.
     * @return
     */
    public List<Result> getTopScore() {
        return rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }
    }

