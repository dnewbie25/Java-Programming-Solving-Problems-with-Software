import java.lang.Math;
import edu.duke.StorageResource;
public class Part1 {
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
    public void printAllGenes(String dna){
        int index = 0;
        while(true){
            String gene = findGene(dna.substring(index));
            if(gene != ""){
                System.out.println("Found = " + gene);
                // the index is now the index of the gene starting from the last value of index plus the length of the gene
                index = dna.indexOf(gene, index)+ gene.length();
            }else{
                System.out.println("Not found");
                break;
            }
        }
    }
    public StorageResource getAllGenes(String dna){
        int index = 0;
        StorageResource storedGenes = new StorageResource();
        while(true){
            String gene = findGene(dna.substring(index));
            if(gene != ""){
                storedGenes.add(gene);
                // the index is now the index of the gene starting from the first value of index plus the length of the gene
                index = dna.indexOf(gene, index)+ gene.length();
            }else{
                break;
            }
        }
        return storedGenes;
    }
    public float cgRatio(String dna){
        // add c and g to a counter
        // the ratio / length of each gene
        int cAndGs = 0;
        float dnaLength = dna.length();
        String geneFound = findGene(dna);
        if(geneFound == ""){
            return 0;
        }
        for(char s: geneFound.toCharArray()){
            if(s == 'C' || s == 'G'){
                cAndGs += 1;
            }
        }
        return (float) cAndGs / dnaLength;
    }
    public void processGenes(StorageResource dna){
        
    }
    // testing methods
    public void testFindStopCodon (String a, int b, String c){
        System.out.println("Position = " + findStopCodon(a,b,c));
    }
    public void testFindGene(String dna){
        System.out.println("Gene = " + findGene(dna));
    }
    public void testGetAllGenes(String dna){
        for(String gene: getAllGenes(dna).data()){
            System.out.println(gene);
        }
    }
    public void testCGRatio(String dna){
        System.out.println(cgRatio(dna));
    }
    public static void main(String[] args){
        Part1 pr = new Part1();
        // pr.testFindStopCodon("ATGATGCTAGTAA",3,"TAA");//13
        // pr.testFindStopCodon("ATGCTAGTAACCTAA",0,"TAA");//12
        // pr.testFindStopCodon("CAGATGCTAGTAA",3,"TAG");//13
        // pr.testFindStopCodon("AGATAASGSATGTCAATCTGAATGTAAGATGCCCTAG",9,"TGA");//18
        // System.out.println("---------------------");
        // pr.testFindGene("CATTGAACDCTGATG"); //NO
        // pr.testFindGene("CATGCACCACTGA"); // YES
        // pr.testFindGene("CATGCACCACTAAA"); //YES
        // pr.testFindGene("CATGCACACTGA"); //no
        // pr.testFindGene("CATGCACCACTAGA");//YES
        // pr.testFindGene("AGATAASGSATGTCAATCTGAATGTAAGATGCCCTAG");//YES
        // pr.testFindGene("CATTGAACDCTGATGCATGCACCACTGACATGCACCACTAAACATGCACACTGACATGCACCACTAGA");//yes
        // pr.testFindGene("XXXATGXASXASTAAXATGXASXASXASTGAXAATGXASXASXASTAGX");
        // pr.testFindGene("XXXXXXXXXXXXXXXXXXATGXASXASTAAXATGXASXASXASTGAXAATGXASXASXASTAGXATG");
        // System.out.println("---------------------");
        // pr.printAllGenes("XXXXXXXXXXXXXXXXXXATGXASXASTAAXATGXASXASXASTGAXAATGXASXASXASTAGXATG");
        pr.printAllGenes("CATTGAACDCTGATGCATGCACCACTGACATGCACCACTAAACATGCACACTGACATGCACCACTAGA");
        System.out.println("---------------------");
        pr.testGetAllGenes("CATTGAACDCTGATGCATGCACCACTGACATGCACCACTAAACATGCACACTGACATGCACCACTAGA");
        pr.testGetAllGenes("ATGGTAATGTG");
        System.out.println("---------------------");
        pr.testCGRatio("ATGCCATAG");
        pr.testCGRatio("ATAATA");
    }
    
}
