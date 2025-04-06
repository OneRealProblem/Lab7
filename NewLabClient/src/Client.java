import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import Collection.MyCol;
import Commands.*;
import Database.MD2Witch;
import Database.ROL;
import Stuff.*;

public class Client {
    private static byte[] genId = new byte[1000];
    private static boolean authorized = false;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String username;
        String password;

        try {

            Mail.connecting();

            authorized = ROL.regOrLog();
            while (!authorized){
                authorized = ROL.regOrLog();
            }


            username = ROL.getUsername();
            password = ROL.getPassword();
            System.err.println("Start work");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                Command c = Change.stringToCommand(scanner.nextLine().toLowerCase());
                Request request = new Request(c,username,password);

                Mail.sendObject(request);
                String s = Mail.readMessage();
                System.out.println(s);
                if (s.equals("See ya!")) {
                    break;
                }
            }
        } catch (BusyIdException busyIdException) {
            busyIdException.printStackTrace();
        } catch (ClientWantsToStopException clientWantsToStopException) {
            clientWantsToStopException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SocketException s){
            System.out.println("Connection is canceled.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
