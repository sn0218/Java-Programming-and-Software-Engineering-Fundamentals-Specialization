
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Samuel Ng
 * @version 1.0
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int markovNum;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int markovNum) {
        myRandom = new Random();
        this.markovNum = markovNum;
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String key = myText.substring(index, index + markovNum);
        
        sb.append(key);
        
        // markov predict the next char
        for (int k=0; k < numChars - markovNum; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("key " + key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            
            // choose one char at random in the arrList of the following chars of the given key 
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            
            // update the key to be the original key that shifting by one + the next char
            // discard the leading char in key, keep the remaining 3 + next => have 4 chars to create new key
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
     public String toString() {
        return "MarkovModel of order " + markovNum;
    }
    
    // build the map for each new training text
    public void buildMap() {
        
        // iterate each char in the training text to get the arrList of each possible string (key)
        for (int i = 0; i <= myText.length() - markovNum; i++){
            // get the key by markov order
            String key = myText.substring(i, i + markovNum);
            
            // if no following char, create empty arrList for that key and exit loop (edge case)
            if (i + markovNum > myText.length() - 1) {
                map.put(key, new ArrayList<String>());
                break;
            }
            
            // get the following char after the key
            String next = myText.substring(i + markovNum, i + markovNum + 1);
            
            // check if map not contains the key
            if (!map.containsKey(key)) {
               // create an empty ArrayList for the key 
               ArrayList<String> follows = new ArrayList<String>();
               
               // append the following char 
               follows.add(next);
               
               // put the associated arrList into map
               map.put(key,follows);
            } 
            // else if map contains the key
            else {
               ArrayList<String> follows = getFollows(key);

               follows.add(next);
               // update the map 
               map.put(key, follows);
            }
            
        }
        
        
    }
    
    @Override
    public ArrayList<String> getFollows(String key) {
         return map.get(key);
    }
    
    public void printHashMapInfo() {
        
        if (map.size() < 50) {
           for (String key : map.keySet()) {
                System.out.println(key + " " + map.get(key));
           } 
        }
        
        System.out.println("No. of keys in hashmap: " + map.size());
        
        int max = Integer.MIN_VALUE;
        ArrayList<String> maxKeys = new ArrayList<String>();
        for (String key : map.keySet()) {
            if (map.get(key).size() > max) {
                max = map.get(key).size();
                
            } 
        } 
        
        for (String key : map.keySet()) {
            if (map.get(key).size() == max) {
                maxKeys.add(key);
            }
        }
        
        System.out.println("The size of the largest ArrayList in the hashmap: " + max);
        System.out.println("The key that have the max. value: " + maxKeys);
    }
}

