package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.model.Author;
import am.itspace.authorbookspring.model.Book;
import am.itspace.authorbookspring.repository.AuthorRepository;
import am.itspace.authorbookspring.repository.BookRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepostory bookRepostory;


    @GetMapping("/")
    public String homePage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "home";
    }


    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }


    @GetMapping("/author")
    public String authorPage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "author";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/author";
    }

    @PostMapping("/updateAuthor")
    public String updateAuthor(Author author, @RequestParam("id") int id) {
        if (author.getId() == id) {
            authorRepository.save(author);
        }
        return "redirect:/author";
    }

    @GetMapping("/updateAuthor")
    private String goUpdateAuthor(ModelMap modelMap, @RequestParam("id") int id) {
        Author one = authorRepository.getOne(id);
        modelMap.addAttribute("author", one);
        return "updateAuthor";
    }

    @GetMapping("/book")
    public String bookPage(ModelMap modelMap) {
        List<Book> allBooks = bookRepostory.findAll();
        List<Author> allAuthors = authorRepository.findAll();
        modelMap.addAttribute("authors", allAuthors);
        modelMap.addAttribute("books", allBooks);
        return "book";
    }

    @PostMapping("/addBook")
    public String addAuthor(@ModelAttribute Book book) {
        bookRepostory.save(book);
        return "redirect:/";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepostory.deleteById(id);
        return "redirect:/book";
    }


    @PostMapping("/updateBook")
    public String updatebook(Book book, @RequestParam("id") int id) {
        if (book.getId() == id) {
            bookRepostory.save(book);
        }
        return "redirect:/book";
    }


    @GetMapping("/updateBook")
    private String goUpdateBook(ModelMap modelMap, @RequestParam("id") int id) {
        Book one = bookRepostory.getOne(id);
        modelMap.addAttribute("book", one);
        List<Author> allAuthors = authorRepository.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "updateBook";
    }

}
