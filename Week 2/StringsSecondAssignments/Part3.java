import java.lang.Math;
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        // initialize index
        int index = 0;
        while(true){
            // finds the stop codon after the ATG, that why startIndex + 3
            index = dna.indexOf(stopCodon, startIndex + 3);
            // the while will break with -1 or if it is multiple of 3
            if(index == -1 || (index - startIndex) % 3 == 0){
                break;
            }
            startIndex += 3;
        }
        // finished the loop, it check the indexes
        if(index != - 1){
            return index;
        }else{
            return dna.length();
        }
    }
    public String findGene(String dna){
        // initializes the ATG index, if -1 return ""
        int atgIndex = dna.indexOf("ATG");
        if(atgIndex == -1){
            return "";
        }
        // searches for the stop codon, uses the min method to get the smallest
        int taaIndex = findStopCodon(dna, atgIndex, "TAA");
        int tgaIndex = findStopCodon(dna, atgIndex, "TGA");
        int tagIndex = findStopCodon(dna, atgIndex, "TAG");
        
        int firstCodon = Math.min(taaIndex,Math.min(tgaIndex, tagIndex));
        // the firstCodon returns the index of the stop codon or the length of dna
        if(firstCodon == dna.length()){
            return "";
        }else{
            return dna.substring(atgIndex, firstCodon+3);
        }
    }
    public int countGenes(String dna){
        int index = 0;
        int count = 0;
        while(true){
            String gene = findGene(dna.substring(index));
            if(gene != ""){
                // the index is now the index of the gene starting from the last value of index plus the length of the gene
                index = dna.indexOf(gene, index)+ gene.length();
                count += 1;
            }else{
                break;
            }
        }
        return count;
    }
    
    public void testCountGenes() {
        System.out.println("Gene count = " + countGenes("XXXXXXXXXXXXXXXXXXATGXASXASTAAXATGXASXASXASTGAXAATGXASXASXASTAGXATG"));
        System.out.println("Gene count = " + countGenes("CATTGAACDCTGATGCATGCACCACTGACATGCACCACTAAACATGCACACTGACATGCACCACTAGA"));
    }
    public static void main(String[]args){
        Part3 pr = new Part3();
        pr.testCountGenes();
    }
}
