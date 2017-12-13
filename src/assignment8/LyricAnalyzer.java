package assignment8;

import java.io.*;
import java.util.*;

public class LyricAnalyzer { // score 4
  private HashMap<String, ArrayList<Integer>> map;
  private int wordCounter=1;
  
  LyricAnalyzer(){
    map=new HashMap<>();
  }
  
//  read the lyrics from file and adds to the map.
  public void read(File file){
    try {
      FileReader reader=new FileReader(file);
      Scanner in=new Scanner(reader);
      while(in.hasNextLine()){
        String[] word=in.nextLine().split("\\s");
        for(int i=0;i<word.length;i++){
          if(i==word.length-1) this.add(word[i], -wordCounter);
          else this.add(word[i], wordCounter);
          wordCounter++;
        }
      }
      in.close();
//      System.out.println(map);
    } catch (FileNotFoundException e) {
      System.err.println("Cannot find the file. Please check!");
      e.printStackTrace();
    }
  }
  
  private void add(String lyricWord, int wordPosition){
    if(map==null) map=new HashMap<>();
    if(!map.containsKey(lyricWord)) map.put(lyricWord, new ArrayList<>());
    this.map.get(lyricWord).add(wordPosition);
  }
  
  public void displayWords(){
    Set<Map.Entry<String, ArrayList<Integer>>> set=new TreeSet<>(Map.Entry.comparingByKey());
    set.addAll(map.entrySet());
    
//    System.out.println(set);
    for(Map.Entry<String, ArrayList<Integer>> e:set){
      System.out.println(e.getKey()+": "+unbracketList(e.getValue()));
    }
    System.out.println();
  }
  
  private String unbracketList(List<Integer> list){
    String s=list.toString();
    return s.substring(1, s.length()-1);
    
  }
  /**
   * This method will write the lyrics for the song (in uppercase) stored in the map to the file.
   * Start with an empty array of strings whose size is the number of words in the lyric plus 1 (don't use cell 0). 
   * Initialize this array with empty strings (not null). Then, get a set of all of the words stored in the map. 
   * For each word, store the word in the array cells corresponding to its word position(s).
   * If a word position is negative, add on an extra newline character to the word when you store the word in the array.
   * Once you finish processing all words that are in the map, iterate through the array and write out each string, 
   * and you should see the lyrics appear, line by line in the passed file. 
   * 
   * Iterate through the array of words using the for loop.
   * @param file
   */
  public void writeLyrics(File file){
    String[] lyrics=new String[this.wordCounter];
    for(Map.Entry<String, ArrayList<Integer>> entry: this.map.entrySet()){
      String word = entry.getKey().toUpperCase();
      List<Integer> indexList = entry.getValue();
      for(int i:indexList){
        if(i>0) lyrics[i]=word+ " ";
        else lyrics[-i]=word+"\n";
      }
    }
    lyrics[0]="";
    try {
      PrintWriter writer = new PrintWriter(file);
      StringBuilder builder=new StringBuilder();
      for(String s:lyrics) builder.append(s);
      writer.print(builder);
      writer.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  //return the total number of unique words in the lyric by analyzing the map
  public int count(){
    return this.map.size();
  }
  
  /**
   * This method will return the word that occurs most frequently in the lyric.
   * Do this by getting a set of all the words in the map and then for each
   * word, find the one that has the largest set of word positions. Do not
   * create any additional data structures to solve this problem. If there is a
   * tie for the most frequent word, print out the most frequent word that comes
   * first alphabetically.
   * 
   * @return the most frequently word in the lyrics
   */
  public String mostFrequentWord(){
    Set<String> set=new TreeSet<>();
    set.addAll(this.map.keySet());
    String hotWord=null;
    int max=0;
    for(String word: set){
      if(this.map.get(word).size()>max){
        hotWord=word;
        max=this.map.get(word).size();
      }
    }
    return hotWord;
  }
  
  public static void main(String[] args) {
    File lyricsSource = new File("/Users/bin/Documents/Workspace/GitHub/info5100/assignments/Assignment_8");
    for(File f:lyricsSource.listFiles()){
      LyricAnalyzer lyricsAnalyzer=new LyricAnalyzer();
      if(f.getAbsolutePath().contains("Question2")){
        lyricsAnalyzer.read(f);
//        lyricsAnalyzer.displayWords();
        System.out.println(lyricsAnalyzer.mostFrequentWord());
        
        //generate the output file using Q2_ + the most frequent word in the lyrics as the name
        File lyricsFile=new File(lyricsSource.getPath(), "Q2_"+lyricsAnalyzer.mostFrequentWord() + ".txt");
        lyricsAnalyzer.writeLyrics(lyricsFile);
      }
    }
  }
}
