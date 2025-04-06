package Commands;

import Collection.MyCol;
import Collection.HumanBeing;

import java.io.Serial;
import java.io.Serializable;

public class SumOfMOW extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -4990800431596618536L;

    public SumOfMOW(){
        super(null);
    }
    public String execute(){
        float sum = 0;
        for (HumanBeing dude: MyCol.dudes.values()){
            sum += dude.getMinutesOfWaiting();
        }
        return(""+sum);
    }
}
