
/**
 * Write a description of CaesarCipher here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.lang.*;
import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
        
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        // iterate over the stringbuilder
        for (int i = 0; i < input.length(); i++) {
            // get the current char at current index
            char curChar = encrypted.charAt(i);
           
            // look up the index of curChar in alphabet
            int idx = alphabet.indexOf(Character.toUpperCase(curChar));
            
            // if curChar in alphabet
            if (idx != -1) {
                // get the shifted char 
                char newChar = shiftedAlphabet.charAt(idx);
                
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
    
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
        
    }
    

}
