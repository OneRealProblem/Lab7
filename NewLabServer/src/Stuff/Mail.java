package Stuff;

import Database.DBManager;
import Database.PasswordWitch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Mail {
    public static Socket socket;
    private static ServerSocket serverSocket;
    private static OutputStream os;
    private static InputStream is;
    private static InetAddress host;
    private static final int port = 748;

    private static boolean start = true;

    // doing connection
    public static void connecting() throws IOException {

        // for server-client connection and input/output streams
        try {
            host = InetAddress.getLocalHost();
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            start = false;
        } catch (IOException e) {
            e.printStackTrace();
            start = false;
        }
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    //читаем команду
    public static byte[] readObject(InputStream is) throws IOException{
        byte[] commandBytes = new byte[readLenOfObject(is)];
        is.read(commandBytes);
        return commandBytes;
    }

    //читаем длину команды
    public static int readLenOfObject(InputStream is) throws IOException{
        byte[] arrCnt = new byte[1];
        is.read(arrCnt);
        byte[] len = new byte[arrCnt[0]];
        is.read(len);
        return Integer.parseInt(new String(len));
    }

    //отправляем сообщение
    public static void sendMessage(String s) throws  IOException{
        try {
            os = socket.getOutputStream();
            byte[] serializableMessage =SerDeser.serialize(s);
            sendLenOfMessage(serializableMessage.length);
            os.write(serializableMessage);
        } catch (IOException e) {
            System.out.println("Проблема в методе Server.sendMessage()");
            start = false;
        }
    }

    //отправляем длину сообщения
    public static void sendLenOfMessage(int lenOfMessage) throws IOException{
        try {
            String lenOfMessageToString = "" + lenOfMessage;
            byte[] lenlen = {(byte) lenOfMessageToString.length()};
            os.write(lenlen);
            byte[] lenOfMessageByteArray = lenOfMessageToString.getBytes(StandardCharsets.UTF_8);
            os.write(lenOfMessageByteArray);
        } catch (IOException e) {
            System.out.println("Проблема в методе Server.sendLenOfMessage()");
            start = false;
        }
    }

    public static InputStream getIs() {
        return is;
    }

    public static OutputStream getOs() {
        return os;
    }
}
