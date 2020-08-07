package am.itspace.authorbookspring.service;

import am.itspace.authorbookspring.model.Book;
import am.itspace.authorbookspring.repository.BookRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepostory bookRepostory;

    public void save(Book book){
        bookRepostory.save(book);
    }

    public Optional<Book> findeById(int id){
        return bookRepostory.findById(id);
    }

    public Page<Book> findAll(PageRequest pageRequest){
        return bookRepostory.findAll(pageRequest);
    }

    public void deleteBook(int id){
        bookRepostory.deleteById(id);
    }
}
