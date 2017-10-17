package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path = "/add")
    public @ResponseBody String addNewBook (@RequestParam String author,
                                            @RequestParam String title,
                                            @RequestParam String startDate,
                                            @RequestParam String finishDate,
                                            @RequestParam int pageNumber) {
        Book newBook = new Book(author, title, startDate, finishDate, pageNumber);
        bookRepository.save(newBook);

        return "Saved";
    }
    @RequestMapping("/")
    public String base(Model model) {
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepository.findAll()){
            books.add(book);
            System.out.println(book.toString());
        }
        model.addAttribute("books", books);

        return "base";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
