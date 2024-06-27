package org.example.model;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name="BorrowedBook")
public class BorrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long borrowerID;
    private Long bookId;
    private LocalDate dueDate;
    private String returnStatus;


}
