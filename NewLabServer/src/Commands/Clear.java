package Commands;

import Collection.MyCol;
import Database.DBRequests;

import java.io.Serial;
import java.io.Serializable;

public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2597934266622029076L;

    public Clear(){
        super(null);
    }

    @Override
    public String execute() {
        MyCol.dudes.clear();
        MyCol.clearGeneratedId();
        return ("Collection cleared!");
    }
}
