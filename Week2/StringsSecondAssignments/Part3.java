public class Part3 {
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
        return dna.length();
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        //int temp = Math.min(taaIndex,tagIndex);
        //int minIndex = Math.min(temp,tgaIndex);
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        if(minIndex == -1){
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
        String dna = "AATGCTAACTAGCTGACTAAT";
        String gene = findGene(dna,0);
        if(!gene.equals("AATGCTAACTAGCTGACTAAT")){
            System.out.println("error");
        }
        System.out.println(gene);
        System.out.println("tests finished");
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if(currGene.isEmpty()){
                break;
            }
            count = count + 1;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }
    
    public void testCountGenes(){
        int genes = countGenes("ATGTAAGATGCCCTAGT");
        System.out.println("genes found in ATGTAAGATGCCCTAGT = " + genes);
    }
}
