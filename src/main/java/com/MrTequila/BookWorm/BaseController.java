package com.MrTequila.BookWorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest servletRequest = attributes.getRequest();
        String username = servletRequest.getRemoteUser();
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepository.findAll()){
            if(book.getUser().getUsername().equals(username)) {
                books.add(book);
            }
        }
        model.addAttribute("books", books);


        return "base";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
