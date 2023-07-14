public class Part1 {
    
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex != -1){
            int stopIndex = dna.indexOf("TAA",startIndex);
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
        String adn4 = "AGGATGAAGGGATAA";
        String adn5 = "ATGATGAAGGGTAA";
        
        System.out.println("ADN1 " + adn1+ "\n" + findSimpleGene(adn1));
        System.out.println("ADN2 " + adn2+ "\n" + findSimpleGene(adn2));
        System.out.println("ADN3 " + adn3+ "\n" + findSimpleGene(adn3));
        System.out.println("ADN4 " + adn4+ "\n" + findSimpleGene(adn4));
        System.out.println("ADN5 " + adn5+ "\n" + findSimpleGene(adn5));
    }
        
}
