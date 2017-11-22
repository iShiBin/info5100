public class Test {
  public int countNumberOfPossibleWays(int m, int n, int x){
    if(n<1 || x<n || x>m*n) return 0;
    else if(n==1 && x>=1 && x<=m){
      return 1;
    }else{
      int counter=0;
      for(int i=1;i<=m;i++){
        counter+=countNumberOfPossibleWays(m, n-1, x-i);
      }
      return counter;
    }
  }
  
  public static void main(String[] args){
    System.out.println(new Test().countNumberOfPossibleWays(6,2,10));
  }
}
