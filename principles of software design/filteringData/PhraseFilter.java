
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String where, String phrase, String filterName) {
        this.where = where;
        this.phrase = phrase;
        this.filterName = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        
        if (where.equals("start") && qe.getInfo().startsWith(phrase)) {
            return true;
        } 
        
        if (where.equals("end") && qe.getInfo().endsWith(phrase)) {
            return true;
        }
        
        
        if (where.equals("any")) {
    
            if (qe.getInfo().contains(phrase)) {
                return true;
            }
            
        }
        
        return false;
        
        
    }
    
    public String getName() {
        return filterName;
    }
}
