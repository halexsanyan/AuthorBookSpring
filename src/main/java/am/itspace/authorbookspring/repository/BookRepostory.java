package am.itspace.authorbookspring.repository;

import am.itspace.authorbookspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepostory extends JpaRepository<Book, Integer> {
}
