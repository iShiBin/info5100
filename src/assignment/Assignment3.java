package assignment;

import java.util.Arrays;

public class Assignment3 {

    public String removeVowelsFromString(String input) {
        if (input == null) return null;
        final char[] vowes = "AEIOUaeiou".toCharArray();

        StringBuilder str = new StringBuilder();
        for (char key : input.toCharArray()) {
            if (Arrays.binarySearch(vowes, key) < 0)
                str.append(key);
        }
        return str.toString();
    }

    public boolean checkIfTwoStringsAreAnagramsWiki(String s1, String s2) {
        if(s1 == null || s2 == null) return false;
        if(getHashCode(s1) == getHashCode(s2)){return true;}
        else return false; 
    }
    
    public boolean checkIfTwoStringsAreAnagrams(String s1, String s2) {
        if(s1 == null || s2 == null) return false;
      final int number = 128;// alphabet range [a-z]
      int[] count = new int[number];
      for(char i:s1.toCharArray()) {
          count[i-'a']++;
      }
      
      for(char i:s2.toCharArray()) {
          count[i-'a']--;
      }
      
      for(int n:count){
          if (n != 0) return false;
      }
      return true;
    }
    
    private int getHashCode(String str){
        final int number = 128;
        int[] count = new int[number];
        for(char c:str.toCharArray()){
            count[c]++;
        }
        return Arrays.hashCode(count);
    }

    public static void main(String[] args) {
        Assignment3 test = new Assignment3();
        // removeVowelsFromString
        String input = "public String removeVowelsFromString(String input){";
        System.out.println(test.removeVowelsFromString(input));

        // checkIfTwoStringsAreAnagrams
        String s1 = "rail safety";
        String s2 = "fairy tales";
        System.out.println(test.checkIfTwoStringsAreAnagrams(s1, s2));
        
        s1 = "William Shakespeare";
        s2 = "I am a weakish speller";
        System.out.println(test.checkIfTwoStringsAreAnagrams(s1, s2));
        
        s1 = "eat"; s2 = "aTe";
        System.out.println(test.checkIfTwoStringsAreAnagrams(s1, s2));
        
        // Extra Credit (Score 2)
        System.out.println(Arrays.toString(Calculator.solveQuadratic(9, -24, -105)));
    }

}

class Calculator {
    // The calculator should be able to perform Addition, subtraction,
    // multiplication, division. (Score 2)
    public static double add(double a, double b) {
        return a + b;
    }

    public static double substract(double a, double b) {
        return a - b;
    }

    public static double multiple(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        return a / b;
    }

    // Should be able to perform squareRoot, square, cube. (Score 1)
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    public static double square(double a) {
        return Math.pow(a, 2.0);
    }

    public static double cube(double a) {
        return Math.pow(a, 3.0);
    }

    // iii. Should be able to convert ‘Fahrenheit-Celsius’ , ‘Feet-Inches’.
    // (Score 1)
    public static double convertFahrenheit(double f) {
        return (f - 32) / 1.8;
    }

    public static double convertCelsius(double c) {
        return c * 1.8 + 32;
    }

    public static double convertFeet(double f) {
        return f * 12;
    }

    public static double convertInch(double i) {
        return i / 12;
    }

    // Extra Credit (Score 2)
    public static double[] solveQuadratic(double a, double b, double c) {
        if (a == 0) {
            if (b != 0)
                return new double[] { -c / b };
            else
                throw new IllegalArgumentException("No solution.");
        } else if (b * b == 4 * a * c) {
            return new double[] { -b / 2 / a };
        } else if (b * b < 4 * a * c) {
            throw new IllegalArgumentException("No rational solution.");
        } else {
            double[] solution = new double[2];
            solution[0] = (-b + Math.sqrt(b * b - 4 * a * c)) / 2 / a;
            solution[1] = (-b - Math.sqrt(b * b - 4 * a * c)) / 2 / a;
            return solution;
        }
    }
}
