package Commands;

import Collection.*;
import Database.DBRequests;

import java.io.Serial;
import java.io.Serializable;

public class RemoveGreaterKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -8634144461949117184L;
    private Integer k;

    public RemoveGreaterKey(Integer k){
        super(DBRequests.REMOVE_GREATER_KEY);
        this.k = k;
    }

    public Integer getK() {
        return k;
    }

    public String execute(){
        MyCol.dudes.values().stream().filter(x -> x.getId() > k).forEach(x -> MyCol.delete(x));
        return("All guys with ID larger than " + k + " were deleted.");};
}
