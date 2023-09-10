
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel {
    
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
    
     public String toString() {
        return "MarkovModel of order 1";
    }
}
