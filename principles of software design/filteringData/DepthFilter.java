
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    private String filterName;
    
    public DepthFilter(double min, double max, String filterName) {
        minDepth = min;
        maxDepth = max;
        this.filterName = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return filterName;
    }
}
