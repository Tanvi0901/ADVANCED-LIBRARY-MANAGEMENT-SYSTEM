package org.example.service;
import org.example.model.User;
import org.example.payload.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
    User registerUser(UserRequest userRequest) throws Exception;
    LoginResponse authenticateUser(LoginRequest loginRequest);
    UpdatePassResponse updatePassword(UpdatePassRequest updatePassRequest) throws Exception;
    UpdateProfileResponse updateProfilePic(String name, String email, String mobile, MultipartFile profilePicFile) throws Exception;
}
