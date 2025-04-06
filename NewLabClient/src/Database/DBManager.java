package Database;

import Collection.HumanBeing;
import Commands.*;

import java.sql.*;
import java.util.Locale;

public class DBManager {
    private static Connection connection;
    private final PasswordWitch witch;

    public DBManager(String dbUrl, String username, String password,PasswordWitch pw) throws SQLException {
        connection = DriverManager.getConnection(dbUrl,username,password);
        if (connection != null){
            witch = pw;
            create_DB();
        }else{
            throw new SQLException("Не удалось установить соединение с базой данных.");
        }
    }

    private void create_DB(){
        try {
            PreparedStatement ids = connection.prepareStatement(DBRequests.CREATE_ID.getDbrequest());
            ids.execute();

            PreparedStatement dt = connection.prepareStatement(DBRequests.CREATE_DUDES_TABLE.getDbrequest());
            PreparedStatement ut = connection.prepareStatement(DBRequests.CREATE_USER_TABLE.getDbrequest());

            ut.execute();
            dt.execute();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static PreparedStatement prepareCom(Command command) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(command.getDbAnalog().getDbrequest());
        switch(command.getClass().toString()) {
            case ("class Command.InsertKey"):
                HumanBeing dude = ((InsertKey) command).getDude();
                ps.setString(1,dude.getName()); //name
                ps.setLong(2, dude.getCoordinates().getX() ); //x
                ps.setLong(3, dude.getCoordinates().getY() ); //y
                ps.setTimestamp(4, Timestamp.valueOf(dude.getCreationDate())); //creation date
                ps.setBoolean(5,dude.isRealHero()); //real hero
                ps.setBoolean(6, dude.doHaveToothpick()); //has toothpick
                ps.setInt(7, (int) dude.getImpactSpeed()); //impact speed
                ps.setFloat(8,dude.getMinutesOfWaiting()); //mow
                ps.setString(9,dude.getWeaponType().toString().toLowerCase());
                ps.setString(10,dude.getMood().toString().toLowerCase());
                ps.setString(11,dude.getCar().getName());
                ps.setBoolean(12,dude.getCar().isCool());
                break;
            case ("class Command.RemoveAllByMOW"):
                ps.setFloat(1, (((RemoveAllByMOW) command).getF()));
                break;
            case ("class Command.RemoveGreaterKey"):
                ps.setInt(1, ((RemoveGreaterKey) command).getK());
                break;
            case ("class Command.RemoveKey"):
                ps.setInt(1, ((RemoveKey) command).getK());
                break;
            case ("class Command.RemoveLower"):
                ps.setInt(1, ((RemoveLower) command).getId());
                break;
            case ("class Command.UpdateId"):
                HumanBeing guy = ((UpdateId) command).getDude();
                ps.setString(1,guy.getName()); //name
                ps.setLong(2, guy.getCoordinates().getX() ); //x
                ps.setLong(3, guy.getCoordinates().getY() ); //y
                ps.setTimestamp(4, Timestamp.valueOf(guy.getCreationDate())); //creation date
                ps.setBoolean(5,guy.isRealHero()); //real hero
                ps.setBoolean(6, guy.doHaveToothpick()); //has toothpick
                ps.setInt(7, (int) guy.getImpactSpeed()); //impact speed
                ps.setFloat(8,guy.getMinutesOfWaiting()); //mow
                ps.setString(9,guy.getWeaponType().toString().toLowerCase());
                ps.setString(10,guy.getMood().toString().toLowerCase());
                ps.setString(11,guy.getCar().getName());
                ps.setBoolean(12,guy.getCar().isCool());
                ps.setInt(13, ((UpdateId) command).getK());
                break;
        }
        return ps;
    }

    public Connection getConnection() {
        return connection;
    }
}
