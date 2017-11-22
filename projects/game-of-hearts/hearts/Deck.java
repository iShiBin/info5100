package hearts;

import java.util.*;

public class Deck extends GroupOfCards{
  public static final int TOTAL_CARDS=52;
  
  public Deck(){
    super(TOTAL_CARDS);
    for(int n=2;n<=14;n++){
      for(Suit s:Suit.values()){
        Card card=new Card(n, s);
        super.addCard(card);
      }
    }
  }
  
  public void shuffle(){
    Collections.shuffle(this.getCards());//, new Random(1));
  }
  
  /**
   * To deal a card, just remove the card at index = 0.  
   * @return the undelt card
   */
  public Card dealCard(){
    return super.removeCard(0);
  }
}
