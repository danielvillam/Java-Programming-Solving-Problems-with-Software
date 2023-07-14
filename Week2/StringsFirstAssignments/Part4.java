import edu.duke.*;
import java.io.*;
public class Part4 {
    
    public URLResource resource = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    
    public void find(){
        for(String item: resource.words()){
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if(pos != -1){
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
            }
        }
    }
}
