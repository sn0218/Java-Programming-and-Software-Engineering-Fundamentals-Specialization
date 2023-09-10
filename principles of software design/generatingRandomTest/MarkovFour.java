
/**
 * Write a description of class MarkovZero here.
 * 
 * @author Samuel Ng
 * @version 1.0
 */

import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
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
        int index = myRandom.nextInt(myText.length() - 4);
        String key = myText.substring(index, index+4);
        
        sb.append(key);
        
        // markovOne predict the next char
        for(int k=0; k < numChars - 4; k++){
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
                
            if (index == -1) {
                break;
            }
            
            if (index != -1 && index + shift >= myText.length() - 1) {
                break;
            }
            
            String followChar = "" + myText.charAt(index + shift);
            follows.add(followChar);
            
            // update pos
            pos = index + key.length();
        }
        
        
        return follows;
    }

}
