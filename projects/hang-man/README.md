# Instructions

* Max score 20
* Deadline is 11:59 PM, Oct 14th , Saturday. 
* Upload this to the same repository of assignments.
* You can use any additional API if needed.
* Contact us if any assistance is needed. 


# Hangman Game

Design a Hangman Game that is played by a user.  

When the user plays Hangman, the computer first selects a secret word at random from a list built into the program. The program then prints out a row of dashes - one for each letter in the secret word - and asks the user to guess a letter. If the user guesses a letter that is in the word, the word is redisplayed
with all instances of that letter shown in the correct positions, along with any letters correctly guessed on previous turns. If the letter does not appear in the word, the user is charged with an incorrect guess. The user keeps guessing letters until either(1) the user has correctly guessed all the letters in the word or (2) the user has made eight incorrect guesses.

Hangman comes from the fact that incorrect guesses are recorded by drawing an evolving picture of the user being hanged at a scaffold. For each incorrect guess, a new part of a stick-figure body - first the head, then the body, then each arm, each leg, and finally each foot - is added to the scaffold until
the hanging is complete. The hanging should follow this sequence:  head, body, left arm, right arm, left leg, right leg, left foot, right foot.

Here is the sample picture of the hangman.

```
		 ___________
		|          | 
		|          O
		|          |
		|       --- ---
		|         / \
		|        /   \
		|      --     --
		|_________________                   picture is not to scale
```

API:  
public class Hangman

Hangman(ArrayList< String >  words) - **Initialises the words list.**  
chooseWord()- **Randomly chooses a word from the list.**  
handleGuess()- **handle the guess and add the letter to correctList or WrongList.**  
gameWon()- **return true if user wins the game.**  
gameOver()- **exit the program after the game is over.**  
printHangman()- **print hangman after every guess.**  
playGame()- **Starts the game.**  
displayWord()- **display the correctly guessed letters and hide the remaining with dashes.** 



**NOTE**:  

* intial step is to welcome the user for game
* display the secret word with dashes
* ask the user to guess a letter
* update the word, display previously guessed letters, remaining guesses and hangman
* try to clear the console and display the games after every move if possible
* exit the console after finishing the game

# Accomplishment

Most of the design follows the requirements above, I will not go into very detail since the source code can explain itself. Here is some extra functions.

## Overload the Constructor of Class Hangman

A default constructor 'Hangman()' is provied to read from an English dictionary file and choose a random eligible word from the word in this file.

```java
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
```

## Random Choose a Word from the Input

This chooseWord(List<String>) function will choose a random word with length <= MAX_Guess from it's input. Here is the source code.

```java
private void chooseWord(List<String> words){
  // to remove the word with length > MAX_GUESS
  List<String> qualified = words.stream().filter(word->word.length()<=MAX_GUESS).collect(Collectors.toList());
  
  int index=new Random().nextInt(qualified.size());
  word=qualified.get(index);
  
  guess=new char[word.length()];
  Arrays.fill(guess, MASK);
}
```

## Take Parameters in the Main() Function for More Fun

Guessing a random word in the dictionary is hard. So I make it easy by read some customized words when running the application.

```java
public static void main(String[] args){
  if(args==null || args.length==0) new Hangman().playGame(); // use default dictionary file
  else new Hangman(Arrays.asList(args)).playGame(); // use customized words list
}
```

## Test and Verification

Here is the output when the guessing is wrong.

```
The mystious word is: --------
The letters you guessed: [x, x, x, x, x, x, x, x]
You have 0 more wrong guesses left.
____________
|          |
|          O
|          |
|       --- ---
|         / \
|        /   \
|      --     -- 
|           
-----------------
****GAVE OVER****
```

And you will see this when you win the game:

```
The mystious word is: guess
The letters you guessed: [s, g, u, e]
You have 8 more wrong guesses left.
____________
|          |
|           
|           
|           
|           
|           
|           
|           
-----------------
Congratulate! You WIN!
```

Note: The command to run in the console is `java Hangman guess` in the .class folder.

**How to Use/Run by Yourself:**

To run the class, go to to the root of 'project1', and type `java Hangman`if you need challenge yourself using the dictionary (JRE is required to run `java`), or you can just type `java Hangman guess the word in my mind` if you want to make it easy and play with someone.

## Source Code and Delivery

The source code is 'Hangman.java' which is in the project1 directory.

And the delievery is 'Hangman.class' in the same folder with the java file.