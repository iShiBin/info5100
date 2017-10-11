package assignment5;

class Sundae extends IceCream {
  private String sundaeName;
  private int topping;
  
  @Override
  public int getCost() {
    return super.getCost()+topping;
  }
  
  Sundae(){}
  
  Sundae(String icecream, int cost, String sundae, int topCost){
    super(icecream, cost);
    this.sundaeName=sundae;
    this.topping=topCost;
  }
  
  @Override
  public String toString(){
    return sundaeName+" Sundae with\n" + super.toString();
  }

}