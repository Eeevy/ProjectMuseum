package main.java.server;


import java.util.StringJoiner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import main.java.database.DBSQL;

/**
 * Handles requests from Server. Starts a separate Task for each request using a ThreadPool.
 * @author Gurkpatrullen / Andreas Andersson, David Isberg, Emma Shakespeare, Evelyn Gustavsson
 */
public class ClientHandler {

    private ThreadPoolExecutor threadPool;
    private DBSQL db=new DBSQL();

    /**
     * Constructor, Initializes the threadpool.
     */
    public ClientHandler(){
        threadPool= (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    /**
     * Returns Categories and GamePackages (Category and GamePackage name only).
     * @param param Language
     * @return String
     */
    public String getCategories(String param){
        String[] str = {new String()};
        threadPool.execute(() ->
         str[0] =db.getCategories(param)
         );
        try {
			Thread.sleep(0,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return str[0];
    }

    /**
     * Returns a specific GamePackage with 10 questions.
     * @param id Gamepackage ID
     * @return String
     */
    public String getGamePackage(String id){
        String[] str = {new String()};
        threadPool.execute(() ->
                str[0] =db.getGamePackage(id));
        try {
			Thread.sleep(0,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

        String[] res = new String[1];
        String finalUsername = username;
        String finalPassword = password;
        threadPool.execute(() ->
                res[0] =db.login(finalUsername, finalPassword));
        try {
			Thread.sleep(0,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res[0];
    }


    /**
     * Lets the user create an account with Username,Password and Email, returns result.
     * @param accountInfo Username and password
     * @return String
     */
    public String createAccount(String accountInfo){
       //Separate Username, Password and Email
        String username,password,email;
        username=accountInfo.substring(0,accountInfo.indexOf('&'));
        password=accountInfo.substring(accountInfo.indexOf('&')+1,accountInfo.length());
        
        email=password.substring(password.indexOf('&')+1,password.length());
        password=password.substring(0,password.indexOf('&'));
        
        username=username.substring(username.indexOf("=")+1,username.length());
        password=password.substring(password.indexOf("=")+1,password.length());
        email=email.substring(email.indexOf("=")+1,email.length());

        System.out.println("Create account-User:"+username+" Password:"+password+" Email:"+email);

        String[] res = new String[1];
        String finalUsername = username;
        String finalPassword = password;
        String finalEmail=email;
        threadPool.execute(() ->
                res[0] =db.createAccount(finalUsername, finalPassword,finalEmail));
        try {
			Thread.sleep(0,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res[0];
    }

    /**
     * Return HighScore.
     * @return String
     */
    public String getHighScore(){
        String[] str = {new String()};
        threadPool.execute(() ->
                str[0] =db.getHighScore());
        try {
			Thread.sleep(0,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return str[0];
    }

    /**
     * Set HighScore
     * str = s=score&user=username
     * @param str players score & username
     * @return String
     */
    public String setHighScore(String str){
        //Split Score and Username
        String username,score;

        score=str.substring(0,str.indexOf('&'));
        username=str.substring(str.indexOf('&')+1,str.length());

        score=score.substring(score.indexOf("=")+1,score.length());
        username=username.substring(username.indexOf("=")+1,username.length());
       

        System.out.println("SetHighscore-Score:"+score+" User:"+username);

        String[] res = {new String()};
        String finalUsername = username;
        String finalScore = score;
        threadPool.execute(() ->
                res[0] = db.setHighScore(finalScore, finalUsername));
        try {
			Thread.sleep(0,1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return res[0];
    }

    //Main method for testing. 
    public static void main(String [] args){
        ClientHandler c=new ClientHandler();
        c.setHighScore("s=105&user=bambismurfen");
        c.login("username=bambismurfen&pw=Test132Test");
        c.createAccount("username=bambismurfen&pw=Test132Test&email=bambismurfen@gmail.com");
    }
}
