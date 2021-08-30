package by.epam.tc.shop.model.service.impl.validator;

public class UserValidator {
    private static final String ID_REGEX = "^[1-9]{0,9}$";
    private static final String LOGIN_REGEX = "^(?=.*[A-Za-z0-9]$)[A-Za-z][\\w.-]{0,19}$";
    private static final String EMAIL_REGEX = "^[\\w\\.]{3,13}@\\w{3,10}\\.\\w{2,5}$";
    private static final String PASSWORD_REGEX = "^[\\w]{3,20}$";

    public static boolean isIdCorrect(String id) {
        return isStringCorrect(id, ID_REGEX);
    }
    public static boolean isLoginCorrect(String login) {
        return isStringCorrect(login, LOGIN_REGEX);
    }
    public static boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }
    public static boolean isPasswordCorrect(String password) {
        return isStringCorrect(password, PASSWORD_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        if (line != null)
            return line.matches(regex);
        return false;
    }
}
