package org.example.service;
import org.example.model.Book;
import org.example.payload.BookRequest;
import org.example.payload.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookResponse bookDetail(BookRequest bookRequest);
    List<Book> searchBooks(String searchText) throws Exception;

    String deleteBookbyId(Long bookId) throws Exception;


}
