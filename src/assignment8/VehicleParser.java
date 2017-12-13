/* Good Work
 * score 10 + extra credit 2; Total score 10
 */
package assignment8;

import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Extra Credit: (Score 2)
 * 
 * Create the original text file from the json text file you have created in
 * question 3
 * 
 * @author bin
 *
 */
public class VehicleParser {
  private static final String DLMT = "~";

  /**
   * Add the webld field back to the attribute map of json file
   * @param fileName: input file name
   * @return a list of map, each map has the full information of a vehicle
   * @throws IOException
   * @throws ParseException
   */
  private static List<Map> getAttributeMap(String fileName) throws IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader(fileName));

    JSONObject jo = (JSONObject) obj;
    List<Map> vehicleList = new LinkedList<>();

    for (Object key : jo.keySet()) {
      String webld = (String) key;
      JSONArray list = (JSONArray) jo.get(webld);
      Iterator itr = list.listIterator();
      while (itr.hasNext()) {
        StringBuilder vehicles = new StringBuilder();
        Map map = (Map) itr.next();
        map.put("webld", webld);
        vehicleList.add(map);
      }
    }
    return vehicleList;
  }

  /**
   * extract the header information
   * @param list
   * @return the json text string
   */
  private static String extractHeader(List<Map> list) {
    StringBuilder header = new StringBuilder();
    StringBuilder vehicleData = new StringBuilder();

    boolean headerExtracted = false;
    for (Map map : list) {
      for (Object e : map.entrySet()) {
        Map.Entry entry = (Map.Entry) e;
        if (!headerExtracted)
          header.append(((String) entry.getKey() + DLMT));
        vehicleData.append(entry.getValue());
        vehicleData.append(DLMT);
      }
      headerExtracted = true;
      vehicleData.deleteCharAt(vehicleData.length() - 1);
      vehicleData.append("\n");
    }

    header.deleteCharAt(header.length() - 1);
    header.append("\n");
    return header.toString() + vehicleData.toString();
  }

  /**
   * wrapper to expose this function to the outside of this package.
   * @param fileFullPath
   * @return
   * @throws IOException
   * @throws ParseException
   */
  public static String parseVehicleJSON(String fileFullPath) throws IOException, ParseException {
    return extractHeader(getAttributeMap(fileFullPath));
  }

  public static void main(String[] args) throws IOException, ParseException {
    String file = "/Users/bin/Documents/Workspace/GitHub/info5100/assignments/Assignment_8/vehicles.json";
    String s = parseVehicleJSON(file);
//  s is too big so just pring some sample.
    System.out.println(s.substring(0, 1000));
    
  }
}
