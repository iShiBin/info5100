
public class Test {

  public static void main(String[] args) {
    int cent=199;
    System.out.println(String.format("%.2f",1.0*199/100));
    
    int width=50;
    String s="M & M DessertShoppe";
    System.out.println("Strawberry Ice Cream 1.45".length());
    int pad=(width-s.length())/2;
    System.out.println(String.format("%"+(s.length()+pad)+"s",s));
  }

}
