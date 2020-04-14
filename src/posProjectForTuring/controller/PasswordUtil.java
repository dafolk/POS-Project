package posProjectForTuring.controller;

public class PasswordUtil {
    private static Pbkdf2 hasher = new Pbkdf2();
    
    public static String hashPassword(String password){
        return hasher.hash(password);
    }
    
    public static boolean isPasswordTrue(String password, String storedPassword){
        return hasher.checkPassword(password, storedPassword);
    }
}
