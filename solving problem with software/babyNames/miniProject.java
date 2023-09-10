
/**
 * Write a description of miniProject here.
 * 
 * @author Samuel Ng
 * @version 1.0.0
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class miniProject {
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        // pass false in function as no header in csv
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // parse the record in 1nd col
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            
        }
        System.out.println("total births = " + totalBirths);
        
    }
    
    public void testTotalBirths () {
        //FileResource fr = new FileResource("data/us_babynames_test/example-small.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob1900.csv");
        totalBirths(fr);
    }
    
    public void totalNames (FileResource fr) {
        int totalNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        
        // pass false in function as no header in csv
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // check the sex by parsing the record in 1st col
            if (rec.get(1).equals("M")) {
                totalBoyNames += 1;
            } else {
                totalGirlNames += 1;
            }
            
            // increment for totalNames for each row
            totalNames += 1;  
        }
        System.out.println("total Names = " + totalNames);
        System.out.println("total Boy Names = " + totalBoyNames);
        System.out.println("total Girl Names = " + totalGirlNames);
        
        
    }
    
    public void testTotalNames () {
        //FileResource fr = new FileResource("data/us_babynames_test/example-small.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob1900.csv");
        totalNames(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        int mRank = 0;
        int fRank = 0;
        
        // create the fr by year
        //FileResource fr = new FileResource("data/us_babyNames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        
        // for each row in csv 
        for (CSVRecord rec: fr.getCSVParser(false)) {
            // Check for gender
            String curGen = rec.get(1);
            String curName = rec.get(0);
            
            if (curGen.equals("M")) {
                mRank++;
                
                if (curName.equals(name) && curGen.equals(gender)) {
                    return mRank;
                }
            } else {
                fRank++;
                
                if (curName.equals(name) && curGen.equals(gender)) {
                    return fRank;
                }
            } 
            
        }
        
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        int genNum = 0;
        String name = "NO NAME";
        // create the fr by year
        //FileResource fr = new FileResource("data/us_babyNames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        
        // for each row in csv 
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // check for gender
            String curGen = rec.get(1);
            if (curGen.equals(gender)) {
                genNum++;
                if (genNum == rank) {
                    name = rec.get(0);
                    return name;
                }
            }
        }
        return name;
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        // create the fr by year
        //FileResource fr = new FileResource("data/us_babyNames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        
        if (gender.equals("M")) {
            System.out.println(name + " born in " + year + " would be " 
            + newName + " if he was born in " + newYear);
        } else {
            System.out.println(name + " born in " + year  + " would be " 
        + newName + " if she was born in " + newYear);
        }
        
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int highestRank = Integer.MAX_VALUE;
        int highestYear = -1;
        
        // create dr object to choose files from a directory
        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f: dr.selectedFiles()) {
            // access each file
            FileResource fr = new FileResource(f);
            
            // get file year
            int year = Integer.parseInt(f.getName().substring(3, 7));
            //System.out.println("The year in this file is " + year);
            
            // get the curRank
            int curRank = getRank(year, name, gender);
            
            // update highest rank if found it is lower in value
            if (curRank != -1 && curRank < highestRank) {
                highestRank = curRank;
                highestYear = year;
            }
            
            
        }
        
        return highestYear;
        
    }
    
    public double getAverageRank(String name, String gender) {
        double avgRank = -1.0;
        double sumRank = 0;
        int count = 0;
        
        // create dr object to choose files from a directory
        DirectoryResource dr = new DirectoryResource();
        
        // iterate over files
        for (File f: dr.selectedFiles()) {
            // access each file
            FileResource fr = new FileResource(f);
            
            int year = Integer.parseInt(f.getName().substring(3, 7));

            // get the curRank
            int curRank = getRank(year, name, gender);
            
            // if rank exists in this file
            if (curRank != -1) {
                sumRank += curRank;
                count ++;
            }
            
        }
        
        // check if name is ranked in one of the file
        if (sumRank > 0) {
            return sumRank / count;
        }
        
        return avgRank;
        
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        
        // create the fr by year
        //FileResource fr = new FileResource("data/us_babyNames_test/yob" + year + "short.csv");
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String curName = rec.get(0);
            String curGen = rec.get(1);
            
            // check if gender is equal but curName not equal to name
            if (curGen.equals(gender) && !curName.equals(name)) {
                totalBirths += Integer.parseInt(rec.get(2));
            } else if (curGen.equals(gender) && curName.equals(name)){
                break;
            }
        }
        
        return totalBirths;
        
        
        }
        


}
