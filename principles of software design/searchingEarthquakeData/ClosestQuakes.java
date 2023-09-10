
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // make a deep copy without mutating the original quakeData
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
       
        // iterate for the closest n quake
        for (int j = 0; j < howMany; j++) {
            // for reference quakeEntry with the closest distance to current in quakeData
            int minIndex = 0;
            // iterate over quakeData to search for the closest quake
            for (int i = 1; i < quakeDataCopy.size(); i++) {
                // get the location of each quake entry
                Location loc = quakeDataCopy.get(i).getLocation();
                Location minLoc = quakeDataCopy.get(minIndex).getLocation();
                // compare the distance between loc - cur(e.g Jakarta) and minLoc - cur(e.g Jakarta)
                if (loc.distanceTo(current) < minLoc.distanceTo(current)) {
                    minIndex = i;
                }
            }
            ret.add(quakeDataCopy.get(minIndex));
            // after getting the cloest one, then remove it from source (quakeData)
            quakeDataCopy.remove(minIndex);
        }
        
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);
        
        // find n closest quakes
        ArrayList<QuakeEntry> close = getClosest(list,jakarta, 10);
        
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
    
}
