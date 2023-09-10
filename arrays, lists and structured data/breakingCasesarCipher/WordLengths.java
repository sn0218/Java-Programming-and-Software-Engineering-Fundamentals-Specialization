
/**
 * Write a description of WordLengths here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */
import java.lang.*;
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        
        // read each word in file
        for (String word: resource.words()) {
            /*
            // for each char in a word
            for (int i = 0; i < word.length(); i++) {
                
                if (!Character.isLetter(word.charAt(i)) && (i == 0 || i == (word.length() - 1))) {
                    continue;
                }
                
                // increment local char count except non-letter as the first or last character,
                count++;

            }
            */
           int nonLetterCount = 0;
            
            if (!Character.isLetter(word.charAt(0))) {
                nonLetterCount++;
            }
            
            if (!Character.isLetter(word.charAt(word.length() - 1))) {
                nonLetterCount++;
            }
            
            int wordLength = word.length() - nonLetterCount;
            
            // handle word with 1 char which is number
            if (wordLength < 0) {
                wordLength = 1;
            }
            
            int maxLength = counts.length - 1;
            
            if (wordLength >= maxLength) {
                counts[maxLength]++;
            } else {
                counts[wordLength]++;            
            }
            
            System.out.println(word);
            
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[100];
        
        try {
            countWordLengths(fr, counts);
        } catch (Exception e) {
            // print the # of words of each length
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    System.out.println("No. of word of length " + i + ": " + counts[i]);
                }
               
            }
        }
        
        // print the # of words of each length
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                System.out.println("No. of word of length " + i + ": " + counts[i]);
            }
           
        }
        
        // the most common word length
        int mostCommonLength = indexOfMax(counts);
        System.out.println("The most common word length in the file: " + mostCommonLength);
    }
    
    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        
        // iterate over the given arr to find max index
        for (int i=0; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        
        return maxIndex;
        
        
    }

}
