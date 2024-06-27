package org.example.repository;

import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.example.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {

}
