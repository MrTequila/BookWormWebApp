package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

   UserService userService;
   @Autowired
   UserRepository userRepository;

    @GetMapping("/login")
    public String loginUser(Model model){
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String userLoggedIn(@ModelAttribute Login login){
        return "redirect:/";
    }

}
