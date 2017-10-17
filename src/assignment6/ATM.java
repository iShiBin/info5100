package assignment6;

import java.util.*;

class User{
  private String name, address, phoneNumber, bankAccountNumber;
  private int birthYear;
  
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
  String getBankAccountNumber() {
    return bankAccountNumber;
  }
  void setBankAccountNumber(String bankAccountNumber) {
    this.bankAccountNumber = bankAccountNumber;
  }
  String getPhoneNumber() {
    return phoneNumber;
  }
  void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  int getBirthYear() {
    return birthYear;
  }
  void setBirthYear(int year) {
    this.birthYear = year;
  }
  
  protected User(){};
  User(String name, String phone){
    this.name=name;
    this.phoneNumber=phone;
  }
  
  public String toString(){
    return name+", "+birthYear+", "+phoneNumber+", "+bankAccountNumber;
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
    super.setBirthYear(birthYear);
    super.setBankAccountNumber(card);
    this.availableBalance=0.0;
  }
  
  ATMUser(String name, int birthYear, String phone, String card, String password){
    this(name, birthYear, phone, card);
    this.password=password;
  }
  
  public String toString(){
    return super.toString() +", "+password+", "+availableBalance;
  }
}

enum Transaction {
  WithDrawal("WithDrawal"), Deposit("Deposit");
  String name;

  Transaction(String name) {
    this.name = name;
  }

  public String toString() {
    return String.format("%-10s", this.name);
  }
}

class ATM {
  private double availableAmountInMachine;
  private double transactionFee;
  private static final int RECENT_TRANS_NUM=10;
  private static final int MAX_TRY_TIMES=3;
  
  private Map<String, ATMUser> customers;//<bankAccountNumber, ATMUser>
  private Map<String, String> phoneToAccount;//map phone info. to account
  private static Map<String, List<String>> transactions;
  
  private Scanner scanner;
  
  double getAvailableAmountInMachine() {
    return availableAmountInMachine;
  }
  
  double getTransactionFee() {
    return transactionFee;
  }

  Map<String, String> getPhoneToAccount() {
    return phoneToAccount;
  }

  void setPhoneToAccount(Map<String, String> phoneToAccount) {
    this.phoneToAccount = phoneToAccount;
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
    this.phoneToAccount=new HashMap<>();
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
    ATMUser user=new ATMUser();
    
    System.out.print("\nEnter your bank account number:");
    user.setBankAccountNumber(scanner.nextLine().trim());
    
    System.out.print("\nEnter your password:");
    user.setPassword(scanner.nextLine());
    
    this.register(user);
    
    System.out.print("You account has been setup. ");
    
//    user=this.getCustomers().get(user.getBankAccountNumber());
    
    System.out.print("Enter your phone number as the login id:");
    String phone=null;
    while(true){
      phone=scanner.nextLine().trim();
      if(this.phoneToAccount.containsKey(phone))
        System.out.println("You cannot use this phone because it has been used."
            + " Verify your phone number or try another one.");
      else break;
    }
    user.setPhoneNumber(phone);
    
    System.out.println("\nAdd more information (name and birth year),"
        + " which you can use to reset your password if you forget it.");
    user.setName(scanner.nextLine().trim());
    user.setBirthYear(Integer.valueOf(scanner.next()));
    
    System.out.println("At last, you can add your address(optional). Please enter to skip.");
    String address=scanner.nextLine();
    if(address!=null && !address.isEmpty()) {
      user.setAddress(address);
    }
    
    return user;
  }
  
  void register(ATMUser user) {
    if (this.customers.containsKey(user.getBankAccountNumber())
        || this.phoneToAccount.containsKey(user.getPhoneNumber())) {
      System.out.println("Failed. This bank account or phone number has been used. Please verify and try again!");
      this.register();
    } else {
      this.phoneToAccount.put(user.getPhoneNumber(), user.getBankAccountNumber());
      customers.put(user.getBankAccountNumber(), user);
      System.out.printf("Finished. Your account is %s, and password is %s", user.getPhoneNumber(), user.getPassword());
    }
  }
  
  void register(String name, int year, String phone, String bankAccount, String password){
    ATMUser user=new ATMUser(name, year, phone, bankAccount, password);
    this.register(user);
  }
  
  private void login(){
    this.login(ATM.MAX_TRY_TIMES, "");
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
  

  boolean resetPassword(String name, int birthYear, String phone, String newPassword){
    if(this.validate(name, birthYear, phone)){
      return this.changePassword(this.customers.get(this.phoneToAccount.get(phone)), newPassword);
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
      else msg.append("Trying wrong password is more than the limit times.");
    }else{
      System.out.print("\nEnter your phone number and password to login: ");
      String phone=scanner.nextLine().trim(), pwd=scanner.nextLine();
      int x=this.authenticate(phone, pwd);
      if(x==1){
        this.run(customers.get(this.phoneToAccount.get(phone)));
      }else if(x==0){
        System.out.println("Your password is wrong. Retry!");
        login(--tryTimeLeft, this.phoneToAccount.get(phone));
      }else{
        login(--tryTimeLeft, "");
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
   * @param account
   * @param password
   * @return -1: id does not exist; 0: phone exists but password is wrong; 1: authenticated successfully
   */
  private int authenticate(String phone, String password){
    ATMUser user=customers.get(this.phoneToAccount.get(phone));
    if(user==null) return -1;
    else if(user.getPassword().equals(password)) return 1;
    else return 0;
  }
  
  private boolean validate(String name, int birthYear, String phone) {
    ATMUser user = customers.get(this.phoneToAccount.get(phone));
    if (user != null && user.getName().equals(name) && user.getBirthYear() == birthYear
        && user.getPhoneNumber().equals(phone))
      return true;
    else
      return false;
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
    if(!transactions.containsKey(user.getBankAccountNumber())){
      transactions.put(user.getBankAccountNumber(), new ArrayList<>());
    }
    transactions.get(user.getBankAccountNumber()).add(log);
  }
  
  void deposit(ATMUser user, double money){
    double balance=user.getAvailableBalance()+money-this.transactionFee;
    user.setAvailableBalance(balance);
    this.availableAmountInMachine+=money;
    this.logTransaction(user, Transaction.Deposit, money);
  }
  
  void recentTransactions(ATMUser user){
    List<String> list=transactions.get(user.getBankAccountNumber());
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
    if(this.customers.containsKey(user.getBankAccountNumber())){
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