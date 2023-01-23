
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int start, int end){
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf(start);
        int endIndex = dna.indexOf(end, startIndex+3);
        String gene = "";
        if(dna.substring(start+3) != "ATG"){
            return "";
        }else if(dna.substring(end+3)!="TAA"){
            return "";
        }
        if(startIndex == -1){
            return "";
        }else if (endIndex == -1){
            return "";
        }
        else if ((endIndex - startIndex) % 3 == 0){
            gene = dna.substring(startIndex, endIndex+3);
        }
        return gene;
    }
    
    public void testSimpleGene(){
        String dna1 = "ACGGCAAtGCGAACATaaGCATG";
        String dna2 = "ACGGCAatgCGATGCAAAACATAAGCATG";
        String dna3 = "ACGGCAATGCGAACCAtaaGCATG";
        String dna4 = "ACGGCAATGCGAAGCATG";
        String dna5 = "ACGGCACGAAACGTGAACTGACCATAAGCAT";
        
        System.out.println("Gene = " + findSimpleGene(dna1,6,15));
        System.out.println("Gene = " + findSimpleGene(dna2,6,21));
        System.out.println("Gene = " + findSimpleGene(dna3,6,16));
        System.out.println("Gene = " + findSimpleGene(dna4,6,15));
        System.out.println("Gene = " + findSimpleGene(dna5,5,24));
    }
    
    public static void main (String[] args){
        Part2 genes = new Part2();
        genes.testSimpleGene();
    }
}
