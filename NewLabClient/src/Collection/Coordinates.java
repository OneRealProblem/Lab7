package Collection;

import java.io.*;

public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = -8188394200810682677L;
    private Long x; //Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Long x,Long y){
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public String toString(){
        return ("x = " + x + ", y = " + y);
    }
}
