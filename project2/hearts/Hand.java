package hearts;

import java.util.*;
import java.util.stream.*;

public class Hand extends GroupOfCards {
  public final int NUM;
  private Suit shortest=Suit.Club;
  
  public Hand(int playerNum, int numberOfCards){
    super(numberOfCards);
    this.NUM=playerNum;
  }
  
  public void sort(){
    this.cards.sort((c1,c2)->13*c2.getSuit().ordinal()+c2.getNum() - 13*c1.getSuit().ordinal()+c1.getNum());
  }
  
  public void setShortest() {
    long clubCounter = this.count(Suit.Club);
    long diamondCounter = this.count(Suit.Diamond);

    if (diamondCounter <= clubCounter)
      this.shortest = Suit.Diamond;
    
//    judge whether this hand has a card of Ace, King, or Queen in spade
    boolean hasBigSpade = this.suitStream(Suit.Spade).filter(card->card.compareTo(Card.QUEEN_OF_SPADES)>=0).count()>0;
    if(!hasBigSpade){
      long spadeCounter = this.suitStream(Suit.Spade).count();
      if(spadeCounter<Math.min(clubCounter, clubCounter)) this.shortest=Suit.Spade;
    }
  }
  
  public Suit getShortest(){
    return this.shortest;
  }
  
//  private int findLowest(Game game){
//    
//  }
  
  private long count(Suit suit){
    return this.suitStream(suit).count();
  }
  
  /**
   * Find whether a card with specified suit and number existed in current hand.
   * @param num
   * @param suit
   * @return True if found; False if not found.
   */
  private boolean find(int num, Suit suit){
    Card target=new Card(num, suit);
    return this.cards.contains(target);
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
  private Card findHighest(Suit suit){
    return this.suitStream(suit).max(Card.COMPARATOR_NUM).orElse(null);
  }
  
  private Card findHighest(){
    return this.cards.stream().max(Card.COMPARATOR_NUM).orElse(null);
  }
  
  /**
   * Find the lowest in the current suit.
   * @param suit
   * @return the lowest card of the input suit in hand
   */
  public Card findLowest(Suit suit){
    return this.suitStream(suit).min(Card.COMPARATOR_NUM).orElse(null);
  }
  
  private Card findLowest(){
    return this.cards.stream().min(Card.COMPARATOR_NUM).orElse(null);
  }
  
  
//  private int findLowest(Game game){
//    
//  }
  
  /**
   * @param suit
   * @return the highest card in the suit led when there are no bad cards in the trick.
   * If this card is the queen of spades, however, and you have another spade, return the highest card you have below your queen.
   * @todo: what if I don't have other spades?
   */
  private Card findLastHigh(Suit suit){
    Card lastHigh=this.findHighest(suit);
    if(lastHigh.equals(Card.QUEEN_OF_SPADES)){
      List<Card> cardList=this.suitStream(suit).filter(card->!card.equals(Card.QUEEN_OF_SPADES)).collect(Collectors.toList());
      if(cardList.size()>1){
        return cardList.stream().max(Card.COMPARATOR_NUM).orElse(null);
      }
    }
    return lastHigh;
  }
  
  /**
   * Use the findHighestBelow method when you are neither the first nor last player in a particular trick’s play sequence, and you are able to follow suit.
   * @param winningCard
   * Given a reference to the current winning card as the parameter value, search through the cards in your hand whose suit equals the winning card’s suit
   * until you find the <b>first one</b> having a number less than the winning card’s number
   * @return the index of that card, but if the <b>next card is a different suit</b>, terminate the search, and return null. //todo
   */
  private Card findHighestBelow(Card winningCard){
    return this.suitStream(winningCard).filter(card->card.getNum()<winningCard.getNum()).max(Card.COMPARATOR_NUM).orElse(null);
  }
  
  /**
   * Get a list of cards in my hand having the same suite as the input parameter suit
   * @param suit
   * @return a list of cards in my hand having the same suite as the input parameter suit;
   * null if such list does not exist
   */
  private Stream<Card> suitStream(Suit suit){
    return this.cards.stream().filter(card->card.hasSameSuit(suit));
  }
  
  //explain
  private Stream<Card> suitStream(Card card){
    return this.suitStream(card.getSuit());
  }
  
  private Card findMiddleHigh(Game game, Suit suit){
    Card card=this.findHighest(suit);
    
    if(suit.equals(Suit.Spade)) {
      boolean isQueenOfSpadesPlayes=game.getQueenOfSpades();
      if(!isQueenOfSpadesPlayes){
        card=this.findHighestBelow(Card.QUEEN_OF_SPADES);
      }
    }
    return card;
  }
  
  private Card findLowestAbove(Card card){
    return this.suitStream(card).filter(c->c.isBiggerThan(card)).min(Card.COMPARATOR_NUM).orElse(null);
  }
  
  public Card playACard(Game game, Trick trick){
    Card card=null;
    
    if(trick.getCurrentSize()==0){
      card=this.firstHand(game, trick);
    }else if(trick.getCurrentSize()==game.PLAYERS-1){
      card=this.lastHand(game, trick);
    }else{
      card=this.middleHand(game, trick);
    }
    this.cards.remove(card);
    return card;
  }
  
  private Card firstHand(Game game, Trick trick){
    Card card=this.findHighest(shortest);
    if(card==null) card=this.findLowest();
    return card;
  }
  
  private Card lastHand(Game game, Trick trick){
    boolean isRisky=trick.isHearts() || trick.isQueen();
    if(!isRisky) return this.findHighest(trick.getCard(0).getSuit());
    else{
      Card candidate=this.findHighestBelow(trick.getWinningCard());
      return candidate!=null?candidate:this.findHighest(trick.getWinningCard().getSuit());
    }
  }
  
  /**
   * choose the 
   * @param game
   * @param trick
   * @return
   */
  private Card middleHand(Game game, Trick trick){
    if(this.suitStream(trick.getWinningCard()).count()!=0){
      Card candidate=this.findHighestBelow(trick.getWinningCard());
      return candidate!=null?candidate:this.findLowest(trick.getWinningCard().getSuit());
    }else{
      return this.getVoidCard(game, trick);
    }
  }
  
  /**
   * 
   * @param game
   * @param trick
   * @return
   */
  private Card getVoidCard(Game game, Trick trick){
    Card candidate=this.findHighest(this.getShortest());
    return candidate==null?this.findHighest():candidate;
  }
  
  public String toString(){
    return this.cards.toString();
  }
}
