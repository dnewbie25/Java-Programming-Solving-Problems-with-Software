/**
 * Reads a chosen CSV file of country exports and prints each country that exports the data specified.
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("------2--------");
        String countryData = countryInfo(parser,"Nauru");
        System.out.println(countryData);
        System.out.println("------3--------");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton","flowers");
        System.out.println("------4--------");
        parser = fr.getCSVParser();
        System.out.println("Countries that export = " + numberOfExporters(parser,"cocoa"));        
        System.out.println("------5--------");
        parser = fr.getCSVParser();        
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser,String country){
        for(CSVRecord record: parser){
            String countryColumn = record.get("Country");
	    if(countryColumn.contains(country)){
	        String exports = record.get("Exports");
	        String value = record.get("Value (dollars)");
                return country+": "+exports+": "+value; 
            }
            
        }
        return "Not Found";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int numberCountries = 0;
        for(CSVRecord record: parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                numberCountries = numberCountries + 1;
            }
        }
        return numberCountries;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        int lengthStr = amount.length();
        for(CSVRecord record: parser){
            String exportValue = record.get("Value (dollars)");
            if(exportValue.length() > lengthStr){
                System.out.println(record.get("Country")+" "+record.get("Value (dollars)"));
            }
        }
    }
    
    public static void main(String[] args){
        WhichCountriesExport we = new WhichCountriesExport();
        we.tester();
    }
}
