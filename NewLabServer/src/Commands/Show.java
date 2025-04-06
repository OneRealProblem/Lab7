package Commands;

import Collection.*;
import Database.DBRequests;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;


public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -8950727473607922756L;

    public Show(){
        super(null);
    }

    public void doMOW(HumanBeing humanBeing){
        //Получим минуты ожидания
        Duration age = Duration.between(humanBeing.getCreationDate(), LocalDateTime.now());
        Long min = age.toMinutes();
        humanBeing.setMinutesOfWaiting(min.floatValue());
    }


    public String execute(){
        String result = new String();
        for (HumanBeing dude: MyCol.dudes.values()){
            doMOW(dude);
            result += dude.toString()+"\n";
        }
        return result;
    }
}
