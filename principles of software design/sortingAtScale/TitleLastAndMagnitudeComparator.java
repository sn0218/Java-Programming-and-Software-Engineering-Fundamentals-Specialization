
/**
 * Write a description of class MagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1Last = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ") +1);
        String q2Last = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ") +1);
        
        // q1Last come first
        if (q1Last.compareTo(q2Last) < 0) {
            return -1;
        } 
        // q1Last come after q2Last
        else if (q1Last.compareTo(q2Last) > 0) {
            return 1;
        }
        // q1Last equals to q2Last
        else if (q1Last.compareTo(q2Last) == 0){
            // compare magnitude
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return 0;    
    }
    

}
