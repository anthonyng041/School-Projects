// -----------------------------------------------------
// Assignment 3
// Question: 1
// Written by: Anthony Nguyen - 40210667 AND Nektarios Zampetoulakis - 40211948
// -----------------------------------------------------

package Part1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// The following method reads the input file and reads the first integer as the size of an array of strings. Each element of the array
// corresponds to the name of each book(year) file. Each line is then split into an array of 6 strings, with each corresponding to a field.
// If an exception is found, the book is written in the syntax error file. If none are found, then the book is written in its respective genre file.

public class Part1 {
	
	/**
	 * Reads input file, saves each book record (books(year).csv) as an array of size of the first integer in the input file.
	 * Then iterates over each element of books in the array, splits the input by comma into 6 fields (title, authors, price, isbn, genre, year).
	 * Checks for 4 exceptions: TooFewFieldsException, TooManyFieldsException, MissingFieldException and UnknownGenreException.
	 * If an exception is found, it writes the book record into syntax_error_file.txt.
	 * If no exception is found, it then writes the book record into its respective genre file.
	 * @throws IOException, TooFewFieldsException, TooManyFieldsException, MissingFieldException,UnknownGenreException
	 * @author Anthony & Nektarios
	 */

    public static void do_part1() throws IOException {
    	String line = null;
        BufferedReader br = new BufferedReader(new FileReader("Comp249_W23_Assg3\\\\part1_input_file_names.txt"));
    	String[] fileNames = new String[Integer.parseInt(br.readLine().trim())]; // Parsing the first line into an integer that corresponds to the size
    	String[] fields;
    	// Looping through the input file and placing each file name in array
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = br.readLine().trim();
        }
  
        br.close();
        // Creating and opening a total of 9 writers, 1 for the syntax error file and 8 for the genre files
        BufferedWriter bw = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\syntax_error_file.txt"));
        BufferedWriter ccbWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Cartoons_Comics.csv.txt"));
        BufferedWriter hcbWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Hobbies_Collectibles.csv.txt"));
        BufferedWriter mtvWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Movies_TV_Books.csv.txt"));
        BufferedWriter mrbWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Music_Radio_Books.csv.txt"));
        BufferedWriter nebWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Nostalgia_Eclectic_Books.csv.txt"));
        BufferedWriter otrWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Old_Time_Radio_Books.csv.txt"));
        BufferedWriter ssmWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Sports_Sports_Memorabilia.csv.txt"));
        BufferedWriter tpaWriter = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\Trains_Planes_Automobiles.csv.txt"));
        // Iterating over book(year) files
        for (int i = 0; i < fileNames.length; i++) {
            // Reading each book(year) file
        	br = new BufferedReader(new FileReader("Comp249_W23_Assg3\\\\" + fileNames[i]));
            // Iterating over each line of each book(year) file
            while ((line = br.readLine()) != null) {
            	
            	fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //Splitting each line by commas, excluding commas within quotes
                
            	try {
            		if (fields.length < 6)
            			throw new TooFewFieldsException("Error: Too few fields\n" + line);
                    if (fields.length > 6)
                        throw new TooManyFieldsException("Error: Too many fields\n" + line);
                    if (fields[0].isEmpty() || fields[1].isEmpty() || fields[2].isEmpty() || fields[3].isEmpty() || fields[4].isEmpty() || fields[5].isEmpty())
                        throw new MissingFieldException("Error: Missing field\n" + line);
                    if (!fields[4].equals("CCB") && !fields[4].equals("HCB") && !fields[4].equals("MTV") && !fields[4].equals("MRB") && !fields[4].equals("NEB") && !fields[4].equals("OTR") && !fields[4].equals("SSM") && !fields[4].equals("TPA"))
                    	throw new UnknownGenreException("Error: Unknown Genre\n" + line);
            	}
            	
                catch (TooFewFieldsException | TooManyFieldsException | MissingFieldException | UnknownGenreException e) {
                    bw.write(e.getMessage() + " (file: " + fileNames[i] + ")\n\n");
                    continue;
                }
            
            	switch (fields[4]) {
            	case "CCB":
            	   ccbWriter.write(line + "\n");
                   break;
            	case "HCB":
                   hcbWriter.write(line + "\n");
                   break;
            	case "MTV":
                   mtvWriter.write(line + "\n");
                   break;
            	case "MRB":
                   mrbWriter.write(line + "\n");
                   break;
            	case "NEB":
                   nebWriter.write(line + "\n");
                   break;
            	case "OTR":
                   otrWriter.write(line + "\n");
                   break;
            	case "SSM":
                   ssmWriter.write(line + "\n");
                   break;
            	case "TPA":
                    tpaWriter.write(line + "\n");
                    break;
            	}
   
            } 
            br.close();
        } 
        bw.close();
        ccbWriter.close();
        hcbWriter.close();
        mtvWriter.close();
        mrbWriter.close();
        nebWriter.close();
        otrWriter.close();
        ssmWriter.close();
        tpaWriter.close();
    }
}