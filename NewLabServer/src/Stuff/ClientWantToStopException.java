package Stuff;

public class ClientWantToStopException extends Exception{
    @Override
    public String toString(){
        return ("Client wants to stop the process");
    }
}