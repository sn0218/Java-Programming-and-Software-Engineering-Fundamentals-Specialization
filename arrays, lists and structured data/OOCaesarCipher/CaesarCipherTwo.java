
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.lang.*;
import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++) {
            // get the current char at current index
            char curChar = input.charAt(i);
           
            // look up the index of curChar in alphabet
            int idx = alphabet.indexOf(Character.toUpperCase(curChar));
            
            
            // if curChar in alphabet
            if (idx != -1) {
                char newChar;
                
                // if char at odd location
                if (i % 2 == 0) {
                    // get the shifted char for key1
                    newChar = shiftedAlphabet1.charAt(idx);
                
                } else {
                    // get the shifted char for key1
                    newChar = shiftedAlphabet2.charAt(idx);
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
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
        
    }
    

}
