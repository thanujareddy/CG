/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Card {
    private int suit, value;
    private String[] cardSuit = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private String[] cardValue = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public Card(int cSuit, int cValue)
    {
        suit = cSuit; 
        value = cValue;
    }

    public String toString()
    {
        String cardInfo = cardValue[value] + " of " + cardSuit[suit];

        return cardInfo;
    }
    public int getValue(){
        return value+1;
    }
    public String getImage(){
        String imageName = cardValue[value] + cardSuit[suit] + ".png";
        return imageName;
    }

}
