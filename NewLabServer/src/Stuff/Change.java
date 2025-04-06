package Stuff;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import Collection.*;
import Commands.*;

/**
 * For metamorphosing one class to another and customizing
*/
public class Change {
    private static final Scanner reader = new Scanner(System.in);

    public static Coordinates tryReadCoordinates(){
        String[] xy;
        while(true) {
            try {
                xy = reader.nextLine().split(" ");
                return (new Coordinates(Long.parseLong(xy[0]),Long.parseLong(xy[1])));
            } catch (Exception e) {
                System.err.println("Are you sure that you enter things correctly? Try again.");
            }
        }
    }

    public static long tryReadSpeed(){
        while(true) {
            try {
                long speed = Long.parseLong(reader.nextLine());
                if (speed<-220){
                    throw new Exception();
                }
                return (speed);
            } catch (Exception e) {
                System.err.println("Are you sure you entered speed correctly? It should be larger than -220. Try again.");
            }
        }
    }

    public static Command stringToCommand(String s) throws BusyIdException, IOException {
        String[] command = s.split(" ");
        try {
            return switch (command[0].toLowerCase()) {
                case ("help") -> (new Help());
                case ("info") -> (new Info());
                case ("show") -> (new Show());
                case ("insert") -> (new InsertKey());
                case ("update_id") -> (new UpdateId(Integer.parseInt(command[1])));
                case ("remove_key") -> (new RemoveKey(Integer.parseInt(command[1])));
                case ("clear") -> (new Clear());
                case ("remove_lower") -> (new RemoveLower(Integer.parseInt(command[1])));
                case ("remove_greater_key") -> (new RemoveGreaterKey(Integer.parseInt(command[1])));
                case ("remove_all_by_minutes_of_waiting") -> (new RemoveAllByMOW(Float.parseFloat(command[1])));
                case ("sum_of_mow") -> (new SumOfMOW());
                case ("print_car") -> (new PrintCar());
                case ("exit") -> (new Exit());
                default -> (new What());
            };
        } catch (ArrayIndexOutOfBoundsException | SQLException a) {
            return (new Where());
        }
    }

    public static Mood stringToMood(String s){
        try {
            return switch (s.toLowerCase()) {
                case ("calm") -> Mood.CALM;
                case ("sorrow") -> Mood.SORROW;
                case ("apathy") -> Mood.APATHY;
                default -> throw new IllegalStateException();
            };
        }catch (IllegalStateException i){
            System.err.println("Something went wrong! We set default calm mood.");
            return Mood.CALM;
        }
    }

    public static WeaponType stringToWeapon(String s){
        WeaponType m;
        switch (s.toLowerCase()) {
            case ("pistol") -> m = WeaponType.PISTOL;
            case ("axe") -> m = WeaponType.AXE;
            case ("bat") -> m = WeaponType.BAT;
            case ("hammer") -> m = WeaponType.HAMMER;
            case ("rifle") -> m = WeaponType.RIFLE;
            default -> {
                System.err.println("Something went wrong and we needed to choose axe. Sorry.");
                m = WeaponType.AXE;
            }
        }
        return m;
    }

    public static boolean TF(String s) {
        String com = s;
        while (!com.equalsIgnoreCase("true") & !com.equalsIgnoreCase("false")) {
            System.out.println("Sorry, didn't get it. Please, write true or false.");
            com = reader.nextLine();
        }
        return (Boolean.parseBoolean(com));
    }

    public static Mood whatMood(String s) {
        String com = s;
        while (!com.equalsIgnoreCase("sorrow") & !com.equalsIgnoreCase("calm") & !com.equalsIgnoreCase("apathy")) {
            System.out.println("Sorry, didn't get it. Please, write sorrow, calm or apathy.");
            com = reader.nextLine();
        }
        return (stringToMood(com));
    }

    public static WeaponType whatWeapon(String s) {
        String com = s;
        while (!com.equalsIgnoreCase("hammer") & !com.equalsIgnoreCase("axe")
                & !com.equalsIgnoreCase("pistol") & !com.equalsIgnoreCase("rifle") & !com.equalsIgnoreCase("bat")) {
            System.out.println("Sorry, didn't get it. Please, write hammer, axe, pistol, rifle or bat.");
            com = reader.nextLine();
        }
        return (stringToWeapon(com));
    }

    public static HumanBeing customize(HumanBeing guy){

        System.out.println("Enter a new name.");
        guy.setName(reader.nextLine().toLowerCase());
        System.out.println("Enter x and y coordinates (with a space between them).");
        guy.setCoordinates(tryReadCoordinates());
        System.out.println("Everyone here have a car. Write the name of the dude's car.");
        guy.setCar(new Car(reader.nextLine()));
        System.out.println("Is this car a cool one? Write true or false.");
        guy.getCar().setCool(TF(reader.nextLine().toLowerCase()));
        System.out.println("Let's have a closer look? Write yes or no.");
        boolean t = true;
        while (t){
            switch (reader.nextLine().toLowerCase()) {
                case ("yes") -> {
                    System.out.println("Is this guy a hero? Write true or false.");
                    guy.isRealHero(TF(reader.nextLine().toLowerCase()));
                    System.out.println("Does he have a toothpick? Write true or false.");
                    guy.doHaveToothpick(TF(reader.nextLine().toLowerCase()));
                    System.out.println("Does he have a weapon? Write true or false.");
                    if (TF(reader.nextLine().toLowerCase())) {
                        System.out.println("Let's pick a weapon! Write axe, bat, hammer, rifle or pistol.");
                        guy.setWeaponType(whatWeapon(reader.nextLine()));
                    }
                    System.out.println("How does he feel? Write calm, sorrow or apathy.");
                    guy.setMood(whatMood(reader.nextLine().toLowerCase()));
                    t = false;
                }
                case ("no") -> {
                    System.out.println("OK. Whatever.");
                    t = false;
                }
                default -> System.out.println("Write yes or no.");
            }
        }
        System.out.println("\n");
        return (guy);
    }

}
