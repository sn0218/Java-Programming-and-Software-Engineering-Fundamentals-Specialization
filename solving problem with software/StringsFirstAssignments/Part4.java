
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part4 {
    public void printWebLink(String url) {
     URLResource ur = new URLResource(url);
         for (String s : ur.words()) {
             // search for the occurence of "youtube.com"
             int pos = s.toLowerCase().indexOf("youtube.com");
             if (pos != -1) {
                 // look for double quote to the left and right of the occurrence of "youtube.com"
                 int openQuoIndex = s.lastIndexOf("\"", pos);
                 int lastQuoIndex = s.indexOf("\"", pos+1);
           
                 System.out.println(s.substring(openQuoIndex+1, lastQuoIndex));
             }
             
             
     }
    }
    
    public void test() {
        printWebLink("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
