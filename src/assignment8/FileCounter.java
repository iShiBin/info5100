package assignment8;

import java.io.*;
import java.util.*;

public class FileCounter { // score 3

  private int characterCount, wordCount, lineCount;

  public FileCounter() {
  }

  /**
   * Processes an input source and adds its character, word, and line counts to
   * the respective variables.
   * 
   * @param in: the scanner to process
   */
  public void read(Scanner in) throws IOException {
    while(in.hasNextLine()){
      String line=in.nextLine();
      this.characterCount+=line.length();
      //split this line using regex (\W) A non-word character: [^\w]
      this.wordCount+=line.split("\\W").length;
      lineCount++;
    }
  }

  public String getCharacterCount() {
    return String.valueOf(this.characterCount);
  }

  public String getWordCount() {
    return String.valueOf(this.wordCount);
  }

  public int getLineCount() {
    return lineCount;
  }
}
