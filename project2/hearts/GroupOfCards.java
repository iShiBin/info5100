package hearts;

import java.util.*;

public class GroupOfCards {
  protected List<Card> cards;
  
  //this filed actually useless
  protected int currentSize=0;
  
  protected GroupOfCards(){}
  
  public GroupOfCards(int num){
    this.cards=new ArrayList<>(num);
    this.currentSize=num;
  }
  
  public int getCurrentSize(){
    return this.cards.size();
  }
  
  public Card getCard(int i){
    return cards.get(i);
  }
  
  public void addCard(Card card){
    this.cards.add(card);
  }
  
  public Card removeCard(int index){
    Card c=cards.get(index);
    cards.remove(index);
    return c;
  }
  
  public void display(){
    System.out.println(this);
    
    int index=this.currentSize/2;
    System.out.println("Now, remove card "+index);
    System.out.println("Removed cards: "+this.removeCard(index));
    
    System.out.println(this);
  }
  
  public String toString(){
    StringBuilder builder=new StringBuilder();
    for(Card card:cards){
      builder.append(card+"\n");
    }
    return builder.toString();
  }
}
