
/**
 * Write a description of CasesarBreaker here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */
import java.lang.*;
import edu.duke.*;

public class CasesarBreaker {
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
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        // compute the index of decryption key as 'e' is the most frequent letter
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        
        return cc.encrypt(encrypted, 26 - dkey);
        
    }
    
    public void testDecrypt() {
        FileResource fr = new FileResource();
        String eMsg = fr.asString();
        String dMsg = decrypt(eMsg);
        System.out.println("Original message: ");
        System.out.println(eMsg);
        System.out.println("Decrypted message: ");
        System.out.println(dMsg);
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
    
    public void testHalfOfString() {
        String msg = "Qbkm Zgis";
        System.out.println("Half of String starting at 0: " + halfOfString(msg, 0));
        System.out.println("Half of String starting at 1: " + halfOfString(msg, 1));
    }
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        return dkey;
    }
    

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        
        // get the two halves for the encrypted msg
        String firstHalf = halfOfString(encrypted, 0);
        String secHalf = halfOfString(encrypted, 1);
        
        
        int key1 = getKey(firstHalf);
        int key2 = getKey(secHalf);
        
        System.out.println("Key1: " + key1);
        System.out.println("Key2: " + key2);
        
        return cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        
    }
    
     public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String eMsg = fr.asString();
        String dMsg = decryptTwoKeys(eMsg);
        System.out.println("Original message: ");
        System.out.println(eMsg);
        System.out.println("Decrypted message: ");
        System.out.println(dMsg);
    }
}
