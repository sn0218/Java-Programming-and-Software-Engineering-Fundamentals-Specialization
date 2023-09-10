
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
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
