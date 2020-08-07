package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.model.Book;
import am.itspace.authorbookspring.service.BookService;
import am.itspace.authorbookspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;
    private final UserService userService;

    @PostMapping("/book/save")
    public String add(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
        String ctrlName = book.getId() > 0 ? "/book" : "/";
        bookService.save(book);
        return "redirect:" + ctrlName + "?msg=" + msg;
    }

    @GetMapping("/book/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Optional<Book> one = bookService.findeById(id);
        if (!one.isPresent()) {
            return "redirect:/";
        }
        model.addAttribute("authors", userService.findAll());
        model.addAttribute("book", one.get());
        return "editBook";
    }

    @GetMapping("/book")
    public String bookPage(ModelMap modelMap,
                           @RequestParam(name = "msg", required = false) String msg,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "10") int size,
                           @RequestParam(value = "orderBy",defaultValue = "title") String orderBy,
                           @RequestParam(value = "order",defaultValue = "ASC") String order

    ) {
        Sort sort = order.equals("ASC") ? Sort.by(Sort.Order.asc(orderBy)) : Sort.by(Sort.Order.desc(orderBy));
        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<Book> books = bookService.findAll(pageRequest);
        int totalPages = books.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers",pageNumbers);
        }
        //  List<User> allAuthors = userService.findAll();
        // modelMap.addAttribute("authors", allAuthors);
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("msg", msg);
        return "book";
    }


    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }


}
