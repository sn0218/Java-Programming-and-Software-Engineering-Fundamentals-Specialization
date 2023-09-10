import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // iterate over quakeData 
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, 
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, 
    String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            //String title = qe.getInfo();
            //String[] phrases = title.split(" ");
            
            if (where.equals("start")) {
                if (qe.getInfo().startsWith(phrase)) {
                    answer.add(qe);
                }
 
            } else if (where.equals("end")) {
                if (qe.getInfo().endsWith(phrase)) {
                    answer.add(qe);
                }
            } else if (where.equals("any")) {
                 if (qe.getInfo().contains(phrase)) {
                    answer.add(qe);
                }
            }
        }
        return answer;
        
    }
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
  
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        // get the filtered (Mag) Quake Entry 
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe);
        }
        System.out.println("Found " + listBig.size() + " quakes that match that criteria.");
    }
    
      
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        double min = -4000.0;
        double max = -2000.0;
        
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("Find quakes with depth between " + min + " and " + max);
        
        // get filtered (depth) Quake Entry
        ArrayList<QuakeEntry> filteredList = filterByDepth(list, min, max);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match that criteria.");
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        System.out.println("read data for "+list.size()+" quakes");
        
        String phrase = "Quarry Blast";
        String where = "start";
        
        // get filtered (phrase) Quake Entry
        ArrayList<QuakeEntry> filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase 
        + " at " + where);
        
        System.out.println();
        
        /*
        phrase = "California";
        where = "end";
        
        // get filtered (phrase) Quake Entry
        filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase 
        + " at " + where);
        
        System.out.println();
        
        System.out.println();
        
        phrase = "Creek";
        where = "any";
        */
       
        // get filtered (phrase) Quake Entry
        filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase 
        + " at " + where);
        
        System.out.println();
        
        /*
        String phrase = "California";
        String where = "end";
        
        // get filtered (phrase) Quake Entry
        ArrayList<QuakeEntry> filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase 
        + " at " + where);
        
        System.out.println();
        
        phrase = "Can";
        where = "any";
        filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase 
        + " at " + where);
        
        System.out.println();
        
        phrase = "Explosion";
        where = "start";
        filteredList = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : filteredList) {
            System.out.println(qe);
        }
        System.out.println("Found " + filteredList.size() + " quakes that match " + phrase 
        + " at " + where);
        */
    }


    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
