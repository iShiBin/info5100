package assignment6;

import java.io.*;
import java.util.*;

class User implements Serializable {
  
  private String name, address, phoneNumber, bankAccountNumber;
  private int birthYear;
  
  private static final long serialVersionUID = -1540178469363304947L;
  
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
    return name+", "+birthYear+", "+phoneNumber+", "+bankAccountNumber+", "+address;
  }
}

class ATMUser extends User implements Serializable {

 /**
   * 
   */
  private static final long serialVersionUID = -1540178469363304947L;
  
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
  
  protected static Map<String, ATMUser> customers;//<bankAccountNumber, ATMUser>
  protected static Map<String, String> phoneToAccount;//map phone info. to account
  protected static Map<String, List<String>> transactions;
  
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
    ATM.phoneToAccount = phoneToAccount;
  }

  void setTransactionFee(double transactionFee) {
    this.transactionFee = transactionFee;
  }

  protected Map<String, ATMUser> getCustomers() {
    return customers;
  }

  protected void setCustomers(Map<String, ATMUser> customers) {
    ATM.customers = customers;
  }

  static Map<String, List<String>> getTransactions() {
    return transactions;
  }

  static void setTransactions(Map<String, List<String>> transactions) {
    ATM.transactions = transactions;
  }

  protected ATM(){}
  
  ATM(double money, double fee){
    availableAmountInMachine=money;
    transactionFee=fee;
    customers=new HashMap<>();
    transactions=new HashMap<>();
    scanner=new Scanner(System.in);
    phoneToAccount=new HashMap<>();
  }
  
  public void init (){
    System.out.print("Welcome! Are you a new user? (1:Yes/0:No and log me in./9:No, but I forget my password.)");
    try {
      String item=scanner.nextLine();
      if(item.equals("1")){
        ATMUser user=this.register();
        this.run(user);
      }else if(item.equals("0")){
        this.login();
      }else if(item.equals("9")){
        this.promptPasswordReset();
      }else if(item.equals("*")){
        this.exit();
      }
      else{
        System.out.println("\nInvalid input. Please try again, and enter * to exit");
        this.init();
      }
    } catch (NoSuchElementException ne) {
      System.out.println("no line was found");
      ne.printStackTrace();
    } catch (IllegalStateException ie){
      System.out.println("this scanner is closed");
      ie.printStackTrace();
    }
  }
  
  ATMUser register() {
    ATMUser user = new ATMUser();

    System.out.print("\nWhat is your bank account number<Enter>:");

    String account = scanner.nextLine().trim();
    if (customers.containsKey(account)) {
      System.out.print("\nThis account seems already registerred.");
      System.out.print("\nPress 1 to continue to login, and 0 to try register again:");
      String item = scanner.nextLine();
      if (item.equals("1"))
        this.login();
      else
        this.register();
    } else {
      user.setBankAccountNumber(account);

      System.out.print("\nChoose a password and press <Enter>:");
      user.setPassword(scanner.nextLine());

      // user=this.getCustomers().get(user.getBankAccountNumber());

      System.out.print("\nWhat is your phone number?");
      String phone = "";
      while (phone != null && !phone.equals("0")) { // 0 to exit
        phone = scanner.nextLine().trim();
        if (phoneToAccount.containsKey(phone))
          System.out.println("Verify your phone number or try another one because it has been used.");
        else
          break;
      }
      user.setPhoneNumber(phone);

      this.register(user);// only register when having a valid phone number

      System.out.print("\nWhat is your name:");
      user.setName(scanner.nextLine().trim());

      System.out.print("\nWhich year did you born?");
      user.setBirthYear(Integer.valueOf(scanner.nextLine()));

      System.out.println("\nAt last, what is your address? (optional - Directly Enter to skip)");
      String address = scanner.nextLine();
      if (address != null && !address.isEmpty()) {
        user.setAddress(address);
      } else {
        System.out.println("\nInfo: No address information is recorded.");
      }
    }

    return user;
  }
  
  void register(ATMUser user) {
    if (customers.containsKey(user.getBankAccountNumber())
        || phoneToAccount.containsKey(user.getPhoneNumber())) {
      System.out.println("\nFailed. This bank account or phone number has been used. Please verify and try again!");
      this.register();
    } else {
      phoneToAccount.put(user.getPhoneNumber(), user.getBankAccountNumber());
      customers.put(user.getBankAccountNumber(), user);
      System.out.printf("\nRegistered. Your login ID is %s, and password is %s \n", user.getPhoneNumber(), user.getPassword());
    }
  }
  
  void register(String name, int year, String phone, String bankAccount, String password){
    ATMUser user=new ATMUser(name, year, phone, bankAccount, password);
    this.register(user);
  }
  
  private void login(){
    this.login(ATM.MAX_TRY_TIMES,"");
  }
  
  boolean promptPasswordReset() throws NumberFormatException {
    System.out.print("\nTo reset your password, please enter your name, year of birth, and phone number:");
    String name=scanner.nextLine().trim();
    int yearOfBirth=Integer.valueOf(scanner.nextLine().trim());
    String phone=scanner.nextLine().trim();
    if(this.validate(name, yearOfBirth, phone)){
      System.out.print("\nYou are a registered user. Please enter your new password:");
      String password=scanner.nextLine();
      return this.resetPassword(name, yearOfBirth, phone, password);
    }else{
      System.out.print("\nYour phone number does not exist in our system. Try again!");
      this.init();
      return false;
    }
  }
  
  boolean resetPassword(String name, int birthYear, String phone, String newPassword){
    if(this.validate(name, birthYear, phone)){
      return this.changePassword(customers.get(phoneToAccount.get(phone)), newPassword);
    }else{
      return false;
    }
  }
  
  private void run(ATMUser user) {
    
    try {
      while (true) {
        this.displayMenu();
        String item = scanner.nextLine();
        if (item.equals("1"))
          this.getBalance(user);
        else if (item.equals("2")) {
          System.out.println("\nHow much would you like to withdrawal?");
          double withdrawal=Double.valueOf(scanner.nextLine());
          this.withDrawal(user, withdrawal);
        } else if (item.equals("3")) {
          System.out.println("\nHow much would you like to deposit?");
          double deposit=Double.valueOf(scanner.nextLine());
          this.deposit(user, deposit);
        } else if (item.equals("4"))
          this.recentTransactions(user);
        else if (item.equals("5")) {
          System.out.print("\nSet your new password:");
          this.changePassword(user, scanner.nextLine());
        } else if (item.equals("0")) {
          this.exit();
        } else {
          System.out.println(item + " is not valid, so please try again.");
        }
      }
    } catch (NumberFormatException nfe){
      System.out.println("The number you enterred is invalid. Please try again.");
      run(user);
    }
    catch (Exception e) {
      System.out.println("Unhandled EXCEPTION.");
      e.printStackTrace();
    } finally {
      scanner.close();
    }
  }
  
  private void exit(){
    System.out.println("See you next time...");
    scanner.close();
    System.exit(0);
  }
  
  private void login(int tryTimeLeft, String phoneNumber){
    if(tryTimeLeft==0){
      System.out.println("Failed. You tried more than max limit times.");
      this.init();
    }else if(phoneNumber!=null && !phoneNumber.isEmpty()){
      System.out.println("Your password is wrong. Retry!");
      login(--tryTimeLeft, phoneNumber);
    }else{
      System.out.print("\nEnter your phone number<Enter> and password<Enter> to login: ");
      String phone=scanner.nextLine().trim(), password=scanner.nextLine();
      
      int type=this.authenticate(phone, password);
      if(type==1) {
        String account=phoneToAccount.get(phone);
        this.run(this.getCustomers().get(account));
      }
      else if(type==0){
        this.login(--tryTimeLeft, phone);
      }else{
        System.out.println("Your account doesn't even exist.");
        this.login(--tryTimeLeft, "");
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
    ATMUser user=customers.get(phoneToAccount.get(phone));
    if(user==null) return -1;
    else if(user.getPassword().equals(password)) return 1;
    else return 0;
  }
  
  private boolean validate(String name, int birthYear, String phone) {
    ATMUser user = customers.get(phoneToAccount.get(phone));
    if (user != null && user.getName().equals(name) && user.getBirthYear() == birthYear
        && user.getPhoneNumber().equals(phone))
      return true;
    else
      return false;
  }
  
  double getBalance(ATMUser user){
    double balance=user.getAvailableBalance();
    System.out.println("Your current balance is: "+ balance);
    return balance;
  }
  
  boolean withDrawal(ATMUser user, double money){
    if(user.getAvailableBalance()<money) {
      System.out.println("Failed. You don't have enough money.");
      return false;
    }
    else{
      double balance=user.getAvailableBalance()-money-this.transactionFee;
      user.setAvailableBalance(balance);
      this.availableAmountInMachine-=money;
      this.logTransaction(user, Transaction.WithDrawal, money);
      return true;
    }
  }
  
  private void logTransaction(ATMUser user, Transaction trans, double money){
    String log=trans+" - "+String.format("%8.2f", money)+" (fee:"+this.transactionFee+")";
    if(!transactions.containsKey(user.getBankAccountNumber())){
      transactions.put(user.getBankAccountNumber(), new ArrayList<>());
    }
    transactions.get(user.getBankAccountNumber()).add(log);
    System.out.println(log);
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
      System.out.println("\nThe recent "+ATM.RECENT_TRANS_NUM+" transactions are:");
      int end=list.size()-1;
      for(int i=end;i>=Math.max(end-10, 0);i--){
        System.out.println(list.get(i));
      }
      System.out.println("**End of rencent transactions**\n");
    }else{
      System.out.println("No transactoins.");
    }
  }
  
  private boolean changePassword(ATMUser user, String newPassword){
    if(customers.containsKey(user.getBankAccountNumber())){
      user.setPassword(newPassword);
      System.out.println("Your password has been changed to "+newPassword);
      return true;
    }else {
      System.out.println("No such user. Please check your login status.");
      return false;
    }
  }
  
  private void displayMenu() {
    StringBuilder menu=new StringBuilder("\n*****MENU*****\n");
    menu.append("Press a number to start a transaction:\n");
    menu.append("1.Check Balance\n");
    menu.append("2.WithDrawal\n");
    menu.append("3.Deposit\n");
    menu.append("4.Recent Transactions\n");
    menu.append("5.Change Password\n");
    menu.append("0.Exit");
    System.out.println(menu);
  }
  
  protected static final String USER_DATA_PATH="./data/ATMUsers.dat";
  protected static final String PHONE_DATA_PATH="./data/PhoneAndAccount.dat";
  protected static final String TRANS_DATA_PATH="./data/Transactions.dat";
  
  protected static void saveData() throws IOException, ClassNotFoundException{
    File users=new File(USER_DATA_PATH);
    FileOutputStream fos=new FileOutputStream(users);
    ObjectOutputStream oos=new ObjectOutputStream(fos);
    oos.writeObject(ATM.customers);
    oos.close();
    fos.close();
    
    File phoneAndAccount=new File(PHONE_DATA_PATH);
    fos=new FileOutputStream(phoneAndAccount);
    oos=new ObjectOutputStream(fos);
    oos.writeObject(ATM.phoneToAccount);
    oos.close();
    fos.close();
    
    File trans=new File(TRANS_DATA_PATH);
    fos=new FileOutputStream(trans);
    oos=new ObjectOutputStream(fos);
    oos.writeObject(ATM.transactions);
    oos.close();
    fos.close();
  }
  
  @SuppressWarnings("unchecked")
  protected static void loadData(String path) throws IOException, ClassNotFoundException{
    File file = new File(ATM.USER_DATA_PATH);
    FileInputStream f = new FileInputStream(file);
    ObjectInputStream s = new ObjectInputStream(f);
    ATM.customers = (Map<String, ATMUser>) s.readObject();
    s.close();
    f.close();
    
    file = new File(ATM.PHONE_DATA_PATH);
    f = new FileInputStream(file);
    s = new ObjectInputStream(f);
    ATM.phoneToAccount = (Map<String, String>) s.readObject();
    s.close();
    f.close();
    
    file = new File(ATM.TRANS_DATA_PATH);
    f = new FileInputStream(file);
    s = new ObjectInputStream(f);
    ATM.transactions = (Map<String, List<String>>) s.readObject();
    s.close();
    f.close();
  }
  
  protected static void clearData(){
    File file = new File(ATM.USER_DATA_PATH);
    file.delete();
    file = new File(ATM.PHONE_DATA_PATH);
    file.delete();
    file=new File(ATM.TRANS_DATA_PATH);
    file.delete();
  }
  
}