package Commands;

import java.io.Serial;

public class What extends Command{
    @Serial
    private static final long serialVersionUID = -6555751565259428102L;

    public What(){
        super(null);
        System.err.println("There is no such command. Write help for command information.");
    }
    public String execute(){return("");};
}
