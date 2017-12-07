//score 1
package assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

class Main {

  public static void parse(File file) throws IOException {
    RandomAccessFile input = null;
    String line = null;

    try {
      input = new RandomAccessFile(file, "r");// FileNotFoundException
      while ((line = input.readLine()) != null) {// IOException
        System.out.println(line);
      }
      return;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (input != null) {
        input.close();// IOException
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
