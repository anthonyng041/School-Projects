
// -----------------------------------------------------
// Assignment 1
// Question: Part II
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------

import java.util.Scanner;

/**A class that implements the Snakes and Ladders game, allowing the user to input the number of players
 * @author Anthony Nguyen
*/

public class PlayLadderAndSnake {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Snakes and Ladders by Anthony Nguyen\nSelect a number of players.");
		Scanner scan = new Scanner(System.in);	// Takes player_number as input
		int player_number = scan.nextInt();	
		
		LadderAndSnake game = new LadderAndSnake(player_number);	// Creates LaddersAndSnake object with player_number as the passed parameter
		game.play();

	}

}