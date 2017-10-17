# Assignment 4

## Instructions

* Max score is 10.
* Deadline is 11:59PM, Oct 7th , Saturday.
* Extra credits are added only if score is less than 10.
* Try to attempt every question and keep practising from other online sites.
* Upload all assignments to a single repository named 'Assignments'.
* Don't use any direct methods for this assignment.
* Contact us if any assistance is needed.

## Questions

1. Now you are given a string S, which represents a software license key which we would like to format. The string is composed of alphanumerical characters and dashes. The dashes split the alphanumerical characters within the string into groups.(i.e If they are M dashes, the string is split into M+1 groups). The dashes in the given string are possibly misplaced. 

  We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character). To satisfy this requirement, we will reinsert dashes. Additionally, all the lower case letters in the string must be converted to upper case.

  So, you are given a non-empty string S, representing a license key to format, and an integer K. And you need to return the license key formatted according to the description above. (Score 2)

  > Example 1:  
  >
  > Input: S = "2-4A0r7-4k", K = 4  
  >
  > Output: "24A0-R74K"  
  >
  > Explanation: The string S has been split into two parts, each part has 4 characters.

  > Example 2:  
  >
  > Input: S = "2-4A0r7-4k", K = 3  
  >
  > Output: "24-A0R-74K"  
  >
  > Explanation: The string S has been split into three parts, each part has 3 characters, except the first part as it could be shorter as said above.  

  Note: 
  *The length of string S will not exceed 12,000, and K is a positive integer.*
  *String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).String S is non-empty.*

2. Implement a class called Tool. It should have an int field called strength and a char field called type. You may make them either private or protected. The Tool class should also contain the function void setStrength(int), which sets the strength for the Tool.

  Create 3 more classes called Rock, Paper, and Scissors, which inherit from Tool. Each of these classes will need a constructor which will take in an int that is used to initialise the strength field. The constructor should also initialise the type field using 'r' for Rock, 'p' for Paper, and 's' for Scissors.

  These classes will also need a public function bool fight(Tool) that compares their strengths in the following way:

  Rock's strength is doubled (temporarily) when fighting scissors, but halved (temporarily) when fighting paper.
  In the same way, paper has the advantage against rock, and scissors against paper.
  The strength field shouldn't change in the function, which returns true if the original class wins in strength and false otherwise.
  You may also include any extra auxiliary functions and/or fields in any of these classes. Run the program without changing the main function, and verify that the results are correct. (Score 2)

  ```java

  class Tool {
      // add your code here
  }

  /*
   * Implement class Scissors 
   */

  /*
   * Implement class Paper
   */

  /*
   * Implement class Rock 
   */

  class RockPaperScissorsGame {

      public static void main(String args[]) {

          Scissors s = new Scissors(5);
          Paper p = new Paper(7);
          Rock r = new Rock(15);

          System.out.println(s.fight(p) + " , " + p.fight(s));
          System.out.println(p.fight(r) + " , " + r.fight(p));
          System.out.println(r.fight(s) + " , " + s.fight(r));

      }
  }
  ```

3. Every computer on the Internet has a unique identifying number, called an Internet protocol (IP) address. To contact a computer on the Internet, you send a message to the computer's' IP address. Here are some typical IP addresses:

  216.27.6.136  

  224.0.118.62

  There are different formats for displaying IP addresses, but the most common format is the dotted decimal format. The above two IP addresses use the dotted-decimal format. It is called "dotted" because dots are used to split up the big IP address number into four smaller numbers. It is called decimal because decimal numbers are used (as opposed to binary) for the four smaller numbers.

  Each of the four smaller numbers is called an octet because each number represents eight bits (oct means eight). For example, the 216 octet represents 11011000 and the 27 octet represents 00011011.

  Implement an IpAddress class that stores an IP address as a dotted-decimal string and as four octet ints.

  You must implement all of the following:

  **Instance variables**:  

  dottedDecimal - a dotted-decimal string. Example value: "216.27.6.136"

  firstOctet, secondOctet, thirdOctet, fourthOctet  four int variables that store the octets for an IP address

  **Constructor**: 

  This constructor receives one parameter, a dotted-decimal string. You may assume that the parameter's' value is valid (i.e., no error checking required). The constructor initialises the instance variables with appropriate values. There are many ways to solve the problem of extracting octets from the given dotted-decimal string. We recommend that you use String methods to extract the individual octets as strings, and then use parseInt method calls to convert the octet strings to ints.

  **getDottedDecimal method**: This is a standard accessor method that simply returns the dottedDecimal instance variable's value.

  **getOctet method**:  This method receives the position of one of the octets (1, 2, 3, or 4) and returns the octet that's' at that position.

  Provide a driver class that tests your IpAddress class. Your driver class should contain this main method: (Score 2)

  ```java
  public static void main(String args[]){
    	IpAddress ip = new IpAddress("216.27.6.136");
    	System.out.println(ip.getDottedDecimal());
    	System.out.println(ip.getOctet(4));
    	System.out.println(ip.getOctet(1));
    	System.out.println(ip.getOctet(3));
    	System.out.println(ip.getOctet(2));
    }
  ```

  Using the above main method, your program should generate the following output.

  Sample output: 

  ```tex
  216.27.6.136
  136
  216
  6	 
  27
  ```

  Design a simple registration system that allows Student to register in a course using 2 classes: class Student & class Course. Implement the scenarios in class Test's main method.\


4. Each student has a name and an id variables. Each object of class Student is initialised using values of name and id passed to constructor. Class Student has accessor methods for its instance variables

   Each Course has a name, and a variable numberOfStudent representing the number of registered students. A course can have a maximum number of 10 students registered in it. 

   Class Course store the registered students in students which is an array of type Student. When a student register in a course, he is added to the array. 

   Each object of class Course is initialised using the title.

   Class Course has the following methods: method getStudents(): return the array of registered students; method boolean isFull():  return true if the course is full, accessor method for the title and numberOfStudent field, method registerStudent (Student student): if the course is not full, register a student in course. (Score 2)

5. Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999. (Score 2)

   Here is the prototype you can work with

   ```java
   public String intToRoman(int num) {
         
   }
   ```

## Extra Credit

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. (Score 2)  
Example 1:  

```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```
Example 2:  

```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```
Here is the prototype you can work with.   

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
  
}
```
## Score and Comments

Awesome Work.  
Score **10** + extra credit **2** ; Total Score **10**

# Accomplishments

Note: All the source code is included in ..\src\Assignment4.java, including some unit test cases.

## 1. Format software license key

### Algorithm

The numbet of characters in the first group could be less than K, so the key to solve this problem is to scan this array from the back.

1. If the char is not '-', then put it in the result, and count the current number of chars in the result.
2. One the number of chars %K = 0, add a '-' and then continue to scan the array.
3. When the pointer reaches index '0', quit the process.
4. Reverse the result array and return it.

score 2  

### Solution

```java
public String splitLicenseKey(String key, int k){
    StringBuffer splittedKey=new StringBuffer();
    char[] chs = key.toCharArray();
    int counter=0;
    for(int i=chs.length-1;i>=0;i--){
        if(chs[i]!='-'){
            splittedKey.append(Character.toUpperCase(chs[i]));
            counter++;
        }
        if(counter%k==0 && i!=0) splittedKey.append('-'); 
    }
    return splittedKey.reverse().toString();
}
```

**Trick**: Step 2 may add an extra '-' when the number of chars in 'last' group reaches K, so we need to add an extra condition to avoid it.

## 2. Rock Paper Scissors Game

This assignment is to design some classes. So inheritance will be used a lot in this case. Here is the solution.  

score 2.  

```java
class Tool{
    private int strength;
    protected char type;
    
    Tool(){}
    Tool(int s){this.strength=s;}
    
    public void setStrength(int n){
        this.strength=n;
    }
    
    public int getStrength(){
        return strength;
    }    
}

class Scissors extends Tool{
    Scissors(){}
    Scissors(int s){
        super.setStrength(s);
        super.type='s';
    }
    
    public boolean fight(Tool tool){
        double factor=1.0;
        if(tool.getClass()==Paper.class) factor*=2;
        if(tool.getClass()==Rock.class) factor/=2;
        
        // put factor first to do a 'double' compare
        return factor*this.getStrength() > tool.getStrength();
    }
}

class Paper extends Tool{
    Paper(){}
    Paper(int s){
        super.setStrength(s);
        super.type='p';
    }
    
    public boolean fight(Tool tool){
        double factor=1.0;
        if(tool.getClass()==Rock.class) factor*=2;
        if(tool.getClass()==Scissors.class) factor/=2;
        
        // put factor first to do a 'double' compare
        return factor*this.getStrength() > tool.getStrength();
    }
}

class Rock extends Tool{
    Rock(){}
    Rock(int n){
        super(n);
        super.type='r';
    }
    
    public boolean fight(Tool tool){
        double factor=1.0;
        if(tool.getClass()==Scissors.class) factor*=2;
        if(tool.getClass()==Paper.class) factor/=2;
        
        // put factor first to do a 'double' compare
        return factor*this.getStrength() > tool.getStrength();
    }
}

class RockPaperScissorsGame {
    public static void main(String args[]) {
        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Scissors r = new Scissors(15);

        System.out.println(s.fight(p) + " , " + p.fight(s));
        System.out.println(p.fight(r) + " , " + r.fight(p));
        System.out.println(r.fight(s) + " , " + s.fight(r));
    }
}
```

The output after running the driver class is.

>true , false
>
>false , true
>
>true , false

## 3. IP

The trick to solve this problem is to design a helper method `splitIP()` because the Java API like  `split()`are not allowed to use. This is my solution source code.  

score 2

```java
class IpAddress{
    private String dottedDecimal;
    private int firstOctet, secondOctet, thirdOctet, fourthOctet;
    
    public void setDottedDecimal(String IP){
        this.dottedDecimal=IP;
    }
    public String getDottedDecimal(){
        return this.dottedDecimal;
    }
    
    IpAddress(){};
    IpAddress(String IP){
        this.dottedDecimal=IP;
        this.setOctets(this.splitIP());
    }
    
    private void setOctets(int[] octets){
        assert octets.length==4;
        firstOctet=octets[0];
        secondOctet=octets[1];
        thirdOctet=octets[2];
        fourthOctet=octets[3];
    }
    
    // could be easy to use string's split function    
    private int[] splitIP(){
        final int N=4;
        int ip[]=new int[N];
        StringBuilder sb=new StringBuilder();
        for(int i=0, j=0;i<N&&j<this.dottedDecimal.length();j++){
            while(j<dottedDecimal.length() && dottedDecimal.charAt(j)!='.'){
                sb.append(dottedDecimal.charAt(j));
                j++;
            }
            ip[i++]=Integer.parseInt(sb.toString());
            
            sb=new StringBuilder();
        }
        return ip;
    }
    
    public int getOctet(int p){
        assert p<=1 && p<=4;
        if(p==1) return firstOctet;
        else if(p==2) return secondOctet;
        else if(p==3) return thirdOctet;
        else if(p==4) return fourthOctet;
        else throw new IllegalArgumentException();
    }
}

class IpAddressDriver{
    public static void main(String args[]){
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));
    }
}
```

After running the driver, the result is the same with the description of this assignment.

>216.27.6.136
>
>136
>
>216
>
>6
>
>27

## 4. Students Register Classes

Here is my solution.  

score 2

```java
class Student{
    private String name;
    private int id;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
//    Student(){}
    Student(String n, int i){
        this.name=n;
        this.id=i;
    }
    
    public String toString(){
        return id+", "+name;
    }
}

class Course{
    private String name;
    private int numberOfStudent=0;
    private final int maxOfStudent=10;
    private Student[] students=new Student[maxOfStudent];
    
    public Course(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
    
    public boolean isFull(){
        return numberOfStudent==maxOfStudent;
    }
    
    public void registerStudent(Student student){
        if(!this.isFull()){
            students[numberOfStudent++]=student;
        }else{
            System.out.println("Cannot register \""+student+"\" because the class is full.");
        }
    }    
}
// test class register system. I change the name from 'Test' to 'CourseRegisterDrive'
class CourseRegisterDrive {
    public static void main(String[] args) {
        String[] names={"Bin","Tianyu","Yuhan","Lulu","Zihan","Aarabhi","Lulu","Yujia","Chun","Liuhui","Xiaoxiao"};
        int[] ids = {1822, 1823, 1824, 1825, 1826, 1827, 1828, 1829, 1830, 1831, 1832};

        Student[] stu5100 = new Student[names.length];

        for (int i = 0; i < names.length && i < ids.length; i++) {
            stu5100[i] = new Student(names[i], ids[i]);
        }

        Course info5100 = new Course("Application Engineering and Development");
        for (Student s : stu5100) {
            if (s != null)
                info5100.registerStudent(s);
        }

        Student[] registeredStudent = info5100.getStudents();
        System.out.println("Here is the registered studetns for course: " + info5100.getName());
        for (Student s : registeredStudent) {
            System.out.println(s);
        }
        System.out.println("The total registerred student number is: " + info5100.getNumberOfStudent());
    }
}
```

After running the driver class, here is the output.

>Cannot register "1832, Xiaoxiao" because the class is full.
>
>Here is the registered studetns for course: Application Engineering and Development
>
>1822, Bin
>
>1823, Tianyu
>
>1824, Yuhan
>
>1825, Lulu
>
>1826, Zihan
>
>1827, Aarabhi
>
>1828, Lulu
>
>1829, Yujia
>
>1830, Chun
>
>1831, Liuhui
>
>The total registerred student number is: 10

## 5. Roman Numerals

### Algorithm

[Roman numerals](https://en.wikipedia.org/wiki/Roman_numerals) are "Numbers in this system are represented by combinations of letters from the Latin alphabet. Roman numerals, as used today, are based on seven symbols." by its definition in WikiPedia.

| Symbol | [I](https://en.wikipedia.org/wiki/I) | [V](https://en.wikipedia.org/wiki/V) | [X](https://en.wikipedia.org/wiki/X) | [L](https://en.wikipedia.org/wiki/L) | [C](https://en.wikipedia.org/wiki/C) | [D](https://en.wikipedia.org/wiki/D) | [M](https://en.wikipedia.org/wiki/M) |
| ------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| Value  | 1                                    | 5                                    | 10                                   | 50                                   | 100                                  | 500                                  | 1,000                                |

And also from WikiPedia:

> Numbers are formed by combining symbols and adding the values, so II is two (two ones) and VIII is eight (a five and three ones). Symbols are placed from left to right in order of value, starting with the largest. Because each numeral has a fixed value rather than representing multiples of ten, one hundred and so on, according to *position*, there is no need for "place keeping" zeros, as in numbers like 207 or 1066; those numbers are written as CCVII (two hundreds, a five and two ones) and MLXVI (a thousand, a fifty, a ten, a five and a one).

> In a few specific cases,[[2\]](https://en.wikipedia.org/wiki/Roman_numerals#cite_note-2) to avoid confusing and hard to read numbers with four characters repeated in succession (such as IIII or XXXX), [subtractive notation](https://en.wikipedia.org/wiki/Subtractive_notation) is used: as in this table:

| Number   | 4    | 9    | 40   | 90   | 400  | 900  |
| -------- | ---- | ---- | ---- | ---- | ---- | ---- |
| Notation | IV   | IX   | XL   | XC   | CD   | CM   |



- > I placed before V or X indicates one less, so four is IV (one less than five) and nine is IX (one less than ten)

- > X placed before L or C indicates ten less, so forty is XL (ten less than fifty) and ninety is XC (ten less than a hundred)

- > C placed before D or M indicates a hundred less, so four hundred is CD (a hundred less than five hundred) and nine hundred is CM (a hundred less than a thousand)[[5\]](https://en.wikipedia.org/wiki/Roman_numerals#cite_note-sun-5)

So we can use greedy strategy to convert an integer to a roman numeral with the help of two arrays.

```java
int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
```

Here is the logical steps to achieve the transform of integer num.

1. Start from the left of the help array values (say index i=0), set k=num/values[i].
2. If k>0, append symbols[i] to the result string `roman`, and deduce values[i] from the orginal num.
3. Set i=i+1 and continue this calculation until num=0
4. At last, `roman` is the desired roman numerical so return it as a string.  

score 2

### Solution

```java
public String intToRoman(int num) {
    final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    final String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
  
    StringBuffer roman = new StringBuffer();
    int i = 0;
    while(num>0){
        int k = num / values[i];
        while(k-->0){
            roman.append(symbols[i]);
            num -= values[i];
        }
        i++;
    }
    return roman.toString();
}
```

## Extra Points: Median in two sorted array

## Approach I: Use Arrays.sort()

As a start, I will take a dummy solution of using Java's Arrays.sort() API, which has s time complexity of $O((m+n)*log(m+n))$, and a space complexity of $O(m+n)$ because it uses an extra nums[] array to hold all the elments in these two input arrays.  

extra credit 2

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums=Arrays.copyOf(nums1, nums1.length+nums2.length);
        for(int i=nums1.length;i<nums.length;i++){
            nums[i]=nums2[i-nums1.length];
        }
        
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        
        int n=nums.length;
        return 1.0*(nums[n/2]+nums[(n-1)/2])/2;
    }
}
```

### Approach II: Use merge sort idea

We can use merge sort strategy taking advantage of  the input arrays are both sorted. Here is the solution. 

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] nums=new int[nums1.length+nums2.length];
    int i=0,j=0;
    while(i<nums1.length && j<nums2.length) {
        if(nums1[i]>nums2[j]) nums[i+j]=nums2[j++];
        else nums[i+j]=nums1[i++];
    }
    while(i<nums1.length) nums[i+j]=nums1[i++];
    while(j<nums2.length) nums[i+j]=nums2[j++];
  
    int n=nums.length;
    return 1.0*(nums[n/2]+nums[(n-1)/2])/2;
}
```

**Complexity**:

*Time*: O(m+n). Merge the sorted array could take as much as O(m+n) time.

*Space*: O(m+n). An extra help array nums contains (m+n) numbers. 

**Improvements**: This solution could be improved to only use half of the space and time by just storing the first half (+1 when the total length is even) of all the elements. However, the complexity does not reduce in this case. So I won't bother to provide the code.
