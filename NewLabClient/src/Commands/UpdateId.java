package Commands;

import Collection.*;
import Database.DBRequests;
import Stuff.Change;

import java.io.Serial;
import java.io.Serializable;

public class UpdateId extends Command implements Serializable {
    private Integer k;
    private HumanBeing dude;

    @Serial
    private static final long serialVersionUID = 6004776089767025081L;

    public UpdateId(Integer par){
        super(DBRequests.UPDATE_DUDE);
        try {
            this.k = par;
            if (MyCol.getGeneratedId().contains(k)) {
                System.out.println("Let's customize this dude.");
                this.dude = Change.customize(MyCol.dudes.get(k));
            } else {
                throw new IllegalArgumentException("Oops! This ID doesn't exist.");
            }
        }catch (Exception e){System.err.println(e);}
    }

    public HumanBeing getDude() {
        return dude;
    }

    public Integer getK() {
        return k;
    }

    @Override
    public String execute() {
        MyCol.delete(MyCol.dudes.get(k));
        MyCol.dudes.put(k,dude);
        dude.setId(k);

        return("That's all! Your dude is customized.");
    }
}