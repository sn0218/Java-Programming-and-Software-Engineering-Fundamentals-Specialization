
/**
 * Write a description of part3 here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */
import edu.duke.FileResource;

public class part3 {
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
    
    public int countGenes(String dna){
        int count = 0;
        int startIndex = 0;
        String curGene = findGene(dna, startIndex);
        
        while (!curGene.isEmpty()){
            count++;
            startIndex = dna.indexOf(curGene, startIndex) + curGene.length();
            curGene = findGene(dna, startIndex);
        }
        
        return count;
    }
    
    public void testCountGenes(){
        // test1
        //String dna = "ATGTAAGATGCCCTAGT";
        //System.out.println("The DNA strand is " + dna);
        
        // quiz
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        System.out.println(dna);
        
        int num = countGenes(dna);
        System.out.println("Number of genes: " + num);

        
    }
}
