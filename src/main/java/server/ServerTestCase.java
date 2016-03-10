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

public class ServerTestCase {
	private ClientHandler ch;
//	private Server server=new Server();

	
	@Test
	public void testGetCategories(){
		ch = new ClientHandler();
		assertEquals("categories" ,ch.getCategories());
	}
	
	@Test
	public void testGetGamePackageLowBVAGood1(){
		ch = new ClientHandler();
		assertEquals("gamePackage1", ch.getGamePackage("1"));
	}
	
	@Test
	public void testGetGamePackageLowBVAGood2(){
		ch = new ClientHandler();
		assertEquals("gamePackage10", ch.getGamePackage("10"));
	}
	
	@Test
	public void testGetGamePackageLowBVABad1(){
		ch = new ClientHandler();
		assertNotSame("gamePackage0", ch.getGamePackage("0"));
	}
	
	@Test
	public void testGetGamePackageLowBVABad2(){
		ch = new ClientHandler();
		assertNotSame("gamePackage11", ch.getGamePackage("11"));
	}
	
	@Test
	public void testLogin(){
		ch = new ClientHandler();
		assertEquals("Emma, bubbelgum", ch.login("username=Emma&pw=bubbelgum"));
		
	}
	
	@Test
	public void testCreateAccount(){
		ch = new ClientHandler();
		assertEquals("rosamunda, hajen", ch.createAccount("username=rosamunda&pw=hajen"));
	}
	
	@Test
	public void testGetHighScore(){
		ch = new ClientHandler();
		assertEquals("highscore", ch.getHighScore());
	}
	
	@Test 
	public void testSetHighScore(){
		ch = new ClientHandler();
		assertEquals("105, emmapemma", ch.setHighScore("s=105&user=emmapemma"));
		
	}
	
	
	
	
	//******Test methods in server.java **************************************
	
	@Test
	public void testServerGetHighScore() {
		//
		String json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/highscore");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "highscore");
	}
	
	@Test
	public void testServerSetHighScore() {
		//
		String json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/highscore/s=11&user=Emma");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "11, Emma");
	}
	
	@Test
	public void testServerCreateAccount() {
		//
		String json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/create-account/user=Emma&pw=blask");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "Emma, blask");
	}
	@Test
	public void testServerLogin() {
		//
		String json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/login/user=Emma&pw=blask");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "Emma, blask");
	}
	
	@Test
	public void testServerGetCategories() {
		//
		String json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/categories");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "categories");
	}
	
	@Test
	public void testServerGetGamePackage() {
		//
		String json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/categories/gamepackage/11");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "gamePackage11");
	}
	
	
	//Metoder
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static String readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	     // JSONObject json = new JSONObject(jsonText);
	      return jsonText;
	    } finally {
	      is.close();
	    }
	  }


}
