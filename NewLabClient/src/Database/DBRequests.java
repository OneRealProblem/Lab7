package Database;

/**
 * SQL requests as ENUM
 */
public enum DBRequests {
    CREATE_USER_TABLE("""
            CREATE TABLE IF NOT EXISTS USERS (
            Username VARCHAR(20) PRIMARY KEY CHECK(Username<>''),
            Password text NOT NULL
            );"""),
    REGISTER_NEW_USER("INSERT INTO USERS (Username,Password) VALUES(?,?)"),
    CHECK_USER("SELECT * FROM USERS WHERE Username = ? AND PASSWORD = ?"),
    CREATE_ID("CREATE SEQUENCE IF NOT EXISTS ids START 1"),
    GENERATE_NEXT_ID("SELECT nextval('ids')"),
    DELETE_ALL_MY_DUDES("DELETE FROM DUDES WHERE Author = ?"),



    CREATE_DUDES_TABLE("""
            CREATE TABLE IF NOT EXISTS DUDES (
            ID BIGINT PRIMARY KEY DEFAULT (nextval('ids')),
            Name VARCHAR(20) NOT NULL CHECK(Name<>''),
            X BIGINT NOT NULL,
            Y BIGINT NOT NULL,
            CreationDate TIMESTAMP NOT NULL,
            RealHero BOOLEAN,
            HasToothpick BOOLEAN,
            ImpactSpeed INT CHECK(ImpactSpeed > -220),
            MinutesOfWaiting FLOAT NOT NULL,
            WeaponType VARCHAR(20) CHECK(WeaponType='hammer' OR WeaponType='axe' OR WeaponType='pistol' OR WeaponType='rifle' OR WeaponType='bat'),
            Mood VARCHAR NOT NULL CHECK(Mood='sorrow' OR Mood='apathy' OR Mood='calm'),
            CarName VARCHAR(20) NOT NULL,
            CarCool BOOLEAN NOT NULL,
            Author VARCHAR(20) REFERENCES USERS (Username)
            );"""),

    ADD_NEW_DUDE("INSERT INTO DUDES (ID,Name,X,Y,CreationDate,RealHero," +
            "HasToothpick,ImpactSpeed,MinutesOfWaiting,WeaponType," +
            "Mood,CarName,CarCool,Author)" +
            "VALUES(nextval('ids'),?,?,?,?,?,?,?,?,?,?,?,?,?)"),
    UPDATE_DUDE("""
            UPDATE DUDES
            SET (Name,X,Y,CreationDate,RealHeroHasToothpick,ImpactSpeed,MinutesOfWaiting,WeaponType,Mood,CarName,CarCool) = (?,?,?,?,?,?,?,?,?,?,?,?,?)
            WHERE ID = ?"""),
    DELETE_DUDE("DELETE FROM DUDES WHERE ID = ?"),
    GET_ALL_DUDES ("SELECT * FROM DUDES"),

    REMOVE_ALL_BY_MOW("DELETE FROM DUDES WHERE MinutesOfWaiting = ?"),
    REMOVE_GREATER_KEY("DELETE FROM DUDES WHERE ID>?"),
    REMOVE_LOWER_KEY("DELETE FROM DUDES WHERE ID<?");


    private final String dbrequest;
    DBRequests(String dbrequest){
        this.dbrequest = dbrequest;
    }

    public String getDbrequest() {
        return dbrequest;
    }
}
