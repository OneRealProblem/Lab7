package Commands;

import Collection.MyCol;
import Database.DBRequests;

import java.io.Serial;
import java.io.Serializable;

public class RemoveAllByMOW extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -8040741975063624985L;
    private Float f;

    public RemoveAllByMOW(Float f){
        super(DBRequests.REMOVE_ALL_BY_MOW);
        this.f = f;
    }

    public Float getF() {
        return f;
    }

    public String execute(){
        MyCol.dudes.values().stream().filter(x -> x.getMinutesOfWaiting() == f).forEach(x -> MyCol.delete(x));
        return("All dudes waiting for " + f + " minutes were deleted.");
    };
}
