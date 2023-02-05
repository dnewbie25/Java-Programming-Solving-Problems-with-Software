
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
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        // finds the lowest humidity hour in a csv
        CSVRecord lowestHumidRecord = null;
        for(CSVRecord record: parser){
            if(record.get("Humidity").equals("N/A")==false){
                double humidity = Double.parseDouble(record.get("Humidity"));
                if(lowestHumidRecord == null){
                    lowestHumidRecord = record;
                }else if(humidity < Double.parseDouble(lowestHumidRecord.get("Humidity"))){
                    lowestHumidRecord = record;
                }
            }
            
        }
        return lowestHumidRecord;
    }
    public CSVRecord lowestHumidityInManyFiles(){
        // finds the lowest humidity across many files and returns a CSVRecord with the data of the lowest humidity day
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidDay = null;
        File lowestHumidFile = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            if(lowestHumidDay == null){
                lowestHumidDay = currentRecord;
                lowestHumidFile = f;
            }else if(lowestHumidDay.get("Humidity")!="N/A" && currentRecord.get("Humidity")!="N/A"){
                if(Double.parseDouble(lowestHumidDay.get("Humidity")) > Double.parseDouble(currentRecord.get("Humidity"))){
                    lowestHumidDay = currentRecord;
                    lowestHumidFile = f;
                }
                
            }
        }
        return lowestHumidDay;
        
    }
    public double averageTemperatureInFile(CSVParser parser){
        //returns the average temperature of a day (csv)
        double sumOfTemperature = 0;
        int hoursCount = 0;
        for(CSVRecord record: parser){
            double currentRecordTemp = Double.parseDouble(record.get("TemperatureF"));
            if(currentRecordTemp > -459.17 && currentRecordTemp < 212){
                sumOfTemperature += currentRecordTemp;
                hoursCount += 1;
            }
        }
        return sumOfTemperature/hoursCount;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        // returns the average temp of the hours with a humidity level greater than or equal to value
        double sumOfTemperature = 0;
        int countHours = 0;
        for(CSVRecord record: parser){
            if(record.get("Humidity")!="N/A"){
                double humidity = Double.parseDouble(record.get("Humidity"));
                if(humidity >= value){
                    sumOfTemperature += Double.parseDouble(record.get("TemperatureF"));
                    countHours += 1;
                }
            }
        }
        if(countHours == 0){
            return -500;
        }
        return sumOfTemperature / countHours;
    }
    // Testing methods
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(coldestHourInFile(parser).get("TemperatureF"));
    }
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double temp = averageTemperatureWithHighHumidityInFile(parser,80);
        if(temp == -500){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average Temp when high Humidity is " + temp);
        }
    }
    
    public static void main(String[]args){
        parseWeatherData pr = new parseWeatherData();
        //pr.testColdestHourInFile();
        pr.testFileWithColdestTemperature();
        //pr.testLowestHumidityInFile();
        //pr.testLowestHumidityInManyFiles();
        //pr.testAverageTemperatureInFile();
        //pr.testAverageTemperatureWithHighHumidityInFile();
    }
}
