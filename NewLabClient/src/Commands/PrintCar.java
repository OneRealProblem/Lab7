package Commands;

import Collection.MyCol;
import Collection.HumanBeing;

import java.io.Serial;
import java.io.Serializable;

public class PrintCar extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -628852223709228324L;

    public PrintCar(){
        super(null);
    }

    public String execute(){
        String s = new String();
        for (HumanBeing dude: MyCol.dudes.values()){
            s+= dude.getCar() + " ";
        }
        return(s);
    }
}
