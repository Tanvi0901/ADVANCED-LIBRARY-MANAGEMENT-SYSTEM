package org.example.repository;
import org.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "SELECT * FROM Book b WHERE b.title LIKE %:searchText% OR b.author LIKE %:searchText% OR b.genre LIKE %:searchText%",nativeQuery = true)
    List<Book> findByTitleContainingOrAuthorContainingOrGenreContaining(String searchText);
}
