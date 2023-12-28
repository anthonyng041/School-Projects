// -----------------------------------------------------
// Assignment 3
// Question: 3
// Written by: Anthony Nguyen - 40210667 AND Nektarios Zampetoulakis - 40211948
// -----------------------------------------------------

package Part3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import Part2.Book;


// This method iterates once over the serialized book files to determine their lengths. Iterates a second time to deserialize book objects.
// Creates a navigation console to view the book records.

public class Part3 {
	
	/**
	 * Iterates over serialized book files and creates navigation console to view book object records.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
public static void do_part3() throws IOException, ClassNotFoundException {
		
		//Set each binary file into array genrelist
		String[] genrelist = new String[8];
		genrelist[0]= "Comp249_W23_Assg3\\\\Cartoons_Comics.csv.ser";
		genrelist[1]= "Comp249_W23_Assg3\\\\Hobbies_Collectibles.csv.ser";
		genrelist[2]= "Comp249_W23_Assg3\\\\Movies_TV_Books.csv.ser";
		genrelist[3]= "Comp249_W23_Assg3\\\\Music_Radio_Books.csv.ser";
		genrelist[4]= "Comp249_W23_Assg3\\\\Nostalgia_Eclectic_Books.csv.ser";
		genrelist[5]= "Comp249_W23_Assg3\\\\Old_Time_Radio_Books.csv.ser";
		genrelist[6]= "Comp249_W23_Assg3\\\\Sports_Sports_Memorabilia.csv.ser";
		genrelist[7]= "Comp249_W23_Assg3\\\\Trains_Planes_Automobiles.csv.ser";
		
		ObjectInputStream binaryReader;
		Object obj;
		int count;
		int[] arraySizes = new int[8];
		Book[] ccbArray, hcbArray, mtvArray, mrbArray, nebArray, otrArray, ssmArray, traArray;
		
		//Count the book records in each genrelist binary file and set them into an array of sizes.
		for (int i = 0; i < genrelist.length; i++) {
			binaryReader = new ObjectInputStream(new FileInputStream(genrelist[i]));
			count = 0;
			
			while ((obj = binaryReader.readObject()) instanceof EOF == false) {	// At the end of each file, an object EOF was placed in part 2, so that the loop stops when it read the object EOF
				count++;
			}
			binaryReader.close();
			arraySizes[i] = count;
		}
		//Set each array of a genre of books to the size found in array of sizes.
		ccbArray = new Book[arraySizes[0]];
		hcbArray = new Book[arraySizes[1]];
		mtvArray = new Book[arraySizes[2]];
		mrbArray = new Book[arraySizes[3]];
		nebArray = new Book[arraySizes[4]];
		otrArray = new Book[arraySizes[5]];
		ssmArray = new Book[arraySizes[6]];
		traArray = new Book[arraySizes[7]];
		
		//Create a 2D Book array for each genre and number of records in file, ex: Book[genre][size].
		Book[][] array_of_arrays = {ccbArray, hcbArray, mtvArray, mrbArray, nebArray, otrArray, ssmArray, traArray};
		int counter;
		
		//Iterate over each genre list binary file and input each serialized book object.
		for (int i = 0; i < genrelist.length; i++) {
			binaryReader = new ObjectInputStream(new FileInputStream(genrelist[i]));
			counter = 0;
			
			while ((obj = binaryReader.readObject()) instanceof EOF == false) {
				array_of_arrays[i][counter] = (Book) obj;
				counter++;
			}
			binaryReader.close();
		}
		
		int select = 1;
		String input = null;
		Scanner scan = new Scanner(System.in);
		int n;
		int position;
		String buffer;
		
		//Main Menu for navigation console
		while (select != 9) { //input 9 exits menu
		
			System.out.println("-----------------------------\n\t  Main Menu\n-----------------------------\n"
					+ "v View the selected file: " + genrelist[select-1] + " (" + arraySizes[select-1] + " records)\n"
					+ "s Select a file to view\n"
					+ "x Exit\n"
					+ "-----------------------------\n\n"
					+ "Enter Your Choice: ");
			//select choice
			input = scan.next();
		
			//Menu for view selected file
			if (input.equals("v")) {
				n = 1;
				position = 0;
				System.out.println("Viewing: " + genrelist[select-1] + " (" + arraySizes[select-1] + " records)\n");
				System.out.println("Control the number of books to display: ");
				while (n != 0) {  //system returns to main menu if n = 0
					buffer = "";
					n = scan.nextInt();
					if (n > 0) {
						try {
							//iterates over chosen genre array of book objects, prints toString method for n chosen books
							for (int i = 0; i < n; i++) {
								buffer += array_of_arrays[select-1][position+i].toString() + "\n";
							}
							System.out.println(buffer);
							position += n-1;
						}
						//Check for array out of bound exception, corrects position by reducing by one until book object is found
						catch (ArrayIndexOutOfBoundsException e) {
							System.out.println(buffer);
							System.out.println("EOF has been reached");
							position = arraySizes[select-1] - 1;
						}
					}
					if (n < 0) {
						try {
							for (int i = 0; i < -n; i++) {
								buffer += array_of_arrays[select-1][position+n+i+1].toString() + "\n";
							}
							System.out.println(buffer);
							position -= -n-1;
						}
						//Check for array out of bound exception, corrects position by adding by one until first book object is found
						catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("BOF has been reached\n");
							for (int i = 0; i <= position; i++) {
								System.out.println(array_of_arrays[select-1][i].toString());
							}
							position = 0;
						}
					}
				}
			}
			//Menu for select view file
			if (input.equals("s")) {
				System.out.println("------------------------------\n"
						+ "File Sub-Menu\n"
						+ "------------------------------\n"
						+ "1 Cartoons_Comics_Books.csv.ser (" + arraySizes[0] + " records)\n"
						+ "2 Hobbies_Collectibles_Books.csv.ser (" + arraySizes[1] + " records)\n"
						+ "3 Movies_TV.csv.ser (" + arraySizes[2] + " records)\n"
						+ "4 Music_Radio_Books.csv.ser (" + arraySizes[3] + " records)\n"
						+ "5 Nostalgia_Eclectic_Books.csv.ser (" + arraySizes[4] + " records)\n"
						+ "6 Old_Time_Radio.csv.ser (" + arraySizes[5] + " records)\n"
						+ "7 Sports_Sports_Memorabilia.csv.ser (" + arraySizes[6] + " records)\n"
						+ "8 Trains_Planes_Automobiles.csv.ser (" + arraySizes[7] + " records)\n"
						+ "9 Exit\n"
						+ "------------------------------\n"
						+ "Enter Your Choice: ");
				//Select genre file to view
				select = scan.nextInt();
			}
	
			if (input.equals("x")){
				break;
			}
		
		}
		System.out.println("\nEnd of Program");
		System.exit(0);
	}
}
