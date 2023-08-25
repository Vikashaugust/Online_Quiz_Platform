package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.model.Proleap;
import pd.example.demo.major.project.repository.ProleapRepository;

import java.util.List;

@Service
public class ProleapServiceImpl implements ProleapService {
    @Autowired
    private ProleapRepository proleapRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * All the data of proleap registration will store in repository(database).
     * @param proleap
     */
    @Override
    public void save(Proleap proleap) {
        proleapRepository.save(proleap);
    }
    /**
     * It will find the uid and password and make some validation if the validation will true then will get.
     * @param uid
     * @param password
     * @return
     */
    @Override
    public Proleap findByUidAndPassword(String uid, String password) {
        List<Proleap> proleaps = proleapRepository.findByUidAndPassword(uid, password);
        return proleaps.isEmpty() ? null : proleaps.get(0);
    }


    /**
     * Java mail sender will send a mail for credentials to the registered gmail id for proleap registration
     * @param proleap
     */
    public void sendEmail (Proleap proleap) {

        proleapRepository.save(proleap);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("agarwalvikash768@gmail.com");
        message.setTo(proleap.getEmail());
        message.setSubject(proleap.getName());
        message.setText("YOUR UID IS :"+ proleap.getUid() + "\n\n" + "YOUR PASSWORD IS :"
                + proleap.getPassword());


        javaMailSender.send(message);



        System.out.println("Message sent successfully");
    }
}
