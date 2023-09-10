
/**
 * Write a description of Part3 here.
 * 
 * @author Samuel Ng    
 * @version 1.0.0
 */

import java.lang.Math;
import java.lang.Object;
import edu.duke.StorageResource;
import edu.duke.FileResource;

public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // find stopCodon starting from curIndex + 3
        int curIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        // as long as curIndex is not equal to -1
        while (curIndex != -1) {
            // check multiple of 3 away from startIndex
            if ((curIndex - startIndex) % 3 == 0) {
                return curIndex;
            }
            
            // update curIndex to find the index of next stopCodon
            curIndex = dna.indexOf(stopCodon, curIndex + 1);
            
        }
        
        return dna.length();
        
        
    }
    
    public String findGene(String dna, int start) {
        String res = "";
        
        // find first occurence of "ATG"
        int startIndex = dna.indexOf("ATG", start);
        
        if (startIndex == -1) {
            return res;
        }
        
        // find the first occurrence of "TAA", "TAG" and "TGA"
        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");
        
        int minIndex = Math.min(taaStopIndex, Math.min(tagStopIndex, tgaStopIndex));
        
        if (minIndex == dna.length()) {
            return res;
        }
        
        return dna.substring(startIndex, minIndex+3);
            
    }
    
    public StorageResource getAllGenes(String dna) {
        // Create String objects to store genes
        StorageResource store = new StorageResource();
        
        // find the first occurrence of gene
        int startIndex = 0;
        String curGene = findGene(dna, startIndex);
        
        
        while (!curGene.isEmpty()){
            store.add(curGene);
            
            startIndex = dna.indexOf(curGene, startIndex) + curGene.length();
            curGene = findGene(dna, startIndex);
            
        }
        
        return store;
    }
    
    public double cgRatio(String dna) {
        int count = 0;
        
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G') {
                count++;
            }
            
        }
        

        return (double)count / dna.length();
    }
    
    public void processGenes(StorageResource sr){
        int stringCountOver9char = 0;
        int stringCountcgRatio = 0;
        int longestStringLength = 0;
        
        for (String s : sr.data()) {
            double cgRatio = cgRatio(s);
            
            if (s.length() > 60) {
                stringCountOver9char++;
                System.out.println(s);
            }
            
            if (cgRatio > 0.35) {
                stringCountcgRatio++;
                System.out.println(s);
                
            }
            
            if (s.length() > longestStringLength) {
                longestStringLength = s.length();
            }
     
        }
        System.out.println("Total genes: " + sr.size());
        System.out.println("No. of Strings longer than 60 chars is " + stringCountOver9char);
        System.out.println("No. of Strings C-G-ratio > 0.35 is " + stringCountcgRatio);
        System.out.println("The length of longest gene is " + longestStringLength);
    }
    
    public void testProcessGenes() {
        //FileResource fr = new FileResource("brca1line.fa");
        
        // quiz
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        

        //String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        //System.out.println(cgRatio(dna));
        StorageResource geneList = getAllGenes(dna);
        processGenes(geneList);
    }
}
