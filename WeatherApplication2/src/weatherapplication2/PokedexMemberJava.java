/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapplication2;

/**
 *
 * @author Austin M. Patton
 */
public class PokedexMemberJava {
    int id;
    String name;
    int height;
    int weight;
    Sprites sprites;
    Stats[] stats;
    Types[] types;
    
    public PokedexMemberJava(int id, String name, int height, int weight){
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
    
    
}
