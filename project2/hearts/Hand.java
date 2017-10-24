package hearts;

import java.util.*;
import java.util.stream.Collectors;

public class Hand extends GroupOfCards {
  public final int NUM;
  private int shortest=0;
  
  public Hand(int playerNum, int numberOfCards){
    super(numberOfCards);
    this.NUM=playerNum;
  }
  
  public void sort(){
    this.cards.sort((c1,c2)->13*c2.getSuit().ordinal()+c2.getNum() - 13*c1.getSuit().ordinal()+c1.getNum());
  }
  
  public void setShortest(){
    
  }
  
  public int getShortest(){
    return this.shortest;
  }
  
//  private int findLowest(Game game){
//    
//  }
  
  private int count(Suit suit){
    return this.getSuitList(suit).size();
  }
  
  //to test the index method of constructing a new card
  private int find(int num, Suit suit){
    for(int i=0;i<this.cards.size();i++){
      if(cards.get(i).getNum()==num && cards.get(i).getSuit().equals(suit))
        return i;
    }
    return -1;
  }
  
  //todo: test the hashCode function
  private int find(Card card){
    return this.cards.indexOf(card);
  }
  
  /**
   * Use this method to find the highest card in your shortest suit to develop a void as fast as possible early in the game.
   * Also use it to select the highest heart to discard on somebody else's suit. 
   * Also use it when you are playing last and there are no bad cards in the trick.
   * Also use as the starting point in the findHighestBelow method and the starting point in findMiddleHigh method.  
   * @param suit
   * @return Return the index of the card having the highest numerical value in the suit indicated by the parameter value.
   * If you have no cards in the suit, return -1.
   */
  private int findHighest(Suit suit){
    Optional<Card> highest=this.getSuitList(suit).stream().max(Comparator.comparing(Card::getNum));
    if(highest!=null) return this.cards.indexOf(highest);
    else return -1;
  }
  
//  private int findLowest(Game game){
//    
//  }
  
  /**
   * @param suit
   * @return the highest card in the suit led when there are no bad cards in the trick.
   * If this card is the queen of spades, however, and you have another spade, return the highest card you have below your queen.  
   */
  private int findLastHigh(Suit suit){
    Card lastHigh=this.cards.get(this.findHighest(suit));
    if(lastHigh.equals(Card.QUEEN_OF_SPADES)){
      List<Card> cardList=this.getSuitList(suit).stream().filter(card->!card.equals(Card.QUEEN_OF_SPADES)).collect(Collectors.toList());
      if(cardList.size()>1){
        Optional<Card> anotherLastHigh=cardList.stream().max(Comparator.comparing(Card::getNum));
        return this.cards.indexOf(anotherLastHigh);
      }
    }
    return this.cards.indexOf(lastHigh);
  }
  
  /**
   * Use the findHighestBelow method when you are neither the first nor last player in a particular trick’s play sequence, and you are able to follow suit.
   * @param winningCard
   * Given a reference to the current winning card as the parameter value, search through the cards in your hand whose suit equals the winning card’s suit
   * until you find the <b>first one</b> having a number less than the winning card’s number
   * @return the index of that card, but if the <b>next card is a different suit</b>, terminate the search, and return -1. //todo
   */
  private int findHighestBelow(Card winningCard){
    List<Card> list=this.getSuitList(winningCard.getSuit());
    if(list!=null || list.isEmpty()) return -1;
    else if (list.size()==1) return 0;
    else{
      Optional <Card> c=this.getSuitList(winningCard.getSuit()).stream().filter(card->card.getNum()<winningCard.getNum()).max(Comparator.comparing(Card::getNum));
      if(c==null) c=this.getSuitList(winningCard.getSuit()).stream().filter(card->card.getNum()>winningCard.getNum()).max(Comparator.comparing(Card::getNum));
      return this.cards.indexOf(c);
    }
  }
  
  /**
   * Get a list of cards in my hand having the same suite as the input parameter suit
   * @param suit
   * @return a list of cards in my hand having the same suite as the input parameter suit;
   * null if such list does not exist
   */
  private List<Card> getSuitList(Suit suit){
    List<Card> list=this.cards.stream().filter(card->card.hasSameSuit(suit)).collect(Collectors.toList());
    return list;
  }
  
  //todo
  private int findMiddleHigh(Game game, Suit suit){
    
    return -1;
  }
  
  public Card playACard(Game game, Trick trick){
    
  }
  
  public String toString(){
    return this.cards.toString();
  }
}
