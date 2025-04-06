package Database;

import java.security.NoSuchAlgorithmException;

public interface PasswordWitch {
    abstract String curseThePassword(String password) throws NoSuchAlgorithmException;
}
