package org.example.service;
import org.example.model.Book;
import org.example.payload.BookRequest;
import org.example.payload.BookResponse;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Override
    public BookResponse bookDetail(BookRequest bookRequest) {
        Book book=new Book();
        if(bookRequest.getTitle()!=null){
            book.setTitle(bookRequest.getTitle());
        }
        if(bookRequest.getAuthor()!=null){
            book.setAuthor(bookRequest.getAuthor());
        }
        if(bookRequest.getGenre()!=null){
            book.setGenre(bookRequest.getGenre());
        }
        if(bookRequest.getAvailabilityStatus()!=null){
            book.setAvailabilityStatus(bookRequest.getAvailabilityStatus());
        }
        bookRepository.save(book);
        BookResponse bookResponse=new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setAvailabilityStatus(book.getAvailabilityStatus());
        return bookResponse;
    }

    @Override
    public List<Book> searchBooks(String searchText) throws Exception {
        List<Book> books=  bookRepository.findByTitleContainingOrAuthorContainingOrGenreContaining(searchText);
        if(books.size()==0){
            throw new Exception("Book not found");
        }
        else {
            return books;
        }
    }
    @Override
    public String deleteBookbyId(Long bookId) throws Exception {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return "Book with ID " + bookId + " has been deleted.";
        } else {
            throw new Exception("Book with ID "+bookId+" not found");
        }
    }

}
