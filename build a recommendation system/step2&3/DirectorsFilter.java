
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

public class DirectorsFilter implements Filter {
    private String directors;
    
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] dirs = directors.split(",");
        
        for (String dir : dirs) {
            if (MovieDatabase.getDirector(id).contains(dir)) {
                return true;
            }
        }
        return false;
    }
}
