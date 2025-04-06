package Commands;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = -3748607841605557874L;
    private Command command;
    private String username;
    private String password;

    public Request(Command command,String username,String password){
        this.command = command;
        this.username = username;
        this.password = password;
    }

    public Command getCommand() {
        return command;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
