package org.example.controller;
import io.swagger.annotations.ApiOperation;
import org.example.model.Book;
import org.example.model.BorrowedBook;
import org.example.payload.BorrowedBookRequest;
import org.example.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BorrowedBookController {
    static Logger logger = Logger.getLogger(String.valueOf(BorrowedBookController.class));
    @Autowired
    BorrowerService borrowerService;
    @RequestMapping(value = {"/borrowed-book"}, method = RequestMethod.POST)
    public ResponseEntity<BorrowedBook> issueBook(@RequestBody BorrowedBookRequest borrowedBookRequest)throws Exception{
        try{
            BorrowedBook borrowedBook = borrowerService.issueBook(borrowedBookRequest);
            if(borrowedBook!=null){
                return ResponseEntity.ok(borrowedBook);
            }
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
    @ApiOperation("Retrieve a list of borrowed books for a specific borrower")
    @RequestMapping(value = "/borrowed-books", method = RequestMethod.GET)
    public List<Book> getBorrowedBooksByBorrower(@RequestParam Long borrowerId) throws Exception {
        try {
            List<Book> borrowedBooks = borrowerService.getBorrowedBooksByBorrower(borrowerId);

            if(borrowedBooks == null){
                throw new Exception("User not found");
            }
            return  borrowedBooks;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @ApiOperation("delete borrowed book ")
    @RequestMapping(value = "/delete-borrowed-book",method = RequestMethod.DELETE)
    public String deleteFromBorrowedBook(@RequestParam(value = "BookId")Long BookId) throws Exception {
        try{
            String dltBook=borrowerService.deleteBookFromBorrowedBook(BookId);
            return dltBook;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
