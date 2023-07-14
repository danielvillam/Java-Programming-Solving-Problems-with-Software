import edu.duke.*;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }else{
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex + 3);
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()){
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()){
                break;
            }
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return geneList;
    }
    
    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna,1,"TAA");
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna,0,"TAG");
        if (dex != 26) System.out.println("error on 26 TAG");
        System.out.println("tests finished");
    }
    
    public void testFindGene(){
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna,0);
        if(!gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("error");
        }
        System.out.println("tests finished");
    }
    
    public float cgRatio (String dna){
        return ((float)howMany("C",dna)+howMany("G",dna))/dna.length();
    }
    
    public int countCTG(String dna){
        return howMany("CTG",dna);
    }
    
    public int howMany(String stringa, String stringb){
        int ocurrence = 0;
        String currString = stringb;
        while(true){
            currString = findString(stringa,currString);
            if(!currString.equals("False")){
                ocurrence = ocurrence + 1;
            }else{
                break;
            }
        }
        return ocurrence;
    }
    
    public void processGenes(StorageResource sr){
        int count1 = 0;
        int count2 = 0;
        int lengthMax = 0;
        for(String g: sr.data()){
            if(g.length() > 60){
                System.out.println(g);
                count1 = count1 + 1;
            }
            if(cgRatio(g) > 0.35){
                System.out.println(g);
                count2 = count2 + 1;
            }
            if(g.length() > lengthMax){
                lengthMax = g.length();
            }
        }
        System.out.println("number of Strings in sr that are longer than 60 characters = "+count1);
        System.out.println("number of strings in sr whose C-G-ratio is higher than 0.35 = "+count2);
        System.out.println("the length of the longest gene in sr = "+lengthMax);
    }
    
    public void testProcessGenes(){
        //FileResource fr = new FileResource("GRch38dnapart.fa");
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
                System.out.println(g);
        }
        System.out.println("length: "+genes.size());
        processGenes(genes);
        System.out.println("count CTG: "+countCTG(dna));
    }
    
    public String findString(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if(startIndex != -1){
            return stringb.substring(startIndex+stringa.length());
        }else{
            return "False";
        }
    }
}