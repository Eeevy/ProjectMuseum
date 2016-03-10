package main.java.server;

import static spark.Spark.get;
import static spark.Spark.put;

/**
 * REST-API, (URL Listener)
 */
public class Server {
    private static ClientHandler clientHandler=new ClientHandler();

    public static void main(String[] args) {

        /**
         * Get Highscore
         */
        get("/highscore", (request, res) -> {
          //  System.out.println(request.params());
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
         */
        get("/highscore/:name", (request, res) -> { //:name = s=score&user=username
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.setHighScore(request.params(":name"));
        });

        /**
         * Get Category and gamepackages.
         */
        get("/categories", (request, res) -> {  //name= swe/eng
            System.out.println(request.params());
            res.type("text/json");
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Request-Method", "*");
            res.header("Access-Control-Allow-Headers", "*");
            return clientHandler.getCategories();
        });


        /**
         * Get gamepackage       //:name EX. id=1&lang=swe
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
         * name: "user=username&pw=password"
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
         * create account, param =username,password (+email and confirm password?)
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
