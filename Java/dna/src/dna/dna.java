package dna;

public class dna {
	 public static void main(String[] args) {
		 dna dnaClass = new dna();
	        String dna = "GATCCGCCCGCCTCGGCCTCCCAAAGTGCTGGGATTACAGGTGTGAGCCA"
	                   + "CCACGCCCGGCTAATTTTTATTTATTTATTTAAAGACAGAGTCTCACTCT"
	                   + "GTCACTCAGGCTAGAGTGCAGTGGCACCATCTCAGCTCACTGCAGCCTTG"
	                   + "ACCTCCCTGGGCTCCGGTGATTTCACCCTCCCAAGTAGCTAGGACTACAG"
	                   + "GCACATGCCACGACACCCAGCTAATTTTTTATTTTCTGTGAAGTCAAGGT"
	                   + "CTTGCTACGTTGCCCATGCTGGTATCAAACCCCTGGGCTCAATCAATCCT"
	                   + "TCCACCTCAGCCTCCCCAAGTATTGGGGTTACAGGCATGAGCTACCACAC"
	                   + "TCAGCCCTAGCCTACTTGAAACGTGTTCAGAGCATTTAAGTTACCCTACA"
	                   + "GTTGGGCAAAGTCATCTAACACAAAGCCCTTTTTATAGTAATAAAATGTT"
	                   + "GTATATCTCATGTGATTTATTGAATATTGTTACTGAAAGTGAGAAACAGC"
	                   + "ATGGTTGCATGAAAGGAGGCACAGTCGAGCCAGGCACAGCCTGGGCGCAG"
	                   + "AGCGAGACTCAAAAAAAGAAAAGGCCAGGCGCACTGGCTCACGCCTGTAA"
	                   + "TCCCAGCATTTCGGGAGGCTGAGGCGGGTGGATCACCTGAGGTCAGGAGT"
	                   + "TCAAGACCAGCCTAGCCAACATGGTGAAACCCCGTCTCTACTAAAATACA"
	                   + "AAAATTAACCGGGCGTGATGGCAGGTGCCTGTAATCCCAGCTACTTGGGA"
	                   + "GGCTGAGGCAGGAGAATCGCTTGAACCAGGAGGCGGAGGTTGCAGGGAGC"
	                   + "CAAGATGGCGCCACTGCACTCCAGCCTGGGCGATAGAGTGAGACTCCGTC"
	                   + "TCAGAAAAAAAAGAAAAGAAACGAGGCACAGTCGCATGCACATGTAGTCC"
	                   + "CAGTTACTTGAGAGGCTAAGGCAGGAGGATCTCTTGAGCCCAAGAGTTTG"
	                   + "AGTCCAGCCTGAACAACATAGCAAGACATCATCTCTAAAATTTAAAAAAG"
	                   + "GGCCGGGCACAGTGGCTCACACCTGTAATCCCAGCACTTTGGGAGGTGGA"
	                   + "GGTGGGTAGATCACCTGACGTCAGGAGTTGGAAACCAGCCTGGCTAACAT";
	        char[] charArray = dna.toCharArray();
	        dnaClass.checkTagg(charArray);
	        dnaClass.checkCcag(charArray);
	        dnaClass.checkAgcc(charArray);
	}
	 public void checkTagg(char[] charArray) {
   		 int count=0;
   		 for(int i=0; i<charArray.length-3; i++) {
   			 if(charArray[i]=='T') {
   					 String check = (charArray[i]+"")+(charArray[i+1]+"")+(charArray[i+2]+"")+(charArray[i+3]+"");
   					 if (check.equals("TAGG")){
   						 count++;
   					 }
   			 	}
   		 	}
   		 System.out.println("TAGG : " + count);
   	 }
	 public void checkCcag(char[] charArray) {
   		 int count=0;
   		 for(int i=0; i<charArray.length-3; i++) {
   			 if(charArray[i]=='C') {
   					 String check = (charArray[i]+"")+(charArray[i+1]+"")+(charArray[i+2]+"")+(charArray[i+3]+"");
   					 if (check.equals("CCAG")){
   						 count++;
   					 }
   			 	}
   		 	}
   		System.out.println("CCAG : " + count);
   	 }
	 public void checkAgcc(char[] charArray) {
   		 int count=0;
   		 for(int i=0; i<charArray.length-3; i++) {
   			 if(charArray[i]=='A') {
   					 String check = (charArray[i]+"")+(charArray[i+1]+"")+(charArray[i+2]+"")+(charArray[i+3]+"");
   					 if (check.equals("AGCC")){
   						 count++;
   					 }
   			 	}
   		 	}
   		System.out.println("AGCC : " + count);
   	 }
}
