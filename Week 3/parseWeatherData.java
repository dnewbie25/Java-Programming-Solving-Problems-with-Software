
/**
 * This program finds the coldest day of the year and other interesting facts about the temperature and humidity in a day
 * Each CSV corresponds to a single day of data
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class parseWeatherData {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        // finds the coldest hours in a csv
        CSVRecord coldestRecord = null;
        for(CSVRecord record: parser){
            double temperature = Double.parseDouble(record.get("TemperatureF"));
            if(coldestRecord == null){
                coldestRecord = record;
            }else if(temperature < Double.parseDouble(coldestRecord.get("TemperatureF")) && temperature > -459.67 && temperature < 212){
                coldestRecord = record;
            }
        }
        return coldestRecord;
    }
    public void fileWithColdestTemperature(){
        // finds the coldest temperature across many files and returns a String with data about it
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestDay = null;
        File coldestFile = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = coldestHourInFile(parser);
            if(coldestDay == null){
                coldestDay = currentRecord;
                coldestFile = f;
            }else if(Double.parseDouble(coldestDay.get("TemperatureF")) > Double.parseDouble(currentRecord.get("TemperatureF"))){
                coldestDay = currentRecord;
                coldestFile = f;
            }
        }
        System.out.println("Coldest day was in file " + coldestFile.getName());
        System.out.println("Coldest temperature on that day was " + coldestDay.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord record: new FileResource(coldestFile).getCSVParser()){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
        
    }
    
    // Testing methods
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(coldestHourInFile(parser));
    }
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    public static void main(String[]args){
        parseWeatherData pr = new parseWeatherData();
        pr.testColdestHourInFile();
        pr.testFileWithColdestTemperature();
    }
}
