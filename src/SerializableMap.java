import java.io.*;
import java.util.*;
import java.util.stream.*;

class SerializableMap {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    Map<Integer, Double> roll=new HashMap<>();
    
    for(int i=0;i<100;i++){
      roll.put(i, Math.random());
    }
    
    File f=new File("./testSerialize.txt");
    if(!f.exists()){
      f.createNewFile();
    }
    
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
    oos.writeObject(roll);
    oos.close();
    
  }

}
