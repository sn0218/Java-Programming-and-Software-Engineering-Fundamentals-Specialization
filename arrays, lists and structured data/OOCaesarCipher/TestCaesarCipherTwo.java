
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.lang.*;
import edu.duke.*;

public class TestCaesarCipherTwo {
    public int[] countLetters(String encrypted) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        
        for (int i = 0; i < encrypted.length(); i++) {
            char ch = Character.toLowerCase(encrypted.charAt(i));
            
            int dex = alpha.indexOf(ch);
            
            if (dex != -1) {
                // increment the corresponding counter
                counts[dex]++;
            }
        }
        
        return counts;
    }
    
    public int maxIndex(int[] counts) {
        int maxIndex = 0;
        
        // iterate over the given arr to find max index
        for (int i=0; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    public String halfOfString(String message, int start) {
        // create empty mutable string
        StringBuilder halfString = new StringBuilder();
        
        // iterate char in string by 2 position
        for (int i = start; i < message.length(); i+=2) {
            // get the ch
            char ch = message.charAt(i);
            // append ch to halfString
            halfString.append(ch);
        }
        
        return halfString.toString();
    }
    
    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //CaesarCipherTwo ccTwo = new CaesarCipherTwo(17, 3);
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(14, 24);
        
        System.out.println("Original message: ");
        System.out.println(message);
        
        String encrypted = ccTwo.encrypt(message);
        //System.out.println("The encrypted message with keys 17, 3: ");
        System.out.println("The encrypted message: ");
        System.out.println(encrypted);
        
        String decrypted = ccTwo.decrypt(encrypted);
        System.out.println("Decrypted message (knowing the key in instance var): ");
        System.out.println(decrypted);
        
        String decryptedAuto = breakCaesarCipher(message);
        System.out.println("Decrypted message (Breaker): ");
        System.out.println(decryptedAuto);
        
    }
    
    public String breakCaesarCipher(String input) {
        // get the two halves for the encrypted msg
        String firstHalf = halfOfString(input, 0);
        String secHalf = halfOfString(input, 1);
        
        int key1 = getKey(firstHalf);
        int key2 = getKey(secHalf);
        
        System.out.println("The keys found by breaker are: " + key1 + ", " + key2);
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(key1, key2);
        String decrypted = ccTwo.decrypt(input);
        
        return decrypted;
        
        
    }
    
    public void quiz() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println("Original message: ");
        System.out.println(message);
        
        String decryptedAuto = breakCaesarCipher(message);
        System.out.println("Decrypted message (Breaker): ");
        System.out.println(decryptedAuto);
        
    }
    
    
    
    
    

}
