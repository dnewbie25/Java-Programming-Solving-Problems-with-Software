
/**
 * This program returns information about the most popular baby names from 1880 to 2014 from the CSV files
 */
import edu.duke.*;
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
    
    public static void main(String[] args){
        BabyNames bn = new BabyNames();
        bn.testTotalBirths();
        bn.testGetRank();
        bn.testGetName();
    }
}
