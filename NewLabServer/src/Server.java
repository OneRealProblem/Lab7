import Collection.MyCol;
import Commands.Request;
import Database.*;
import Stuff.*;


import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;


public class Server {
    private static DBManager manager;
    private static final Logger log = Logger.getAnonymousLogger();
    private static boolean authorized = false;


    public static void main(String[] args) throws IOException, SQLException, ClientWantToStopException {
        log.info("Waiting for our client...");

        Mail.connecting();
        log.info("client connection succeeded");

        doDatabase();

        //first register or log in
        while (!authorized){
            String did = ROL.regOrLog();

            switch (did) {
                case "exit" -> throw new ClientWantToStopException();
                case "profit" -> authorized = true;
                default -> {
                }
            }
        }
        log.info("Did ROL");



        OutputStream os = Mail.getOs();
        os.flush();
        while (true) {
            manager.readElementsFromDB();
            os.flush();

            RequestReader requestReader = new RequestReader();
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            Request request = (Request) forkJoinPool.invoke(requestReader);

            if (request.getCommand().getClass().toString().equals("class Commands.Exit")){
                Mail.sendMessage(request.getCommand().execute());
                os.flush();
                log.info("I hear exit");
                break;
            }else{
                RequestDoer requestDoer = new RequestDoer(request);
                String answer = (String) forkJoinPool.invoke(requestDoer);
                Executors.newCachedThreadPool().execute(() -> {
                    try {
                        Mail.sendMessage(answer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            os.flush();
        }
        log.info("Broke this sh");
    }

    public static void doDatabase() throws IOException, SQLException {
        //Doing database
        FileInputStream prop = new FileInputStream("src/Resources/DB.properties");
        Properties properties = new Properties();
        properties.load(prop);
        String host = properties.getProperty("db.host");
        String login = properties.getProperty("db.login");
        String password = properties.getProperty("db.password");
        PasswordWitch witch = new MD2Witch();
        manager = new DBManager(host,login,password, witch);
        log.info("everything is alright with database");
        MyCol.setInitDate(LocalDateTime.now());
        log.info("let's start");

    }


}
