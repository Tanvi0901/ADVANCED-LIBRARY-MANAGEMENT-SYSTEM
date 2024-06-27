package org.example.payload;

public class UpdatePassRequest {
    private String email;
    private String newPassword;
    private String password;


    public String getNewPassword() {
        return newPassword;
    }

    public String setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return newPassword;
    }

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

    public boolean findByEmail(String email) {
        return false;
    }
}
