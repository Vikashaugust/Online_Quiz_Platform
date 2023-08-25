package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.model.Exam;
import pd.example.demo.major.project.repository.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * It will save the all the details of exam registration candidate into repository(database)
     * @param exam
     * @return
     */
    @Override
    public String register(Exam exam) {
        examRepository.save(exam);
        return "register successfull";
    }
    /**
     * Java mail sender will send the mail for the exam registration page from entered mail to registered gmail id.
     * @param exam
     */
    @Override
    public void sendEmail(Exam exam) {
        examRepository.save(exam);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("agarwalvikash768@gmail.com");
        message.setTo(exam.getEmail());
        message.setSubject(exam.getName());
        message.setText("Hi" +" "+ exam.getName() +
                "\n\n" + "Its time for gear up for your exam rounds." +
                "\n\n" + "YOUR ExamId  :"+ exam.getExamId() +
                "\n\n" + "YOUR Validated Name :"+ exam.getName() +//edit
                "\n\n" + "Your PhoneNo :" + exam.getPhone());


        javaMailSender.send(message);



        System.out.println("Message sent successfully");

    }
}
