package org.example.payload;

import lombok.Data;

@Data
public class BorrowedBookRequest {
    private Long id;
    private Long borrowerID;
    private Long bookId;
    private int year;
    private int month;
    private int day;
    private String returnStatus;


}
