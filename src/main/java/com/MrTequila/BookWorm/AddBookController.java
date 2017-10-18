package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddBookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/addBook")
    public String addBook(){
        return "addBook";
    }
}
