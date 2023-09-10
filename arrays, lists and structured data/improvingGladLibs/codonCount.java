
/**
 * Write a description of codonCount here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.lang.*;
import java.util.*;
import edu.duke.*;

public class codonCount {
    private HashMap<String, Integer> codonMap;
    
    public codonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        
        // iterate over the dna strand for every codon by given starting pos
        for (int i = start; i < dna.length(); i+=3) {
            // exit the loop if there is no 3 char to form a codon
            if (dna.length() - i < 3) {
                break;
            }
            // extract codon from dna strand
            String codon = dna.substring(i, i+3);
            
            // check if codon in codonMap
            if (!codonMap.containsKey(codon)) {
                // insert key,val pair into codonMap for first occurrence
                codonMap.put(codon, 1);
            } else {
                // update the associated val
                codonMap.put(codon, codonMap.get(codon)+1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int maxCount = Integer.MIN_VALUE;
        
        // iterate over key in hashmap
        for (String key: codonMap.keySet()) {
            if (codonMap.get(key) > maxCount) {
                maxCount = codonMap.get(key);
                mostCommonCodon = key;
            }
        }
        
        return mostCommonCodon;
 
    }
    
    public void printCodonCounts(int start, int end) {
        // iterate over key in hashmap
        for (String key: codonMap.keySet()) {
            if (codonMap.get(key) >= start && codonMap.get(key) <= end) {
                System.out.println(key + "\t" + codonMap.get(key));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        // remove white space by reading FileResource
        dna = dna.trim().toUpperCase();
        
        for (int i = 0; i < 3; i++) {
            int start = i;
            buildCodonMap(start, dna);
            System.out.println("Reading frame starting with " + start + " results in " + codonMap.size() + " unique codons");
            System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonMap.get(getMostCommonCodon()));
            System.out.println("Counts of codons between 1 and 8 inclusive are:");
            printCodonCounts(1, 8);
        }

    }
}
