
/**
 * Write a description of MarkovWorld here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private HashMap<WordGram, ArrayList<String>> map;
    private Random myRandom;
    private int myOrder;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        
        // choose a wordGram with given order at random from myText
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg);
        sb.append(" ");
        
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(wg);

            //System.out.println("key " + key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            
            // update the wordGram to be the original wordGram that shifting by one + the next word
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    
    // helper method for search for the next occurence of wordGram in string arr (similiar to .indexOf method)
    private int indexOf(String[] words, WordGram target, int start) {
        // iterate over the arr to search for the wordGram
        for (int i = start; i < words.length - myOrder; i++) {
            // get the wordGram from String arr(words) given the order
            WordGram wg = new WordGram(words, i, myOrder);
            // compare the wordGram with the target
            if (wg.equals(target)) {
                return i;
            }

        }
        
        return -1;
    }
    
    public ArrayList<String> getFollows(WordGram kGram) {
         return map.get(kGram);
    }
    
    public void buildMap() {
        // iterate each string in myText (Training text) to get the arrList of each possible string (word)
        for (int i = 0; i <= myText.length - myOrder; i++) {
            // get the wordGram by from myText given the order
            WordGram wg = new WordGram(myText, i, myOrder);
            
            // if no following String, create empty arrList for that wordgram and exit the loop
            if (i + myOrder > myText.length - 1) {
                map.put(wg, new ArrayList<String>());
                break;
            }
            
            // get the following string after the current wordgram
            String next = myText[i + myOrder];
            
            // check if map not contains the wordgram
            if (!map.containsKey(wg)) {
                // create an empty ArrayList for the wg
                ArrayList<String> follows = new ArrayList<String>();
                
                // append the following string
                follows.add(next);
                
                // put the associated arrList into map
                map.put(wg, follows);
            }
            // else if map contains key
            else {
                ArrayList<String> follows = getFollows(wg);
                follows.add(next);
                
                // update the map 
                map.put(wg, follows);
            }
            
        }
    }
    
    public void printHashMapInfo() {
        if (map.size() < 50) {
           for (WordGram wg : map.keySet()) {
                System.out.println(wg + " " + map.get(wg));
           } 
        }
        
        System.out.println("No. of keys in hashmap: " + map.size());
        
        int max = Integer.MIN_VALUE;
        ArrayList<WordGram> maxKeys = new ArrayList<WordGram>();
        for (WordGram key : map.keySet()) {
            if (map.get(key).size() > max) {
                max = map.get(key).size();
            } 
        } 
        
        for (WordGram key : map.keySet()) {
            if (map.get(key).size() == max) {
                maxKeys.add(key);
            }
        }
        
        System.out.println("The size of the largest ArrayList in the hashmap: " + max);
        System.out.println("The key that have the max. value: " + maxKeys);
        
    }
}
