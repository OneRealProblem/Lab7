package Commands;

import Database.DBRequests;

import java.io.IOException;
import java.io.Serial;

public class LogIn extends Command{
    @Serial
    private static final long serialVersionUID = 6101903192252516454L;

    public LogIn(){
        super(DBRequests.CHECK_USER);
    }

    public String execute() throws IOException {return("GOOD TO SEE YA AGAIN");};
}
