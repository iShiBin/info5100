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

