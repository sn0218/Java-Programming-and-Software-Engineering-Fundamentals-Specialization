
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) {
            return "";
        }
        
        if ((stopIndex - startIndex) % 3 == 0) {
            result = dna.substring(startIndex, stopIndex+3);
        }
        
       
        return result;
          
    }
    
    public void testSimpleGene () {
        //Test 1: DNA with no "ATG"
        String dna = "ASTGSGTAATTAATCG";
        System.out.println("DNA with no ATG " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        //Test 2: DNA with no "TAA"
        dna = "AATGSGTAGTTASTCG";
        System.out.println("DNA with no TAA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    
        //Test 3: DNA with no "ATG" or "TAA"
        dna = "AATTSGTAGTTASTCG";
        System.out.println("DNA with no ATG or TAA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);    
    
        //Test 4: DNA with ATG&TAA and the substring between them is a multiple of 3
        dna = "AATGSGGTAATCGTTAATCG";
        System.out.println("DNA with ATG and TAA correct mod3 " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);    
    
        //Test 5" DNA with ATG&TAA and the substring between them is not a multiple of 3
        dna = "AATGSGTAATCGTTAGTCG";     
        //dna = "AATGSGTAATCGTCTAATCG";
        System.out.println("DNA with ATG and TAA incorrect mod3 " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene); 
        
    }
        
       
}
