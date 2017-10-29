package hearts;

public class Trick extends GroupOfCards {
  private int winner;
  private Card winningCard;
  private boolean hearts=false;
  private boolean queen=false;
  
  public Trick(int players){
    super(players);
//    super(players*2-1);
  }
  
  protected Trick(){
    super();
  };
  
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

  /**
   * If the current card is the winner, 
   * set winner equal to current player’s number and set the winning card equal to the current card. 
   * If the current card is a heart, set hearts to true. 
   * If the current card is the queen of spades, set queen to true.
   * @param playerNum
   * @param card
   */
  public void update(int playerNum, Card card){
    if(isWinner(card)){
      this.winner=playerNum;
      this.winningCard=card;
    }
    if(card.getSuit().equals(Suit.Heart)) this.hearts=true;
    if(card.equals(Card.QUEEN_OF_SPADES)) this.queen=true;
  }
  
  public int getPoints(){
    int points=0;
    for(Card c:this.getCards()){
      if(c.equals(Card.QUEEN_OF_SPADES)) points+=13;
      else if(c.hasSameSuit(Suit.Heart)) points++;
    }
    return points;
  }
  
  /**
   * Judge whether the card is a winner
   * @param card: a card in the trick
   * @return The isWinner method should return true unless: 
   * - the previous winning card is not null and the current card is not in the suit being played
   * - or its number is less than the winning card’s number.
   */
  public boolean isWinner(Card card){
    if(this.winningCard!=null && !this.getCards().isEmpty()){
      if(!card.hasSameSuit(winningCard) || card.isSmallerThan(winningCard))
        return false;
    }
    return true;
  }
  
  @Override
  public String toString() {
    return "winner " + winner + " " + this.winningCard + super.getCards();
  }
}
