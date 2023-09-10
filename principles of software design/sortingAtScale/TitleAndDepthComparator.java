
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1T = q1.getInfo();
        String q2T = q2.getInfo();
        
        /* 
        ** DONT RELY ON SPECIFIC VALUE -1 OR +1, 
        compareTo(): an interface type's method promises +ve / -ve value only ** 
        */
        // compare lexicographical order of string first
        // q1T comes first
        if (q1T.compareTo(q2T) < 0) {
            return -1;
        }
        // q1T comes after q2T 
        else if (q1T.compareTo(q2T) > 0) {
            return 1;
        }
        else if (q1T.compareTo(q2T) == 0) {
            // q1T and q2T are the same, compare depth
            if (q1.getDepth() < q2.getDepth()) {
                return -1;
            } else if (q1.getDepth() > q2.getDepth()) {
                return 1;
            }
        }

        return 0;
        
    }
}
