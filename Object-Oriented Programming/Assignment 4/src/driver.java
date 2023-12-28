// -----------------------------------------------------
// Assignment 4
// Part: Driver
// Written by: Anthony Nguyen - 40210667
// -----------------------------------------------------

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The driver implements the methods of the BookList class.
 * @author Anthony Nguyen
 */

public class driver {

	public static void main(String[] args) throws IOException {
		ArrayList<Book> arrLst = new ArrayList<>();			
		BookList bkLst = new BookList();
		
		BufferedReader br = new BufferedReader(new FileReader("Books.txt"));
		
		String line = null;
		String[] fields;
		String title;
		String author;
		double price;
		long isbn;
		String genre;
		int year;
		
		while ((line = br.readLine()) != null) {	// Iterating over file, line by line.
			fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);		// Splits each line into an array of strings stored in fields[].
			title = fields[0];								// Storing strings in respective field.
			author = fields[1];
			price = Double.parseDouble(fields[2]);			// Parsing each string into double, long and int respectively and storing in respective field.
			isbn = Long.parseLong(fields[3]);
			genre = fields[4];
			year = Integer.parseInt(fields[5]);
			
			if (year >= 2024) {										
				arrLst.add(new Book(title, author, price, isbn, genre, year));		// Add to ArrayList if year >= 2024.
			}
			
			else {
				bkLst.addToEnd(new Book(title, author, price, isbn, genre, year));	// Else, add to BookList.
			}
		}
		br.close();
		
		if (!arrLst.isEmpty()) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("YearErr.txt"));			//Iterating over ArrayList and writing each record to YearErr.txt file
			for (int i = 0; i < arrLst.size(); i++)
				bw.write(arrLst.get(i).toString() + "\n");
			bw.close();
			System.out.println("YearErr File Created.");
		}
	
		int input = 0;
		long isbnInput1 = 0;
		long isbnInput2 = 0;
		boolean commited = false;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to my program!");
		
		while (true) {		// Infinite loop that only stops if input = 8.
			bkLst.displayContent();
			
			System.out.println("Tell me what you want to do? Let's chat since this is trnding now! Here are the options:\n"
					+ "\t1) Give me a year # and I would extract all records of that year and store them in a file for that year;\n"
					+ "\t2) Ask me to delete all consecutive repeated records;\n"
					+ "\t3) Give me an author name and I will create a new list with the records of this author and display them;\n"
					+ "\t4) Give me an ISBN number and a Book object, and I will insert Node with the book before the record with this ISBN;\n"
					+ "\t5) Give me 2 ISBN numbers and a Book object, and I will insert a Node between them, if I find them!\n"
					+ "\t6) Give me 2 ISBN numbers and I will swap them in the list for rearrangement of records; of course if they exist!\n"
					+ "\t7) Tell me to COMMIT! Your command is my wish. I will commit your list to a file called Updated_Books;\n"
					+ "\t8) Tell me to STOP TALKING. Remember, if you do not commit, I will not!\n"
					+ "\tEnter your Selection: ");
			
			input = scan.nextInt();
			
			if (input == 1) {											// Calling respective methods based on input.
				System.out.println("Enter year: ");
				bkLst.storeRecordsByYear(scan.nextInt());
			}
			if (input == 2) {
				bkLst.delConsecutiveRepeatedRecords();
			}
			if (input == 3) {
				System.out.println("Enter the name of the author: ");
				scan.nextLine();			// Skipping current line, otherwise name of author will be set to 3 (input).
				bkLst.extractAuthList(scan.nextLine()).displayContent();
			}
			if (input == 4) {
				System.out.println("Enter the reference ISBN: ");
				isbnInput1 = scan.nextLong();
				System.out.println("Enter the name of the book to be inserted: ");
				scan.nextLine();
				title = "\"" + scan.nextLine() + "\"";
				System.out.println("Enter the author of the book to be inserted: ");
				author = scan.nextLine();
				System.out.println("Enter the price of the book to be inserted: ");
				price = scan.nextDouble();
				System.out.println("Enter the ISBN of the book to be inserted: ");
				isbn = scan.nextLong();
				System.out.println("Enter the genre of the book to be inserted: ");
				genre = scan.next();
				System.out.println("Enter the year of publishing of the book to be inserted: ");
				year = scan.nextInt();
			
				bkLst.insertBefore(isbnInput1, new Book(title, author, price, isbn, genre, year));
				
			}
			if (input == 5) {	
				System.out.println("Enter the first reference ISBN: ");
				isbnInput1 = scan.nextLong();
				System.out.println("Enter the second reference ISBN: ");
				isbnInput2 = scan.nextLong();
				System.out.println("Enter the name of the book to be inserted: ");
				scan.nextLine();
				title = "\"" + scan.nextLine() + "\"";
				System.out.println("Enter the author of the book to be inserted: ");
				author = scan.nextLine();
				System.out.println("Enter the price of the book to be inserted: ");
				price = scan.nextDouble();
				System.out.println("Enter the ISBN of the book to be inserted: ");
				isbn = scan.nextLong();
				System.out.println("Enter the genre of the book to be inserted: ");
				genre = scan.next();
				System.out.println("Enter the year of publishing of the book to be inserted: ");
				year = scan.nextInt();
				
				bkLst.insertBetween(isbnInput1, isbnInput2, new Book(title, author, price, isbn, genre, year));
			}
			if (input == 6) {	
				System.out.println("Enter the first reference ISBN: ");
				isbnInput1 = scan.nextLong();
				System.out.println("Enter the second reference ISBN: ");
				isbnInput2 = scan.nextLong();
				
				bkLst.swap(isbnInput1, isbnInput2);
			}
			if (input == 7) {
				bkLst.commit();
				System.out.println("Updated_Books file created.");
				commited = true;
			}
			
			if (input == 8) {
				if (commited) {
					System.out.println("Program terminated.");
					System.exit(0);
				}
				else {
					System.out.println("I will not exit until you commit!");
				}
			}
		}		
	}
}
