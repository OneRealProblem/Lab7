package Collection;

import java.io.*;
public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 8221107877602885609L;
    private boolean cool;
    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public boolean isCool() {
        return cool;
    }

    public String getName() {
        return name;
    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }

    public Car(String name){
        setName(name);
    }
    public Car(String name, boolean cool){ setName(name); setCool(cool);}
    public Car(){
        setName("idk");
        this.cool = false;
    }

    @Override
    public String toString(){
        return (name);
    }
}
