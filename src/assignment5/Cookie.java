package assignment5;

class Cookie extends DessertItem{
  private int number;
  private int price;
  
  public int getCost(){
    //the price is for a dozen 12, so here cost/12
    return (int) 1.0*number*price/12;
  }
  
  Cookie(){};
  
  Cookie(String name, int n, int price){
    super(name);
    this.number=n;
    this.price=price;
  }
  
  public String toString(){
    StringBuilder builder=new StringBuilder();
    builder.append(number+ " @ "+price+" /dz.\n");
    builder.append(super.toString());
    return builder.toString();
  }
}
