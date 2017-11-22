/**
 * Represent numbers and face-card values by integers ranging from 2 to 14, with 14 being the ace. 
 * Represent suits by integers as follows: 0 = clubs; 1 = diamonds; 2 = hearts; 3 = spades. 
 * Write a nice display method that uses switch statements to convert suit integers to the words, “clubs,” “diamonds,” and so on, 
 * and the numbers 11…14 to the words “Jack,” “Queen,” and so on. Use the display method to test this class.
 */

package hearts;

import java.util.Comparator;

public class Card implements Comparable<Card>{
  private int num;
  private Suit suit;
  public static final Card QUEEN_OF_SPADES=new Card(12,Suit.Spade);
  public static final Card TWO_OF_CLUB=new Card(2,Suit.Club);
  public static final Comparator<Card> COMPARATOR_NUM=Comparator.comparing(Card::getNum);
  
  public Card(int num, Suit suit){
    this.num=num;
    this.suit=suit;
  }
  
  public int getNum(){return num;}
  public Suit getSuit() {return suit;}
  
  public void display(){
    System.out.println(this.toString());
  }
  
  @Override
  public String toString(){
    String cardString = new String();
    if(num>=2 && num<=10){
      cardString=String.valueOf(num);
    }else if(num>=11 && num<=14) {
      switch (num){
      case 11:cardString="J"; break;
      case 12:cardString="Q"; break;
      case 13:cardString="K"; break;
      case 14:cardString="A"; break;
      }
    }else{
      cardString="Invalid "+ suit.toString() + num;
    }
    return suit.toString()+cardString;
  }
  
  public boolean hasSameSuit(Card card){
    return suit.equals(card.suit);
  }
  
  public boolean hasSameSuit(Suit suit){
    return this.suit.equals(suit);
  }

  @Override
  public int compareTo(Card card) {
    assert this.hasSameSuit(card);
    return this.num-card.num;
  }
  
  public boolean isBiggerThan(Card card){
    assert this.hasSameSuit(card);
    return this.compareTo(card)>0;
  }
  
  public boolean isSmallerThan(Card card){
    return !isBiggerThan(card);
  }
  
  /**
   * To support list.remove(card)
   */
  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null || !object.getClass().getName().equals(this.getClass().getName()))
      return false;

    Card card = (Card) object;
    return this.hasSameSuit(card) && this.compareTo(card) == 0;
  }
}