import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter f = new MinMagFilter(4.0); 
        /*
        Filter f1 = new MagnitudeFilter(4.0, 5.0, "magF");
        Filter f2 = new DepthFilter(-35000.0, -12000.0, "depF");
        
        ArrayList<QuakeEntry> tmp  = filter(list, f1); 
        ArrayList<QuakeEntry> res = filter(tmp, f2);
        */
       
        //Location tokyo = new Location(35.42, 139.43);
        Location colorado = new Location(39.7392, -104.9903);
        
        Filter f1 = new DistanceFilter(colorado, 1000000, "disF");
        Filter f2 = new PhraseFilter("end" , "a", "phrF");
        //Filter f1 = new MagnitudeFilter(4.0, 5.0, "MagF");
        //Filter f2 = new DepthFilter(-35000.0, -12000.0, "DepF");
        
        ArrayList<QuakeEntry> tmp  = filter(list, f1); 
        ArrayList<QuakeEntry> res = filter(tmp, f2);
        
        
       
        int count = 0;
        for (QuakeEntry qe: res) { 
            count++;
            System.out.println(qe);
        } 
        System.out.println(count);
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
