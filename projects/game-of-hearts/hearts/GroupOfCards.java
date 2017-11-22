package hearts;

import java.util.*;
import java.util.stream.Stream;

public class GroupOfCards {
  private List<Card> cards;
  
  // useless when using a list to store the cards, so i won't update the value.
  @Deprecated
  private int currentSize=0;
  
  protected GroupOfCards(){}
  
  /**
   * The constructor’s parameter should establish the size of the card array.
   * @param num
   */
  public GroupOfCards(int num){
    this.cards=new ArrayList<>(num);
  }
  
  @Deprecated
  public int getCurrentSize(){
    return this.cards.size();
  }
  
  public Card getCard(int i){
    return cards.get(i);
  }
  
  public List<Card> getCards(){
    return this.cards;
  }
  
  /**
   * The addCard method should increment currentSize after adding the input card to the end of the currently filled part of the cards array. 
   * Note: No need to increase the currentSize in case of using a list instead of [] 
   * @param card
   */
  public void addCard(Card card){
    this.cards.add(card);
  }
  
  /**
   * Add a card to a specific location in the list.
   * @param index
   * @param card
   */
  public void addCard(int index, Card card){
    this.cards.add(index, card);
  }
  
  public Card removeCard(int index){
    Card c=cards.get(index);
    cards.remove(index);
    return c;
  }
  
  public boolean removeCard(Card card){
    return this.cards.remove(card);
  }
  
  /**
   * Get the stream of this card group having the same suite as the input parameter
   * @param suit
   * @return a list of cards in my hand having the same suite as the input parameter suit;
   * null if such list does not exist
   */
  protected Stream<Card> suitStream(Suit suit){
    return this.cards.stream().filter(card->card.hasSameSuit(suit));
  }
  
  protected Stream<Card> suitStream(Card card){
    return this.suitStream(card.getSuit());
  }
  
  protected Stream<Card> stream(){
    return this.cards.stream();
  }
  
  public String toString(){
    StringBuilder builder=new StringBuilder();
    for(Card card:cards){
      builder.append(card+"\n");
    }
    return builder.toString();
  }

  
  public void display(){
    System.out.println(this);
    
    //just try to remove the first card in the list
    int index=0;
    System.out.println("Now, remove card "+index);
    System.out.println("Removed cards: "+this.removeCard(index));
    
    System.out.println(this);
  }
}
