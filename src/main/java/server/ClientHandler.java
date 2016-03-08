package server;

import client.Client;
import database.DBSQL;

import java.util.StringJoiner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Handles requests from Server. Starts a separate Task for each request using a ThreadPool.
 * Created by Andreas on 2016-03-08.
 */
public class ClientHandler {

    private ThreadPoolExecutor threadPool;
    private DBSQL db=new DBSQL();

    public ClientHandler(){
        threadPool= (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    /**
     * Returns Categories and GamePackages (Category and GamePackage name only).
     * @return String
     */
    public String getCategories(){
        final String[] str = {new String()};
        threadPool.execute(() -> {
            str[0] =db.getCategories();
        });
        return str[0];
    }

    /**
     * Returns a GamePackage with 10 questions.
     * @return String
     */
    public String getGamePackage(String id){
        final String[] str = {new String()};
        threadPool.execute(() -> {
            str[0] =db.getGamePackage(id);
        });
        return str[0];
    }

    /**
     * Attempt to login with username and password, returns result.
     * accountinfo="user=username&pw=password"
     * @param accountInfo username and password
     * @return String
     */
    public String login(String accountInfo){
        //separate username and password.
        String username,password;
        username=accountInfo.substring(0,accountInfo.indexOf('&'));
        password=accountInfo.substring(accountInfo.indexOf('&')+1,accountInfo.length());

        username=username.substring(username.indexOf("=")+1,username.length());
        password=password.substring(password.indexOf("=")+1,password.length());

        System.out.println("Login-User:"+username+" Password:"+password);

        final String[] res = new String[1];
        final String finalUsername = username;
        final String finalPassword = password;
        threadPool.execute(() -> {
            res[0] =db.login(finalUsername, finalPassword);
        });
        return res[0];
    }


    /**
     * Lets the user create an account with Username and Password, returns result.
     * @param accountInfo Username and password
     * @return String
     */
    public String createAccount(String accountInfo){//Parameter +E-mail+confirm password(Check confirm password in client)??
        //separate username and password.
        String username,password;
        username=accountInfo.substring(0,accountInfo.indexOf('&'));
        password=accountInfo.substring(accountInfo.indexOf('&')+1,accountInfo.length());

        username=username.substring(username.indexOf("=")+1,username.length());
        password=password.substring(password.indexOf("=")+1,password.length());

        System.out.println("Create account-User:"+username+" Password:"+password);

        final String[] res = new String[1];
        final String finalUsername = username;
        final String finalPassword = password;
        threadPool.execute(() -> {
            res[0] =db.createAccount(finalUsername, finalPassword);
        });
        return res[0];
    }

    /**
     * Return HighScore.
     * @return String
     */
    public String getHighScore(){
        final String[] str = {new String()};
        threadPool.execute(() -> {
            str[0] =db.getHighScore();
        });
        return str[0];
    }

    /**
     * Set HighScore
     * str = s=score&user=username
     * @param str players score & username
     * @return String
     */
    public String setHighScore(String str){
        //Split String
        String username,score;

        score=str.substring(0,str.indexOf('&'));
        username=str.substring(str.indexOf('&')+1,str.length());

        score=score.substring(score.indexOf("=")+1,score.length());
        username=username.substring(username.indexOf("=")+1,username.length());

        System.out.println("SetHighscore-Score:"+score+" User:"+username);

        final String[] res = {new String()};
        final String finalUsername = username;
        final String finalScore = score;
        threadPool.execute(() -> {
            res[0] =db.setHighScore(finalScore, finalUsername);
        });
        return res[0];
    }

    //TEST
    public static void main(String [] args){
        ClientHandler c=new ClientHandler();
        c.setHighScore("s=105&user=bambismurfen");
        c.login("username=bambismurfen&pw=Test132Test");
        c.createAccount("username=bambismurfen&pw=Test132Test");
    }
}