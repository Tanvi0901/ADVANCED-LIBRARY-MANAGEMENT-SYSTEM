package org.example.service;
import org.example.Utility.AppUtils;
import org.example.model.User;
import org.example.payload.*;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.lang.Exception;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    AppUtils appUtils;
    @Override
    public User registerUser(UserRequest userRequest) throws Exception {
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new Exception("Email already registered.");
        }
        if (userRequest.getName() == null || userRequest.getName().trim().isEmpty() ||
                userRequest.getEmail() == null || userRequest.getEmail().trim().isEmpty() ||
                userRequest.getPassword() == null || userRequest.getPassword().trim().isEmpty()) {
            throw new Exception("Empty user information");
        }
        User user=new User();
        if(null!=userRequest.getName()){
            user.setName(userRequest.getName());
        }
        if(null!=userRequest.getEmail()){
            user.setEmail(userRequest.getEmail());
        }
        if(null!=userRequest.getPassword()){
            user.setPassword(AppUtils.encodeBase64(userRequest.getPassword()));
        }
        User savedUser=userRepository.save(user);
        return savedUser;
    }
    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        LoginResponse loginResponse=new LoginResponse();
        String inputEmail=loginRequest.getEmail();
        String password1=loginRequest.setPassword(AppUtils.encodeBase64(loginRequest.getPassword()));
        String inputPassword = loginRequest.getPassword();
        User user=userRepository.getByEmail(inputEmail);
        if(user!=null) {
            if(user.getPassword().equals(inputPassword)){
                loginResponse.setId(user.getId());
                loginResponse.setName(user.getName());
                loginResponse.setEmail(user.getEmail());
                loginResponse.setPassword(AppUtils.decodeBase64(user.getPassword()));
            }
        }
        return loginResponse;
    }

    @Override
    public UpdatePassResponse updatePassword(UpdatePassRequest updatePassRequest) throws Exception {
        UpdatePassResponse updatePassResponse=new UpdatePassResponse();
        String inputEmail=updatePassRequest.getEmail();
        String oldPassword = AppUtils.encodeBase64(updatePassRequest.getPassword());
        String newPassword=updatePassRequest.setNewPassword(AppUtils.encodeBase64(updatePassRequest.getNewPassword()));
        String newPassword1=updatePassRequest.getNewPassword();
        User user=userRepository.getByEmail(inputEmail);
        if (user != null) {
            if (user.getPassword().equals((oldPassword))) {
                user.setPassword(newPassword);
                updatePassResponse.setEmail(user.getEmail());
                updatePassResponse.setNewPassword(AppUtils.decodeBase64(newPassword1));
                updatePassResponse.setPassword((AppUtils.decodeBase64(oldPassword)));
            }
            else{
                throw new Exception("Old Password not match");
            }
        }
        else{
            throw new Exception("Wrong info");
        }
        userRepository.save(user);
        return updatePassResponse;
    }
    private final String profilePicDirectory = "C:\\Users\\Shubham\\OneDrive\\Desktop\\Library-management\\library-management-system\\src\\main\\resources\\ProfilePic\\";
    @Override
    public UpdateProfileResponse updateProfilePic(String name, String email, String mobile, MultipartFile profilePicFile) throws Exception {
        String fileName = profilePicFile.getOriginalFilename();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date=currentDate.format(formatter);
        String url=profilePicDirectory+date+fileName;
        File destFile = new File(profilePicDirectory + fileName);
        profilePicFile.transferTo(destFile);
        UpdateProfileResponse updateProfileResponse=new UpdateProfileResponse();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        user.setName(name);
        user.setMobile(mobile);
        user.setProfilePic(url);
        updateProfileResponse.setId(user.getId());
        updateProfileResponse.setName(user.getName());
        updateProfileResponse.setEmail(user.getEmail());
        updateProfileResponse.setMobile(user.getMobile());
        updateProfileResponse.setProfilePic(user.getProfilePic());
        userRepository.save(user);
        return updateProfileResponse;
    }

}
