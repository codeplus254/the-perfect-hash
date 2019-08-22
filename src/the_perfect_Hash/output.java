package the_perfect_Hash;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;

public class output {

	static cichelliHashTable cht = new cichelliHashTable();
	static String textFileName=main.sendOutput();
	ArrayList<String> keyWords=cht.words;
	public static HashMap<Character, Integer> gValuesMap = cht.getGValues();
	//public Hashtable<Integer, String> hashTable;
	static ArrayList<String> scannedText = new ArrayList<String>();
	static ArrayList<String> keyWordsCollected = new ArrayList<String>();
	static Integer numberOfLines=0;
	
	public output() throws IOException {
		//System.out.println("output hash values map: "+hashTable);
		//scanFile();	
		//print();	
		}
	//to check whether key word, we need first and last character plus their g values
	public void checkKeyWord(String word, Hashtable hashTable){
		if(!word.isEmpty()){
			char first = word.charAt(0);
			char last = word.charAt(word.length() - 1);
			
			//check if both first and last letter have been assigned g-values
			
			if (cht.gValuesMap.containsKey(first) && cht.gValuesMap.containsKey(last)) {
				//System.out.println("word "+word+" hash: "+cht.hashIt(word,cht.gValuesMap.get(first),cht.gValuesMap.get(last)));
				//System.out.println("word "+word+" curren hash: "+hashTable.get(cht.hashIt(word,cht.gValuesMap.get(first),cht.gValuesMap.get(last))));
				//check if the hash value is in the hashtable
				//System.out.println("Confirming: "+word);
				//System.out.println("word "+word+" current hash: "+hashTable.get(cht.hashIt(word,cht.gValuesMap.get(first),cht.gValuesMap.get(last))));
				
				
				if(word.equals(hashTable.get(cht.hashIt(word,cht.gValuesMap.get(first),cht.gValuesMap.get(last))))){
					//System.out.println("caught: "+word);
					keyWordsCollected.add(word);
				}
				
				}
			}
		}
	public void scanFile(){
		
		File text = new File( textFileName );
	    try {
            // Create a new Scanner object which will read the data
            // from the file passed in. To check if there are more 
            // line to read from it we check by calling the 
            // scanner.hasNextLine() method. We then read line one 
            // by one till all lines is read.
            Scanner scanner = new Scanner(text);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

            	if (line.length() == 0) continue;
                String[] arr = line.split(" ");
                for ( String ss : arr){
                	scannedText.add(ss.toLowerCase());
                }
                numberOfLines++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public void print(Hashtable hashTable) throws IOException{
		
        BufferedWriter output = null;
        try {
            File file = new File("result.txt");
            if (!file.exists()) {
                /*
                 * createNewFile() method is used to creates a new, empty file
                 * mentioned by given abstract pathname if and only if a file with
                 * this name does not exist in given abstract pathname.
                 */
                file.createNewFile();
                System.out.print("created");              }
            output = new BufferedWriter(new FileWriter(file));
            output.write("**********************\n");
    	    output.write("***** Statistics *****\n");
    	    output.write("**********************\n");
    	    output.write("Total Lines Read: "+numberOfLines+"\n");
    	    output.write("Total Words Read: "+scannedText.size()+"\n");
    	    output.write("Break Down by Key Word\n");
    	    for (String word : scannedText){
    	    	
    	    	//populate the KeyWOrds colected array
    	    	checkKeyWord(word,hashTable);
    	    	
    	    }
    	    
    	    // processing loop
    		for (String key : keyWords){
    			//compute frequency of key word in the file
    			output.write("	"+key + " : " + Collections.frequency(keyWordsCollected, key)+"\n");
    				}
    		output.write("Total Key Words: "+keyWordsCollected.size()+"\n");
    		System.out.println("You can now checkthe analysis in the file: 'results.txt'");

        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }      
	    

	   
		
	}
}

