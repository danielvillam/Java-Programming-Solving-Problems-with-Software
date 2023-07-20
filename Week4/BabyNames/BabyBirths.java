/** 
 * @author (https://github.com/danielvillam) 
 * @version (July 17, 2023)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;
        int totalNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            }
            else {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
            totalNames++;
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("male boys names = " + totalBoysNames);
        System.out.println("female girls names = " + totalGirlsNames);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank (int year, String name, String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int rank = -1;
        int rankTemp = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)) {
                    rank = rankTemp;
                }
                rankTemp++;
            }
        }
        return rank;
    }
    
    public String getName (int year, int rank, String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        String name = "NO NAME";
        int rankTemp = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                if (rankTemp == rank) {
                    name = rec.get(0);
                }
                rankTemp++;
            }
        }
        return name;
    }
    
    public void whatIsNameInYear (String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear+".");
    }
    
    public void testWhatIsNameInYear (){
        whatIsNameInYear("Isabella",2012,2014,"F");
    }
    
    public int yearOfHighestRank (String name, String gender){
        int maxRank = 0;
        int currentRank = 0;
        int maxYear = 0;
        for (int i=1880;i<=2014;i++){
            currentRank = getRank(i,name,gender);
            if(maxRank == 0 && currentRank != -1){
                maxRank = currentRank;
                maxYear = i;
            }
            else if(currentRank != -1 && currentRank < maxRank){
                maxRank = currentRank;
                maxYear = i;
            }
        }
        if(maxYear == 0){
            return -1;
        }else{
            return maxYear;
        }    
    }
    
    public void testYearOfHighestRank (){
        int year = yearOfHighestRank("Mason","M");
        if(year != -1){
            System.out.println(" His highest ranking was in "+year);
        }else{
           System.out.println("Name Mason and gender ‘M’ is not found"); 
        }
        
    }
    
    public double getAverageRank (String name, String gender){
        int currentRank = 0;
        int rankSum = 0;
        int count = 0;
        for (int i=1880;i<=2014;i++){
            currentRank = getRank(i,name,gender);
            if(currentRank != -1){
                rankSum += currentRank;
            }
            count++;
        }
        return (double)rankSum/count;
    }
    
    public void testGetAverageRank (){
        double average = getAverageRank("Jacob","M");
        System.out.println(average);
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        FileResource fr = new FileResource("data/yob"+year+".csv");
        int count = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)){
                if (!rec.get(0).equals(name)) {
                    count += Integer.parseInt(rec.get(2));
                }else{
                    break;
                }
            }
        }
        return count;
    }
    
    public void testGetTotalBirthsRankedHigher (){
        int count = getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println(count);
    }
    
    public void testQuiz (){
        //1
        FileResource fr = new FileResource("data/yob1900.csv");
        totalBirths(fr);
        
        //2
        fr = new FileResource("data/yob1905.csv");
        totalBirths(fr);
        
        //3
        int rank = getRank(1960,"Emily","F");
        System.out.println(rank);
        
        //4
        rank = getRank(1971,"Frank","M");
        System.out.println(rank);
        
        //5
        String name = getName(1980,350,"F");
        System.out.println(name);
        
        //6
        name = getName(1982,450,"M");
        System.out.println(name);
        
        //7
        whatIsNameInYear("Susan",1972,2014,"F");
        
        //8
        whatIsNameInYear("Owen",1974,2014,"M");
        
        //9
        rank = yearOfHighestRank("Genevieve","F");
        System.out.println(rank);
        
        //10
        rank = yearOfHighestRank("Mich","M");
        System.out.println(rank);
        
        //11
        double avg = getAverageRank("Susan","F");
        System.out.println(avg);
        
        //12
        avg = getAverageRank("Robert","M");
        System.out.println(avg);
        
        //13
        int count = getTotalBirthsRankedHigher(1990,"Emily","F");
        System.out.println(count);
        
        //14
        count = getTotalBirthsRankedHigher(1990,"Drew","M");
        System.out.println(count);
    }
}
