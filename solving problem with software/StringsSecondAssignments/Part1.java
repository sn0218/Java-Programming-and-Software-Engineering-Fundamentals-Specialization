
/**
 * Write a description of Part1 here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */

import java.lang.Math;
public class Part1 {
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
    
    public void testFindStopCodon() {
        //             1    6   10
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        int stopIndex = findStopCodon(dna, 1, "TAA");
        System.out.println("StopCodon at Index " + stopIndex);
        if (stopIndex != dna.length()) {
            String gene = dna.substring(2, stopIndex + 3);
            System.out.println("Gene is " + gene);
        }
        
        //       2       10    16     23
        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA strand is " + dna);
        stopIndex = findStopCodon(dna, 2, "TAA");
        System.out.println("StopCodon at Index " + stopIndex);
        
        if (stopIndex != dna.length()) {
            String gene = dna.substring(2, stopIndex + 3);
            System.out.println("Gene is " + gene);
        }
        
        
        //       2       10    16   21
        dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA strand is " + dna);
        stopIndex = findStopCodon(dna, 2, "TAA");
        System.out.println("StopCodon at Index " + stopIndex);
        if (stopIndex != dna.length()) {
            String gene = dna.substring(2, stopIndex + 3);
            System.out.println("Gene is " + gene);
        }
        

        
    }
    
    public void testFindGene() {
        // ATG with TAA
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna, 0);
        
        if (gene.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(gene);
        }
        
        // no ATG
        dna = "ACTGCGTAATTAATCG";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        
        if (gene.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(gene);
        }
        
        // ATG with mutiple stop codons, stop codon: TAG
        dna = "AATGCGTAATATCTAGTCG";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        
        if (gene.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(gene);
        }
        
        // ATG with mutiple stop codons, stop codon: TGA
        dna = "AATGCGTAATATCTGATAG";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        
        if (gene.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(gene);
        }
        
        // ATG with no stop codon
        dna = "AATGCGTAATATCTACTCG";
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        
        if (gene.isEmpty()) {
            System.out.println("");
        } else {
            System.out.println(gene);
        }
        
    }
    
    public void printAllGenes(String dna){
       // set startIndex to 0
       int startIndex = 0;
       
       // repeat the following steps
       while (true) {
            // find the next gene after startIndex
            String curGene = findGene(dna, startIndex);
            // if no gene, leave the loop
            if (curGene.isEmpty()) {
                break;
            }
            // print that gene out
            System.out.println(curGene);
            
            // set startIndex to just past the end of the gene
            startIndex = dna.indexOf(curGene, startIndex) + curGene.length();
            
      
       }
    }
    
    public void testPrintAll(){
        //            ^1     ^     ^2           ^
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("DNA is "+ dna);
        printAllGenes(dna);
    }
}
