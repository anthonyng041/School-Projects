// -----------------------------------------------------
// Assignment 3
// Question: 2
// Written by: Anthony Nguyen - 40210667 AND Nektarios Zampetoulakis - 40211948
// -----------------------------------------------------

package Part2;

import Part3.EOF;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

// This method iterates over each genre file, and checks for semantic errors. If an exception is found, the book is written to the semantic error file
// If not, then the book recorded to an object that is then serialized and placed into a binary file.

public class Part2 {
	
	/**
	 * Iterates over each genre list file, checks for BadIsbn10Exception, BadIsbn13Exception, BadPriceException, and BadYearException.
	 * If an exception is found, it writes the book record into semantic_error_file.txt.
	 * If an exception is not found, it writes each book record into a serialized book object in their respective binary file.
	 * @throws IOException, BadIsbn10Exception, BadIsbn13Exception, BadPriceException, and BadYearException
	 * @author Anthony and Nektarios
	 */
	
	public static void do_part2() throws IOException {
		//Create an array of each genre file.
		String[] genrelist = new String[8];
		genrelist[0]= "Comp249_W23_Assg3\\\\Cartoons_Comics.csv.txt";
		genrelist[1]= "Comp249_W23_Assg3\\\\Hobbies_Collectibles.csv.txt";
		genrelist[2]= "Comp249_W23_Assg3\\\\Movies_TV_Books.csv.txt";
		genrelist[3]= "Comp249_W23_Assg3\\\\Music_Radio_Books.csv.txt";
		genrelist[4]= "Comp249_W23_Assg3\\\\Nostalgia_Eclectic_Books.csv.txt";
		genrelist[5]= "Comp249_W23_Assg3\\\\Old_Time_Radio_Books.csv.txt";
		genrelist[6]= "Comp249_W23_Assg3\\\\Sports_Sports_Memorabilia.csv.txt";
		genrelist[7]= "Comp249_W23_Assg3\\\\Trains_Planes_Automobiles.csv.txt";
		
		String line;
		String isbn;
		int digit;
		int sum;
		double price;
		int year;
		String[] fields;
		Book book;
		BufferedReader br;
		BufferedWriter bw = new BufferedWriter(new FileWriter("Comp249_W23_Assg3\\\\semantic_error_file.txt"));
		ObjectOutputStream binaryWriter;
		
		//Iterate over each book record in each book genre file
		for (int i = 0; i < genrelist.length; i++) {
			br = new BufferedReader(new FileReader(genrelist[i]));
			binaryWriter = new ObjectOutputStream(new FileOutputStream(genrelist[i].substring(0,genrelist[i].length()-3) + "ser"));	// Removing .txt extension and adding .ser instead
			
			while ((line = br.readLine()) != null) {
				
			   //Create String array for each individual record, split the contents (title, authors, price, isbn, genre, year) by comma into fields.
				fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //Code used to ignore commas found between quotation marks
				sum = 0;
				isbn = fields[3];
				price = Double.parseDouble(fields[2]);
				year = Integer.parseInt(fields[5]);
				
				//Check for isbn, price and year exceptions
				try {
					if (isbn.length()==10) {
						for (int j = 0; j < isbn.length(); j++) {
							digit = Character.getNumericValue(isbn.charAt(j));
							if (digit < 0)
								throw new BadIsbn10Exception("Error: Invalid 10-digit ISBN\n" + line);
							sum += (10 - j) * digit;
						}
						if (sum % 11 != 0)
							throw new BadIsbn10Exception("Error: Invalid 10-digit ISBN\n" + line);
					}
					
					if (isbn.length()==13) {
						for (int j = 0; j < isbn.length(); j++) {
							digit = Character.getNumericValue(isbn.charAt(j));
							if (digit < 0)
								throw new BadIsbn13Exception("Error: Invalid 13-digit ISBN\n" + line);
							if (j % 2 != 0)
								sum += 3 * digit;
							else
								sum += digit;
						}
						if (sum % 10 != 0)
							throw new BadIsbn13Exception("Error: Invalid 13-digit ISBN\n" + line);
					}
					
					if (price < 0)
						throw new BadPriceException("Error: Invalid price\n" + line);
					
					if (year < 1995 || year > 2010)
						throw new BadYearException("Error: Invalid year\n" + line);
				}
				
				catch (BadIsbn10Exception | BadIsbn13Exception | BadPriceException | BadYearException e) {
					bw.write(e.getMessage() + " (file: " + genrelist[i] + ")\n\n");
					continue;
				} 
				//Set each book record into serialized book object and write into binary file
				book = new Book(fields[0], fields[1], price, isbn, fields[4], year);
				binaryWriter.writeObject(book);
			}
			binaryWriter.writeObject(new EOF()); // A dummy EOF object is placed at the end of the file to indicate end of file
			br.close();							 // An EOF class that contains no attributes was made for this purpose in part 3
			binaryWriter.close();
		}
		bw.close();
	}
}