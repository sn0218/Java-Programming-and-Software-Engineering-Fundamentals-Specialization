
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Samuel Ng
 * @version 1.0
 */

import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int markovNum;
    
    public MarkovModel(int markovNum) {
        myRandom = new Random();
        this.markovNum = markovNum;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - markovNum);
        String key = myText.substring(index, index+markovNum);
        
        sb.append(key);
        
        // markovOne predict the next char
        for(int k=0; k < numChars - markovNum; k++){
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

}
