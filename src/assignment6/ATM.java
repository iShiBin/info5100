package assignment6;

import java.util.*;

class User{
  private String name, address, phoneNumber, bankAccountNumber;
  private int age;//stand for birth year because age is changing.
  
  String getName() {
    return name;
  }
  void setName(String name) {
    this.name = name;
  }
  String getAddress() {
    return address;
  }
  void setAddress(String address) {
    this.address = address;
  }
  String getPhoneNumber() {
    return phoneNumber;
  }
  void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  String getBankAccountNumber() {
    return bankAccountNumber;
  }
  void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }
  int getAge() {
    return age;
  }
  void setAge(int age) {
    this.age = age;
  }
  
  protected User(){};
  User(String name, String phone){
    this.name=name;
    this.phoneNumber=phone;
  }
  
  public String toString(){
    return name+", "+age+", "+phoneNumber+", "+bankAccountNumber;
  }
}

class ATMUser extends User{
  private double availableBalance;
  private String password;
  
  double getAvailableBalance() {
    return availableBalance;
  }
  void setAvailableBalance(double availableBalance) {
    this.availableBalance = availableBalance;
  }
  String getPassword() {
    return password;
  }
  void setPassword(String password) {
    this.password = password;
  }
  
  protected ATMUser(){}
  
  ATMUser(String name, String phone){
    super(name,phone);
  }
  
  ATMUser(String name, int birthYear, String phone, String card){
    super(name, phone);
    super.setAge(birthYear);
    super.setBankAccountNumber(card);
    this.availableBalance=0.0;
  }
  
  ATMUser(String name, int birthYear, String phone, String card, String password){
    this(name, birthYear, phone, card);
    this.setPassword(password);
  }
  
  public String toString(){
    return super.toString() +", "+password+", "+availableBalance;
  }
  
}

class ATM {
  enum Transaction{
    WithDrawal("WithDrawal"), Deposit("Deposit");
    String name;
    
    Transaction(String name){
      this.name=name;
    }

    public String toString(){
      return String.format("%-10s", this.name);
    }
  }
  
  private double availableAmountInMachine;
  double getAvailableAmountInMachine() {
    return availableAmountInMachine;
  }

  private double transactionFee;
  private Map<String, ATMUser> customers;
  private static Map<String, List<String>> transactions;
  private static final int RECENT_TRANS_NUM=10;
  private static final int MAX_TRY_TIMES=3;
  private Scanner scanner;
  
  
  double getTransactionFee() {
    return transactionFee;
  }

  void setTransactionFee(double transactionFee) {
    this.transactionFee = transactionFee;
  }

  Map<String, ATMUser> getCustomers() {
    return customers;
  }

  void setCustomers(Map<String, ATMUser> customers) {
    this.customers = customers;
  }

  static Map<String, List<String>> getTransactions() {
    return transactions;
  }

  static void setTransactions(Map<String, List<String>> transactions) {
    ATM.transactions = transactions;
  }

  ATM(double money, double fee){
    availableAmountInMachine=money;
    transactionFee=fee;
    customers=new HashMap<>();
    transactions=new HashMap<>();
    scanner=new Scanner(System.in);
  }
  
  public void init(){
    System.out.println("Welcome! Are you a new user? (1:Yes/0:No)");
    int n=scanner.nextInt();
    if(n==1){
      ATMUser user=this.register();
      this.run(user);
    }else if(n==0){
      System.out.println("Press 1 to login, 0 to reset your password.");
      if(scanner.nextInt()==1) this.login();
      else this.promptPasswordReset();
    }else{
      System.out.println("Invalid input. Please try again.");
      this.init();
    }
  }
  
  ATMUser register(){
    ATMUser newUser=new ATMUser();
    
    System.out.print("\nEnter your name:");
    newUser.setName(scanner.nextLine().trim());
    
    System.out.print("\nEnter your age<DoB>:");
    newUser.setAge(Integer.valueOf(scanner.nextLine()));
    
    System.out.print("\nEnter your phone number:");
    newUser.setPhoneNumber(scanner.nextLine().trim());
    
    System.out.print("\nEnter your bank account number:");
    newUser.setBankAccountNumber(scanner.nextLine().trim());
    
    System.out.print("\nEnter your password:");
    newUser.setPassword(scanner.nextLine());
    
    this.register(newUser);
    return newUser;
  }
  
  void register(ATMUser user) {
    if (!this.customers.containsKey(user.getPhoneNumber())) {
      customers.put(user.getPhoneNumber(), user);
      System.out.println("Your account has been created with phone number as ID.");
    } else {
      System.out.println("This phone has been registered. Please try again!");
      this.register();
    }

  }
  
  void register(String name, int age, String phone, String bankAccount, String password){
    ATMUser user=new ATMUser(name, age, phone, bankAccount, password);
    this.register(user);
  }
  
  private void login(){
    this.login(ATM.MAX_TRY_TIMES, null);
  }
  
  boolean promptPasswordReset(){
    System.out.print("\nTo reset your password, please enter your name, year of birth, and phone number:");
    String name=scanner.next().trim();
    int yearOfBirth=scanner.nextInt();
    String phone=scanner.next().trim();
    if(this.validate(name, yearOfBirth, phone)){
      System.out.print("\nYou are a registered user. Please enter your new password:");
      String password=scanner.next();
      return this.resetPassword(name, yearOfBirth, phone, password);
    }else{
      System.out.print("\nYour phone number does not exist in our system. Try again!");
      this.init();
      return false;
    }
  }
  

  boolean resetPassword(String name, int yearOfBirth, String phone, String newPassword){
    if(this.validate(name, yearOfBirth, phone)){
      this.changePassword(this.customers.get(phone), newPassword);
      return true;
    }else{
      return false;
    }
  }
  
  private void run(ATMUser user) {
    
    try {
      while (true) {
        this.displayMenu();
        System.out.print("\nPress a number to start a transaction:");
        int n = scanner.nextInt();
        this.displayMenu();
        if (n == 1)
          this.getBalance(user);
        else if (n == 2) {
          System.out.println("How much would you like to withdraw?");
          this.withDrawal(user, scanner.nextDouble());
        } else if (n == 3) {
          System.out.println("How much would you like to deposit?");
          this.deposit(user, scanner.nextDouble());
        } else if (n == 4)
          this.recentTransactions(user);
        else if (n == 5) {
          this.changePassword(user, scanner.nextLine());
        } else if (n == 0) {
          this.exit();
        } else {
          System.out.println(n + " is not legal, so please try again.");
        }
      }
    } catch (Exception e) {
      System.out.println("Exception. XXX Something went wrong XXX");
      e.printStackTrace();
    } finally {
      scanner.close();
    }
  }
  
  private void exit(){
    scanner.close();
    System.out.println("Thanks! See you next time...");
  }
  
  private void login(int tryTimeLeft, String uid){
    if(tryTimeLeft==0){
      StringBuilder msg=new StringBuilder("Exit: ");
      if(uid==null || uid.isEmpty()) msg.append("Your account doesn't exist.");
      else msg.append("Trying wrong password is more than the limit.");
    }else{
      System.out.print("\nEnter your phone number and password to login: ");
      String phone=scanner.next(), pwd=scanner.next();
      int x=this.authenticate(phone, pwd);
      if(x==1){
        this.run(customers.get(phone));
      }else if(x==0){
        System.out.println("Your password is wrong. Retry!");
        login(--tryTimeLeft, phone);
      }else{
        login(--tryTimeLeft, null);
      }
    }
  }
  
  /**
   * Expose for testing purpose.
   * @param phone
   * @param password
   * @return
   */
  boolean login(String phone, String password){
    if(this.authenticate(phone, password)==1) return true;
    else return false;
  }
  
  /**
   * 
   * @param phone
   * @param password
   * @return -1: id does not exist; 0: phone exists but password is wrong; 1: authenticated successfully
   */
  private int authenticate(String phone, String password){
    ATMUser user=customers.get(phone);
    if(user==null) return -1;
    else if(!user.getPassword().equals(password)) return 0;
    else return 1;
  }
  
  private boolean validate(String name, int yearOfBirth, String phone){
    ATMUser user=customers.get(phone);
    if(user!=null && user.getAge()==yearOfBirth && user.getPhoneNumber().equals(phone)) return true;
    else return false;
  }
  
  double getBalance(ATMUser user){
    return user.getAvailableBalance();
  }
  
  boolean withDrawal(ATMUser user, double money){
    if(user.getAvailableBalance()<money) return false;
    else{
      double balance=user.getAvailableBalance()-money-this.transactionFee;
      user.setAvailableBalance(balance);
      this.availableAmountInMachine-=money;
      this.logTransaction(user, Transaction.WithDrawal, money);
      return true;
    }
  }
  
  private void logTransaction(ATMUser user, Transaction trans, double money){
    String log=trans+" - "+String.format("%.2f", money)+" (fee:"+this.transactionFee+")";
    if(!transactions.containsKey(user.getPhoneNumber())){
      transactions.put(user.getPhoneNumber(), new ArrayList<>());
    }
    transactions.get(user.getPhoneNumber()).add(log);
  }
  
  void deposit(ATMUser user, double money){
    double balance=user.getAvailableBalance()+money-this.transactionFee;
    user.setAvailableBalance(balance);
    this.availableAmountInMachine+=money;
    this.logTransaction(user, Transaction.Deposit, money);
  }
  
  void recentTransactions(ATMUser user){
    List<String> list=transactions.get(user.getPhoneNumber());
    if(list!=null) {
      System.out.println("The recent "+ATM.RECENT_TRANS_NUM+" transactions are:");
      int end=list.size()-1;
      for(int i=end;i>=Math.max(end-10, 0);i--){
        System.out.println(list.get(i));
      }
    }else{
      System.out.println("No transactoins.");
    }
  }
  
  private boolean changePassword(ATMUser user, String newPassword){
    if(this.customers.containsKey(user.getPhoneNumber())){
      user.setPassword(newPassword);
      return true;
    }else return false;
  }
  
  private void displayMenu() {
    System.out.println("1.Check Balandce");
    System.out.println("2.WithDrawal");
    System.out.println("3.Deposit");
    System.out.println("4.Recent Transactions");
    System.out.println("5.Change Password");
    System.out.println("0.Exit");
  }
  
}