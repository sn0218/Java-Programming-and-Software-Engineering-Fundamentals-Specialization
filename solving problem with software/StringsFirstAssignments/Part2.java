
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        
        int startIndex = dna.toUpperCase().indexOf(startCodon.toUpperCase());
    
        int stopIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex+3);
        if (startIndex == - 1 || stopIndex == -1) {
            return "";
        }
        
        if ((stopIndex - startIndex) % 3 == 0) {
            result = dna.substring(startIndex, stopIndex+3);
        }
        
       
        return result;
          
    }
    
    public void testSimpleGene () {
        String startCodon = "ATG";
        String endCodon = "TAA";
        
        //Test 1: DNA with no "ATG"
        String dna = "ASTGSGTAATTAATCG";
        System.out.println("DNA with no ATG " + dna);
        String gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
        
        //Test 2: DNA with no "TAA"
        dna = "AATGSGTAGTTASTCG";
        System.out.println("DNA with no TAA " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
    
        //Test 3: DNA with no "ATG" or "TAA"
        dna = "AATTSGTAGTTASTCG";
        System.out.println("DNA with no ATG or TAA " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);    
    
        //Test 4: DNA with ATG&TAA and the substring between them is a multiple of 3
        dna = "AATGSGGTAATCGTTAATCG";
        System.out.println("DNA with ATG and TAA correct mod3 " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);    
    
        //Test 5" DNA with ATG&TAA and the substring between them is not a multiple of 3
        dna = "AATGSGTAATCGTTAGTCG";     
        //dna = "AATGSGTAATCGTCTAATCG";
        System.out.println("DNA with ATG and TAA incorrect mod3 " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene); 
        
        //Test 6 DNA with lower case
        dna = "gatgctataat";     
        //dna = "AATGSGTAATCGTCTAATCG";
        System.out.println("DNA with lowercase " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene);
        
        //Test 7 DNA with upper case
        dna = "ATGGGTTAAGTC";     
        //dna = "AATGSGTAATCGTCTAATCG";
        System.out.println("DNA with lowercase " + dna);
        gene = findSimpleGene(dna, startCodon, endCodon);
        System.out.println("Gene is " + gene); 
        
        
    }
}
