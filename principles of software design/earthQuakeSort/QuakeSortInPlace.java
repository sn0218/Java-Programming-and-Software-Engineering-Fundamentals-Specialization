
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        // set 70 passes
        for (int i = 0; i < 70; i++) {
            int maxIdx = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i, qmax);
            in.set(maxIdx, qi);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 1; i < quakeData.size(); i++) {
            // check adjacent pair of elements in order
            if (quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude()) {
                // if inorder, swap
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qAdj = quakeData.get(i-1);
                quakeData.set(i, qAdj);
                quakeData.set(i-1, qi);
                
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        // call n-1 pass for bubble sort
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            
            System.out.println("Printing Quakes after pass " + i);
            
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            } 
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");  
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        /*
        sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        */
        
        /*
        sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        */
        
        
        /*
        sortByMagnitudeWithBubbleSort(list);
        System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        */
        
        
        sortByMagnitudeWithBubbleSortWithCheck(list);
        System.out.println("EarthQuakes in sorted order:");
       
        
        
        
        /*
        sortByMagnitudeWithCheck(list);
        System.out.println("EarthQuakes in sorted order:");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
        */
        
        
        
        
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        // iteratve over quakes to check a pair of adj quake in order
        for (int i = 1; i < quakes.size(); i++) {
            if (quakes.get(i-1).getMagnitude() > quakes.get(i).getMagnitude()) {
                return false;
            }
            
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int pass = 0;
        // call n-1 pass for bubble sort
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
            
            System.out.println("Printing Quakes after pass " + i);
            /*
            for (QuakeEntry qe: in) { 
                System.out.println(qe);
            }
            */
            pass++;
            
            // exit bubble sort if the arrList is in order
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        
        System.out.println("It takes " + pass + " pass(es) to sort.");
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int pass = 0;
        
        for (int i=0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            
            pass++;
            
            // exit selection sort if the arrList is in order
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("It takes " + pass + " pass(es) to sort.");
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
