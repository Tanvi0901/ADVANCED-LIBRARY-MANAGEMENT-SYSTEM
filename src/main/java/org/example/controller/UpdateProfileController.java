package org.example.controller;
import io.swagger.annotations.ApiOperation;
import org.example.payload.UpdateProfileResponse;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UpdateProfileController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @ApiOperation("this api is used for update profile ")
    @RequestMapping(value={"/update-profile"},method = RequestMethod.POST)
    public ResponseEntity<UpdateProfileResponse> updateProfile(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("profilePic") MultipartFile profilePicFile
    ) throws Exception {
        try {
            UpdateProfileResponse updateProfileResponse=userService.updateProfilePic(name,email,mobile,profilePicFile);
            return ResponseEntity.ok(updateProfileResponse);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}



