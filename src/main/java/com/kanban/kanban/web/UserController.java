package com.kanban.kanban.web;

import com.kanban.kanban.UserService.UserService;
import com.kanban.kanban.dto.UserDto;
import com.kanban.kanban.model.ContactFormDto;
import com.kanban.kanban.model.ToDoEntity;
import com.kanban.kanban.model.UserEntity;
import com.kanban.kanban.services.ContactFormServices.ContactFormSerivce;
import com.kanban.kanban.services.mailservices.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private KanBanController kanBanController;
    @Autowired
    private ContactFormSerivce contactFormSerivce;


    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("userlogs/registration")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "userlogs/registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") UserDto userdto, Model model) {
        System.out.print("REGISTRATION");

        userdto.setRole("USER");
        UserEntity user = userService.saveUser(userdto);
        if (user == null) {
            return "redirect:/userlogs/registration?error";
        } else {
            mailService.sendMail(user.getEmail(), "Welcome", "hello my Friend");
            String message = "Successfully registered." + " " + "Thank you " + userdto.getFullName(userdto.getFirstname(), userdto.getLastname());
            model.addAttribute("message", message);
            return "userlogs/registration";
        }

    }

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        if (principal == null || authentication instanceof AnonymousAuthenticationToken) {

            return "userlogs/login";
        } else {
            UserEntity userEntity = userService.findUserByEmail(principal.getName());
            model.addAttribute("user", userEntity);
            if (userEntity.getRole().equals("ADMIN")) {
                return "userlogs/admin";

            } else {
                return "userlogs/user";

            }
        }


    }

    /* @GetMapping("user-page")
     public String userPage(Model model, Principal principal) {
         UserEntity user = userService.findUserByEmail(principal.getName());
         model.addAttribute("user", user);
         return "userlogs/user";
     }

     @GetMapping("admin-page")
     public String adminPage(Model model, Principal principal) {
         UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
         UserEntity user = userService.findUserByEmail(principal.getName());
         model.addAttribute("user", user);
         return "userlogs/admin";
     }
 */
    @GetMapping("myprofile")
    public String profile(Model model, Principal principal) {
        UserEntity user = userService.findUserByEmail(principal.getName());
        List<ToDoEntity> todos = kanBanController.getTodos(principal);
        model.addAttribute("todos", todos);
        model.addAttribute("user", user);
        UserEntity userEntity = userService.findUserByEmail(principal.getName());
        if (userEntity.getRole().equals("ADMIN")) {
            return "userlogs/admin";

        } else {
            return "userlogs/user";

        }
    }

    @GetMapping("aboutus")
    public String aboutus() {

        return "nav/aboutus";
    }

    @GetMapping("impressum")
    public String impressum() {

        return "nav/impressum";
    }

    @GetMapping("contact")
    public String contact() {

        return "nav/contactus";
    }

    @GetMapping("projects/kanban")
    public String kanban() {

        return "nav/projects/kanban";
    }

    @GetMapping("projects/typepro")
    public String typepro() {
        return "nav/projects/typepro";
    }

    @GetMapping("projects/pingpong")
    public String pingpong() {

        return "nav/projects/pingpong";
    }


    @GetMapping("showcontactform")
    public String showContactForm(Model model) {
        ContactFormDto contactFormDto = new ContactFormDto();
        model.addAttribute("contactformdto", contactFormDto);
        return "nav/contactform";
    }

    @PostMapping("sendcontactform")
    public String sendContactForm(@ModelAttribute("contactformdto") ContactFormDto contactFormDto, Model model) {
        contactFormSerivce.saveContactFormEntity(contactFormDto);
        System.out.print("Messege sent");
        return "redirect:showcontactform?sent";

    }
}
