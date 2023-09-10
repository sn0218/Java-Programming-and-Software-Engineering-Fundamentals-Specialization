
/**
 * Write a description of Part2 here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */
import edu.duke.FileResource;

public class Part2 {
    public float cgRatio(String dna) {
        int count = 0;
        
        for (int i = 0; i < dna.length(); i++) {
            if (dna.toLowerCase().charAt(i) == 'c' || dna.toLowerCase().charAt(i) == 'g') {
                count++;
            }
            
        }
        

        return (float)count / dna.length();
    }
    
    public void testcgRatio() {
        String dna = "ATGCCATAG";
        float cgRatio = cgRatio(dna);
        System.out.println(cgRatio);
        
    }
    
    public int countCTG(String dna) {
        String codon = "CTG";
        int count = 0;
        
        // find the first occurrence of "CTG"
        int startIndex = 0;
        int curIndex = dna.indexOf(codon, startIndex);
        
        while (curIndex != -1) {
            count++;
            startIndex = dna.indexOf(codon, startIndex) + codon.length();
            curIndex = dna.indexOf(codon, startIndex);
            
        }
        
        return count;
        
    }
    
    public void testCountCTG() {
        // ans is 4
        //String dna = "CTGCCTGGCTGCTG";
        
        // quiz
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        
        System.out.println("Number of codon CTG in dna strand is " + countCTG(dna));
    }
    
}   

