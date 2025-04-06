package Stuff;

import Collection.MyCol;
import Commands.Command;
import Commands.Request;
import Database.DBManager;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.RecursiveTask;

public class RequestDoer extends RecursiveTask {

    private final Request request;

    public RequestDoer(Request request){
        this.request = request;
    }

    @Override
    protected String compute(){
        Command command = request.getCommand();
        try{
            if (command.getDbAnalog() != null){
                PreparedStatement ps = DBManager.prepareCom(request);
                ps.execute();
            }
            return MyCol.lockAndExecute(command);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return "Sorry, something went wrong during command execution";
        }
    }
}
