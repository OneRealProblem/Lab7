package Commands;

import Database.DBRequests;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 504984739455315206L;

    private final DBRequests dbAnalog;

    Command(DBRequests dbAnalog){
        this.dbAnalog = dbAnalog;
    }

    public DBRequests getDbAnalog() {
        return dbAnalog;
    }

    public String execute() throws IOException {return("");};
}
