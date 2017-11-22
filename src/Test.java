

//A recursive Java solution for partition problem

class Test
{
   // A utility function that returns true if there is a
   // subset of arr[] with sun equal to given sum
   static boolean canPartition (int arr[], int n, int sum)
   {
       // Base Cases
       if (sum == 0)
           return true;
       if (n == 0 && sum != 0)
           return false;

       // If last element is greater than sum, then ignore it
       if (arr[n-1] > sum)
           return canPartition (arr, n-1, sum);

       /* else, check if sum can be obtained by any of
          the following
       (a) including the last element
       (b) excluding the last element
       */
       return canPartition (arr, n-1, sum) ||
              canPartition (arr, n-1, sum-arr[n-1]);
   }

   // Returns true if arr[] can be partitioned in two
   // subsets of equal sum, otherwise false
   static boolean canPartition (int arr[], int n)
   {
       // Calculate sum of the elements in array
       int sum = 0;
       for (int i = 0; i < n; i++)
           sum += arr[i];

       // If sum is odd, there cannot be two subsets
       // with equal sum
       if (sum%2 != 0)
           return false;

       // Find if there is subset with sum equal to half
       // of total sum
       return canPartition (arr, n, sum/2);
   }

   /*Driver function to check for above function*/
   public static void main (String[] args)
   {

       int arr[] = {2,-1,3,2,10};
       int n = arr.length;
       if (canPartition(arr, n) == true)
           System.out.println("Can be divided into two "+
                               "subsets of equal sum");
       else
           System.out.println("Can not be divided into " +
                               "two subsets of equal sum");
   }
}
/* This code is contributed by Devesh Agrawal */
