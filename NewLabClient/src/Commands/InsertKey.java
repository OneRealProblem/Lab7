package Commands;

import Database.DBRequests;
import Collection.MyCol;
import Collection.HumanBeing;
import Stuff.Change;

import java.io.Serial;
import java.io.Serializable;

public class InsertKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 7412308215487423851L;
    private HumanBeing dude;

    public InsertKey(){
        super(DBRequests.ADD_NEW_DUDE);
        this.dude = Change.customize(new HumanBeing());
    }

    public HumanBeing getDude() {
        return dude;
    }

    public String execute(){
        MyCol.dudes.put(dude.getId(), dude);
        System.out.println("Added");
        return("New dude was added.");
    }
}
