package Commands;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -8019003199040952691L;

    public Exit(){
        super(null);
    }

    @Override
    public String execute() throws IOException {
        return("See ya!");}
}
