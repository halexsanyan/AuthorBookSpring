package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.model.Author;
import am.itspace.authorbookspring.model.Book;
import am.itspace.authorbookspring.repository.BookRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BookController {

    @Autowired
    private BookRepostory bookRepostory;




}
