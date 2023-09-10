
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.lang.*;
import edu.duke.*;

public class TestCaesarCipher {
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
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //CaesarCipher cc = new CaesarCipher(18);
        CaesarCipher cc = new CaesarCipher(15);
        
        System.out.println("Original message: ");
        System.out.println(message);
        
        String encrypted = cc.encrypt(message);
        //System.out.println("The encrypted message with key 18: ");
        System.out.println("The encrypted message with key 15: ");
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message (knowing the key in instance var): ");
        System.out.println(decrypted);
        
        String decryptedAuto = breakCaesarCipher(encrypted);
        System.out.println("Decrypted message (Breaker): ");
        System.out.println(decryptedAuto);
        
        
        

    }
    
    public String breakCaesarCipher(String input) {
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        
        int dkey = maxDex - 4;
        
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        
        System.out.println("The key found by breaker is: " + dkey);
        CaesarCipher cc = new CaesarCipher(dkey);
        String decrypted = cc.decrypt(input);
        
        return decrypted;
    }

}
