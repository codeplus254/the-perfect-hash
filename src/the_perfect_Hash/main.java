package the_perfect_Hash;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;

public class main {
	public static ArrayList<Integer> hashValues = new ArrayList<>();
	static ArrayList<String> words = new ArrayList<String>();

	
	public static HashMap<String, Integer> wordsValuesMap = new HashMap<>();
	public static HashMap<String, Integer> testValuesMap = new HashMap<>();
	public static HashMap<Character, Integer> letterValuesMap = new HashMap<>();
	public static HashMap<Character, Integer> gValuesMap = new HashMap<>();
	static Stack wordStack = new Stack();
	// g is the value associated with a given letter. The value ranges between 0 and
	// some maximum value. The max value is one of the inputs to the Cichelli
	// Algorithm.
	public static int g = 0;
	public static int max = 0;
	public static String textFileName;

    
	public static void main(String[] args) throws IOException,FileNotFoundException {
		// Scanner for user input
	    Scanner user = new Scanner( System.in ); 
	    String  keyFileName;
	    // prepare the input file
	    System.out.println("Key Word File Name: ");
	    keyFileName = user.nextLine().trim();
	    File input = new File( keyFileName );
	    try {
            // Create a new Scanner object which will read the data
            // from the file passed in. To check if there are more 
            // line to read from it we check by calling the 
            // scanner.hasNextLine() method. We then read line one 
            // by one till all lines is read.
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                words.add(line.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	 // prepare the output file
	    System.out.println("Text to be scanned File Name: ");
	    textFileName =user.nextLine().trim();

	    
		cichelliHashTable cht = new cichelliHashTable();
		cht.CountLetters();
		cht.populateStack(cht.wordsValuesMap);
		cht.cichelli(cht.wordStack);
		
		output out = new output();
		out.scanFile();
		out.print(cht.cichelliTable);
			
	}
	//function to send input to the cichellie table class
	public static ArrayList<String> getInput()
    {
          return words;
    }
	
	//function to send text file name to the output class
	public static String sendOutput()
    {
          return textFileName;
    }
	
}