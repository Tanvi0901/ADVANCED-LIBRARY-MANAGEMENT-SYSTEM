package org.example.payload;
public class LoginRequest {
    private String email;
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}
