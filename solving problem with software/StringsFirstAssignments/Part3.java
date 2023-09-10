
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int startIndex = stringb.indexOf(stringa);
        
        if (startIndex == -1) {
            return false;   
        }
        
        int nextStartIndex = stringb.indexOf(stringa, startIndex+1);
        
        if (nextStartIndex == -1) {
            return false;
        }
        
        
        return true;
        
    }
    
    public String lastPart(String stringa, String stringb) {
        int startIndex = stringb.indexOf(stringa);
        
        if (startIndex == -1) {
            return stringb;
        }
        
        String res = stringb.substring(startIndex + stringa.length());
        
        return res;
    }
    
    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
        System.out.println(lastPart("zoo", "farzookeeper"));
    }

}
