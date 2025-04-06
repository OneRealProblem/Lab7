package Database;

import Commands.Command;
import Commands.Request;
import Stuff.Mail;
import Stuff.SerDeser;

import java.io.IOException;
import java.net.SocketException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ROL {

    // for registration or log in
    public static String regOrLog() throws IOException {
        try {
            byte[] commandBytes = Mail.readObject(Mail.getIs());
            Request request = (Request) SerDeser.deserialize(commandBytes);
            Command command1 = request.getCommand();

            if (command1.getClass().toString().equals("class Commands.Exit")) {
                Mail.sendMessage("ex");
                return ("exit");
            }

            PreparedStatement psRL = DBManager.getConnection().prepareStatement(command1.getDbAnalog().getDbrequest());
            psRL.setString(1, request.getUsername());
            psRL.setString(2, request.getPassword());

            if (command1.getClass().toString().equals("class Commands.Register")) {
                if (DBManager.checkIfUsernameExists(request.getUsername())) {
                    Mail.sendMessage("uae"); //Username already exists.
                    return ("no");
                } else {
                    psRL.execute();
                    Mail.sendMessage(command1.execute()); //hello and stuff
                    return ("profit");
                }
            }

            if (command1.getClass().toString().equals("class Commands.LogIn")) {
                if (DBManager.checkIfUsernameExists(request.getUsername())) {
                    ResultSet rs = psRL.executeQuery();
                    if (rs.next()) { //if found such user
                        Mail.sendMessage(command1.execute()); //hello and stuff
                        return ("profit");
                    } else {
                        Mail.sendMessage("wp"); //wrong password
                        return ("no");
                    }
                } else {
                    Mail.sendMessage("ude"); //username doesn't exist.
                    return ("no");
                }
            }
        } catch (SocketException se) {
            System.err.println(se);
        } catch (Exception e) {
            System.err.println(e);
            Mail.sendMessage("sorry"); //Sorry,something went wrong. Please, try again.
            return ("no");
        }
        return ("wtf");
    }
}
