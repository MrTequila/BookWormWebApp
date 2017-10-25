package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class AddBookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/addBook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        Set<User> users = new HashSet<>();
        for (User user : userRepository.findAll()){
            users.add(user);
        }
        model.addAttribute("users", users);
        return "addBook";
    }

    @PostMapping("/addBook")
    public String bookSubmit(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/";
    }

}
