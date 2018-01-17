package hearts;

import java.util.*;

public class Game {
  public final int PLAYERS;
  private Deck deck;
  private List<Hand> players;
  private List<Trick> tricks;
  private int numberOfTricks = 0 ;
  private boolean hearts=false;
  private boolean queenOfSpades = false;
  
  private static boolean DEBUG;
  
  /**
   * Initialize PLAYERS in the Game constructor with a value equal to the constructor’s parameter value.
   * Instantiate a Hand array with constructor parameter equal to the number of players.
   * It should instantiate individual Hand objects for each player, with player identification number and maximum number of cards in a player’s hand as constructor arguments. 
   * It should also instantiate a Trick array with total number of tricks as the constructor argument, but it should not populate this array with any individual tricks.  
   * @param numberOfPlayers
   */
  public Game(int numberOfPlayers){
    this(numberOfPlayers, false);
  }
  
  /**
   * 
   * @param numberOfPlayers
   * @param debug
   */
  public Game(int numberOfPlayers, boolean debug){
    
    //the player number cannot bigger than a deck of card
    if(numberOfPlayers>Deck.TOTAL_CARDS)
      throw GameException.exceedMaxPlayers;
    
    this.PLAYERS=numberOfPlayers;
    this.players=new ArrayList<>(PLAYERS);
    for(int i=0;i<PLAYERS;i++){
      this.players.add(new Hand(this.PLAYERS, Deck.TOTAL_CARDS/this.PLAYERS));
    }
    this.tricks=new ArrayList<>(Deck.TOTAL_CARDS/PLAYERS);
    Game.DEBUG = debug;
  }
  

  public int getNumberOfTricks() {
    return numberOfTricks;
  }


  public boolean getHearts() {
    return hearts;
  }


  public boolean getQueenOfSpades() {
    return queenOfSpades;
  }
  
  public void playAGame(){
    this.deck=new Deck();
    this.deck.shuffle();
    
    int[] startPlayerAndCardNum=this.dealCards();
    int playerNum=startPlayerAndCardNum[0];
    Card card=new Card(startPlayerAndCardNum[1], Suit.Club);
    
    this.displayUndeltCards();
    this.displayPlayers();
    
    while(this.numberOfTricks < Deck.TOTAL_CARDS/this.PLAYERS){
      Trick trick=new Trick(this.PLAYERS);
      this.tricks.add(trick);
      
      /*
       *  If it’s the first trick, set index equal to the index of the lowest club 
       *  in the hand of the player having the lowest club, and set card equal to a reference to that card. 
       *  Have that player remove that card, and update the trick.
       */
      if (this.numberOfTricks != 0) {
//        If it’s not the first trick, set card equal to the value returned by player.playACard.
        playerNum = this.tricks.get(numberOfTricks - 1).getWinner();
        card = this.players.get(playerNum).playACard(this, trick);
      }

//    Display the player number and card value for this first play of the trick.
      System.out.println("player " + playerNum + " " + card + (Game.DEBUG?this.players.get(playerNum):""));
      trick.addCard(card);
      trick.update(playerNum, card); //set the player number to the winner of the trick in flight
      this.updateHeartsAndQueen(card);
      this.players.get(playerNum).getCards().remove(card);
      /*
       * Then loop through all remaining players, and for each such player, increment the player number, 
       * using % PLAYERS to count in a circle, assign the value returned by playACard to card, 
       * and display the player number and card value. 
       */
      int startPlayer=playerNum;
      while((++playerNum % this.PLAYERS)!=startPlayer){
        playerNum%=this.PLAYERS;
        card = this.players.get(playerNum).playACard(this, trick);
        System.out.println("player " + playerNum + " " + card + (Game.DEBUG?this.players.get(playerNum):""));
        trick.addCard(card);
        trick.update(playerNum, card); //set the player number to the winner of the trick in flight
        this.updateHeartsAndQueen(card);
        this.players.get(playerNum).getCards().remove(card);
      }
      
      if(Game.DEBUG) System.out.println(trick);
      System.out.println();
      this.numberOfTricks++;
    }
    
//    After all the tricks are done, print each player’s number and score, using a call to computePoints. 
    for(int i=0;i<this.PLAYERS;i++){
      System.out.printf("Player %d score= %d\n",i, computePoints(i));
    }
  }
  
  public void updateHeartsAndQueen(Card card){
    if(!this.hearts && (card.hasSameSuit(Suit.Heart))) {
      this.hearts=true;
      System.out.println("hearts is now broken...");
    }else if(card==Card.QUEEN_OF_SPADES){
      this.queenOfSpades=true;
    }
    
  }
  
  /**
   * Display and remove the undelt cards in the deck.
   */
  private void displayUndeltCards(){
    System.out.println("undelt cards: "+this.deck.getCards() +"\n");
    this.deck.getCards().clear();
  }
  
  /**
   * Deal cards to all the players 
   * @return the player number who has the lowest clubs; and the lowest available club card
   */
  private int[] dealCards(){
    int startPlayer=-1, startCardNum=0;
    for(int i=0;i<this.PLAYERS;i++){
      int numberOfCardInHand = Deck.TOTAL_CARDS/this.PLAYERS;
      while(numberOfCardInHand-->0){
        Card card=this.deck.dealCard();
        this.players.get(i).addCard(card);
        if(startCardNum==0 || (card.hasSameSuit(Suit.Club) && card.getNum()<startCardNum)){
          startCardNum=card.getNum();
          startPlayer=i;
        }
      }
      this.players.get(i).sort();
      this.players.get(i).setShortest();
    }
    if(startPlayer==-1 || startCardNum==0) 
      throw new GameException("Error! Cannot start the game due: no valid player or start card.");
    return new int[]{startPlayer, startCardNum};
  }
  
  private void displayPlayers(){
    for(int i=0;i<this.PLAYERS;i++){
      Hand currentPlayer=this.players.get(i);
      System.out.println("player "+i+" shortest="+currentPlayer.getShortest()+currentPlayer);
    }
    System.out.println();
  }
  
  private int computePoints(int playerNum) {
    return this.tricks.stream().filter(t->t.getWinner()==playerNum).mapToInt(t->t.getPoints()).sum();
  }
}
