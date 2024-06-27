package org.example.controller;

import io.swagger.annotations.ApiOperation;
import org.example.model.Borrower;
import org.example.payload.BorrowerRequest;
import org.example.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BorrowerController {
    @Autowired
    BorrowerService borrowerService;
    @ApiOperation("this api is used to add borrower")
    @RequestMapping(value = {"/borrower"}, method = RequestMethod.POST)
    public ResponseEntity<Borrower> createBorrower(@RequestBody BorrowerRequest borrowerRequest) throws Exception {
        try {
            if (borrowerRequest.getName() != null || borrowerRequest.getEmail() != null) {
                Borrower borrowerResponse = borrowerService.createBorrower(borrowerRequest);
                return ResponseEntity.ok(borrowerResponse);
            }
            return null;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }


    }
}
