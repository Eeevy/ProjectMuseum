package main.java.server;

import static spark.Spark.get;
import static spark.Spark.put;

/**
 * REST-API/ Server with URL Routing, Calls Clienthandler for each request.
 * @author Gurkpatrullen / Andreas Andersson, David Isberg, Emma Shakespeare, Evelyn Gustavsson
 *
 */
public class Server {
    private static ClientHandler clientHandler=new ClientHandler();

    public static void main(String[] args) {

        /**
         * Get Highscore
         */
        get("/highscore", (request, res) -> {
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            String str=clientHandler.getHighScore();
            System.out.println(str);
            return str;
        });

        /**
         * Add highscore to Database, Param= score and username.
         * :name = s=Score&user=Username
         */
        get("/highscore/:name", (request, res) -> { 
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.setHighScore(request.params(":name"));
        });

        /**
         * Get Category and gamepackages.
         * :name= Language
         */
        get("/categories/:name", (request, res) -> { 
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.getCategories(request.params(":name"));
        });


        /**
         * Get Gamepackage       
         * :name=ID
         */
        get("/categories/gamepackage/:name", (request, res) -> {
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.getGamePackage(request.params(":name"));
        });

        /**
         * Login, param with Username and Password.
         * name: "user=Username&pw=Password"
         */
        get("/login/:name", (request, res) -> {
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.login(request.params(":name"));
        });

        /**
         * create account, param =username,password and email
         * :name= user=Username&pw=Password&email=Email
         */
        get("/create-account/:name", (request, res) -> {
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.createAccount(request.params(":name"));
        });

    }
}
