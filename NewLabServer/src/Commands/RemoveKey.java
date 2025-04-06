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
        try {
            if (!MyCol.dudes.containsKey(k)){
                IllegalArgumentException e = new IllegalArgumentException("No such ID in collection!");
                throw e;
            } else {
                this.k = k;
            }
        }catch (Exception e) {
            System.err.println(e);
        }

    }

    public Integer getK() {
        return k;
    }

    public String execute(){
        MyCol.delete(MyCol.dudes.get(k));
        return("Dude with ID " + k + " was deleted.");};
}
