
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.lang.*;
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
        
    }
    
    public void update(String person) {
        int index = names.indexOf(person);
        
        if (index == -1) {
            names.add(person);
            freqs.add(1);
        } else {
            int val = freqs.get(index);
            freqs.set(index, val+1);
        }
        
    }
    
    public void findAllCharacters() {
        // clear two parallel arrList
        names.clear();
        freqs.clear();
        
        FileResource fr = new FileResource();
        for (String line: fr.lines()) {
            
            int periodIndex = line.indexOf(".");
            
            if (periodIndex != -1) {
                String speakerName = line.substring(0, periodIndex).toLowerCase();
            
                update(speakerName);
            }
            
            
        }
    }
    
    public void tester() {
        findAllCharacters();
        
        /*
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + "\t" + freqs.get(i));

        }
        */
        
        System.out.println("");
        characterWithNumParts(10, 1000);
        
    }
    
    public void characterWithNumParts(int num1, int num2) {
         for (int i = 0; i < freqs.size(); i++) {
            if (freqs.get(i) >= num1 && freqs.get(i)  <= num2) {
                System.out.println(names.get(i) + "\t" + freqs.get(i));
            }
            
        }
        
        
    }
    
    
    

}
