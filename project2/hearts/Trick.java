package hearts;

public class Trick extends GroupOfCards {
  private int winner;
  private Card winningCard;
  private boolean hearts=false;
  private boolean queen=false;
  
  public Trick(int players){
    super(players*2-1);
  }
  
  public int getWinner(){
    return this.winner;
  }
  
  public Card getWinningCard(){
    return this.winningCard;
  }

  public boolean isHearts() {
    return hearts;
  }

  public boolean isQueen() {
    return queen;
  }

  public void update(int playerNum, Card card){
    if(isWinner(card)){
      this.winner=playerNum;
      this.winningCard=card;
    }
    if(card.getSuit().equals(Suit.Heart)) this.hearts=true;
    if(card.equals(Card.QUEEN_OF_SPADES)) this.queen=true;
  }
  
  /**
   * Judge whether the card is a winner
   * @param card: a card in the trick
   * @return The isWinner method should return true unless: 
   * - the previous winning card is not null and the current card is not in the suit being played
   * - or its number is less than the winning card’s number.
   */
  public boolean isWinner(Card card){
    Card firstCard=this.cards.get(0);
    if(!card.hasSameSuit(firstCard)){
      return false;
    }else if(card.compareTo(firstCard)<0){
      return false;
    }else return true;
  }
}
