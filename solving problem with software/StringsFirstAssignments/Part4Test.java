
/**
 * Write a description of Part4Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4Test {
    public void findLinks(String url) {
        URLResource urlResource = new URLResource(url);
        
        for (String line : urlResource.lines()) {
            int youtubeIndex = line.toLowerCase().indexOf("youtube.com");
            
            if (youtubeIndex != -1) {
                int startIndex = line.lastIndexOf("\"", youtubeIndex);
                int lastIndex = line.indexOf("\"", youtubeIndex);
                
                System.out.println("Youtube link: " + line.substring(startIndex + 1, lastIndex));
            }
        }
    }
    
    public void test() {
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        System.out.println(url);
        findLinks(url);
    }
    
}
