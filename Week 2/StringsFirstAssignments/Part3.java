
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String a, String b){
        int bOccurences = 0;
        int lastIndexFound = 0;
        int lengthOfB = b.length();
        
        while(a.indexOf(b, lastIndexFound) > -1){
            if(lastIndexFound == 0){
                int foundPosition = a.indexOf(b);
                bOccurences = bOccurences + 1;
                lastIndexFound = foundPosition + lengthOfB;
            }else{
                int foundPosition = a.indexOf(b, lastIndexFound);
                bOccurences = bOccurences + 1;
                lastIndexFound = foundPosition + lengthOfB;
            }
        }
        if (bOccurences >= 2){
            System.out.println("numer =" + bOccurences);
            return true;
        }
        return false;
    }
    
    public String lastPart(String stringA, String stringB){
        int indexFound = stringB.indexOf(stringA);
        int lengthA = stringA.length();
        if(indexFound > -1){
            return stringB.substring(indexFound+lengthA, stringB.length());
        }
        return stringB;
    }
    
    public void test(){
        System.out.println("Occurrences below");
        System.out.println(twoOccurrences("byebyebystander","byes"));
        System.out.println("Substring below");
        System.out.println(lastPart("bye","byebyebystander"));
    }
    
    public static void main(String[] args){
        Part3 pr = new Part3();
        pr.test();
    }
}
