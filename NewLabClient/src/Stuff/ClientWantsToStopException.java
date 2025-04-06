package Stuff;

public class ClientWantsToStopException extends Exception{
    @Override
    public String toString(){
        return ("Client wants to stop the process");
    }
}
