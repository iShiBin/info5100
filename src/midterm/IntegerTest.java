package midterm;

public class IntegerTest {
  
  public static void swap(Integer i, Integer j) 
  {
     Integer temp = new Integer(i);
     i = j;
     j = temp;
  }
  public static void main(String[] args) 
  {
    Integer i = new Integer(10);
    Integer j = new Integer(20);
    swap(i, j);
    System.out.println("i = " + i + ", j = " + j);
     
     String s1="shi", s2="bin";
     swap(s1, s2);
     System.out.println(s1);
     System.out.println(s2);
  }
  
  public static void swap(String s, String t){
    System.out.println(s+","+t);
    String temp=s;
    s=t;
    t=temp;
    System.out.println(s+","+t);
  }

}
