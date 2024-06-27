package org.example.controller;
import io.swagger.annotations.ApiOperation;
import org.example.model.Book;
import org.example.payload.BookRequest;
import org.example.payload.BookResponse;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    BookService bookService;
    @ApiOperation("this is used for borrowed book detail")
    @RequestMapping(value = {"/book"}, method = RequestMethod.POST)
    public ResponseEntity<?> BookDetail(@RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.bookDetail(bookRequest);
        return ResponseEntity.ok(bookResponse);
    }
    @ApiOperation("search book on basis of title, author and genre")
    @RequestMapping(value = "/book/search", method = RequestMethod.GET)
    public List<Book> searchBooks(@RequestParam(value = "SearchText",required = false) String SearchText
    ) throws Exception {
        try {
            List<Book> books = bookService.searchBooks(SearchText);
            if(books.size()==0){
                throw new Exception("Not found");
            }
            return books;
        }catch(Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @ApiOperation("delete book by bookId")
    @RequestMapping(value = "/delete-book",method = RequestMethod.DELETE)
    public String deleteBook(@RequestParam(value = "BookId",required = true) Long BookId) throws Exception {
        try{
            String dltBook=bookService.deleteBookbyId(BookId);
            return dltBook;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
