
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex+3);
        String gene = "";
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
        String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
        String dna2 = "ACGGCAATGCGATGCAAAACATAAGCATG";
        String dna3 = "ACGGCAATGCGAACCATAAGCATG";
        String dna4 = "ACGGCAATGCGAAGCATG";
        String dna5 = "ACGGCACGAAACGTGAACTGACCATAAGCAT";
        
        System.out.println("Gene = " + findSimpleGene(dna1));
        System.out.println("Gene = " + findSimpleGene(dna2));
        System.out.println("Gene = " + findSimpleGene(dna3));
        System.out.println("Gene = " + findSimpleGene(dna4));
        System.out.println("Gene = " + findSimpleGene(dna5));
    }
    
    public static void main (String[] args){
        Part1 genes = new Part1();
        genes.testSimpleGene();
    }
}
