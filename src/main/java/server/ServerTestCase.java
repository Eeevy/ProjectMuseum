package main.java.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

	@BeforeClass
	public void test() {
		ClientHandler ch = new ClientHandler();
	}
	//Test methods in clientHandler
	
	@Test
	public void testGetCategories(){
		
	}
	
	@Test
	public void testGetGamePackage(){
		
	}
	
	@Test
	public void testLogin(){
		
	}
	
	@Test
	public void testCreateAccount(){
		
	}
	
	@Test
	public void testGetHighScore(){
		
	}
	
	@Test 
	public void testSetHighScore(){
		
	}
	
	
	
	
	//Test methods in server.java
	
	@Test
	public void testHighScore() {
		//
		JSONObject json = null;
		try {
			json = readJsonFromUrl("http://localhost:4567/highscore/s=11&user=Emma");
		}
		catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		String str=json.toString();
		assertEquals(str, "Emma 11");
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

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }


}
