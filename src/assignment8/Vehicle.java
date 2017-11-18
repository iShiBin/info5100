package assignment8;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Year;

public class Vehicle{

  String id;
  String webId;
  Category category;
  Year year;
  String make;
  String model;
  String trim;
  String type;
  double price;
  URL photo;

  Vehicle(String[] arr){
      this.id = arr[0];
      this.webId = arr[1];
      this.category = Category.getCategory(arr[2].toLowerCase());
      this.year = Year.parse(arr[3]);
      this.make = arr[4];
      this.model = arr[5];
      this.trim = arr[6];
      this.type = arr[7];
      this.price = Double.parseDouble(arr[8]);
      try {
          this.photo = new URL(arr[9]);
      } catch (MalformedURLException e) {
          e.printStackTrace();
      }
  }
  
  @Override
  public String toString() {
    return MyJson.prettyVehicleString(this);
  }
  
}

enum Category{
  NEW , USED, CERTIFIED;

  public static Category getCategory(String cat){
     switch (cat){
         case "used": return USED;
         case "new": return NEW;
         case "certified": return CERTIFIED;
         default: throw new IllegalArgumentException();
     }
  }

  @Override
  public String toString() {
      switch (this){
          case NEW: return "NEW";
          case USED: return "USED";
          case CERTIFIED: return "CERTIFIED";
          default: throw new IllegalArgumentException();
      }
  }
  
}

