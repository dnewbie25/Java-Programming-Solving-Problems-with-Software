import edu.duke.*;
import java.io.File;

public class Part4 {
    public void printYT(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String line: ur.lines()){
            //line = line;
            if(line.indexOf("youtube.com")>-1){
                int startDoubleQuote = line.lastIndexOf("\"",line.indexOf("youtube.com"));
                int endDoubleQuote = line.indexOf("\"", startDoubleQuote+1);
                System.out.println(line.substring(startDoubleQuote,endDoubleQuote+1));
            }
        }
    }
    public static void main(String[] args){
        Part4 pr = new Part4();
        pr.printYT();
    }
}
