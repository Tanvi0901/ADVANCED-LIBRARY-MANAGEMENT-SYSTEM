package org.example.controller;
import io.swagger.annotations.ApiOperation;
import org.example.model.User;
import org.example.payload.UserRequest;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @ApiOperation(value="this api is used to register users detail")
    @RequestMapping(value={"/register"},method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) throws Exception {
        try {
            User userResponse = userService.registerUser(userRequest);
            return ResponseEntity.ok(userResponse);
            //return ResponseEntity.status(400).body(new MessageResponse(true, "Your old password incorrectly"));
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

}
