
/**
 * Write a description of WordFrequencies here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.lang.*;
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    // kth pos in my Freqs represent the frequency of a word
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        // initialize instance vars
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();

    }
    
    public void findUnique() {
        // clear two parallel arrList
        myWords.clear();
        myFreqs.clear();
        
        FileResource fr = new FileResource();
        for (String word: fr.words()) {
            // make the word to be case insensitive in subsequent comparison
            word = word.toLowerCase();
            
            // use indexOf to search the occurence of the cur Word
            int index = myWords.indexOf(word);
            // if word is not seen in myWords
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                int val = myFreqs.get(index);
                myFreqs.set(index, val+1);
                
            }
            
            
        }
        
    }
    
    public void tester() {
        findUnique();
        
        // print the freq of each word and the word
        
        for (int i = 0; i < myFreqs.size(); i++) {
            System.out.println(myWords.get(i) + "\t" + myFreqs.get(i));
        }
        
        int MostFreqIndex = findIndexOfMax();
        // print the most freq word
        System.out.println("Word occurs the most often: " + myWords.get(MostFreqIndex));
        System.out.println("No. of occurences: " + myFreqs.get(MostFreqIndex));
        System.out.println("No. of unique words: " + myWords.size());
    }
    
    public int findIndexOfMax() {
        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > max) {
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    

}
