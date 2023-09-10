
/**
 * Write a description of Part2 here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */
public class Part2 {
    public int howMany(String stringb, String stringa){
        int count = 0;
        int startIndex = 0;
        
        while (true) {
            // find the index of next stringa
            int curIndex = stringb.indexOf(stringa, startIndex);
            
            // if no stringa is found
            if (curIndex == -1) {
                break;
            }
            
            count++;
            startIndex = curIndex + stringa.length();
            
            
        }
        
        return count;
        

    }
    
    public int howMany2(String stringb, String stringa){
        int count = 0;
        int startIndex = 0;
        int curIndex = stringb.indexOf(stringa, startIndex);
        
        while (curIndex != -1) {
            count++;
            startIndex = curIndex + stringa.length();
            
            // find the index of next occurence of stringa
            curIndex = stringb.indexOf(stringa, startIndex);
  
        }
        
        return count;
    }
    
    
    public void testhowMany(){
        // test1
        String str = "ATGAACGAATTGAATC";
        System.out.println("The original string is " + str);
        int num = howMany(str, "GAA");
        System.out.println("Number of occurence: " + num);
        int num2 = howMany2(str, "GAA");
        System.out.println("Number of occurence: " + num2);
        
        // test2
        str = "ATAAAA";
        System.out.println("The original string is " + str);
        num = howMany(str, "AA");
        num2 = howMany2(str, "AA");
        System.out.println("Number of occurence: " + num);
        System.out.println("Number of occurence: " + num2);
        
    }

}
