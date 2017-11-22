package assignment;

public class Assignment2 {

  // 1. Calculate Employee Salary
  public double employeeSalary(double hours) {
    if (hours <= 0)
      return 0;

    double salary = 0;
    if (hours > 48) {
      // max salary
      salary += 36 * 15;
      salary += 5 * 15 * 1.5;
      salary += (48 - 36 - 5) * 15 * 2;
    } else if (hours > 41) {
      salary += 36 * 15;
      salary += 5 * 15 * 1.5;
      salary += (hours - 36 - 5) * 15 * 2;
    } else if (hours > 36) {
      salary += 36 * 15;
      salary += (hours - 36) * 15 * 1.5;
    } else {
      salary += hours * 15;
    }
    return salary;
  }

  // 2. Sum Digits
  public int addDigits(int input) {
    if (input < 0)
      input = -input;
    int sum = 0;
    while (input != 0) {
      sum += input % 10;
      input /= 10;
    }
    return sum < 10 ? sum : addDigits(sum);
  }

  // 3. Perfect Numbers
  public void printPerfectNumbers(int n) {
    for (int m = 1; m <= n; m++) {
      if (isPerfectNumber(m)) {
        System.out.println(m);
      }
    }
  }

  private boolean isPerfectNumber(int num) {
    if (num == 0)
      return false;

    int sum = 0;
    for (int i = 1; i < Math.sqrt(num); i++) {
      if (num % i == 0)
        sum += num / i + i;
    }
    return sum - num == num ? true : false;
  }

  // 6. Generates Isoscele Right Angled Triangles
  public void printIsoscelesTriangle(int n) {
    if (n <= 0)
      return;
    if (n == 1) {
      System.out.println("*");
      return;
    }
    // first line
    StringBuilder lines = new StringBuilder("*");

    // following lines
    for (int i = 1; i < n - 1; i++) {
      lines.append("\n*");
      for (int j = 1; j < i; j++)
        lines.append(" ");
      lines.append("*");
    }
    lines.append("\n");

    // last line
    while (n-- > 0) {
      lines.append("*");
    }

    // print all lines in the end
    System.out.println(lines);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Assignment2 test = new Assignment2();
    test.printIsoscelesTriangle(3);
  }
}

// 4. Pizza
class Pizza {
  String type, name;
  double price;
  int loyaltyPoint;
  int number; // for 5. Customer

  Pizza() {
  }

  Pizza(String name) {
    this.name = name;
  }

  Pizza(String name, double price) {
    this.name = name;
    this.price = price;
  }

  Pizza(String name, double price, int number) {
    this(name, price);
    this.number = number;
  }
}

// 5. Customer
class Customer {
  String name;
  boolean gender;
  int age;
  Pizza pizzas[];

  public static void main(String[] args) {

    Customer bin = new Customer();

    // bin starts to order two kinds of pizzas
    bin.pizzas = new Pizza[2];
    bin.pizzas[0] = new Pizza("Summer Fire", 10.5, 1);
    bin.pizzas[1] = new Pizza("Winter Snow", 12.8, 2);

    // sum up the money bin spends
    double bill = 0;
    for (Pizza pizza : bin.pizzas) {
      bill += pizza.price * pizza.number;
    }

    System.out.println(bill);
  }
}
