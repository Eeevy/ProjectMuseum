package main.java.database;

/**
 * The purpose with this class is to request data from the database using SQL-requests and then convert the 
 * response to JSON and return each request as a JSON formatted String.
 * 
 * OBS: Is only implemented as a STUB!
 * 
 * @author Gurkpatrullen / Andreas Andersson, David Isberg, Emma Shakespeare, Evelyn Gustavsson
 *
 */
public class DBSQL {

	/**
	 * Get categories and gamepackage titles with a special language
	 * @param param language
	 * @return String
	 */
    public String getCategories(String param){
        return "categories "+param;
    }

	/**
	 * Get Gamepackage, returns a specific gamepackage from a specific ID.
	 * @param id Gamepackage ID
	 * @return String
	 */
    public String getGamePackage(String id){
    	
        return "gamePackage"+id;
    }

    /**
     * Confirm correct account information. Return "success/Failure".
     * @param username Username
     * @param password Password
     * @return String
     */
    public String login(String username, String password){
        return username + ", " + password;
    }


    /**
     * Create an account with Username, Password and E-mail. Return "Success/Failure"
     * @param username Username
     * @param password Password
     * @param email Email
     * @return String
     */
    public String createAccount(String username,String password, String email){//Parameter +E-mail?

        return username + ", "+ password+", "+email;
    }

    /**
     * Get Highscore list.
     * @return String
     */
    public String getHighScore(){

        return "highscore";
    }

    /**
     * Set Highscore for a specific user, Return "Success/Failure".
     * @param score Score
     * @param username Username
     * @return String
     */
    public String setHighScore(String score,String username){

        return score + ", "+ username;
    }
}

