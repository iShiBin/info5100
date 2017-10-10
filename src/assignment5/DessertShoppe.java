package assignment5;

import java.text.DecimalFormat;

public class DessertShoppe {
  static final float TAX_RATE=0.065f;

  static final String NAME="M & M Dessert Shoppe";
  static final int ITEM_NAME_MAX_SIZE=23;
  static final int PRINT_WIDTH=30;
  
  static final DecimalFormat currencyFormat=new DecimalFormat("#.00");
  
  static String cents2dollars (int cent){
    return currencyFormat.format(1.0*cent/100);
  } 
}