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
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public ChoiceBox searchSettingPicker;
    
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
    public TextField searchBar;
    
    @FXML
    public ImageView pokemonSpriteView;
    
    @FXML
    public ImageView pokemonShinySpriteView;
    
    private final String USER_AGENT = "Mozilla/5.0";
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
        
       
        
        
        
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
    private void sendGet() throws Exception {

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
                String urlTwo = "https://pokeapi.co/api/v2/pokemon/treecko/";
                
                url = url + "pokemon/" + searchBar.getText() + "/";
                
                if(searchSettingPicker.getSelectionModel().getSelectedItem().equals("Name")){
                    System.out.print("Searching for pokemon by name with this url:" + url);
                } else{
                    System.out.print("Searching for pokemon by ID # with this url:" + url);
                }
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
                String pokemon = response.toString();
                System.out.println(pokemon);
                
                
                JsonParser parser = new JsonParser();
                JsonObject jsonPokemon = (JsonObject) parser.parse(pokemon);
                System.out.println(jsonPokemon);
                
               
               
             
                Gson gson = new Gson();
                PokedexMemberJava treecko = gson.fromJson(jsonPokemon, PokedexMemberJava.class);

                System.out.println(treecko.types[0].type.name);
                System.out.println(treecko.sprites.front_default);
                
                
               pokemonName.setText("Name: " + treecko.name);
               pokemonID.setText("ID #: " + Integer.toString(treecko.id));
               pokemonHeight.setText("Height: " + Integer.toString(treecko.height)+ " ft");
               pokemonWeight.setText("Weight: " + Integer.toString(treecko.weight)+ " lbs");
               
               if(treecko.types.length == 1){
               pokemonTypes.setText("Type: " + treecko.types[0].type.name);
               } else{//A pokemon can only have a maximum of two types, so if he doesnt have one type then it has to have two
               pokemonTypes.setText("Types: " + treecko.types[0].type.name + " "+ treecko.types[1].type.name);
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
    searchSettingPicker.getItems().removeAll(searchSettingPicker.getItems());
    searchSettingPicker.getItems().addAll("Search pokemon by...", "Name", "ID");
    searchSettingPicker.getSelectionModel().select("Search pokemon by...");
    }    
    
}
