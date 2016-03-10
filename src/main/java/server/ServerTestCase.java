package main.java.server;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This class contains testcases that runs via JUnit. Used for testing of Server.java / our Rest-API.
 * 
 * OBS: Don't forget to start Server.java before running any tests!
 * @author Gurkpatrullen / Andreas Andersson, David Isberg, Emma Shakespeare, Evelyn Gustavsson
 *
 */
public class ServerTestCase {
	
	@Test
	/**
	 * Get highscore
	 */
	public void testServerGetHighScore() {
		//
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/highscore");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "highscore");
	}
	
	@Test
	/**
	 * Set highscore
	 */
	public void testServerSetHighScore() {
		//
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/highscore/s=11&user=Emma");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "11, Emma");
	}
	
	@Test
	/**
	 * Create Account
	 */
	public void testServerCreateAccount() {
		
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/create-account/user=Emma&pw=blask&email=bambismurfen@gmail.com");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "Emma, blask, bambismurfen@gmail.com");
	}
	@Test
	/**
	 * Login
	 */
	public void testServerLogin() {
		
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/login/user=Emma&pw=blask");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "Emma, blask");
	}
	
	@Test
	/**
	 * Login as Guest
	 */
	public void testServerLoginGuest() {
	
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/login/user=Guest&pw=Guest");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "Guest, Guest");
	}
	
	@Test
	/**
	 * Get Categories
	 */
	public void testServerGetCategories() {
		//
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/categories/swe");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "categories swe");
	}
	
	@Test
	/**
	 * Get Gamepackage
	 */
	public void testServerGetGamePackage() {
		String json = null;
		try {
			json = readFromUrl("http://localhost:4567/categories/gamepackage/11");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "gamePackage11");
	}
	
	
	/**
	 * This method is used to save the return from json to String. 
	 * is used with readFromURL.
	 * @param rd -reader with input
	 * @return String
	 * @throws IOException
	 */
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	/**
	 * Establishes a connection to a specific URL and lets a Reader read the returning data. 
	 * Uses readAll() to save the data as a String.
	 * @param url -URL for request.
	 * @return String
	 * @throws IOException
	 * @throws JSONException
	 */
	  public static String readFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      return jsonText;
	    } finally {
	      is.close();
	    }
	  }


}
