
/**
 * Write a description of LargestQuakes here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("Found " + list.size() + " quakes that read in.");
        
        /*
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
        int largestIndex = indexOfLargest(list);
        System.out.println("The largest earthquake: ");
        System.out.println("Index at " + largestIndex);
        System.out.println(list.get(largestIndex));
        
        System.out.println();
        int num = 50;
        ArrayList<QuakeEntry> largestN = getLargest(list, num);
        System.out.println(num + " earthqueakes of largest magnitude: ");
        for (int i = 0; i < largestN.size(); i++) {
            System.out.println(largestN.get(i));
        }
       
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
       int maxIndex = 0;
       
       for (int i = 0; i < data.size(); i++) {
           if (data.get(i).getMagnitude() > data.get(maxIndex).getMagnitude()) {
               maxIndex = i;
            }
        }
       
       return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> res = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        
        // get the n largest Magnitude of earth quake
        for (int i = 0; i < howMany; i++) {
            int largestIndex = indexOfLargest(copy);
            QuakeEntry largest = copy.get(largestIndex);
            res.add(largest);
            copy.remove(largestIndex);
        }
        
        return res;
    }
}
