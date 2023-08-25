package pd.example.demo.major.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pd.example.demo.major.project.model.User;
import pd.example.demo.major.project.repository.UserRepository;
import pd.example.demo.major.project.service.UserDetailsServiceImpl;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private  UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Get the index page
     * @return
     */
    @GetMapping("/index")
    public String showHomePage () {
        return "index";
    }
    /**
     * Get the dashboard page
     * @return
     */
    @GetMapping("/")
    public String showDashboardPage () {
        return "dashboard";
    }
    /**
     * Get the contact page
     * @return
     */
    @GetMapping("/contact")
    public String showContactPage () {
        return "contact";
    }
    /**
     * Get the navigation admin page
     * @return
     */
    @GetMapping("/navigation-admin")
    public String showAdminNavigation(){return "navigation-admin";}
    /**
     * Get the navigation page
     * @return
     */
    @GetMapping("/navigation")
    public String showNavigationPage () {
        return "navigation";
    }
    /**
     * Get the about page
     * @return
     */
    @GetMapping("/about")
    public String showAboutPage(){return "about";}
    /**
     * Get the login page
     * @return
     */
    @GetMapping("/login")
    public String showLoginPage () {
        return "login";
    }
    /**
     * Get the otp verification page
     * @return
     */

    @GetMapping("/otp-verification")
    public String otpSent() {
        return "otp-verification";

    }

    /**
     * After getting otp it will save in database and comparing role wise dashboard
     * @param userDetail
     * @return
     */
    @PostMapping("/otp-verification")
    public String otpVerification(@ModelAttribute("otpValue") User userDetail) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();


        List<User> users = userRepository.findByUid(user.getUsername());
        if (users.get(0).getOtp() == userDetail.getOtp()) {
            boolean isAdmin = user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
            boolean isUser = user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));

            if (isAdmin) {

                return "redirect:/navigation-admin";
            } else if (isUser) {

                return "redirect:/navigation";
            }
//            return "redirect:/navigation-admin";
//        }
            else
                return "redirect:/otp-verification?error";



    }
        return "redirect:/otp-verification?error";
    }}
