// score 7
/**
  You have a total of n coins that you want to form in a staircase shape,
  where every k­th row must have exactly k coins.
  Given n, find the total number of full staircase rows that can be formed.
  n is a non­negative integer and fits within the range of a 32­bit signed integer.
  
  Example 1:
  n=5
  The coins can form the following rows: 
  ¤
  ¤¤
  ¤¤
   Because the 3rd row is incomplete, we return 2.
  
  Example 2:
  n=8
  The coins can form the following rows: ¤
  ¤¤
  ¤¤¤
  ¤¤
  Because the 4th row is incomplete, we return 3.
*/
package midterm;

/**
 * @author bin
 *
 */
class Question2 {
  public int arrangeCoins(int n) {
    if (n <= 0)
      return n;
    long coin = 1;// use long since int will overflow on big n=Integer.MAX_VALUE
    int staircase = 1;
    while (coin < n) {
      coin += ++staircase;
    }
    return coin == n ? staircase : staircase - 1;
  }  

  public static void main(String[] args) {
    Question2 test = new Question2();
    
    int[] nums={1,2,3,5,8,10, 100, 1000, 10000, 1000000, Integer.MAX_VALUE};
    
    for(int n:nums){
      System.out.println(n+":"+test.arrangeCoins(n));
    }
  }
}
