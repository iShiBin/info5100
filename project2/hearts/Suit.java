package hearts;

/**
 * Define the suit of a card: Club, Diamond, Heart and Spade
 * @author bin
 *
 */
public enum Suit implements Comparable<Suit>{
  Club("♧"), Diamond("♢"), Heart("♡"), Spade("♤");
  private String name;
  
  Suit(String name){
    this.name=name;
  }
  
  public String toString() {
    return name;
  }
  
  public boolean equals(Suit suit){
    return this.toString().equals(suit.toString());
  }
}
