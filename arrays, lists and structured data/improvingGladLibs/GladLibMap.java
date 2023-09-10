
/**
 * Write a description of GladLibMap here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCats;
    
    private Random myRandom;
    private int totalReplacedWords;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        
        usedWords = new ArrayList<String>();
        usedCats = new ArrayList<String>();
        totalReplacedWords = 0;
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        
        usedWords = new ArrayList<String>();
        usedCats = new ArrayList<String>();
        totalReplacedWords = 0;
    }
    
    private void initializeFromSource(String source) {
        // create an Array of categories
        String[] labels = {"country", "noun", "animal", 
                           "adjective", "name", "color",
                           "timeframe", "verb", "fruit"};
        
        // create a arraylist for each category and add the category with the associated arrList to hashmap                   
        for (String s: labels) {
            // read words from assciated file and create an Arraylist of words
            ArrayList<String> list = readIt(source+"/"+ s +".txt");
            // put the category and arrList into hashmap
            myMap.put(s, list);
        }
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!usedCats.contains(label)) {
            usedCats.add(label);
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom(myMap.get(label));
  
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        // find a new sub if sub is used
        while (usedWords.contains(sub) && sub != "**UNKNOWN**") {
            sub = getSubstitute(w.substring(first+1,last));
        }
        
        // add the sub into usedWords
        usedWords.add(sub);
        
        totalReplacedWords++;
        
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        
        // regex: string is split by one or more whitesapce characters
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
        
        System.out.println("\n\nTotal replaced words = " + totalReplacedWords);
    
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        // clear the usedWords
        usedWords.clear();
        
        System.out.println("\n");
        //String story = fromTemplate("data/madtemplate.txt");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("Total no.of words possible to pick: " + totalWordsInMap());
        System.out.println("Total no.of words considered: " + totalWordsConsidered());
    }
    
    public int totalWordsInMap() {
        int totalWords = 0;
        
        for (String key : myMap.keySet()) {
            int curWordNum = myMap.get(key).size();
            totalWords += curWordNum; 
        }
        
        return totalWords;
        
    }
    
    public int totalWordsConsidered() {
        int totalWords = 0;
        
        for (String key : myMap.keySet()) {
            if (usedCats.contains(key)) {
                int curWordNum = myMap.get(key).size();
                totalWords += curWordNum; 
            }
        }
        
        return totalWords;
        
    }
}
