
/**
 * Write a description of WordPlay here.
 * 
 * @author Samuel Ng    
 * @version 1.0.0
 */

import java.lang.*;
import edu.duke.*;


public class WordPlay {
    public boolean isVowel(char ch) {
        // convert ch to lowercase to be case insensitive
        char lowCh = Character.toLowerCase(ch);
        
        // check vowel
        if (ch =='a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        } 
        
        return false;
    }
    
    public void testIsVowel() {
        //String word = "F";
        String word = "Apollo";
        
        for (int i=0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                System.out.println(word.charAt(i) + " at index " 
                + i + " is vowel.");
            } else {
                System.out.println(word.charAt(i) + " at index " 
                + i + " is not vowel.");
            }
            
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        // create mutable string builder
        StringBuilder newPhrase = new StringBuilder(phrase);
        
        // iterate the mutable string builder newPhrase
        for (int i=0; i < newPhrase.length(); i++) {
            if (isVowel(newPhrase.charAt(i))) {
                // if vowel, replace the vowel by ch
                newPhrase.setCharAt(i, ch);
            }
        }
        
        return newPhrase.toString();
    }
    
    public void testReplaceVowels() {
        String oldPhrase = "Hello World";
        String newPhrase = replaceVowels(oldPhrase, '*');
        
        System.out.println(newPhrase);
        
    }
    
    public String emphasize(String phrase, char ch) {
        // create mutable string builder
        StringBuilder newPhrase = new StringBuilder(phrase);
        
        // iterate over the mutable string builder newPhrase
        for (int i=0; i < newPhrase.length(); i++) {
            
            char curChar = newPhrase.charAt(i);
            curChar = Character.toLowerCase(curChar);

            // skip the curChar if not match ch
            if (curChar != ch) {
                continue;
            }
            
            // replace odd number location by '*'
            if (i % 2 == 0) {
                newPhrase.setCharAt(i, '*');
            } 
             
            // replace even number location by '+'
            else{
                newPhrase.setCharAt(i,'+');
            }
        }
        
        return newPhrase.toString();
        
    }
    
    public void testEmphasize() {
        String testPhrase1 = "dna ctgaaactga";
        String testPhrase2 = "Mary Bella Abracadabra";
        System.out.println(emphasize(testPhrase1, 'a'));
        System.out.println(emphasize(testPhrase2, 'a'));
    }
    
    
    

}
