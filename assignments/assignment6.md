# Assignment 6

## Instructions

*  Max score is 10.
*  Deadline is 11:59PM, Oct 21, Saturday.
*  Extra credits are added only if score is less than 10.
*  Try to attempt every question and keep practising from other online sites.
*  Upload all assignments to a single repository named ‘Assignments’.
*  Contact us if any assistance is needed.

## Questions

1. When a program fails due to an uncaught exception, the system automatically prints out the exception’s stack trace. If the failure is not easily reproducible, it may be difficult or impossible to get any more information. Therefore, it is critically important that the exception’s toString() method return, as much information as possible concerning the cause of the failure. In other words, the detail message of an exception should capture the failure for subsequent analysis. To capture the failure, the detail message of an exception should contain the values of all parameters and fields that “contributed to the exception.” (Score 2).
 - Create your own **MyIndexOutOfBoundException** Class. It should contain these parameters  

  * **lowerBound** - the lowest legal index value.  
   * **upperBound** - the highest legal index value.  
   * **index** - the current index value.  

   Test your code in main method, by creating an indexOutOfBoundException.
   Output error message should be like this:   

   ``` java
    “Error Message: Index: 10, but Lower bound: 0, Upper bound: 9”   
   ```

2. Modify the following parse() method so that it will compile: (Score 1)  

    ``` java
      public static void parse(File file) {
          RandomAccessFile input = null;
          String line = null;
          
          try {
              input = new RandomAccessFile(file, "r");
              while ((line = input.readLine()) != null) {
                  System.out.println(line);
              }
              return;
          } finally {
                if (input != null) {
                  input.close();
                }
            }
      }  
    ```

3. Design an **Automated Teller Machine** (ATM). Write the test cases to each function of the machine in AtmTest class. (Score 7).  
  - -  Create an **User** class with attributes *name*, *age*, *address*, *phoneNumber* and *bankAccountNumber*.
  - -  Create a **Atm** class with attributes *availableAmountInMachine*, *transactionFee* and *userData*.  
    *  *userData* should store **USER**, **PASSWORD**, and other account details internally in a dataStructure of your choice. 
    *  The constructor should initialize all the attributes.
  - -  The machine should be able to perform these actions.  
    *  Ask for **NEW USER** or **CURRENT USER** as the start.
    *  Create a **NEW USER** with a unique *bankAccountNumber* and *password*.
    *  **CURRENT USER** should be able to login using phoneNumber and password.
    *  **CURRENT USER** should be able to use *FORGOT PASSWORD*.
    *  *PASSWORD* can be resetted by validating the *name*, *age* and *phoneNumber* of the user.  
    *  After loggin the user should be able to use **availableBalance**, **withDrawal**, **deposit**, **recentTransactions**, **changePassword** and **exit**.  
    *  _recentTransaction_ should display the last 10 trasactions, in a format of *transactionName* - *amount*. (*transactionName* is *withDrawal* or *deposit*).  
    *  Add a *transactionFee* for every transaction done and update the *availableAmountInMachine*.
    *  Machine should not allow user to withdrawal more amount than his *availableBalance*.
    *  You can add additional fucntions to make it pretty. UI is not required.

    **EXTRA CREDIT**  
    Implement the above "ATM" by storing the above data in a file, rather than in a dataStructure.   (Score 2)

---

# Achievements

## 1. Design an Exception Class

```java
import java.util.Random;

class MyIndexOutOfBoundException extends RuntimeException {
  int lowerBound, upperBound, index;

  MyIndexOutOfBoundException() {
  }

  MyIndexOutOfBoundException(int current, int lowest, int highest) {
    this.index = current;
    this.lowerBound = lowest;
    this.upperBound = highest;
  }

  public String toString() {
    return "Error Message: Index: " + index + ", but Lower bound: " + this.lowerBound + ", Upper Bound: "
        + this.upperBound;
  }

  public static void main(String[] args) {
    final int SIZE = 100;
    int[] nums = new int[SIZE];

    Random random = new Random();

    System.out.println("Starting random access in an int array with size " + SIZE + " ...");
    while (true) {
      int i = random.nextInt(SIZE + SIZE / 2);
      try {
        System.out.println("Try to access the number with index: " + i);
        if (i >= SIZE)
          throw new MyIndexOutOfBoundException(i, 0, SIZE - 1);
        else
          nums[i] = random.nextInt(SIZE);
      } catch (Exception ex) {
        System.out.println(ex);
        return;
      }
    }
  }
}
```

## 2. Correct Function parse()

It is common to have exceptions when accessing files like the file is not there and it is locking by other application etc. In the parse() function, there are several exceptions like 'FileNotFoundException' and general 'IOException'. Here is the correct code without complile error.

```java
public static void parse(File file) throws IOException {
  RandomAccessFile input = null;
  String line = null;

  try {
    input = new RandomAccessFile(file, "r");// FileNotFoundException
    while ((line = input.readLine()) != null) {// IOException
      System.out.println(line);
    }
    return;
  } catch (FileNotFoundException e) {
    e.printStackTrace();
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    if (input != null) {
      input.close();// IOException
    }
  }
}
```

What I have done are:

* add `catch (FileNotFoundException e)` because of statement `input = new RandomAccessFile(file, "r");`
* add `catch (IOException e) ` because of `line = input.readLine()`
* add `throws IOException` in the method signature because this line `input.close();` in the `finally` code block might cause exception and it is urly to write nested `try...catch ` in this section. So I move it to the method signatruel.

# 3. Design an **Automated Teller Machine**

## Main Source Code

\src\assignment6\ATM.java

## Source Code Docs

\doc\assignment6\User.html

\doc\assignment6\ATMUser.html

\doc\assignment6\ATM.html

etc...

## JUnit Test Code

\src\assignment6\ATMTest.java

**JUnit Test Screenshot**

The screenshot is in \doc\assignment6\JUnitTestCases.png, and here is one regular test case.

```
Welcome! Are you a new user? (1:Yes/0:No and log me in./9:No, but I forget my password.)1

What is your bank account number<Enter>:44006645206

Choose a password and press <Enter>:password1

What is your phone number?2068180007

Registered. Your login ID is 2068180007, and password is password1 

What is your name:BinShi

Which year did you born?1985

At last, what is your address? (optional - Directly Enter to skip)
somewhere in the earth

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
1
Your current balance is: 0.0

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
3

How much would you like to deposit?
100
Deposit    -   100.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
50
WithDrawal -    50.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
100
Failed. You don't have enough money.

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
3

How much would you like to deposit?
500
Deposit    -   500.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
10
WithDrawal -    10.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
100
WithDrawal -   100.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
4

The recent 10 transactions are:
WithDrawal -   100.00 (fee:1.0)
WithDrawal -    10.00 (fee:1.0)
Deposit    -   500.00 (fee:1.0)
WithDrawal -    50.00 (fee:1.0)
Deposit    -   100.00 (fee:1.0)
**End of rencent transactions**


*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
1
Your current balance is: 435.0

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
20
WithDrawal -    20.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
30
WithDrawal -    30.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
50
WithDrawal -    50.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
100
WithDrawal -   100.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
70
WithDrawal -    70.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
50
WithDrawal -    50.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
35
35 is not valid, so please try again.

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
2

How much would you like to withdrawal?
198
Failed. You don't have enough money.

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
3

How much would you like to deposit?
100
Deposit    -   100.00 (fee:1.0)

*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
4

The recent 10 transactions are:
Deposit    -   100.00 (fee:1.0)
WithDrawal -    50.00 (fee:1.0)
WithDrawal -    70.00 (fee:1.0)
WithDrawal -   100.00 (fee:1.0)
WithDrawal -    50.00 (fee:1.0)
WithDrawal -    30.00 (fee:1.0)
WithDrawal -    20.00 (fee:1.0)
WithDrawal -   100.00 (fee:1.0)
WithDrawal -    10.00 (fee:1.0)
Deposit    -   500.00 (fee:1.0)
WithDrawal -    50.00 (fee:1.0)
**End of rencent transactions**


*****MENU*****
Press a number to start a transaction:
1.Check Balance
2.WithDrawal
3.Deposit
4.Recent Transactions
5.Change Password
0.Exit
0
Bye. See you next time...

```

