
/**
 * Write a description of export_data here.
 * 
 * @author Samuel Ng    
 * @version 1.0.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class export_data {
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        // 1) test for finding Germany's info in CSV file
        //System.out.println(countryInfo(parser, "Germany"));
        //System.out.println(countryInfo(parser, "China"));
        //System.out.println(countryInfo(parser, "Nauru"));
        
        
        // 2) test for listExportersTwoProducts
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        // 3) test for numberOfExporters
        //System.out.println(numberOfExporters(parser, "gold"));
        //System.out.println(numberOfExporters(parser, "cocoa"));
        
        // 4) test for bigExporters
        //bigExporters(parser, "$999,999,999,999");
        bigExporters(parser, "$999,999,999,999");
        
        
    }
    
    public String countryInfo(CSVParser parser, String country){
        String res = "";
        
        // for each row in the CSV file
        for (CSVRecord record: parser) {
            // look at the country column
            String countryCol = record.get("Country");
            
            // check if it contains the country
            if (countryCol.contains(country)) {
                
               
                // write down the "Exports" from that row
                String exports = record.get("Exports");
                
                // write down the Value from that row
                String val = record.get("Value (dollars)");
                
                /*
                System.out.print(country + ": ");
                System.out.print(exports + ": ");
                System.out.println(val);
                */
                
                res = country + ": " + exports + ": " + val;
                return res;
                
            }
        }
        
        return "NOT FOUND";
  
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, 
    String exportItem2) {
        // for each row in CSV
        for (CSVRecord record: parser) {
            // look at the "Exports" column
            String exports = record.get("Exports");
            //System.out.println(exports);
            
            if (exports.contains(exportItem1) 
            && exports.contains(exportItem2)) {
                // write down the country 
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
            
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        // for each line in csv 
        for (CSVRecord record: parser) {
            // get the value in Exports col
            String exports = record.get("Exports");
            
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        // for each line in csv
        for (CSVRecord record: parser) {
            // get the value in Value col
            String val = record.get("Value (dollars)");
            
            // compare the length
            if (val.length() > amount.length()) {
                String countryName = record.get("Country");
                System.out.print(countryName + " ");
                System.out.println(val);
                
                
            }
        }
    }
    
    
    
    
    
    
    
    
    
    

}
