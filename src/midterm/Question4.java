// score 10
/**
Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X.
X is the summation of values on each face when all the dice are thrown.
Your function should take, number of faces, number of dice and required sum as input and return a number of possible ways.
}
 
 */
package midterm;

public class Question4 {


  /**
   * Algorithm: reduce the problem scale and use recursion. Here is the base cases:
   * - if there is no dice available, return 0 because there is no way you can get a sum
   * - if the sum is either smaller than the minimum (all sides =1) or the maximum(all sides=m), return 0
   * - if there is only one dice available now and the sum is in its range [1,m], return 1
   * Otherwise, recursively call countNumberOfPossibleWays(m, n-1, x-i) when the current dice has value i.
   * @param m: number of faces in a dice
   * @param n: number of dice
   * @param x: target sum
   * @return
   */
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
  
  public static void main(String[] args) {
    int m = 6, n = 2;

    Question4 test = new Question4();
    for (int x = 1; x <= 25; x++) {
      int c=test.countNumberOfPossibleWays(m, n, x);
      System.out.println(m+","+n+","+x+": "+c);
    }
    /*
    6,4,1: 0
    6,4,2: 0
    6,4,3: 0
    6,4,4: 1
    6,4,5: 4
    6,4,6: 10
    6,4,7: 20
    6,4,8: 35
    6,4,9: 56
    6,4,10: 80
    6,4,11: 104
    6,4,12: 125
    6,4,13: 140
    6,4,14: 146
    6,4,15: 140
    6,4,16: 125
    6,4,17: 104
    6,4,18: 80
    6,4,19: 56
    6,4,20: 35
    6,4,21: 20
    6,4,22: 10
    6,4,23: 4
    6,4,24: 1
    6,4,25: 0
    */
  }
}
