package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.model.User;
import pd.example.demo.major.project.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;



    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /**
     * It will fetch the user from database.
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> optionalUser =  userRepository.findByUid(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return optionalUser.get(0);
    }
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Assuming you have a field named "roles" in your User entity which contains the user's roles as strings
        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    /**
     * it will save the user details.
     * @param user
     */
    public void register(User user) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    /**
     * It will send the otp for two step verification into registered mail.
     * @param user
     */
    public void sendOtpEmail(User user) {
        int otp =  (int) (Math.random() * 9000) + 1000;
        user.setOtp(otp);
        userRepository.save(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("agarwalvikash768@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Your One-Time Password (OTP) for Online Quiz Platform");
        message.setText("Dear "+user.getUsername()+",\n\n"+
                "Thank you for logging in to Online Quiz Platform. To ensure the security of your account, we have generated a one-time password (OTP) for you.\n\n"+
                "OTP : "+user.getOtp()+"\n\nPlease use this OTP to complete your login process. It is valid for a limited time and for a single use only.\n\n"+
                "If you did not attempt to log in or receive this email, please ignore it. However, if you suspect any unauthorized access to your account, please contact our support team immediately.\n\n" +
                "Best Regards, \n\nOnline Quiz Platform Team");
        javaMailSender.send(message);
    }


}
