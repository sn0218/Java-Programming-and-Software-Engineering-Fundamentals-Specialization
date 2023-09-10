
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    private String filterName;
    
    public MagnitudeFilter(double min, double max, String filterName) {
        minMag = min;
        maxMag = max;
        this.filterName = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return filterName;
    }
}
