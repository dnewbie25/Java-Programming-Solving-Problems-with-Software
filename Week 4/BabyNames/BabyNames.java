
/**
 * This program returns information about the most popular baby names from 1880 to 2014 from the CSV files
 */
import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class BabyNames {
    public void totalBirths (FileResource fr){
        // returns the births by girls, boys and total for the names in the file
        int totalGirls = 0;
        int totalBoys = 0;
        // getCSVParser(false) means there are no headers on the csv file
        
        for(CSVRecord record: fr.getCSVParser(false)){
            String gender = record.get(1);
            if(gender.equals("F")){
                totalGirls += Integer.parseInt(record.get(2));
            }else{
                totalBoys += Integer.parseInt(record.get(2));
            }
        }
        System.out.println("Total number of girls = "+totalGirls);
        System.out.println("Total number of boys = "+totalBoys);
        System.out.println("Total names in file = "+ (totalBoys+totalGirls));
        
    }
    public int getRank(int year, String name, String gender){
        // get the rank for given name in the year and gender specified
        int rank = 0;
        String nameOfFile = "yob"+year+"short.csv";
        FileResource fr = new FileResource(nameOfFile);
        for(CSVRecord record: fr.getCSVParser(false)){
            String recordGender = record.get(1);
            String recordName = record.get(0);
            if(gender.equals(recordGender)){
                rank += 1;
                if(name.equals(recordName)){
                    return rank;
                }
            }
        }
        return -1;
    }
    public String getName(int year, int rank, String gender){
        // get the name in the specified rank for a specific year and gender
        int recordRank = 0;
        String nameOfFile = "yob"+year+"short.csv";
        FileResource fr = new FileResource(nameOfFile);
        for(CSVRecord record: fr.getCSVParser(false)){
            String recordGender = record.get(1);
            String recordName = record.get(0);
            if(gender.equals(recordGender)){
                recordRank += 1;
                if(rank == recordRank){
                    return recordName;
                }
            }
        }
        return "NO NAME";
    }
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        // return the popular name in newYear that was in the same ranking position as the initial year
        int birthNameRank = getRank(year, name, gender);
        String pronoun = "she";
        if(gender.equals("M")){
            pronoun = "he";
        }
        if(birthNameRank != -1){
            String newName = getName(newYear, birthNameRank, gender);
            return name+" born in "+year+" would be "+newName+" if "+pronoun+" was born in "+newYear;
        }      
        
        return "The name "+name+" was not popular in "+year;       
    }
    public int yearOfHighestRank (String name, String gender){
        // from a group of files it chooses the year where the name had the highest rank
        DirectoryResource dr = new DirectoryResource();
        int rank = 1000000;
        int year = 0;
        for(File f: dr.selectedFiles()){
            String fileName = f.getName();
            int fileYear = Integer.parseInt(fileName.substring(3,7));
            int rankCurrentFile = getRank(fileYear, name, gender);
            if(rankCurrentFile < rank && rankCurrentFile != -1){
                rank = rankCurrentFile;
                year = fileYear;
            }
        }
        if(rank == 1000000){
            return -1;
        }
        return year;
    }
    
    // testing methods
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public void testGetRank(){
        System.out.println("The ranking is "+getRank(2014,"Isabella","F"));
    }
    public void testGetName(){
        System.out.println("The name is "+getName(2014,1,"M"));
    }
    public void testWhatIsNameInYear(){
        System.out.println(whatIsNameInYear("Emma", 2014,2013,"F"));
    }
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Jacob","M"));
    }
    
    public static void main(String[] args){
        BabyNames bn = new BabyNames();
        //bn.testTotalBirths();
        //bn.testGetRank();
        //bn.testGetName();
        //bn.testWhatIsNameInYear();
        bn.testYearOfHighestRank();
    }
}
