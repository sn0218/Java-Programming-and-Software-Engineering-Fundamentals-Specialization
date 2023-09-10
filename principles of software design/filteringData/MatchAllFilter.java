
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public String getName() {
        String filterName = "";
        
        for (Filter f : filters) {
            System.out.println(f.getName());
            filterName += f.getName() + " ";
        }
        
        return filterName;
        
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0, "magF");
        Filter f2 = new DepthFilter(-180000.0, -30000.0, "depF");
        Filter f3 = new PhraseFilter("any", "o", "phrF");
        //System.out.println(f1.getName());
        //System.out.println(f2.getName());
        //System.out.println(f3.getName());
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> res = filter(list, maf);
       
        int count = 0;
        for (QuakeEntry qe: res) { 
            System.out.println(qe);
            count++;
        } 
        System.out.println(count);
        
        String filterNames = maf.getName();
        System.out.println("Filter used are: " + filterNames);
        
        
    }
    
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        /*
        Filter f1 = new MagnitudeFilter(0.0, 3.0, "f1");
        // location is Tulsa, Oklahoma
        Location tulsa = new Location(36.1314, -95.9372);
        Filter f2 = new DistanceFilter(tulsa, 10000000, "f2");
        Filter f3 = new PhraseFilter("any", "Ca", "f3");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        */
        Location den = new Location(55.7308, 9.1153);
        
        Filter f1 = new DistanceFilter(den, 3000000, "disF");
        Filter f2 = new PhraseFilter("any" , "e", "phrF");
        Filter f3 = new MagnitudeFilter(0.0, 5.0, "magF");
        
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> res = filter(list, maf);
        int count = 0;
        for (QuakeEntry qe: res) { 
            System.out.println(qe);
            count++;
        } 
        System.out.println(count);
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
}
