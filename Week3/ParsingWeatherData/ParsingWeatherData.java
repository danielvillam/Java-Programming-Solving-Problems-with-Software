/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 16, 2023)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    public CSVRecord hottestHourInFile (CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public void testHottestInDay () {
        FileResource fr = new FileResource("data/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays () {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                   " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public CSVRecord getColdestOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
        double auxTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        if(auxTemp != -9999){
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp) {
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile (){
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature (){
        CSVRecord coldestSoFar = null;
        String name = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
            if(currentRow.equals(coldestSoFar)){
                name = f.getName();
            }
        }
        return name;
    }
    
    public CSVRecord coldestInManyDays () {
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    
    public void testFileWithColdestTemperature (){
        CSVRecord coldest = coldestInManyDays();
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }
    
    public void allTemperaturasInDays (CSVParser parser){
        for (CSVRecord currentRow : parser) {
            System.out.println(currentRow.get("DateUTC")+" "+currentRow.get("TemperatureF"));
        }
    }
    
    public void testFileWithColdestTemperaturePrintAll (){
        String fileColdest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileColdest);
        String start = "";
        if(fileColdest.indexOf("2012") != -1){
            start = "data/2012/";
        }else if(fileColdest.indexOf("2013") != -1){
            start = "data/2013/";
        }else if(fileColdest.indexOf("2014") != -1){
            start = "data/2014/";
        }else{
            start = "data/2015/";
        }
        FileResource fr = new FileResource(start+fileColdest);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        allTemperaturasInDays(fr.getCSVParser());
    }
    
    public CSVRecord lowestHumidityInFile  (CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord currentRow : parser) {
            lowestHumidity = getLowestHumidityOfTwo(currentRow, lowestHumidity);
        }
        return lowestHumidity;
    }
    
    public CSVRecord getLowestHumidityOfTwo (CSVRecord currentRow, CSVRecord lowestHumidity) {
        String auxHumidity = currentRow.get("Humidity");
        if(!auxHumidity.equals("N/A")){
            if (lowestHumidity == null) {
                lowestHumidity = currentRow;
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidityTemp = Double.parseDouble(lowestHumidity.get("Humidity"));
                if (currentTemp < lowestHumidityTemp) {
                    lowestHumidity = currentRow;
                }
            }
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles (){
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestHumiditySoFar = getLowestHumidityOfTwo(currentRow, lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }
    
    public void testLowestHumidityInManyFiles (){
        CSVRecord LowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + LowestHumidity.get("Humidity") +
                   " at " + LowestHumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile (CSVParser parser){
        double addition = 0.0;
        int count = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentTemp != -9999){
                addition += currentTemp;
            }
            count++;
        }
        return addition/count;
    }
    
    public void testAverageTemperatureInFile (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+average);
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
        double addition = 0.0;
        int count = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if(currentTemp != -9999){
                double currentHumedity = Double.parseDouble(currentRow.get("Humidity"));
                if(currentHumedity >= value){
                    addition += currentTemp;
                    count++;
                }
            }
        }
        return addition/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile (){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser,80);
        if(average==0.0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average Temp when high Humidity is "+average);
        }
        
    }
}
