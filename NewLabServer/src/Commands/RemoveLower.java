package Commands;

import Database.DBRequests;
import Stuff.Change;
import Collection.MyCol;

import java.io.Serial;
import java.io.Serializable;

public class RemoveLower extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 1208672449847728650L;
    private Integer id;

    public RemoveLower(Integer id) {
        super(DBRequests.REMOVE_LOWER_KEY);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String execute(){
        MyCol.dudes.values().stream().filter(x -> x.getId() > id).forEach(x -> MyCol.delete(x));
        return("Succeeded");};
}
