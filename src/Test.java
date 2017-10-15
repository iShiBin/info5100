
public class Test {

  public static void main(String[] args) {
    int i=10;
    Integer x=10;
    pass(i);
    System.out.println(i);
    
    
  }
  
  static int pass(Integer integer){
    integer=1+integer;
    return integer;
  }
  
  static int pass(int i){
    i=i+1;
    return i;
  }

}
