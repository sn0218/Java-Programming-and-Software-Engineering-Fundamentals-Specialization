
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index+1);
        
        sb.append(key);
        
        // markovOne predict the next char
        for(int k=0; k < numChars - 1; k++){
            ArrayList<String> follows = getFollows(key);
            
            if (follows.size() == 0) {
                break;
            }
            
            // choose one char at random in the arrList of the following chars of the given key 
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            
            // continue with random string as next one character key
            key = next;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        int shift = 1;
        // check key length
        if (key.length() > 1) {
            shift = key.length();
        }
       
        
        // iterate over the text using pos as pointer
        while (pos < myText.length()) {
            
            // flag to search for next possible occurence starting at pos
            int index = myText.indexOf(key, pos);
                
            if (index != -1 && index > myText.length() - shift) {
                break;
            }
            // break the loop when index not found or reaching the last char
            if (index == -1 || index == myText.length() - 1) {
                break;
            }
            String followChar = "" + myText.charAt(index + shift);
            follows.add(followChar);
            
            // update pos
            pos = index + 1;
        }
        
        
        return follows;
    }
}
