## Question 4 of 25

Write the type and value of the expression. If an expression will not compile, write illegal under type and put an X in value. You must fill in "type, value"(without quotes but include comma).
( 9/4.0) * 4

Answer given: double, 9.0

Accepted answers: float, 9.0

**Explanation**: The answer is actually `wrong` because the following code has compiling error. As a matter of fact, a decimal is a double type unless it ended with 'f' explicitly.

```java
public class FloatTest {
  public static void main(String[] args) {
    float f = (9 / 4.0) * 4;// compile error: cannot convert from double to float
    float z = 0.0; // compile error for the same reason
  }
}
```

**Follow-up**

- convert int to float works fine `float zero = 0;`and `float three=10/3;`
- Using double/float for decimal could go wrong because computer try to use binary to express decimal.
  `System.out.println(10/3.0); //3.3333333333333335 `
  `System.out.println(0.035*100);//3.5000000000000004`
  `System.out.println(0.036*100);//3.5999999999999996`
- But it works fine to * a multiple of divisor `System.out.println(10/3.0*9);//30.0`
- Recommand to use 'BigDecimal' for precision sensentive calculations.

## Question 21 of 25

Write the type and value of the expression. If an expression will not compile, write illegal under type and put an X in value. You must fill in "type, value"(without quotes but include comma).
10 + 20.2 + "11.1" + "ac"

Answer given: String, 1020.211.1ac

Accepted answers: String, 30.211.1ac

**Explanation** I won't write this kind of sloppy code. Anyway, the format match happens when it is the nearest.

# Question 2 of 25

Which of the following are true about HashMap. Pick all that apply

Correct answer: A) B) D) E) 

A) Complexity of get and put operation is O(1).
B) Initial Capacity is 16
C) Using TreeMap, will store the values in a sorted manner.
D) Maximum capacity for a HashMap is Math.pow(2,30)
E) HashMap Allows null and store the null as key at index 0 of HashTable.

**Explanation**
C) TreeMap will store the **keys** in a sorted manner, rather than the values. 
D) The default initial capacity - MUST be a power of two.
`static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16`
The maximum capacity, used if a higher value is implicitly specified by either of the constructors with arguments. MUST be a power of two <= 1<<30.
`static final int MAXIMUM_CAPACITY = 1 << 30;`

# Question 17 of 25

```java
class Main {
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
   }
}
```

What will be the Output?

Correct answer: B)

A) i = 10, j = 10
**B) i = 10, j = 20**
C) i = 20, j = 20
D) i = 20, j = 10

Explanation: Other than normal objects passing reference, the primitive data type wrapper works like immutable objects like String. So whatever you do in the swap(), it won't change the value in the main function. Another example is like below.

```java
  public static void main(String[] args) {
    String s1 = "shi", s2 = "bin";
    swap(s1, s2);
    System.out.println(s1);
    System.out.println(s2);
  }

  public static void swap(String s, String t) {
    System.out.println(s + "," + t);
    String temp = s;
    s = t;
    t = temp;
    System.out.println(s + "," + t);
  }
```

# Question 19 of 25

```java
int[] x = {5,6,7,8,9}; 
int[] y = x; 
y[2] = 10; 
```

What is the value of x[2]? 

Correct answer: A)

**A) 10**
B) 7
C) 6
D) 8

**Explanation**: 

> An *object* is a *class instance* or an *array*.
>
> -- The first line in the *Object* definition from Oracle docs.

Then it will make it obvious to choose A, and it has something like x.length in Java. Also, that's why declaring `List<int[]>` is eligible.  

# Question 23 of 25

If int x = 3, float y = 0, then what should be the value of x / y?

Correct answer: B)

A) 0
**B) infinity**
C) Arithmetic Exception 
D) Compilation Error

**Explanation**: 

```java
System.out.println(3/0);// Exception in thread "main" java.lang.ArithmeticException: / by zero
```

```java
System.out.println(3/0f);// Infinity
```

In java, double supports infinity, so check this code.

```java
    double inf = Double.POSITIVE_INFINITY;
    System.out.println(inf + 5); // Infinity
    System.out.println(inf - inf); // same as Double.NaN
    System.out.println(inf * -1); // same as Double.NEGATIVE_INFINITY
```

