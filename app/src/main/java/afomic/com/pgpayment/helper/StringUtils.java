package afomic.com.pgpayment.helper;

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

}
