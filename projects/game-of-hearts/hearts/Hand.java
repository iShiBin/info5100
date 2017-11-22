package hearts;

import java.util.*;
import java.util.stream.*;

public class Hand extends GroupOfCards {
  //each player’s identification should remain constant after that player has been instantiated
  public final int NUM;
  private Suit shortest=Suit.Club;
  
  /**
   * Initialize NUM in the Hand constructor with a value equal to the constructor’s first parameter value.
   * The second parameter is the maximum number of cards the player will receive. 
   * Use it for the base-constructor call argument.  
   * @param playerNum
   * @param numberOfCards
   */
  public Hand(int playerNum, int numberOfCards){
    super(numberOfCards);
    this.NUM=playerNum;
  }
  
  /**
   * Sort current hand according to suit and number
   */
  public void sort(){
//    super.getCards().sort(Card.COMPARATOR_NUM.reversed());
    super.getCards().sort((c1,c2)->(13*c2.getSuit().ordinal()+c2.getNum()) - (13*c1.getSuit().ordinal()+c1.getNum()));
  }
  
  /**
   * Use the setShortest method to determine the best suit to play early in the game,
   * to establish a void as quickly as possible. Start with shortest = clubs.
   * If the number of diamonds is less than or equal to the number of clubs, change shortest to diamonds.
   * If the number of spades is less than or equal to the shorter of those two, 
   * and your spades do not include Ace, King, or Queen, change shortest to spades. 
   * (Use the find method to see if you have an Ace, King, or Queen.)
   */
  public void setShortest() {
    long clubCounter = this.count(Suit.Club);
    long diamondCounter = this.count(Suit.Diamond);

    if (diamondCounter <= clubCounter)
      this.shortest = Suit.Diamond;
    
//    judge whether this hand has a card of Ace, King, or Queen in spade
    boolean hasBigSpade = this.suitStream(Suit.Spade).filter(card->card.compareTo(Card.QUEEN_OF_SPADES)>=0).count()>0;
    if(!hasBigSpade){
      long spadeCounter = this.suitStream(Suit.Spade).count();
      if(spadeCounter < Math.min(clubCounter, clubCounter)) this.shortest=Suit.Spade;
    }
  }
  
  public Suit getShortest(){
    return this.shortest;
  }
  
  /**
   * Find whether a card with specified suit and number existed in current hand.
   * @deprecated because it is super easy to find a card object in the list of cards.
   * @param num
   * @param suit
   * @return True if found; False if not found.
   */
  private boolean find(int num, Suit suit){
    Card target=new Card(num, suit);
    return super.getCards().contains(target);
  }
  
  /**
   * Use this method to find the highest card in your shortest suit to develop a void as fast as possible early in the game.
   * Also use it to select the highest heart to discard on somebody else's suit. 
   * Also use it when you are playing last and there are no bad cards in the trick.
   * Also use as the starting point in the findHighestBelow method and the starting point in findMiddleHigh method.  
   * @param suit
   * @return Return the card having the highest numerical value in the suit indicated by the parameter value.
   * If you have no cards in the suit, return -1.
   */
  private Card findHighest(Suit suit){
    return this.suitStream(suit).max(Card.COMPARATOR_NUM).orElse(null);
  }
  
  /**
   * @return The highest card in this hand regardless of the suit.
   */
  private Card findHighest(){
    return super.stream().max(Card.COMPARATOR_NUM).orElse(null);
  }
  
  /**
   * Use the findLowest(int suit) method to find the lowest club dealt, to start the game.
   * You’ll also need it in the rare situation when you have the lead, hearts have not been broken, 
   * and hearts are all you have left in your hand. 
   * @param suit
   * @return the lowest card of the input suit in hand or null if you have no cards in that suit.
   */
  public Card findLowest(Suit suit){
    return this.suitStream(suit).min(Card.COMPARATOR_NUM).orElse(null);
  }
  
  /**
   * Use the findLowest(Game game) method when leading, after you have developed your void. 
   * Return the lowest number in your hand, but not a heart until after hearts have been broken. 
   * If hearts have not been broken and all you have left is hearts, return null
   * @param game
   * @return
   */
  private Card findLowest(Game game){
    Card card=super.stream().min(Card.COMPARATOR_NUM).orElse(null);
    if(!game.getHearts() && card.hasSameSuit(Suit.Heart)){
      card=super.stream().filter(c->!c.hasSameSuit(Suit.Heart)).min(Card.COMPARATOR_NUM).orElse(card);
    }
    return card;
  }
  
  /**
   * To count the number of cards in a specific suit
   * @param suit
   * @return
   */
  private long count(Suit suit){
    return super.suitStream(suit).count();
  }
  
  /**
   * @param suit
   * @return the highest card in the suit led when there are no bad cards in the trick.
   * If this card is the queen of spades, however, and you have another spade, return the highest card you have below your queen.
   */
  private Card findLastHigh(Suit suit){
    Card lastHigh=this.findHighest(suit);
    if(lastHigh!=null && lastHigh.equals(Card.QUEEN_OF_SPADES)){
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
   * @return the index of that card, but if the <b>next card is a different suit</b>, terminate the search, and return null.
   */
  private Card findHighestBelow(Card winningCard){
    return this.suitStream(winningCard).filter(card->card.getNum()<winningCard.getNum()).max(Card.COMPARATOR_NUM).orElse(null);
  }

  /**
   * Use the findMiddleHigh method if the findHighestBelow method returned null.
   * @param game
   * @param suit
   * @return highest card in the suit;
   * but if the suit is spades and the queen of spades has not been played yet, 
   * try to find a spade that is not higher than the Jack of spades.
   */
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
    
    if(trick.getCards().isEmpty()){//first hand
      card=this.firstHand(game, trick);
    }else if(trick.getCards().size()==game.PLAYERS-1){ //last hand
      card=this.lastHand(game, trick);
    }else{//middle hand
      card=this.middleHand(game, trick);
    }
    if(card==null) throw GameException.noCardFound;
    else super.removeCard(card);
    return card;
  }
  
  /**
   * If the current size of the trick is zero (you are the first hand), play highest card in your shortest suit, 
   * but if this suit is a void let index equal the lowest card in any suit (the lowest of all)
   * @param game
   * @param trick
   * @return
   */
  private Card firstHand(Game game, Trick trick){
    Card card=this.findHighest(shortest);
    if(card==null) card=this.findLowest(game);
    if(card==null) throw GameException.noCardFound;
    return card;
  }
  
  /**
   * If the current size of the trick is one less than the total number of players (you are the last hand),
   * and if the trick does not have the queen of spades or any hearts, pay the highest card to try to win.
   * @param game
   * @param trick
   * @return
   */
  private Card lastHand(Game game, Trick trick){
    Card card=null;
    boolean isRisky=trick.isHearts() || trick.isQueen();
    if(!isRisky) {
      card = this.findLastHigh((trick.getCard(0).getSuit())); // play the highest and try to win this trick
      if(card==null) card=this.developVoid(game, trick);
    }
    else{
      card=this.findHighestBelow(trick.getWinningCard()); //play a smaller than the current winning card
      if(card==null) card = this.findMiddleHigh(game, trick.getWinningCard().getSuit()); // if cannot win this trick
      if(card==null) card = this.developVoid(game, trick); //if cannot follow the suit, just try to develop a void suit
    }
    return card;
  }
  /**
   * Play a slight low than the winning card.
   * If I don't have it, just play the lowest in this suit.
   * If I could not follow this suit, just develop a void.
   * @param game
   * @param trick
   * @return
   */
  private Card middleHand(Game game, Trick trick){
    Card card=null;
    if(this.suitStream(trick.getWinningCard()).count()!=0){
      card=this.findHighestBelow(trick.getWinningCard());
      if(card==null) card=this.findLowestAbove(trick.getWinningCard());
      if(card==null) card=this.findLowest(game);
    }else{
      card = this.developVoid(game, trick);
    }
    return card;
  }
  
  /**
   * Get a card to develop a void suit at the best, which will be used in case of:
   * 1. cannot follow the suit
   * 2. lead a trick
   * @param game
   * @param trick
   * @return the highest card in the shortest suit
   */
  private Card developVoid(Game game, Trick trick){
    Card card=this.findHighest(this.getShortest());
    return card==null?this.findHighest():card;
  }
  
  public String toString(){
    return super.getCards().toString();
  }
}
