package finki.emt.BookRent.service;

import finki.emt.BookRent.model.Book;
import finki.emt.BookRent.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    List<Book> findAllByCategory(String categoryName);

    Optional<Book> findById(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> markAsTaken(Long id);
}
