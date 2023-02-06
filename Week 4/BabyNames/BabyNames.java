
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
        String nameOfFile = "yob"+year+".csv";
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
        String nameOfFile = "yob"+year+".csv";
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
    public double getAverageRank(String name, String gender){
        // returns the average ranking for a paticular name across multiple files
        DirectoryResource dr = new DirectoryResource();
        double sum = 0.0;
        int counter = 0;
        for(File f: dr.selectedFiles()){
            String fileName = f.getName();
            int fileYear = Integer.parseInt(fileName.substring(3,7));
            int rankInFile = getRank(fileYear,name,gender);
            if(rankInFile == -1){
                return -1.0;
            }
            sum += rankInFile;
            counter += 1;
        }
        return sum / counter;
    }
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        // returns the sum of people that is above the name in the ranking
        int ranking = getRank(year, name, gender);  
        int count = 0;
        int sumOfPeople = 0;
        if(ranking != -1){
            String nameOfFile = "yob"+year+".csv";
            FileResource fr = new FileResource(nameOfFile);
            for(CSVRecord record: fr.getCSVParser(false)){
                int totalBirths = Integer.parseInt(record.get(2));
                if(count < ranking-1 && record.get(1).equals(gender)){
                    count += 1;
                    sumOfPeople += totalBirths;
                }
            }
        }
        if(count != 0){
            return sumOfPeople;
        }
        return -1;
    }
    
    // testing methods
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    public void testGetRank(){
        System.out.println("The ranking is "+getRank(1960,"Emily","F"));
    }
    public void testGetName(){
        System.out.println("The name is "+getName(1982,450,"M"));
    }
    public void testWhatIsNameInYear(){
        System.out.println(whatIsNameInYear("Owen", 1974,2014,"M"));
    }
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mich","M"));
    }
    public void testGetAverageRank(){
        System.out.println("Average rank is "+getAverageRank("Robert","M"));
    }
    public void testGetTotalBirthsRankedHigher(){
        System.out.println("Total births of rankings above "+getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
    
    public static void main(String[] args){
        BabyNames bn = new BabyNames();
        bn.testTotalBirths();
        //bn.testGetRank();
        //bn.testGetName();
        //bn.testWhatIsNameInYear();
        //bn.testYearOfHighestRank();
        //bn.testGetAverageRank();
        //bn.testGetTotalBirthsRankedHigher();
    }
}
