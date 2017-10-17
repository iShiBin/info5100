package assignment6;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ATMTest {
  
  ATM atm=new ATM(10000, 1);
  ATMUser user=new ATMUser("Bin", 1985, "2068180000", "44006645", "NewBin");

  @Test
  public void register() {
    atm.register(user);
    String info="Bin, 1985, 2068180000, 44006645, NewBin, 0.0";
    Assert.assertTrue(info.equals(atm.getCustomers().get(user.getBankAccountNumber()).toString()));
  }
  
  @Test
  public void resetPassword(){
    atm.register(user);
    String newPassword="IamBin";
    atm.resetPassword(user.getName(), user.getBirthYear(), user.getPhoneNumber(), newPassword);
    Assert.assertTrue(newPassword.equals(atm.getCustomers().get(user.getBankAccountNumber()).getPassword()));
  }
  
  @Test
  public void login(){
    atm.register(user);
    Assert.assertTrue(atm.login(user.getPhoneNumber(), user.getPassword()));
  }
 
//  test deposit and fee
  @Test
  public void deposit(){
    this.register();
    double money = 100+atm.getTransactionFee();
    atm.deposit(user, money);
    double balance=atm.getCustomers().get(user.getBankAccountNumber()).getAvailableBalance();
    Assert.assertTrue( balance == money-atm.getTransactionFee());
  }
  
//  test withdrawal and fee
  @Test
  public void withDrawal(){
    this.deposit();
    double money = 10-atm.getTransactionFee();
    atm.withDrawal(user, money);
    double balance=atm.getCustomers().get(user.getBankAccountNumber()).getAvailableBalance();
    Assert.assertTrue(balance==90);
    
//    expect to fail to get more money than the atm has 
    Assert.assertFalse(atm.withDrawal(user, Integer.MAX_VALUE));
    Assert.assertTrue(balance==90);// the balance keeps no change
  }
  
  @Test
  public void showRecentTransactions(){
//    no transaction
    this.register();
    List<String> trans=ATM.getTransactions().get(user.getBankAccountNumber());
    Assert.assertTrue("No transactions.", trans==null || trans.isEmpty());
    
//    a few transactions less than the default displaying number
    double money=100;
    atm.deposit(user, 3*money);
    atm.withDrawal(user, money);
    atm.withDrawal(user, money);
    atm.recentTransactions(user);
    Assert.assertFalse(ATM.getTransactions().get(user.getBankAccountNumber()).isEmpty());
    
//     many more transactions
    for (int i = 100; i <= 1000; i+=100) {
      atm.deposit(user, i);
      atm.withDrawal(user, i);
    }
    atm.recentTransactions(user);
    Assert.assertTrue(ATM.getTransactions().get(user.getBankAccountNumber()).size()>10);
  }
  
  @Test
  public void availableAmountInMachine(){
//    deposit
    double amount=atm.getAvailableAmountInMachine();
    double money=100;
    atm.deposit(user, money);
    double expected=amount+money;
    double actual=atm.getAvailableAmountInMachine();
    System.out.println(expected);
    System.out.println(actual);
    Assert.assertTrue("new balance should equal (old balance+depoit)",expected==actual);
    
//    withdrawal
    money=10;
    amount=atm.getAvailableAmountInMachine();
    atm.withDrawal(user, money);
    expected=amount-money;
    actual=atm.getAvailableAmountInMachine();
    System.out.println(expected+","+actual);
    Assert.assertTrue("new balance should equal (old balance-withdrawal)",expected==actual);
  }
 
//  simulate in the console and check for exceptions
  @Test
  public void simulate(){
    atm.init();
  }
}
