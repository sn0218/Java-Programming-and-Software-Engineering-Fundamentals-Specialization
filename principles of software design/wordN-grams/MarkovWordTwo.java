
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);

            //System.out.println("Key1 & Key2: " + key1 + " " + key2 + " -> " + follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        // iterate over the text arr using post as pointer 
        while (pos < myText.length) {
            // flag to search for next possible occurence starting at pos
            int index = indexOf(myText, key1, key2, pos);
            
            // word not found in myText
            if (index == -1) {
                break;
            }
            
            if (index + 2 > myText.length - 1 ) {
                break;
            }
            
            String next = myText[index + 2];
            follows.add(next);
            pos = index + 1;
        }
        
        return follows;
    }
    
    // helper method for search for the next occurence of word in string arr (similiar to .indexOf method)
    private int indexOf(String[] words, String target, String target2, int start) {
        // iterate over the arr to search for the word
        for (int i = start; i < words.length - 1; i++) {
            if (words[i].equals(target) && words[i+1].equals(target2)) {
                return i;
            }

        }
        
        return -1;
    }
    

    
   
}

