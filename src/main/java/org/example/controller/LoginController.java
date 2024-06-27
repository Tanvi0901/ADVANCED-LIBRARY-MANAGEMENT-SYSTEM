package org.example.controller;

import io.swagger.annotations.ApiOperation;
import org.example.Utility.AppUtils;
import org.example.payload.LoginRequest;
import org.example.payload.LoginResponse;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    AppUtils appUtils;
    @ApiOperation("this api is used for user login ")
    @RequestMapping(value={"/login"},method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            LoginResponse loginResponse=userService.authenticateUser(loginRequest);
            if (loginResponse!=null) {
                return ResponseEntity.ok(loginResponse);
            }
            return null;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
