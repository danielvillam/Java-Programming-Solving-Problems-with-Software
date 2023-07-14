public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex != -1){
            int stopIndex = dna.indexOf(stopCodon,startIndex);
            if(stopIndex != -1){
                if( (stopIndex - startIndex) % 3 == 0){
                    result = dna.substring(startIndex, stopIndex);
                }
            }
        } 
        return result;
    }
    
    public void testSimpleGene(){
        String adn1 = "ATTAAGGGATTA";
        String adn2 = "ATGATTAAGGGATTA";
        String adn3 = "AGGATTAAGGGATTA";
        String adn4 = "ATGGGTTAAGTC";
        String adn5 = "gatgctataat";
        
        System.out.println("ADN1 " + adn1+ "\n" + findSimpleGene(adn1,"ATG","TAA"));
        System.out.println("ADN2 " + adn2+ "\n" + findSimpleGene(adn2,"ATG","TAA"));
        System.out.println("ADN3 " + adn3+ "\n" + findSimpleGene(adn3,"ATG","TAA"));
        System.out.println("ADN4 " + adn4+ "\n" + findSimpleGene(adn4,"ATG","TAA"));
        System.out.println("ADN5 " + adn5+ "\n" + findSimpleGene(adn5,"atg","taa"));
    }
}
