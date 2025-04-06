package Database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD2Witch implements PasswordWitch{
    @Override
    public String curseThePassword(String password) throws NoSuchAlgorithmException{
        String cursed;
        MessageDigest md = MessageDigest.getInstance("MD2");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger ouch = new BigInteger(1,messageDigest);
        cursed = ouch.toString(16);
        while (cursed.length() < 32){
            cursed = "0" + cursed;
        }
        return cursed;
    }

}
