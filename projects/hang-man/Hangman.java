/* Good Work
 * Score 20
 */

import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.*;

public class Hangman {
  private String word;
  private char[] guess;
  private static final char MASK='-';
  private static final int MAX_GUESS=8;
  private int wrongGuess=0;
  private String[] hanging;
  private List<Character> correctList=new ArrayList<>();
  private List<Character> wrongList=new ArrayList<>();
  private List<Character> guessList=new ArrayList<>();
  private static String dictionaryFile="word_list_En.txt";
  
  Hangman(){
    try {
      String dictionary = new String(Files.readAllBytes(Paths.get(dictionaryFile)));
      this.chooseWord(Arrays.asList(dictionary.split("\r\n")));//bug fixed - add \r
    } catch (IOException e) {
      System.out.println("Cannot read dictionary file:" +dictionaryFile);
      System.out.println("But you can run this case with some Engish words(seperated by a space).");
      e.printStackTrace();
    }
  }
  
  Hangman(List<String> words){
    this.chooseWord(words);
  }
  
  private void chooseWord(List<String> words){
    // to remove the word with length > MAX_GUESS
    List<String> qualified = words.stream().filter(word->word.length()<=MAX_GUESS).collect(Collectors.toList());
    
    int index=new Random().nextInt(qualified.size());
    word=qualified.get(index);
    
    guess=new char[word.length()];
    Arrays.fill(guess, MASK);
  }
  
  private void handleGuess(char c){
    if(word.contains(String.valueOf(c))){
      for(int i=0;i<word.length();i++){
        if(word.charAt(i)==c) guess[i]=c;
      }
      this.correctList.add(c);
    }else{
      wrongGuess++;
      this.wrongList.add(c);
    }
    guessList.add(c);
    this.displayWord();
    this.printHangman();
  }
  
  private void displayWord(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("The mystious word is: "+String.valueOf(guess));
    System.out.println("The letters you guessed: "+ this.guessList);
    System.out.println("You have "+(MAX_GUESS-this.wrongGuess)+" more wrong guesses left.");
  }
  
  private void printHangman(){
    String[] hanging={
        "____________",
        "|          |",
        "|           ",
        "|           ",
        "|           ",
        "|           ",
        "|           ",
        "|           ",
        "|           ",
        "-----------------"
    };
    this.hanging=hanging;
    
    for(int i=1;i<=wrongGuess;i++){
      this.setMan(i);
    }
    
    for(String line:hanging){
      System.out.println(line);
    }
    
  }

  private void setMan(int i){
    int offset=2, n=offset+i-1;
    if(i==1){
      hanging[n]="|          O";
    }else if(i==2){
      hanging[n]="|          |";
    }else if(i==3){
      hanging[n]="|       ---";
    }else if(i==4){
      hanging[n-1]="|       --- ---";
    }else if(i==5){
      hanging[n-1] = "|         /";
      hanging[n]="|        /";
    }else if(i==6){
      hanging[n-2]="|         / \\";
      hanging[n-1]  ="|        /   \\";
    }else if(i==7){
      hanging[n-1]="|      --";
    }else if(i==8){
      hanging[n-2]="|      --     -- ";
    }else{
      throw new IllegalArgumentException();
    }
  }
  
  private void playGame(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Welcome! Here is the secret word:"+String.valueOf(this.guess));
    System.out.println("Enter a char(lower case letter) to start guessing:");
    Scanner scanner=new Scanner(System.in);
    
    do{
      char c=scanner.next().charAt(0);
      this.handleGuess(c);
      if(this.gameWon()){
        System.out.println("Congratulate! You WIN!");
        scanner.close();
        System.exit(0);
      }
    }while(this.wrongGuess<Hangman.MAX_GUESS);
    this.gameOver();
  }
  
  private boolean gameWon(){
    return Arrays.equals(this.word.toCharArray(), this.guess);
  }
  
  private void gameOver(){
//    System.out.println("The mysterious word is:"+this.word);
    System.out.println("****GAVE OVER****");
    System.exit(0);
  }
  
  public static void main(String[] args){
    if(args==null || args.length==0) new Hangman().playGame(); // use default dictionary file
    else new Hangman(Arrays.asList(args)).playGame(); // use customized words list
  }
}

