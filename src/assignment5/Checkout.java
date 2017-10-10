package assignment5;

import java.util.*;

public class Checkout {
  private List<DessertItem> cart=new ArrayList<>();
  private static final String TAX_TEXT="Tax";
  private static final String TOTAL_COST_TEXT="Total Cost";
  
  
  public Checkout(){};
  
  public int numberOfItems(){
    return cart.size();
  }

  public void enterItem(DessertItem item){
    cart.add(item);
  }
  
  public void clear(){
    cart.clear();
  }
  
  int totalCost(){
    int cost=0;
    for(DessertItem item:cart){
      cost+=item.getCost();
    }
    return cost;
  }
  
  int totalTax(){
    return Math.round(totalCost()*DessertShoppe.TAX_RATE);
  }

  
  public String toString(){
    StringBuilder builder=new StringBuilder();
    builder.append(this.getReceiptHeader());
    builder.append(getReceiptBodyItems());
    builder.append(getReceiptFooter());
    builder.append("\n\n");
    return builder.toString();
    
  }
  private String alignCenter(String str){
    return String.format("%"+((DessertShoppe.PRINT_WIDTH+DessertShoppe.NAME.length())/2)+"s",str);
  }
  
  String getReceiptHeader(){
    StringBuilder header=new StringBuilder();
    header.append(alignCenter(DessertShoppe.NAME)+"\n");
    
    char[] seperateLine=new char[DessertShoppe.NAME.length()];
    Arrays.fill(seperateLine, '-');
    header.append(alignCenter(String.valueOf(seperateLine))+"\n\n");
    
    return header.toString();
  }
  
  String getReceiptFooter(){
    StringBuilder footer=new StringBuilder("\n");
    footer.append(alignBothSides(TAX_TEXT, totalTax()));
    footer.append(alignBothSides(TOTAL_COST_TEXT, totalCost()+totalTax()));    
    return footer.toString();
  }
  
  String alignBothSides(String str, int cost){
    StringBuilder footer=new StringBuilder();
    footer.append(str);
    int pad=DessertShoppe.PRINT_WIDTH-str.length();
    footer.append(String.format("%"+pad+"s", DessertShoppe.cents2dollars(cost)));
    footer.append("\n");
    return footer.toString();
  }
  
  String getReceiptBodyItems(){
    StringBuilder builder=new StringBuilder();
    for(DessertItem item: this.cart){
      builder.append(item.toString());
      builder.append("\n");
    }
    return builder.toString();
  }
}
