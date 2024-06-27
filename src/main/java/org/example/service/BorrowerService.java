package org.example.service;

import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.example.model.Borrower;
import org.example.payload.BorrowedBookRequest;
import org.example.payload.BorrowerRequest;
import org.example.payload.UpdateStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BorrowerService {
    Borrower createBorrower(BorrowerRequest borrowerRequest);

    BorrowedBook issueBook(BorrowedBookRequest borrowedBookRequest);

    UpdateStatusResponse updateReturnStatus(Long bookId) throws Exception;


    List<Book> getBorrowedBooksByBorrower(Long borrowerId) throws Exception;

    String deleteBookFromBorrowedBook(Long bookId) throws Exception;
}
