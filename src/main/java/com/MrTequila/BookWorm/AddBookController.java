package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddBookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/addBook")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String bookSubmit(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/";
    }

}
