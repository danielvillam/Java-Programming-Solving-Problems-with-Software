public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int ocurrence1 = stringb.indexOf(stringa);
        int length = stringa.length();
        if(ocurrence1 != -1){
            int ocurrence2 = stringb.indexOf(stringa,ocurrence1+length);
            if(ocurrence2 != -1){
                return true;
            }
        }
        return false;
    }
    
    public void testing(){
        boolean result;
        String exampleA = "by";
        String exampleB = "A story by Abby Long";
        result = twoOccurrences(exampleA,exampleB);
        if(result==true){
            System.out.println(exampleA + ","+ exampleB + " (True)");
        }else{
            System.out.println(exampleA + ","+ exampleB + " (False)");
        }
        
        exampleA = "a";
        exampleB = "banana";
        result = twoOccurrences(exampleA,exampleB);
        if(result==true){
            System.out.println(exampleA + ","+ exampleB + " (True)");
        }else{
            System.out.println(exampleA + ","+ exampleB + " (False)");
        }
        
        exampleA = "atg";
        exampleB = "ctgtatgta";
        result = twoOccurrences(exampleA,exampleB);
        if(result==true){
            System.out.println(exampleA + ","+ exampleB + " (True)");
        }else{
            System.out.println(exampleA + ","+ exampleB + " (False)");
        }
        
        String example2A = "an";
        String example2B = "banana";
        System.out.println("The part of the string after "+example2A + " in "+ example2B + " is "+lastPart(example2A,example2B));
        
        example2A = "zoo";
        example2B = "forest";
        System.out.println("The part of the string after "+example2A + " in "+ example2B + " is "+lastPart(example2A,example2B));
    }
    
    public String lastPart(String stringa, String stringb){
        int ocurrence = stringb.indexOf(stringa);
        int length = stringa.length();
        String result = "";
        if(ocurrence != -1){
            result = stringb.substring(ocurrence+length);
        }else{
            result = stringb;
        }
        return result;
    }
        
}
