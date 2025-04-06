package Stuff;

public class BusyIdException extends Exception{
    @Override
    public String toString(){
        return ("This ID already exists.");
    }
}