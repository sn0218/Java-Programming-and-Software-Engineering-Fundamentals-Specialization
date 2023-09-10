
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);

            //System.out.println("key " + key + " " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        // iterate over the text arr using post as pointer 
        while (pos < myText.length) {
            // flag to search for next possible occurence starting at pos
            int index = indexOf(myText, key, pos);
            
            // word not found in myText
            if (index == -1) {
                break;
            }
            
            if (index + 1 > myText.length -1 ) {
                break;
            }
            
            String next = myText[index + 1];
            follows.add(next);
            pos = index + 1;
        }
        
        return follows;
    }
    
    // helper method for search for the next occurence of word in string arr (similiar to .indexOf method)
    private int indexOf(String[] words, String target, int start) {
        // iterate over the arr to search for the word
        for (int i = start; i < words.length; i++) {
            if (words[i].equals(target)) {
                return i;
            }

        }
        
        return -1;
    }
    
    public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String[] textArr = text.split(" ");
        
        String target = "this";
        int index = indexOf(textArr, target, 0);
        if (index != -1) {
            System.out.println(textArr[index] + " at " + index);
        } else {
            System.out.println(target + " is not found.");
        }

        index = indexOf(textArr, target, 3);
        if (index != -1) {
            System.out.println(textArr[index] + " at " + index);
        } else {
            System.out.println(target + " is not found.");
        }
        
        target = "frog";
        index = indexOf(textArr, target, 0);
        if (index != -1) {
            System.out.println(textArr[index] + " at " + index);
        } else {
            System.out.println(target + " is not found.");
        }
        
        index = indexOf(textArr, target, 5);
        if (index != -1) {
            System.out.println(textArr[index] + " at " + index);
        } else {
            System.out.println(target + " is not found.");
        }
        
        target = "test";
        if (index != -1) {
            System.out.println(textArr[index] + " at " + index);
        } else {
            System.out.println(target + " is not found.");
        }
        
        target = "simple";
        index = indexOf(textArr, target, 5);
        if (index != -1) {
            System.out.println(textArr[index] + " at " + index);
        } else {
            System.out.println(target + " is not found.");
        }
        
        
    }
    
   
}
