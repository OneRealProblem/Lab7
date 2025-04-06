package Database;

import Collection.Car;
import Collection.HumanBeing;
import Collection.MyCol;
import Commands.*;
import Stuff.Change;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Locale;

public class DBManager {
    private static Connection connection;
    private final PasswordWitch witch;

    public DBManager(String dbUrl, String username, String password,PasswordWitch pw) throws SQLException {
        connection = DriverManager.getConnection(dbUrl,username,password);
        if (connection != null){
            witch = pw;
            create_DB();
            System.err.println("Created database");
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
    public static PreparedStatement prepareCom(Request request) throws SQLException {
        Command command = request.getCommand();
        System.out.println(command.getClass().toString());
        PreparedStatement ps = connection.prepareStatement(command.getDbAnalog().getDbrequest());

        if(command.getClass().toString().equals("class Commands.InsertKey")){
            HumanBeing dude = ((InsertKey) command).getDude();
            dude.setId(DBManager.getNeededId());
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
            ps.setString(13, request.getUsername());
        }
        if(command.getClass().toString().equals("class Commands.RemoveAllByMOW")){
            ps.setFloat(1, (((RemoveAllByMOW) command).getF()));
            ps.setString(2, request.getUsername());
        }
        if(command.getClass().toString().equals("class Commands.RemoveGreaterKey")){
            ps.setInt(1, ((RemoveGreaterKey) command).getK());
            ps.setString(2, request.getUsername());
        }
        if(command.getClass().toString().equals("class Commands.RemoveKey")){
            ps.setInt(1, ((RemoveKey) command).getK());
            ps.setString(2, request.getUsername());
        }
        if(command.getClass().toString().equals("class Commands.RemoveLower")){
            ps.setInt(1, ((RemoveLower) command).getId());
            ps.setString(2, request.getUsername());
        }
        if(command.getClass().toString().equals("class Commands.UpdateId")){
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
            ps.setString(14, request.getUsername());
        }
        System.out.println(ps.toString());
        return ps;
    }

    public static Integer getNeededId() throws SQLException {

        PreparedStatement ps = connection.prepareStatement(DBRequests.SHOW_LAST_ID.getDbrequest());
        ResultSet resultSet = ps.executeQuery();
        Integer id = null;
        while(resultSet.next()){
            id = resultSet.getInt(1);
        }

        return id;
    }

    public void readElementsFromDB() throws SQLException {
        MyCol.dudes.clear();
        PreparedStatement statement = connection.prepareStatement(DBRequests.GET_ALL_DUDES.getDbrequest());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            HumanBeing dude = new HumanBeing();
            int columnIndex = 1;
            dude.setId(resultSet.getInt(columnIndex++));
            dude.setName(resultSet.getString(columnIndex++) );
            dude.setCoordinates(resultSet.getLong(columnIndex++), resultSet.getLong(columnIndex++));
            dude.setCreationDate(resultSet.getTimestamp(columnIndex++).toLocalDateTime());
            dude.isRealHero(resultSet.getBoolean(columnIndex++));
            dude.doHaveToothpick(resultSet.getBoolean(columnIndex++));
            dude.setImpactSpeed(resultSet.getLong(columnIndex++));
            dude.setMinutesOfWaiting(resultSet.getFloat(columnIndex++));
            dude.setWeaponType(Change.stringToWeapon(resultSet.getString(columnIndex++)));
            dude.setMood(Change.stringToMood(resultSet.getString(columnIndex++)));
            dude.setCar(new Car(resultSet.getString(columnIndex++),resultSet.getBoolean(columnIndex++)));
            dude.setAuthor(resultSet.getString(columnIndex++));
            MyCol.dudes.put(dude.getId(),dude);
        }
    }


    public static boolean checkIfUsernameExists(String s) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ? ");
        ps.setString(1,s);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }

    public static Connection getConnection() {
        return connection;
    }
}
