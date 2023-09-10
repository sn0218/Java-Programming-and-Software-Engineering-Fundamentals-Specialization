
/**
 * Write a description of DistanceFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private float maxDist;
    private String filterName;
    
    public DistanceFilter(Location location, float dist, String filterName) {
        loc = location;
        maxDist = dist;
        this.filterName = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (qe.getLocation().distanceTo(loc) < maxDist) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return filterName;
    }
}
