public class Part2 {
    public int howMany (String stringa, String stringb){
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
    
    public String findString(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if(startIndex != -1){
            return stringb.substring(startIndex+stringa.length());
        }else{
            return "False";
        }
    }
    
    public void testHowMany(){
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        int ocurrences = howMany(stringa,stringb);
        System.out.println(stringa +" is in "+ stringb + " = " + ocurrences);
        
        stringa = "AA";
        stringb = "ATAAAA";
        ocurrences = howMany(stringa,stringb);
        System.out.println(stringa +" is in "+ stringb + " = " + ocurrences);
        
        stringa = "AA";
        stringb = "HUSDUHDUSUHD";
        ocurrences = howMany(stringa,stringb);
        System.out.println(stringa +" is in "+ stringb + " = " + ocurrences);
    }
    
}
