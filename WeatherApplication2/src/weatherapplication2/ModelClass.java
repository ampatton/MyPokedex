/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapplication2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Austin M. Patton
 */
public class ModelClass {
    
    private final String USER_AGENT = "Mozilla/5.0";
     private String pokemon;
    
    public PokedexMemberJava sendGetRequest(String url){
                String urlTwo = "https://pokeapi.co/api/v2/pokemon/treecko/";//can sub this url in to pull up a pokemon without any user input
                
                         
                try{
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                //add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
                
                if(responseCode == 404){
                    JOptionPane.showMessageDialog(null, "It seems the Pokemon was not found! This could be caused by the Pokemon's name being misspelled.");
                }

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                
		System.out.println(response.toString());
                pokemon = response.toString();
                System.out.println(pokemon);
                } 
                catch (MalformedURLException E){
                    System.out.println("MalformedURLException occured when trying to create obj of class URL");
                     E.printStackTrace(System.err);
                } catch (ProtocolException E){
                    System.out.println("ProtocolException occured. This most likely happened when setting cons request method to GET");
                    E.printStackTrace(System.err);
                } catch(IOException E){
                    System.out.println("IOException occured.");
                    E.printStackTrace(System.err);
                }
		
                
                JsonParser parser = new JsonParser();
                JsonObject jsonPokemon = (JsonObject) parser.parse(pokemon);
                System.out.println(jsonPokemon);
                             
             
                Gson gson = new Gson();
                PokedexMemberJava treecko = gson.fromJson(jsonPokemon, PokedexMemberJava.class);
                
                return treecko;
}
    
    
}
