package Commands;

import Collection.MyCol;

import java.io.Serial;
import java.io.Serializable;

public class Info extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 4599791153297031444L;

    public Info(){
        super(null);
    }
    @Override
    public String execute(){
        return("Collection info:" + "\n" +
                "Type: " + MyCol.dudes.getClass() + "\n" +
                "Initialization date: " + MyCol.initDate + "\n" +
                "Number of elements: " + MyCol.dudes.size());
    }

}
