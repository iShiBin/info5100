// score 6
/**
 * Write a method named reverseEvenIndices that takes an integer array as input and outputs an array
 * such that all the values with odd indices remain in the same position.
 * However, elements with even indices should be output in reverse order. 
 * That is, the first element with even index should be swapped with the last element with even index, 
 * the second even­indexed element with the second­to­last even­indexed element, and so on.
 * 
 * Note that zero is an even index.
 * 
 * Example inputs and outputs are as follows:
   Input: {9, 4, 8, 7, 5, 1, 3} Output: {3, 4, 5, 7, 8, 1, 9}
   Input: {6, 4, 1, 0, 3, 2} Output: {3, 4, 1, 0, 6, 2}
   Input: {1, 2, 3} Output: {3, 2, 1}

 */
package midterm;

import java.util.Arrays;

/**
 * @author bin
 * 
 */
class Question1 {
  public int[] reverseEvenIndices(int[] nums){
    if(nums!=null) {
      int i=0;//first even index
      int j=nums.length-1;
      j=j%2==0?j:j-1;//last even index
      while(i<=j-2){//in place swap
        nums[i]=nums[i]^nums[j];
        nums[j]=nums[i]^nums[j];
        nums[i]=nums[i]^nums[j];
        i+=2;
        j-=2;
      }
    }
    return nums;
  }
  
  public static void main(String[] args){
    Question1 test=new Question1();
    int[] nums={9, 4, 8, 7, 5, 1, 3};
    System.out.println(Arrays.toString(test.reverseEvenIndices(nums)));
    
    nums = new int[]{6, 4, 1, 0, 3, 2};
    System.out.println(Arrays.toString(test.reverseEvenIndices(nums)));
    
    nums = new int[]{1, 2, 3};
    System.out.println(Arrays.toString(test.reverseEvenIndices(nums)));
    
    nums = new int[]{};
    System.out.println(Arrays.toString(test.reverseEvenIndices(nums)));
  }
}
