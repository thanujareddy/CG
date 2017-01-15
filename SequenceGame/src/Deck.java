/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.Collections;
public class Deck {

     ArrayList <Card> deck = new ArrayList<Card>();   
    public Deck(){
        for(int i=0;i<4;i++){
           for(int j=0;j<13;j++){
               deck.add(new Card(i,j));
           }
        }
    }
    public void shuffle(){
        Collections.shuffle(deck);  
    }
    public void displayCards(){
        for(int i=0;i<deck.size();i++){
            if(i%13 == 0 && i!=0){
                System.out.println();
            }
            System.out.print(deck.get(i) + ", ");
        }
    }
}

