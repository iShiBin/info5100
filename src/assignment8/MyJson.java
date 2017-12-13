package assignment8;

import java.io.*;
import java.util.*;
import javax.*;

public class MyJson {// score 3
  /**
   * read from the file, create vehicle objects, add them to an arrayList and
   * return the arrayList.
   * 
   * @param file
   * @return
   */
  private static ArrayList<Vehicle> readAndGetVehicles(File file) {
    ArrayList<Vehicle> list=new ArrayList<>();
    Scanner read=null;
    try {
      read = new Scanner(file);
      read.nextLine();//skip the first line
      while(read.hasNextLine()){
        Vehicle v=new Vehicle(read.nextLine().split("[~]"));
        list.add(v);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      read.close();
    }
    return list;
  }

  /**
   * take the vehicle objects of the particular dealer (stated as webId in the
   * input file) and convert its content to a json string and return it. you can
   * try and display it in pretty format.
   * 
   * @param vehicles
   * @return
   */
  public static String getJsonString(ArrayList<Vehicle> vehicles) {
    Map<String, List<Vehicle>> map=buildVehicleMap(vehicles);
    StringBuilder json=new StringBuilder();
    json.append("{\n");
    for(Map.Entry<String, List<Vehicle>> entry: map.entrySet()){
      json.append("\n  "+quote(entry.getKey())+": ");
      json.append(entry.getValue().toString());
      json.append(",");
    }
    
    json.deleteCharAt(json.length()-1);
    json.append("\n}");
  
    return json.toString();
  }
  
  private static Map<String, List<Vehicle>> buildVehicleMap(ArrayList<Vehicle> vehicles) {
    Map<String, List<Vehicle>> map=new HashMap<>();
    for(Vehicle v:vehicles){
      if(!map.containsKey(v.webId)) map.put(v.webId, new LinkedList<>());
      map.get(v.webId).add(v);
    }
    return map;
  }

  /**
   * write the input string to the file created in the path given.
   * 
   * @param inputToWrite
   * @param filePath
   */
  public static void writeToFile(String inputToWrite, String filePath) {
    try {
      File file=new File(filePath+"/vehicles.json");
      PrintWriter writer=new PrintWriter(file);
      writer.println(inputToWrite);
      writer.flush();
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
  }
  
  public static String quote(String s){
    if(s==null || s.equals("null") || s.isEmpty()) return "null";//null doesn't need quote
    else return "\""+s+"\"";
  }
  
  /**
   * ToString method for easy print JSON like string for vehicles.
   * @param v
   * @return
   */
  public static String prettyVehicleString(Vehicle v){
    StringBuilder attributes=new StringBuilder("\n    {\n");
    String indicent="      ";
    attributes.append(indicent+quote("id")+": "+quote(v.id)+",\n");
    attributes.append(indicent+quote("category")+": "+quote(v.category.toString())+",\n");
    attributes.append(indicent+quote("make")+": "+quote(v.make)+",\n");
    attributes.append(indicent+quote("model")+": "+quote(v.model)+",\n");
    attributes.append(indicent+quote("trim")+": "+quote(v.trim)+",\n");
    attributes.append(indicent+quote("type")+": "+quote(v.type)+",\n");
    attributes.append(indicent+quote("price")+": "+(int)v.price+",\n");
    attributes.append(indicent+quote("photo")+": "+quote(v.photo.toString())+"\n");
    attributes.append("    }");
    return attributes.toString();
    
  }

  public static void main(String[] args) throws IOException {
    File file = new File("/Users/bin/Documents/Workspace/GitHub/info5100/assignments/Assignment_8/Question3_camino.txt");
    ArrayList<Vehicle> vehicles = readAndGetVehicles(file);
    String s = getJsonString(vehicles);
//    System.out.println(s);
    writeToFile(s, file.getParent());
  }
}
