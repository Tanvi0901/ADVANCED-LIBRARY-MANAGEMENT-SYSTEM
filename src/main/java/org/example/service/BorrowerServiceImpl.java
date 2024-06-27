package org.example.service;
import org.example.Utility.AppUtils;
import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.example.model.Borrower;
import org.example.payload.*;
import org.example.repository.BookRepository;
import org.example.repository.BorrowerBookRepository;
import org.example.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowerServiceImpl implements BorrowerService {
    @Autowired
    BorrowerRepository borrowerRepository;
    @Autowired
    BorrowerBookRepository borrowerBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AppUtils appUtils;

    @Override
    public Borrower createBorrower(BorrowerRequest borrowerRequest) {
        Borrower borrower = new Borrower();
        borrower.setName(borrowerRequest.getName());
        borrower.setEmail(borrowerRequest.getEmail());
        BorrowerResponse borrowerResponse = new BorrowerResponse();
        borrowerResponse.setId(borrower.getId());
        borrowerResponse.setName(borrower.getName());
        borrowerResponse.setEmail(borrower.getEmail());
        Borrower borrower1 = borrowerRepository.save(borrower);
        return borrower1;
    }

    @Override
    public BorrowedBook issueBook(BorrowedBookRequest borrowedBookRequest) {
        BorrowedBook borrowedBook = new BorrowedBook();
        Integer year = borrowedBookRequest.getYear();
        Integer month = borrowedBookRequest.getMonth();
        Integer day = borrowedBookRequest.getDay();
        LocalDate dateWithoutTime = AppUtils.getDateWithoutTime(year, month, day);
        BorrowedBookResponse borrowedBookResponse = new BorrowedBookResponse();
        if (borrowedBookRequest.getBorrowerID() != null) {
            borrowedBook.setBorrowerID(borrowedBookRequest.getBorrowerID());
        }
        if (borrowedBookRequest.getBookId() != null) {
            borrowedBook.setBookId(borrowedBookRequest.getBookId());
        }
        if (year != null || month != null || day != null) {
            borrowedBook.setDueDate(dateWithoutTime);
        }
        if (borrowedBookRequest.getReturnStatus() != null) {
            borrowedBook.setReturnStatus(borrowedBookRequest.getReturnStatus());
        }
        borrowedBookResponse.setBookId(borrowedBook.getBookId());
        borrowedBookResponse.setBorrowerID(borrowedBook.getBorrowerID());
        borrowedBookResponse.setDueDate(borrowedBook.getDueDate());
        borrowedBookResponse.setReturnStatus(borrowedBook.getReturnStatus());
        BorrowedBook borrowedBook1 = borrowerBookRepository.save(borrowedBook);
        return borrowedBook1;
    }

    @Override
    public UpdateStatusResponse updateReturnStatus(Long bookId) throws Exception {
        BorrowedBook borrowedBook = new BorrowedBook();
        UpdateStatusResponse updateStatusResponse = new UpdateStatusResponse();
        BorrowedBook borrowedBook1 = borrowerBookRepository.findByBookId(bookId);
        if (borrowedBook1 == null) {
            throw new Exception("Book not available");
        }
        borrowedBook1.setReturnStatus("Returned");
        borrowerBookRepository.save(borrowedBook1);
        updateStatusResponse.setBorrowerId(borrowedBook1.getBorrowerID());
        updateStatusResponse.setReturnStatus(borrowedBook1.getReturnStatus());
        return updateStatusResponse;
    }

    @Override
    public List<Book> getBorrowedBooksByBorrower(@RequestParam(value = "borrowerId", required = true) Long borrowerId) throws Exception {
        List<Book> books = new ArrayList<>();
        List<Long> borrowedBookIds = borrowerBookRepository.findByBorrowerID(borrowerId);
        if (borrowedBookIds.size() == 0) {
            throw new Exception("Borrower is not found");
        }

        for (Long bookId : borrowedBookIds) {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                books.add(book);
            } else {
                throw new Exception("Book not found");
            }
        }
        return books;
    }
    @Override
    public String deleteBookFromBorrowedBook(Long bookId) throws Exception {
        Long ids = borrowerBookRepository.findByBookIds(bookId);

        if (ids!= null) {
            borrowerBookRepository.deleteByBookId(ids);
            return "Borrower with ID " + bookId + " has been deleted.";
        } else {
            throw new Exception("Borrower with ID " + bookId + " not found");
        }
    }
}