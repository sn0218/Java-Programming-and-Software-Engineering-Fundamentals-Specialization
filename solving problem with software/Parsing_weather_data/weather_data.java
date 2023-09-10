
/**
 * Write a description of weather_data here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weather_data {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // initalize coldestSoFar as nothing
        CSVRecord coldestSoFar = null;
        
        // for each row in CSV
        for (CSVRecord curRow: parser) {
            // if coldestSoFar is nothing, assign the first row as coldestSoFar
            if (coldestSoFar == null) {
                coldestSoFar = curRow;
            } else {
                // parse the row's temp to double
                double curTemp = Double.parseDouble(curRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                // check if currentRow's temp < coldestSoFar
                if (curTemp < coldestTemp && curTemp != -9999) {
                    // if so, update coldestSoFar to currentRow
                    coldestSoFar = curRow;
                }
                
                    
            }
        }
        
        return coldestSoFar;
        
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource("nc_weather/2012/weather-2012-01-01.csv");
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature: " + lowest.get("TemperatureF") + " at " + lowest.get("TimeEST"));
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        } else {
            double curTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
            
            if (curTemp < lowestTemp) {
                lowestSoFar = currentRow;
            }
        }
        
        return lowestSoFar;
    }
    
    // return File object rather than string for convenience 
    public File fileWithColdestTemperature() {
        CSVRecord coldestSoFar = null;
        File fileColdest = null;
        
        // create dr object to choose files from a directory
        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f : dr.selectedFiles()) {
            // access each file
            FileResource fr = new FileResource(f);
            
            // get the current lowest temp in the file
            CSVRecord curRow = coldestHourInFile(fr.getCSVParser());
            
            if (coldestSoFar == null) {
                coldestSoFar = curRow;
                fileColdest = f;
            } else {
                double curTemp = Double.parseDouble(curRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if (curTemp < lowestTemp) {
                    coldestSoFar = curRow;
                    fileColdest = f;
                }
            }
        }
        
      
        return fileColdest;
    }
    
    public void testFileWithColdestTemperature() { 
        File coldestFile = fileWithColdestTemperature();
        FileResource fr = new FileResource(coldestFile);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("Coldest day was in file " + coldestFile.getName());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were");
        
        // get the row iterables in file containing lowest temp
        CSVParser parser = fr.getCSVParser();
        // iterate the CSVParser iteratbles
        for (CSVRecord row: parser){
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        // initalize lowestSoFar as nothing
        CSVRecord lowestSoFar = null;
        
        // for each row in CSV
        for (CSVRecord curRow: parser) {
            int curHum;
            int lowestHum;
            
            // if lowestSoFar is nothing, assign the first row as lowestSoFar
            if (lowestSoFar == null) {
                // check first row has string "N/A"
                if (curRow.get("Humidity") == "N/A") {
                    // skip this row
                    continue;
                }
                lowestSoFar = curRow;
                
            } else {
                // check string "N/A"
                if (curRow.get("Humidity").equals("N/A")) {
                    continue;
                } 
                
                // parse the row's humdity to Int
                curHum = Integer.parseInt(curRow.get("Humidity"));
                lowestHum = Integer.parseInt(lowestSoFar.get("Humidity"));
            
                if (curHum < lowestHum) {
                    lowestSoFar = curRow;
                } 
                    
                
                
      
            }
        }
        
        return lowestSoFar;
        
        
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
        + " at " + csv.get("DateUTC"));
    
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        
        // create dr object to choose files from a directory
        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f : dr.selectedFiles()) {
            // access each file
            FileResource fr = new FileResource(f);
            
            // get the current lowest humidity in the file
            CSVRecord curRow = lowestHumidityInFile(fr.getCSVParser());
            
            
            if (lowestSoFar == null) {
                lowestSoFar = curRow;
                
            } else {
                int curHum = Integer.parseInt(curRow.get("Humidity"));
                int lowestHum = Integer.parseInt(lowestSoFar.get("Humidity"));
                
                if (curHum < lowestHum) {
                    lowestSoFar = curRow;
                    
                }
            }
        }
        
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
        + " at " + csv.get("DateUTC"));
        
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.0;
        int count = 0;
        // for each row in CSV
        for (CSVRecord curRow: parser) {
            
            // get the curRow temp and parse it to int
            double curTemp = Double.parseDouble(curRow.get("TemperatureF"));
            count++;
            sum += curTemp;
                
        }
        
        return sum / count;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        
        System.out.println("Average temperature in file is " + avgTemp );
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0.0;
        int count = 0;
        int curHum;
        // for each row in CSV
        for (CSVRecord curRow: parser) {
            // check if the humidity >= the given value
            if (curRow.get("Humidity").equals("N/A")) {
               // skip this row
               continue;
            } 
                    
                // Parse the humidity to int if it is not N/A
            curHum = Integer.parseInt(curRow.get("Humidity"));
            
            // check if the curHum >= given value
            if (curHum >= value) {
                // if yes, add the temp to the sum
                sum += Double.parseDouble(curRow.get("TemperatureF"));
                count++;
            }
        
        }
        
        return sum / count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        // check humidity over 80
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTempHighHum = averageTemperatureWithHighHumidityInFile(parser, 80);
        
        if (Double.isNaN(avgTempHighHum)) {
            System.out.println("No temperatures with that humidity.");
        } else {
             System.out.println("Average Temp when high Humidity is " + avgTempHighHum);
        }
       
       
    }
}


