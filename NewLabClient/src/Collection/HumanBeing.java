package Collection;

import Database.DBManager;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Dudes class. Can be put into main collection.
 */
public class HumanBeing implements Serializable{
    @Serial
    private static final long serialVersionUID = 1509699496894965085L;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private Boolean hasToothpick; //Поле может быть null
    private long impactSpeed; //Значение поля должно быть больше -220
    private Float minutesOfWaiting; //Поле не может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле не может быть null

    private static final List<Names> VALUES = Collections.unmodifiableList(Arrays.asList(Names.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Names randomName()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public Integer getId(){
        return id;
    }

    public boolean isRealHero() {
        return realHero;
    }
    public boolean doHaveToothpick(){ return hasToothpick; }


    public Float getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public long getImpactSpeed() {
        return impactSpeed;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String MoodToString() {
        return switch (mood) {
            case CALM -> "calm";
            case APATHY -> "in apathy";
            case SORROW -> "draining";
        };
    }
    public String RealHeroToString() {
        if (realHero){
            return "Yup";
        }else{
            return "Nope";
        }
    }
    public String ToothpickToString() {
        if (hasToothpick == null){
            return "Who knows";
        }else{
            if (hasToothpick){
                return "Yeah";
            }
        }
        return "No";
    }

    public Car getCar() {
        return car;
    }

    public Mood getMood() {
        return mood;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public String getName(){ return this.name;}

    public void setName(String name){
        StringBuilder nam = new StringBuilder(name);
        nam.setCharAt(0, java.lang.Character.toUpperCase(name.charAt(0)));
        this.name = nam.toString();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCoordinates(Long x, Long y){
        this.coordinates = new Coordinates(x,y);
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setMinutesOfWaiting(Float minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public void isRealHero(boolean b){
        this.realHero = b;
    }
    public void doHaveToothpick(Boolean b){
        this.hasToothpick = b;
    }
    public void setImpactSpeed(long impactSpeed){
        if (impactSpeed <= -220){
            this.impactSpeed = -220;
        } else {
            this.impactSpeed = impactSpeed;
        }
    }
    public void setWeaponType(WeaponType weaponType){
        this.weaponType = weaponType ;
    }
    public void setMood(Mood mood){
        this.mood = mood ;
    }
    public void setCar(Car car){
        this.car = car ;
    }

    public String writeToFile() {
        return name + ";" + id +";" + creationDate + ";" + coordinates + ";" + RealHeroToString() +
                ";" + ToothpickToString() + ";" + impactSpeed + ";" + minutesOfWaiting +
                ";" + MoodToString() + ";" + car;
    }

    public int compareTo(HumanBeing humanBeing) {
        if (id < humanBeing.getId()){ 
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String toString(){
        return ("Dude named  " + name + " (ID = " + id +")" + "\n" +
                "   Creation date: " + creationDate + "\n" +
                "   Coordinates: " + coordinates + "\n" +
                "   Real Hero: " + RealHeroToString() + "\n" +
                "   Has Toothpick: " + ToothpickToString() + "\n" +
                "   Speed: " + impactSpeed + "\n" +
                "   Minutes of waiting: " + minutesOfWaiting + "\n" +
                "   Mood: " + MoodToString() + "\n" +
                "   Car: " + car);
    }

    public HumanBeing(){
        setId(null);
        String nam = randomName().name().toLowerCase();
        setName(nam);
        setCoordinates(0L, 0L);
        this.creationDate = LocalDateTime.now();
        isRealHero(false);
        doHaveToothpick(false);
        setImpactSpeed(0);
        setMinutesOfWaiting(0.0F);
        setCar(new Car());
        setWeaponType(WeaponType.BAT);
        setMood(Mood.CALM);
    }
}
