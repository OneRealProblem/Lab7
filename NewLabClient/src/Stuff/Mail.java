package Stuff;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Mail {
    private static SocketChannel socketChannel;
    private static String username;
    private static String password;


    public static void connecting() throws IOException {
        int port = 748;
        InetAddress host = InetAddress.getLocalHost();
        SocketAddress socketAddress = new InetSocketAddress(host, port);
        socketChannel = SocketChannel.open();
        System.out.println("Waiting...");
        socketChannel.connect(socketAddress);
    }


    //отправляем команду
    public static void sendObject(Object command) {
        try {
            byte[] byteArrayObject = SerDeser.serialize(command);
            sendLenOfObject(byteArrayObject.length);
            socketChannel.write(ByteBuffer.wrap(byteArrayObject));
        } catch (IOException e) {
            System.out.println("Проблема в методе Client.sendObject()");
        }
    }

    //отправляем длину десереализованной команды
    public static void sendLenOfObject(int lenArr) {
        try {
            String lenArrStr = "" + lenArr;
            byte[] arr = lenArrStr.getBytes(StandardCharsets.UTF_8);
            byte[] cnt = {(byte) arr.length};
            socketChannel.write(ByteBuffer.wrap(cnt));
            socketChannel.write(ByteBuffer.wrap(arr));
        } catch (IOException e) {
            System.out.println("Проблема в методе Client.sendLenOfObject()");
        }
    }

    //читаем длину сообщения
    public static int readLenOfMessage() throws IOException{
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[1]);
        while (byteBuffer.hasRemaining()) {
            socketChannel.read(byteBuffer);
        }
        int len = byteBuffer.array()[0];
        ByteBuffer lenMessage = ByteBuffer.allocate(len);
        while (lenMessage.hasRemaining()) {
            socketChannel.read(lenMessage);
        }
        return Integer.parseInt(new String(lenMessage.array()));
    }

    public static String readMessage() throws IOException, ClassNotFoundException{
        ByteBuffer byteBuffer = ByteBuffer.allocate(readLenOfMessage());
        while (byteBuffer.hasRemaining()) {
            socketChannel.read(byteBuffer);
        }
        byte[] b = byteBuffer.array();
        return (String)SerDeser.deserialize(b);
    }
}
