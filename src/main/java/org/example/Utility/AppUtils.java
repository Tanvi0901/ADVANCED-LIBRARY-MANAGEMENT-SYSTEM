package org.example.Utility;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Base64;


@Service
public class AppUtils {
    public static String encodeBase64(String password){
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        return encodedPassword;
    }
    public static String decodeBase64(String password){
        byte[] decodedPassword = Base64.getDecoder().decode(password);
        String decodedPasswordString = new String(decodedPassword);
        return decodedPasswordString;
    }
    public static LocalDate getDateWithoutTime(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }
}
