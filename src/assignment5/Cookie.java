package assignment5;

class Cookie extends DessertItem{
  private int number;
  private int pricePerDozen;
  
  /**
   * The cookie price is for a dozen, so divide by 12 to get a piece of cookie.
   */
  public int getCost(){
    return (int) (1.0*number*pricePerDozen/12 + 0.5);
  }
  
  Cookie(){};
  
  Cookie(String name, int n, int price){
    super(name);
    this.number=n;
    this.pricePerDozen=price;
  }
  
  @Override
  public String toString(){
    StringBuilder builder=new StringBuilder();
    builder.append(number+ " @ "+pricePerDozen+" /dz.\n");
    builder.append(super.toString());
    return builder.toString();
  }
}
