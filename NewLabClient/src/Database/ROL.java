package Database;


// Im just tired

import Commands.*;
import Stuff.ClientWantsToStopException;
import Stuff.Mail;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class ROL {
    private static Scanner reader = new Scanner(System.in);
    private static String username;
    private static String password;


    public static boolean regOrLog() throws IOException, ClassNotFoundException, ClientWantsToStopException {
        String intention = askIntention();
        Request request;

        if (intention.equals("exit")){
            request = new Request(new Exit(),null,null);
        }else{
            String name = askName();
            String pass = askPassword();
            username = name;
            password = pass;

            if (intention.equals("register")){
                request = new Request(new Register(),name,pass);
            }else{
                request = new Request(new LogIn(),name,pass);
            }
        }

        Mail.sendObject(request);
        String ans = Mail.readMessage(); //returns client-oriented message or answer about failed log in/registration

        switch (ans){
            case "ex":
                throw new ClientWantsToStopException();
            case "uae":
                System.out.println("This username already exists. Try again.");
                return false;
            case "wp":
                System.out.println("Wrong password. Try again.");
                return false;
            case "ude":
                System.out.println("This username doesn't exist. Try again.");
                return false;
            default:
                System.out.println(ans);
                return true;
        }






    }

    public static String askName(){ //returns not null username
        while (true) {
            try {

                System.out.println("Enter username");
                String username = reader.nextLine();
                if (username == null || username.equals("")) {
                    throw new Exception();
                }

                return username;
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Something went wrong. Try again, please.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Username cannot be null");
            }
        }
    }

    public static String askPassword(){ //returns cursed password
        while (true) {
            try {

                System.out.println("Enter password");
                String raw = reader.nextLine();
                if (raw == null || raw.equals("")) {
                    throw new Exception();
                }

                String password = (new MD2Witch()).curseThePassword(raw);

                return password;
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Something went wrong. Try again, please.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Password cannot be null");
            }
        }
    }

    public static String askIntention() { //returns exit/register/login
        String intention;
        while (true) {
            System.out.print("Do you wanna register (1) or log in (2) ?: ");
            String answer = reader.nextLine();

            if ("exit".equalsIgnoreCase(answer)){
                intention = "exit";
                break;
            }

            if ("1".equals(answer)) {
                intention = "register";
                break;
            }
            if ("2".equals(answer)) {
                intention = "login";
                break;
            }

        }
        return intention;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
