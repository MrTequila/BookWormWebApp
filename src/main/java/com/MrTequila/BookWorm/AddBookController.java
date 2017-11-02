package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AddBookController {

    private final StorageService storageService;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    public AddBookController(StorageService storageService) {
        this.storageService = storageService;
    }


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
    public String bookSubmit(@RequestParam("file") MultipartFile file,
                             @ModelAttribute Book book){

        storageService.store(file);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest servletRequest = attributes.getRequest();
        String username = servletRequest.getRemoteUser();
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(username)) {
                book.setUser(user);
            }
        }
        bookRepository.save(book);
        return "redirect:/";
    }

}
