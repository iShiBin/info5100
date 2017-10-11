

import java.util.*;

public class StringLab {

  public static void main(String[] args) {
//    getBytes()
    String name = "shibin";
    byte[] namebytes = name.getBytes();
    System.out.println(Arrays.toString(namebytes));
//    [115, 104, 105, 98, 105, 110]
    
//    hashCode()
//    Returns a hash code for this string. The hash code for a String object is computed as
//    s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
    System.out.println(name.hashCode()); //-903343149
    
//    intern() returns a canonical representation for the string object.
    String bin = "shibin";
    System.out.println(bin.hashCode());//-903343149
    
    String nib = new String("shibin");
    System.out.println(bin.hashCode());//-903343149
    
    System.out.println(name == bin);//true
    System.out.println(name == nib);//false
    
//    this will set the new String("shibin") free because the new object is not in the pool
    nib = null;
    
    String internBin = name.intern();
    System.out.println(internBin == name);//true
    
//    Summary of regular-expression constructs
//    \d  A digit: [0-9]
//    \D  A non-digit: [^0-9]
//    \s  A whitespace character: [ \t\n\x0B\f\r]
//    \S  A non-whitespace character: [^\s]
//    \w  A word character: [a-zA-Z_0-9]
//    \W  A non-word character: [^\w]
    String line = "this is a line.";
    String[] words = line.split("\\s");
    System.out.println(Arrays.toString(words));
    
//    to remove the end non-word character
    words = line.split("\\W");
    System.out.println(Arrays.toString(words));
    //true[this, is, a, line.]
    
    String message = String.join("-", "Java", "is", "cool");
//     message returned is: "Java-is-cool"
  }

}
