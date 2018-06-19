/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapplication2;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

/**
 *
 * @author Austin M. Patton
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    public Label date;
    
    @FXML
    public ChoiceBox searchSettingPicker;//ChoiceBox to choose between seraching for a name or an id
    
    @FXML
    public Label pokemonName;
    
    @FXML
    public Label pokemonID;
    
    @FXML
    public Label pokemonHeight;
    
    @FXML
    public Label pokemonWeight;
    
    @FXML
    public Label pokemonHP;
    
    @FXML
    public Label pokemonAttack;
    
    @FXML
    public Label pokemonDefense;
    
    @FXML 
    public Label pokemonSpecialAttack;
    
    @FXML
    public Label pokemonSpecialDefense;
    
    @FXML Label pokemonSpeed;
    
    @FXML
    public Label pokemonTypes;
    
    @FXML
    public TextField searchBar;//TextField that is used as a search bar for the pokedex
    
    @FXML
    public ImageView pokemonSpriteView;//ImageView that displays the regular version of the pokemon
    
    @FXML
    public ImageView pokemonShinySpriteView;//ImageView that displays the shiny version of the pokemon
    
    
    private final String USER_AGENT = "Mozilla/5.0";
    
    private LocalDateTime timePoint;
    private String pokemon;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {

               /* try
                {
                ApiConfig config = new ApiConfig().setKey("RGAPI-65112403-076b-4f7a-bf57-1ae2fa894124");
		RiotApi api = new RiotApi(config);
                Summoner summoner = api.getSummonerByName(Platform.NA, "tryndamere");
                System.out.print(summoner);
                }
                catch(RiotApiException E)
                {
                    System.out.println("RiotApiException occured: " + E.getMessage());
                    //throw E;
                }*/
        
       /* try{
            URL url = new URL("http://example.com");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException E) {
            System.out.println("Caught MalformedURLException:" + E.getMessage());
        } catch (IOException E) {
            System.out.println("Caught IOException: " + E.getMessage());
        }*/
         
    }
    
    @FXML
    private void sendGet() {//sends a get request to the API which returns the serached pokemon's information

                //checks to see if the user selected a valid search category (Name, ID) instead of "Search pokemon by..."
                if (searchSettingPicker.getSelectionModel().getSelectedItem().equals("Search pokemon by...")){
                        JOptionPane.showMessageDialog(null, "Please select a valid search category.");  
                        return;
                }
                
                //checks to make sure the user doesnt search with an empty search bar
                if (searchBar.getText().trim().isEmpty() == true){
                    JOptionPane.showMessageDialog(null, "The search bar is empty. Please identify what you would like to search by.");  
                        return;
                }
        
		String url = "https://pokeapi.co/api/v2/";
                String urlTwo = "https://pokeapi.co/api/v2/pokemon/treecko/";//can sub this url in to pull up a pokemon without any user input
                
                url = url + "pokemon/" + searchBar.getText().toLowerCase() + "/";//Adds the correct parts of the path to enable the user to request the API for the pokemons data.
                                                                      //Also, the API doesn't allow lowercase letters as input, which is why the text was converted to lower case.
                if(searchSettingPicker.getSelectionModel().getSelectedItem().equals("Name")){
                    System.out.print("Searching for pokemon by name with this url:" + url);
                } else{
                    System.out.print("Searching for pokemon by ID # with this url:" + url);
                }
		
                
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

                System.out.println(treecko.types[0].type.name);//test message to see if the name was corretly loaded
                System.out.println(treecko.sprites.front_default);//test message to see if the image of the default sprite was loaded
         
                
               //All of the strings given by the API are given in all lowercase. That is why I am using the substring.toUpperCase method so I can capitalize the first letter
               //of each piece of string output
               treecko.name = treecko.name.substring(0, 1).toUpperCase() + treecko.name.substring(1);
               pokemonName.setText("Name: " + treecko.name);
               
               pokemonID.setText("ID #: " + Integer.toString(treecko.id));
               pokemonHeight.setText("Height: " + Integer.toString(treecko.height)+ " ft");
               pokemonWeight.setText("Weight: " + Integer.toString(treecko.weight)+ " lbs");
               
               if(treecko.types.length == 1){
                treecko.types[0].type.name = treecko.types[0].type.name.substring(0, 1).toUpperCase() + treecko.types[0].type.name.substring(1);
               pokemonTypes.setText("Type: " + treecko.types[0].type.name);
               } else{//A pokemon can only have a maximum of two types, so if he doesnt have one type then it has to have two
               treecko.types[0].type.name = treecko.types[0].type.name.substring(0, 1).toUpperCase() + treecko.types[0].type.name.substring(1);
               treecko.types[1].type.name = treecko.types[0].type.name.substring(0, 1).toUpperCase() + treecko.types[1].type.name.substring(1);
               pokemonTypes.setText("Types: " + treecko.types[0].type.name + ", "+ treecko.types[1].type.name);
               }
 
               
                Image spriteImage = new Image(treecko.sprites.front_default);
                pokemonSpriteView.setImage(spriteImage);
                
                Image shinySpriteImage = new Image(treecko.sprites.front_shiny);
                pokemonShinySpriteView.setImage(shinySpriteImage);
                
                
                pokemonSpeed.setText("Speed: " + treecko.stats[0].base_stat);
                pokemonSpecialDefense.setText("Special Defense: " + treecko.stats[1].base_stat);
                pokemonSpecialAttack.setText("Special Attack: " + treecko.stats[2].base_stat);
                pokemonDefense.setText("Defense: " + treecko.stats[3].base_stat);
                pokemonAttack.setText("Attack: " + treecko.stats[4].base_stat);
                pokemonHP.setText("HP: " + treecko.stats[5].base_stat);
                
                System.out.println(treecko.stats[5].stat.name);//can change these print statements to whatever position in the array to verify    
                System.out.println(treecko.stats[5].base_stat);//that the correct category matches with the correct number (currently verifies HP value)
                
               
	}
    /**
     * @param args the command line arguments
     * 
     */
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        timePoint = LocalDateTime.now();
        //Month month = timePoint.getMonth(); Returns value in all caps
        int day = timePoint.getDayOfMonth();
        int year = timePoint.getYear();
        //DayOfWeek dayOfWeek = timePoint.getDayOfWeek(); Returns value in all caps.
        
        String monthString = timePoint.getMonth().toString();// Doing it this way instead gives you a modifiable string
        String dayOfWeekString = timePoint.getDayOfWeek().toString();//which allows you to change the capitalization of the words
        
        monthString = monthString.substring(0, 1).toUpperCase() + monthString.substring(1).toLowerCase();
        dayOfWeekString = dayOfWeekString.substring(0,1).toUpperCase() + dayOfWeekString.substring(1).toLowerCase();
        
         
        
        date.setText(dayOfWeekString + ", " + monthString + " " + day + ", " + year);
        
    searchSettingPicker.getItems().removeAll(searchSettingPicker.getItems());
    searchSettingPicker.getItems().addAll("Search pokemon by...", "Name", "ID");
    searchSettingPicker.getSelectionModel().select("Search pokemon by...");
    
    
    }    
    
}
