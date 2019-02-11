package afomic.com.pgpayment.helper;

import java.util.Random;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return string == null || string.equals("");
    }
    public static boolean isNotEmpty(String string) {
        return string != null && !string.equals("");
    }

    public static boolean isValidMatricNumber(String matricNumber) {
        return matricNumber.length() == 12;
    }

    public static boolean isValidPassword(String password) {
        return password.length() > 6;
    }
    public static String getRandomString(int length) {
        String SALTCHARS = "abcdefghiyrhha89awnv56zpi89yytwqw2123uru12ifjs399fjdksldfmnkqwmzbcb1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }

}
