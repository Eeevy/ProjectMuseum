package main.java.database;

public class DBSQL {

    public String getCategories(){

        return "categories";
    }


    public String getGamePackage(String id){
    	
        return "gamePackage"+id;
    }


    public String login(String username, String password){
        return username + ", " + password;
    }



    public String createAccount(String username,String password){//Parameter +E-mail?

        return username + ", "+ password;
    }


    public String getHighScore(){

        return "highscore";
    }


    public String setHighScore(String score,String username){

        return score + ", "+ username;
    }
}

