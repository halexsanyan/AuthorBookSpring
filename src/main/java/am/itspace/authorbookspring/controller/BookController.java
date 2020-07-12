package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.model.Author;
import am.itspace.authorbookspring.model.Book;
import am.itspace.authorbookspring.repository.AuthorRepository;
import am.itspace.authorbookspring.repository.BookRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepostory bookRepostory;
    private final AuthorRepository authorRepository;

    @PostMapping("/saveBook")
    public String add(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
        String ctrlName = book.getId() > 0 ? "/book" : "/";
        bookRepostory.save(book);
        return "redirect:" + ctrlName + "?msg=" + msg;
    }

    @GetMapping("/editBook")
    public String edit(@RequestParam("id") int id, Model model) {
        Book book = bookRepostory.getOne(id);
        List<Author> users = authorRepository.findAll();
        model.addAttribute("authors", users);
        model.addAttribute("book", book);
        return "editBook";
    }

    @GetMapping("/book")
    public String bookPage(ModelMap modelMap) {
        List<Book> allBooks = bookRepostory.findAll();
        List<Author> allAuthors = authorRepository.findAll();
        modelMap.addAttribute("authors", allAuthors);
        modelMap.addAttribute("books", allBooks);
        return "book";
    }


    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepostory.deleteById(id);
        return "redirect:/book";
    }


}
