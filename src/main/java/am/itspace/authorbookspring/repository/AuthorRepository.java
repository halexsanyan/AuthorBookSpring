package am.itspace.authorbookspring.repository;

import am.itspace.authorbookspring.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
