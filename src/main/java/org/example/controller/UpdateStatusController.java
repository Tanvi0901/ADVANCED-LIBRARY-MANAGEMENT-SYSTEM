package org.example.controller;
import io.swagger.annotations.ApiOperation;
import org.example.payload.UpdateStatusResponse;
import org.example.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class UpdateStatusController {
    @Autowired
    BorrowerService borrowerService;
    @ApiOperation("this is used for update status")
    @RequestMapping(value = {"/borrowed-books/{BookId}/return"}, method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@RequestParam(value = "BookId",required = true) Long BookId) throws Exception {
        try {
            UpdateStatusResponse updateStatusResponse = borrowerService.updateReturnStatus(BookId);
            return ResponseEntity.ok(updateStatusResponse);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
