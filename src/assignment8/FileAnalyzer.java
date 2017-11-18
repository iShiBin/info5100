package assignment8;
/**
 * Write a program that asks a user for a file name and prints the number of characters, words, and lines in that file.
 * Use the following class as your main class:  
 */
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileAnalyzer
{
   public static void main(String[] args) throws IOException
   {
      System.out.println("Filename: ");
      Scanner in = new Scanner(System.in);
      String name = in.nextLine();
      FileCounter counter = new FileCounter();
      FileReader reader = new FileReader(name);
      Scanner fileIn = new Scanner(reader);
      counter.read(fileIn);
      fileIn.close();
      System.out.println("Characters: " + counter.getCharacterCount());
      System.out.println("Words: " + counter.getWordCount());
      System.out.println("Lines : " + counter.getLineCount());
   }
}