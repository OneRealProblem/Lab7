package Commands;

import Database.DBRequests;

import java.io.IOException;
import java.io.Serial;

public class Register extends Command{
    @Serial
    private static final long serialVersionUID = -2745531742235700468L;

    public Register(){
        super(DBRequests.REGISTER_NEW_USER);
    }

    public String execute() throws IOException {return("Welcome to our community :)");};
}
