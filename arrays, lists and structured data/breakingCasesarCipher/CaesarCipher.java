
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */


import java.lang.*;
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        // create mutable string builder
        StringBuilder encrypted = new StringBuilder(input);
        
        // Alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        // compute shifted alphabet
        String shiftedAlpha = alphabet.substring(key) + alphabet.substring(0, key);
        
        // iterate over the stringbuilder
        for (int i = 0; i < encrypted.length(); i++) {
            // get the current char at current index
            char curChar = encrypted.charAt(i);
           
            // look up the index of curChar in alphabet
            int idx = alphabet.indexOf(Character.toUpperCase(curChar));
            
            // if curChar in alphabet
            if (idx != -1) {
                // get the shifted char 
                char newChar = shiftedAlpha.charAt(idx);
                
                // if curChar is lowercase
                if (Character.isLowerCase(curChar)) {
                    // replace the ith char of encrpyed with lowercase newChar
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    encrypted.setCharAt(i, newChar);
                }

            }
  
        }
        
        return encrypted.toString();
        
    }
    
    public void testCaesar() {
        // shifted position
        //int key = 23;
        int key = 15;
        
        // test phrase
        //String testPhrase = "FIRST LEGION ATTACK EAST FLANK!";
        String testPhrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        System.out.println(encrypt(testPhrase, key));
        System.out.println(encrypt("First Legion", key));
        
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        // create mutable string builder
        StringBuilder encrypted = new StringBuilder(input);
        
        // Alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        // compute shifted alphabet
        String shiftedAlpha = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlpha2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
        // iterate over the stringbuilder
        for (int i = 0; i < encrypted.length(); i++) {
            // get the current char at current index
            char curChar = encrypted.charAt(i);
           
            // look up the index of curChar in alphabet
            int idx = alphabet.indexOf(Character.toUpperCase(curChar));
            
            
            // if curChar in alphabet
            if (idx != -1) {
                char newChar;
                
                // if char at odd location
                if (i % 2 == 0) {
                    // get the shifted char for key1
                    newChar = shiftedAlpha.charAt(idx);
                
                } else {
                    // get the shifted char for key1
                    newChar = shiftedAlpha2.charAt(idx);
                }
                
                // if curChar is lowercase
                if (Character.isLowerCase(curChar)) {
                    // replace the ith char of encrpyed with lowercase newChar
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    encrypted.setCharAt(i, newChar);
                }

            }
  
        }
        
        return encrypted.toString();
    }
    
    public void testEncryptTwoKey() {
        // shifted position
        //int key1 = 23;
        //int key2 = 17;
        //int key1 = 8;
        //int key2 = 21;
        int key1 = 21;
        int key2 = 8;
        
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptTwoKeys(message, key1, key2);
        
        // test phrase
        //System.out.println(encryptTwoKeys("First Legion", key1, key2));
        //System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", key1, key2));
        System.out.println(encrypted);
    }
    

}
