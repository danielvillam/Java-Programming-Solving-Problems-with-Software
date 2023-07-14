
/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 14, 2023)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String country){
        String result = "NOT FOUND";
        for (CSVRecord record : parser) {
            String countryRecord = record.get("Country");
            if (countryRecord.contains(country)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                result = countryRecord+ ": " + export + ": " +value;
            }
        }
        return result;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country+" "+value);
            }
        }
    }
        
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        String countryInformation = countryInfo(parser,"Germany");
        System.out.println(countryInformation);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"gold", "diamonds");
        
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser,"gold");
        System.out.println("Number of gold exporters: "+count);
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999");
        
        //Code for quiz questions
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"fish", "nuts");
        
        parser = fr.getCSVParser();
        count = numberOfExporters(parser,"gold");
        System.out.println("Number of gold exporters: "+count);
        
        parser = fr.getCSVParser();
        countryInformation = countryInfo(parser,"Nauru");
        System.out.println(countryInformation);
        
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}
