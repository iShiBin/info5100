package hearts;

import java.util.Collections;
import java.util.Random;

public class Deck extends GroupOfCards{
  private static int TOTAL_CARDS=52;
  
  public Deck(){
    super(TOTAL_CARDS);
  }
  
  public void shuffle(){
    Collections.shuffle(this.cards);
  }
  
  public Card dealCard(){
    return super.removeCard(0);
  }
}
