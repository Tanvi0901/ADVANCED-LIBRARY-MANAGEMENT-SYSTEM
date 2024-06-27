package org.example.controller;
import io.swagger.annotations.ApiOperation;
import org.example.payload.UpdatePassRequest;
import org.example.payload.UpdatePassResponse;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UpdatePassController {
    @Autowired
    UserService userService;
    @ApiOperation("this api is used for update password test ")
    @RequestMapping(value={"/update-password"},method = RequestMethod.POST)
    public ResponseEntity<UpdatePassResponse> updatePassword(@RequestBody UpdatePassRequest updatePassRequest) throws Exception {
        try {
            UpdatePassResponse updatePassResponse = userService.updatePassword(updatePassRequest);
            return ResponseEntity.ok(updatePassResponse);
        }catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
