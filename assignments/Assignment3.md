# Assignment 3

## Instructions

* Max score is 10.
* Deadline is 11:59PM Sept 30, Saturday.
* Extra credits are added only if total score is less than 10.
* Try every question and keep practising from online sites.
* Upload all your assignments to a single repository named ‘Assignments’.
* Contact us if any assistance is needed.

## Questions

1. Find the error in the following code and explain in few lines why it is wrong. (Score 1)
   Here is the code.

```java
public class Book {
    int size;
    int price;
    String name;

    public Book(int size) {
        this.size = size;
    }

    public Book(int size, int price, String name) {
        super();
        this.size = size;
        this.price = price;
        this.name = name;
    }

    public Book(int price) {
        this.price = price;
    }

    public setName(String name){
        return name;
    }
}
```

2. Find the error in the following code and explain in few lines why it is wrong. (Score 1)
   Here is the code.

```java
class Clock {
    String time;

    void getTime() {
        return time;
    }

    void setTime(String t) {
        time = t;
    }
}
```
3. Write a Java function to remove vowels in a string. (Score 2)

   i. The function should take a string as input.
   ii. Should return the input string after omitting the vowels.
   Here is the prototype you can work with

   ```java
   public String removeVowelsFromString(String input){
   // add your code here
   }
   ```

4. Write a java function to check if two strings are Anagrams or not. (Score 2)

   i. The function should take two input strings.
   ii. Should return a boolean ‘true’ if the inputs are Anagrams else return ‘false’.
   Here is the prototype you can work with

   ```java
   public boolean checkIfTwoStringsAreAnagrams(String s1, String s2){
   // add your code here
   }
   ```

5. Create a calculator that can perform the following features. (Total Score 4)
   i. The calculator should be able to perform Addition, subtraction, multiplication, division. (Score 2)
   ii. Should be able to perform squareRoot, square, cube. (Score 1)
   iii. Should be able to convert ‘Fahrenheit-Celsius’ , ‘Feet-Inches’. (Score 1)
   Extra Credit(Score 2)

   The calculator should be able to solve a quadratic equation and return the solution as array.

   i. This function should take three arguments.
   ii. For example, if quadratic equation is $Ax^2 + Bx + C$. The function should take A, B, C as arguments
   and return a solution as array.

---
**Score 10 + extra credit 2**

# Achievements

### 1. Find the error in the following code and explain in few lines why it is wrong. (Score 1)

Here is the code.

```java
public class Book {
    int size;
    int price;
    String name;

    public Book(int size) {// error 1: contructor doesn't need 'public'
        this.size = size;
    }

    // error 2: contructor doesn't need 'public'
    public Book(int size, int price, String name) {
        super();
        this.size = size;
        this.price = price;
        this.name = name;
    }

    // error 3: contructor doesn't need 'public'; the overload of Book() is also wrong
    public Book(int price) {
        this.price = price;
    }

    public setName(String name){ // error 4: no return type
        return name; // error 5: no need to return anything in a setter method
    }
}
```

So, here is a correct version. (I add a constructor without any parameter following the best practice.) (result 1)

```java
public class Book {
    int size;
    int price;
    String name;

    Book(){} 
  
    Book(int size) {
        this.size = size;
    }

    Book(int size, int price, String name) {
        super();
        this.size = size;
        this.price = price;
        this.name = name;
    }

    Book(int size, int price) {
        this(size);
        this.price = price;
    }

    public void setName(String name){
        this.name = name;
    }
}
```



### 2. Find the error in the following code and explain in few lines why it is wrong. (Score 1)

Here is the code.

```java
class Clock {
    String time;

    void getTime() { // error 1: The 'void' is a wrong return type
        return time;
    }

    void setTime(String t) {
        time = t;
    }
}
```

So, the right version is as below. (result 1)

```java
class Clock {
    String time;

    String getTime() {
        return time;
    }

    void setTime(String t) {
        time = t;
    }
}
```



### 3. Write a Java function to remove vowels in a string. (Score 2)

i. The function should take a string as input.
ii. Should return the input string after omitting the vowels.

Here is the solution. (result 2)

```java
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
```

**Note**: Binary search is used to escalate speed.

### 4. Write a java function to check if two strings are Anagrams or not. (Score 2)

i. The function should take two input strings.
ii. Should return a boolean ‘true’ if the inputs are Anagrams else return ‘false’.

**Algorithm**

As required in the assignment notes - the characters in the string is case senstive and all the space and punctuation counts. Count up the each char's occurance in s1 and then count down it in s2. If all the number is anagram, then every number in the count should be 0. Otherwise, they are not anagrams. (result 2)

```java
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
```



**Follow-up**

By [WikiPedia defination of Anagram](https://en.wikipedia.org/wiki/Anagram), two strings are anagrams in the following conditions:

- have same count for each English charactor [a-z]
- the capitalization doesn't matter ("roast beef" = "eat for BSE")
- other charactors other than English alphabet do not count

To transfer these conditions to codes. We need to:

* turn all the chars in the input strings s1 and s2 to the same case  (lowercase or uppercase)
* use a interger array (count[]) of length 26 to count each char's occrence [a-z]
* go though s1 to count each char's occrence [a-z] in count[]
* go though s2 to decrease each char's occrence [a-z] in count[]
* in the end, if any number in count[] is not zero, then quit and return false; otherwise, return true

Here is the solution.

```java
public boolean checkIfTwoStringsAreAnagrams(String s1, String s2) {
    if(s1 == null || s2 == null) return false;
    final int number = 26;// alphabet range [a-z]
    int[] count = new int[number];
    s1 = s1.toLowerCase();
    for(char i:s1.toCharArray()) {
        if(i>='a' && i<='z') count[i-'a']++;
    }
    
    s2 = s2.toLowerCase();
    for(char i:s2.toCharArray()) {
        if(i>='a' && i<='z') count[i-'a']--;
    }
    
    for(int n:count){
        if (n != 0) return false;
    }
    return true;
}
```

Note: If either of the string is null, then it returns false even both of them are null.

*Related LeedCode Links*

- [Valid Anagram](https://leetcode.com/problems/valid-anagram/description/)



### 5. Create a calculator that can perform the following features. (Total Score 4)

i. The calculator should be able to perform Addition, subtraction, multiplication, division. (Score 2)
ii. Should be able to perform squareRoot, square, cube. (Score 1)
iii. Should be able to convert ‘Fahrenheit-Celsius’ , ‘Feet-Inches’. (Score 1)

**Extra Credit** (Score 2)

The calculator should be able to solve a quadratic equation and return the solution as array.

i. This function should take three arguments.
ii. For example, if quadratic equation is $Ax^2 + Bx + C$. The function should take A, B, C as arguments and return a solution as array.

#### Algorithm

Since this calculator class is just to calculate, so static functions will be suitable.(result 4 + 2)

```java
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
```

#### Follow-up

The following implmentation is mostly for practicing functions in Java so accuracy and exceptions are not handled quite well.

Java has many built-in classes and funtions like Math to fulfill these calculations even more safely (no exception) and precisely (double is not safe when calculating). So the best way to achieve it is wrapper  *[java.math.BigDecimal](https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html)*

# References

[1] ../src/Assignment3.java
