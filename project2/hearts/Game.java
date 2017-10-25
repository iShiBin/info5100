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
  
  
  public Game(int numberOfPlayers){
    this.PLAYERS=numberOfPlayers;
    
    this.players=new ArrayList<>(numberOfPlayers);
    for(Hand h:players){
      h=new Hand(numberOfPlayers, deck.getCurrentSize()/numberOfPlayers);
    }
    
    this.tricks=new ArrayList<>(deck.getCurrentSize()/numberOfPlayers);
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
  
  public void palyAGame(){
    this.deck.shuffle();
    int cardsLeft=this.deck.getCurrentSize()%this.PLAYERS;
    
    int playerNum=-1;
    for(int i=0;i<this.PLAYERS;i++){
      Hand currentPlayer=this.players.get(i);
      System.out.println("player "+i+" shortest="+currentPlayer.getShortest());
      System.out.println(currentPlayer);
      System.out.println();
      
      for(Card c:currentPlayer.cards){
        if(c.equals(Card.TWO_OF_CLUB)) playerNum=i;
      }
    }
    
    while(this.numberOfTricks<this.tricks.size()){
      this.tricks.set(numberOfTricks, new Trick(this.PLAYERS));
      
      if(this.numberOfTricks==0){
        this.tricks.get(numberOfTricks).addCard(Card.TWO_OF_CLUB);
        this.players.get(playerNum).cards.remove(Card.TWO_OF_CLUB);
      }else{
        //todo
        int winnerOfLastTrick=this.tricks.get(numberOfTricks-1).getWinner();
        this.players.get(winnerOfLastTrick).playACard(this, this.tricks.get(numberOfTricks));
      }
      
      this.numberOfTricks++;
    }
  }
  
}
