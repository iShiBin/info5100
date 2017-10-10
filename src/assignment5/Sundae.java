package assignment5;

class Sundae extends IceCream {
  private String sundae;
  private int topping;
  
  @Override
  public int getCost() {
    return super.getCost()+topping;
  }
  
  Sundae(){}
  
  Sundae(String icecream, int cost, String sundae, int topCost){
    super(icecream, cost);
    this.sundae=sundae;
    this.topping=topCost;
  }
  
  public String toString(){
    return sundae+" Sundae with\n" + super.toString();
  }

}