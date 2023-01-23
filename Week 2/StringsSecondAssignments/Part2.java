
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a,String b){
        int lengthA = a.length();
        int index = 0;
        int occurrences = 0;
        while(true){
            if(b.indexOf(a, index) > -1){
                occurrences = occurrences + 1;
                index = b.indexOf(a,index) + lengthA;
            }else{
                break;
            }
        }
        return occurrences;
    }
    public void testHowMany(){
        System.out.println(howMany("AA", "ATAAAA"));//2
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));//3
        System.out.println(howMany("AVA", "AVTAAVAAVA"));//2
        System.out.println(howMany("AVA", "AVTAAVAVA"));//1
        System.out.println(howMany("AVA", "AVTAAVVA"));//0
    }
    public static void main(String[] args){
        Part2 pr = new Part2();
        pr.testHowMany();
    }
}
