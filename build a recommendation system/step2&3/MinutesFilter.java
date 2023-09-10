
/**
 * Write a description of MinutesFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

public class MinutesFilter implements Filter {
    private int max;
    private int min;
    
    public MinutesFilter(int max, int min) {
        this.max = max;
        this.min = min;
    }
    
    @Override 
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= this.min && MovieDatabase.getMinutes(id) <= this.max;
    }

}
