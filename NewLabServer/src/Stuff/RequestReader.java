package Stuff;

import Commands.Request;

import java.io.IOException;
import java.util.concurrent.RecursiveTask;

public class RequestReader extends RecursiveTask {
    private Request request;

    @Override
    protected Request compute(){
        byte[] commandBytes;
        try {
            commandBytes = Mail.readObject(Mail.getIs());
            request = (Request) SerDeser.deserialize(commandBytes);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return request;
    }
}
