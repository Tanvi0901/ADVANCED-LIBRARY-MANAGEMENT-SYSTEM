package org.example.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowedBookResponse {
    private Long id;
    private Long borrowerID;
    private Long bookId;
    private LocalDate dueDate;
    private String returnStatus;


}
