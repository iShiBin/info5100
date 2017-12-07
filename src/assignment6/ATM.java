/* 
Awesome work score 10 + extra credit 2
*/
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
  private static final double DEFAULT_AMOUNT_IN_MACNINE = 10000;
  private static final double DEFAULT_TRANSACTION_FEE = 0.2;
  private double availableAmountInMachine;
  private double transactionFee;

  
  private static final int RECENT_TRANS_NUM=10;
  private static final int MAX_TRY_TIMES=3;

  /**
   * 'customers' to store the main ATMUser information by mapping the bank account number to ATMUser
   */
  protected static Map<String, ATMUser> customers;
  
  /**
   * To enable login using phone number, I map the phone number to the primary identify "bank account number.
   * If it allows user to login using other identity like email, just build another mapping. That's it.
   */
  protected static Map<String, String> phoneToAccount;//map phone info. to account
  
  /**
   * This map 'transactions' stores the historical transactions information and shares with other ATM instances. 
   */
  protected static Map<String, List<String>> transactions;
  
  /**
   * Unified input reader to read user's input.
   */
  private Scanner scanner;
  
  //Note: Most of the methods are with 'default' access privilege in order to share with ATMTest for unit test purpose.
  
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

  protected static Map<String, List<String>> getTransactions() {
    return transactions;
  }

  protected static void setTransactions(Map<String, List<String>> transactions) {
    ATM.transactions = transactions;
  }

  /**
   * This is the default constructor and loads the history data.
   */
  public ATM(){
    this(true, true);
  }
  
  /**
   * 
   * @param loadHistoryData: True to load the history records. False if no load.
   */
  public ATM(boolean loadHistoryData, boolean startConsole) {
    this(ATM.DEFAULT_AMOUNT_IN_MACNINE, ATM.DEFAULT_TRANSACTION_FEE, loadHistoryData, startConsole);    
  }
  
  /**
   * Initialize the availableAmountInMachine, transactionFee, and load the history data by default.
   * @param money
   * @param fee
   */
  public ATM(double money, double fee){
    this(money, fee, true, true);
  }
  
  /**
   * Initialize the availableAmountInMachine, transactionFee and all other fields.
   * And then start this application. It is a protected constructor
   * because careless use may wipe the whole transaction data.
   * @param money: The available amount of money this machine has.
   * @param fee: Some fee will be charged on every transaction.
   * @param loadHistoricalRecords: True to load history account records. False if you do not want to.
   */
  protected ATM(double money, double fee, boolean loadHistoricalRecords, boolean startConsole){
    scanner=new Scanner(System.in);
    availableAmountInMachine=money;
    transactionFee=fee;
    if(!loadHistoricalRecords){
      ATM.customers=new HashMap<>();
      ATM.phoneToAccount=new HashMap<>();
      ATM.transactions=new HashMap<>();
    }else{
      ATM.loadData();
    }
    
    if(startConsole) this.init();
  }
  
  /**
   * Welcome the user and initialize before starting a real transaction.
   */
  private void init (){
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
  
  /**
   * Interact with the end user to register.
   * @return  A new ATMUser which will be used in future operations. 
   */
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
  
  /**
   * Register this user as an legal ATM user.
   * @param user: A potential customer.
   */
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
  
  /**
   * Expose for test purpose by ATMTest class.
   * @param name
   * @param year
   * @param phone
   * @param bankAccount
   * @param password
   */
  protected void register(String name, int year, String phone, String bankAccount, String password){
    ATMUser user=new ATMUser(name, year, phone, bankAccount, password);
    this.register(user);
  }
  
  private void login(){
    this.login(ATM.MAX_TRY_TIMES,"");
  }
  
  /**
   * Interact with user to reset the password.
   * NumberFormatException: The birth year may not be a valid integer year.
   * @return True: The password reset succeeded. False: Reset failed.
   */
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
  
  /**
   * This is the actual password reset method.
   * @param name
   * @param birthYear
   * @param phone
   * @param newPassword
   * @return
   */
  boolean resetPassword(String name, int birthYear, String phone, String newPassword){
    if(this.validate(name, birthYear, phone)){
      return this.changePassword(customers.get(phoneToAccount.get(phone)), newPassword);
    }else{
      return false;
    }
  }
  
  /**
   * Daemon Process: It runs forever unless the user choose to exit or some exception happens.
   * @param user: A valid and authorized ATM user.
   */
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
  
  /**
   * Close the input stream and exit this program, and save the data before quiting.
   */
  private void exit(){
    System.out.println("Bye. See you next time...");
    scanner.close();
    ATM.saveData();
//    System.exit(0);
    return;
  }
  
  /**
   * Check whether this is an authenticated user.
   * @param tryTimeLeft: A user can only try ATM.MAX_TRY_TIMES at most for security concerns.
   * @param phoneNumber: User's phone number.
   */
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
   * @return True if login succeeds. Otherwise, return False. 
   */
  protected boolean login(String phone, String password){
    if(this.authenticate(phone, password)==1) return true;
    else return false;
  }
  
  /**
   * Authenticate this user by the phone number and password
   * @param phone
   * @param password
   * @return -1: id does not exist; 0: phone exists but password is wrong; 1: authenticated successfully
   */
  private int authenticate(String phone, String password){
    ATMUser user=customers.get(phoneToAccount.get(phone));
    if(user==null) return -1;
    else if(user.getPassword().equals(password)) return 1;
    else return 0;
  }
  
  /**
   * Validate whether it is an actual existed user for password reset purpose.
   * @param name: a valid customer name
   * @param birthYear
   * @param phone
   * @return True if it is a valid user, and False if negative.
   */
  private boolean validate(String name, int birthYear, String phone) {
    ATMUser user = customers.get(phoneToAccount.get(phone));
    if (user != null && user.getName().equals(name) && user.getBirthYear() == birthYear
        && user.getPhoneNumber().equals(phone))
      return true;
    else
      return false;
  }
  
  /**
   * Get the user's current balance.
   * @param user: A valid registered ATM user.
   * @return The current balance for the current user.
   */
  double getBalance(ATMUser user){
    double balance=user.getAvailableBalance();
    System.out.println("Your current balance is: "+ balance);
    return balance;
  }
  
  /**
   * To get some money out from the ATM machine.
   * @param user: a valid ATM user.
   * @param money: the money amount the user wants to get.
   * @return True: if the withdrawal succeeds. False: If the current balance is less that the money to withdrawal.
   */
  protected boolean withDrawal(ATMUser user, double money){
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
  
  /**
   * Log a transaction in the transaction records on deposit or withdrawal.
   * @param user
   * @param trans
   * @param money
   */
  private void logTransaction(ATMUser user, Transaction trans, double money){
    String log=trans+" - "+String.format("%8.2f", money)+" (fee:"+this.transactionFee+")";
    if(!transactions.containsKey(user.getBankAccountNumber())){
      transactions.put(user.getBankAccountNumber(), new ArrayList<>());
    }
    transactions.get(user.getBankAccountNumber()).add(log);
    System.out.println(log);
  }
  
  /**
   * Deposit some money for this user, and invoke the logTransaction method to log this transaction.
   * @param user
   * @param money
   */
  protected void deposit(ATMUser user, double money){
    double balance=user.getAvailableBalance()+money-this.transactionFee;
    user.setAvailableBalance(balance);
    this.availableAmountInMachine+=money;
    this.logTransaction(user, Transaction.Deposit, money);
  }
  
  /**
   * Get the recent (defined by ATM.RECENT_TRANS_NUM) transactions of this user.
   * @param user: a valid user.
   */
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
  
  /**
   * Change the password for a valid user.
   * @param user
   * @param newPassword
   * @return True if succeed otherwise return False.
   */
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
  
  /**
   * Display the menu in the console so that a user can choose.
   */
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
  
//  Note: This is for saving the data to disk so that it won't lose after program quit.
  protected static final String USER_DATA_PATH="./data/ATMUsers.dat";
  protected static final String PHONE_DATA_PATH="./data/PhoneAndAccount.dat";
  protected static final String TRANS_DATA_PATH="./data/Transactions.dat";
  
  /**
   * Serialize to save the register users information, phone--account mapping and transaction records. 
   */
  protected static void saveData(){
    try {
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
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Load the previously saved data.
   */
  @SuppressWarnings("unchecked")
  protected static void loadData(){
    try {
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
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Just in case to clear all the data. Probably never used.
   */
  private static void clearData(){
    File file = new File(ATM.USER_DATA_PATH);
    file.delete();
    file = new File(ATM.PHONE_DATA_PATH);
    file.delete();
    file=new File(ATM.TRANS_DATA_PATH);
    file.delete();
  }
}
