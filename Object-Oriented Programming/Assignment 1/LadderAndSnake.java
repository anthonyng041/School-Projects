
// -----------------------------------------------------
// Assignment 1
// Question: Part I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
//
// This program simulates a game of snakes and ladders with 2 players. A 2-D array stores the positions of the board
// from 1 to 100, going from left to right on even rows and from right to left on odd ones. To simulate the snakes and ladders,
// the position in the array where the bottom of a ladder is located is overwritten to be equal to the position at the top of the ladder,
// i.e., for the ladder from 1 to 38, position [0][0] which would normally be position 1 is overwritten to be equal to 38. 
// The opposite is done for snakes. The order of play is determined by rolling the die. Both player positions start at 0. During play, once the die
// is rolled, the value of the die is added to position of the player. The value stored in that position in the array is then stored back into position.
// The value of position should not be different if there is no snake or ladder, but should change if there is any. First player to reach 100 wins.

import java.util.Random;
import java.util.Scanner;

/**
 * The class "LadderAndSnake" models a board game with snakes and ladders.
 * It has "player_number" as the number of players, a 2-D "board" array of size 10x10,
 * "input" of type Scanner to read user inputs, and positions "p_1" and "p_2" to track the positions of players.
 * The class has a parameterized constructor, a method "flipDice" to return a random integer between 1 and 6,
 * a method "setBoard" to set the values of the board, a method "printBoard" to display the board,
 * and a method "play" to start the game by deciding which player goes first and playing the game.
 * @author Anthony Nguyen
 */

public class LadderAndSnake {
	
	private int player_number;	// The player number inputed by the user (in the driver) is a member attribute of the class.
	private static int rows = 10;	// There are 10 rows and columns in the board.
	private static int columns = 10;	
	private static int[][] board = new int[rows][columns];	// Declaring the 2-D array which models the board.
	Scanner input = new Scanner(System.in);	// This input is used to make players roll the die by pressing enter.
	private int p_1 = 0;	// These represent the position of each player that is used in both the printBoard() and play() methods, which is why they are declared here.
	private int p_2 = 0;	
	
	public LadderAndSnake(int player_number) {	// This parameterized constructor takes the number of players as the input.
		
		if (player_number > 2)	// If player_number > 2, then message is displayed and player_number is set to 2.
			System.out.println("Initialization was attempted for " + player_number + " players; however, this is only expected for an extended version of the game. Value will be set to 2");
		
		if (player_number < 2) {	// If player_number < 2, then exiting game.
			System.out.println("Error: Cannot execute the game with less than 2 players! Exiting game.");
			System.exit(0);
		}
		
		this.player_number = 2;
	}	 
	
	/** The "flipDice" method returns a random integer between 1 and 6.
	 * @return dice.nextInt(6)+1
	 * @author Anthony Nguyen
	*/
	
	public int flipDice() {
		Random dice = new Random();	// Creates a dice object of type random.
		return dice.nextInt(6)+ 1;	// Takes values from 1 to 6.
	}
	
	/** The "setBoard" method sets the values of the board.
	 * @author Anthony Nguyen
	 */
	
	public void setBoard() {
		for (int i = 0; i < rows; i++)	// Nested for loops iterating over rows and columns.
			for (int j = 0; j < columns; j++) {
				if(i % 2 == 0)	// If row number is even, then column number goes in ascending order.
					board[i][j] = i * 10 + j + 1;
				else	// If odd, column number goes in descending order.
					board[i][j] = (i + 1) * 10 - j;
			}
		
		board [0][0] = 38;	// Overwriting the board positions where ladders and snakes are located.
		board [0][3] = 14;
		board [0][8] = 31;
		board [1][4] = 6;
		board [2][0] = 38;
		board [2][7] = 84;
		board [3][4] = 44;
		board [4][7] = 30;
		board [5][9] = 67;
		board [6][3] = 60;
		board [7][9] = 91;
		board [7][1] = 19;
		board [7][0] = 100;
		board [9][7] = 68;
		board [9][5] = 24;
		board [9][3] = 76;
		board [9][2] = 78;
	}
	
	/** The method "printBoard" prints the board.
	 * @author Anthony Nguyen
	 */
	
	public void printBoard() {
		int pos;
		System.out.println("\n           SNAKES AND LADDERS");
	    System.out.println("******************************************");
	    	for (int i = 0; i < 10; i++) {	// Nested for loops iterating over rows and columns. 
	    		for (int j = 0; j < 10; j++) {
	    			
	    			if (i % 2 == 0) pos = 101 - (10 * i + j + 1);	// If row is even, then position follows ascending order along row.
	    			else		  pos = 101 - (10 *(i+1) - j);	// If odd, then position follows descending order along row.
	    				
	    			if (p_1 == pos) {
	                System.out.print(" *P1");
	            } else if (p_2 == pos) {
	              System.out.print(" *P2");
	            } else {
	          System.out.format("%4d",pos); // Formating the board.
	            }
	          }
	          System.out.println("\n");
	        }
	        System.out.println("******************************************");
	      }
	
	/** The method "play" initiates the game.
	 * @author Anthony Nguyen
	 */
	
	public void play() {
		
		System.out.println("\nNow deciding which player will start playing;");
		System.out.println("\nPress enter to roll the die.");
		input.nextLine();	// Requiring user input to roll the die.
		int	roll_1 = flipDice();	// Storing the value of the die into a variable.
		System.out.println("Player 1 got a dice value of " + roll_1 + ".");
		
		System.out.println("\nPress enter to roll the die.");
		input.nextLine();
		int roll_2 = flipDice();
		System.out.println("Player 2 got a dice value of " + roll_2 + ".");
		
		int count = 1;	// Count of die rolls.
		if (roll_1 == roll_2)
			System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie.");
		
		while (roll_1 == roll_2) {	// If equal, looping until unequal.
			System.out.println("\nPress enter to roll the die.");
			input.nextLine();
			roll_1 = flipDice();
			System.out.println("Player 1 got a dice value of " + roll_1 + ".");
			
			System.out.println("\nPress enter to roll the die.");
			input.nextLine();
			roll_2 = flipDice();
			System.out.println("Player 2 got a dice value of " + roll_2 + ".");
			
			count++;
			System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie.");
		}
		
		if (roll_1 > roll_2)	// Highest number starts first.
			System.out.println("\nReached final decision on order of playing: Player 1 then Player 2.");
		
		if (roll_2 > roll_1)
			System.out.println("\nReached final decision on order of playing: Player 2 then Player 1.");
		
		System.out.println("It took " + count +" attempt(s) before a decision could be made.");
		
		setBoard();	// Setting board.
		
		int dice;	// Stores value of dice every turn.
		int row;	// Stores position of player in term of row and column.
		int col;
		
		if (roll_2 > roll_1) {	// Player 2 starts first if higher roll, which is why this segment is outside while loop.
			System.out.println("\nPress enter to roll the die.");
			input.nextLine();
			dice = flipDice();
			p_2 += dice;
			System.out.println("Player 2 got a dice value of " + dice + "; ");
			
			row = (p_2 - 1) / 10;	// Finding the row and column of a certain position.
			col = (p_2 - 1) % 10;
			p_2 = board [row][col];	// Assigning the value of the position in the array to p_2, if the value is different, then a snake or ladder was encountered.
			System.out.println("now in square " + p_2);
			
			printBoard();	// Printing board every turn.
			
			
		}
			
		while (p_1 != 100 || p_2 != 100) {	// If either position equals 100, exit loop.
			System.out.println("\nPress enter to roll the die.");
			input.nextLine();
			dice = flipDice();
			p_1 += dice;
			System.out.println("Player 1 got a dice value of " + dice + "; ");
			
			if (p_1 > 100)	// If position > 100, subtract from 100 excess (or just subtract position from 200).
				p_1 = 200 - p_1;	
			
			row = (p_1 - 1) / 10;	// Determining row of position
			if (row % 2 == 0)		// If row number is even, then columns are counted from left to right. Opposite if odd.
				col= (p_1 - 1) % 10;
			else
				col = 9 - ((p_1 - 1) % 10);	
			
			p_1 = board [row][col];
			
			System.out.println("now in square " + p_1);
			
			if (p_1 == 100)					
				break;	// Must break otherwise rest of loop will be iterated.
			
			if (p_1 == p_2) {	// Kick other player to 0 if equal in position.
				p_2 = 0;
				System.out.println("\nPlayer 2 returns to square 0.");
			}
			
			printBoard();
				
			System.out.println("\nPress enter to roll the die.");	// Player 2 now.
			input.nextLine();
			dice = flipDice();
			p_2 += dice;
			System.out.println("Player 2 got a dice value of " + dice + "; ");
			
			if (p_2 > 100) 
				p_2 = 200 - p_2;
			
			row = (p_2 - 1) / 10;
			if (row % 2 == 0)
				col= (p_2 - 1) % 10;
			else
				col = 9 - ((p_2 - 1) % 10);
			
			p_2 = board [row][col];	
			
			System.out.println("now in square " + p_2);
			
			if (p_2 == p_1) {
				p_1 = 0;
				System.out.println("\nPlayer 1 returns to square 0.");
			}
			
			printBoard();
		}
		
		if (p_1 == 100) {	// The first player to position 100 wins
			printBoard();
			System.out.println("\nCongratulations! Player 1 is the winner.");
		}
		
		if (p_2 == 100) {
			printBoard();
			System.out.println("\nCongratulations! Player 2 is the winner.");
		}
		
	}

}
