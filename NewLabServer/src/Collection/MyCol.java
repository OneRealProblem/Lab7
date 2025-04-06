package Collection;

import Commands.Command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyCol {
    public static LinkedHashMap<Integer, HumanBeing> dudes = new LinkedHashMap<>();
    private static ArrayList<Integer> generatedId = new ArrayList<>();
    private static Integer lastId = 1;
    public static LocalDateTime initDate;

    public static void setInitDate(LocalDateTime initDate) {
        MyCol.initDate = initDate;
    }

    public static String lockAndExecute(Command command) throws IOException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
        String ans;
        switch (command.getClass().toString()) {
            case "class Commands.Where":
            case "class Commands.What":
            case "class Commands.Help":
            case "class Commands.Exit":
                ans = command.execute();
                break;
            case "class Commands.Info":
            case "class Commands.PrintCar":
            case "class Commands.Show":
            case "class Commands.SumOfMOW":
                readLock.lock();
                ans = command.execute();
                readLock.unlock();
                break;
            default:
                writeLock.lock();
                ans = command.execute();
                writeLock.unlock();
                break;
        }
        return ans;
    }

    public static ArrayList<Integer> getGeneratedId(){
        return generatedId;
    }

    public static void clearGeneratedId(){
        generatedId.clear();
    }

    public static void addGeneratedId() {
        generatedId.add(lastId++);
    }

    public static void removeGeneratedId(Integer id) {
        generatedId.remove(id);
    }

    public static Integer getLastId() {
        return lastId;
    }

    public static void delete(HumanBeing dude){
        MyCol.dudes.remove(dude.getId());
        MyCol.removeGeneratedId(dude.getId());
    }

}
