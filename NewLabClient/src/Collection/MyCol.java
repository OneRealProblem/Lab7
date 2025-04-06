package Collection;

import java.time.LocalDateTime;
import java.util.*;

public class MyCol {
    public static LinkedHashMap<Integer, HumanBeing> dudes = new LinkedHashMap<>();
    private static ArrayList<Integer> generatedId = new ArrayList<>();
    public static LocalDateTime initDate;

    public static void setInitDate(LocalDateTime initDate) {
        MyCol.initDate = initDate;
    }

    public static ArrayList<Integer> getGeneratedId(){
        return generatedId;
    }

    public static void clearGeneratedId(){
        generatedId.clear();
    }

    public static void addGeneratedId(Integer id) {
        generatedId.add(id);
    }

    public static void removeGeneratedId(Integer id) {
        generatedId.remove(id);
    }

    public static void delete(HumanBeing dude){
        MyCol.dudes.remove(dude.getId());
        MyCol.removeGeneratedId(dude.getId());
    }

    public static void setGeneratedId(ArrayList<Integer> generatedId) {
        MyCol.generatedId = generatedId;
    }
}
