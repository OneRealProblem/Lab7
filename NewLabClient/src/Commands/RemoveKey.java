package Commands;

import Collection.MyCol;
import Database.DBRequests;

import java.io.Serial;
import java.io.Serializable;

public class RemoveKey extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 9089023555825590714L;
    private Integer k;



    public RemoveKey(Integer k){
        super(DBRequests.DELETE_DUDE);
        this.k = k;
    }

    public Integer getK() {
        return k;
    }

    public String execute(){
        MyCol.delete(MyCol.dudes.get(k));
        return("Dude with ID " + k + " was deleted.");};
}
