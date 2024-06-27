package org.example.repository;
import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BorrowerBookRepository extends JpaRepository<BorrowedBook,Long> {
    @Query(value = "SELECT * FROM borrowed_book b WHERE b.book_id =:bookId",nativeQuery = true)
    BorrowedBook findByBookId(Long bookId);
    @Query(value = "SELECT book_id FROM borrowed_book b WHERE b.borrowerid =:borrowerId",nativeQuery = true)
    List<Long> findByBorrowerID(Long borrowerId);
    @Query(value = "SELECT id FROM borrowed_book b WHERE b.book_id =:bookId",nativeQuery = true)
    Long findByBookIds(Long bookId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM borrowed_book b WHERE b.id = :ids",nativeQuery = true)
    void deleteByBookId(Long ids);
}
