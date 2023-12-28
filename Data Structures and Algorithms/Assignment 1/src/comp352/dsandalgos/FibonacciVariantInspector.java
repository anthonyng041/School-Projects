// Name: Anthony Nguyen
// Student ID: 40210667
// A1
//
// Q2) How does the nature of the sequence (sum of all previous values except the ones we skip, and multiplication of certain values) affect the complexity of 
// a recursive solution? Limit your answer to 150 words.
// 
// To sum the previous values, 3 recursive calls must be made for each function call, leading to exponential growth in the number of function calls.  
// This recursive method has exponential time complexity. The multiplication operation should not add any significant complexity to the method,
// as it is a multiplication by a constant, 2, which can be disregarded.
//
// Q3) How does the size of the input sequence affect the memory requirements of your program? Explain.
// Limit your answer to 100 words.
//
// Because of the exponential nature of the recursive solution, larger inputs relatively require more memory. Each additional size of input require exponentially larger
// amounts of recursive calls, which add exponentially more stack frames to the call stack. Eventually, with an input size too large, stack overflow could occur, where 
// the program requires more memory than what was allocated to the stack.

package comp352.dsandalgos;

public class FibonacciVariantInspector {
	
	private static int calls = 0;

	public static int sum(int length) {
		if (length <= 5)
			return 1;
		calls += 3;
		return sum(length-1) + sum(length-3) + 2*sum(length-4);
	}
	
	public static void print(int length) {
		if(length == 0)
			return;
		calls++;
		print(length-1);
		System.out.print(sum(length) + " ");
	}
	
	public static void main(String[] args) {
		print(Integer.parseInt(args[0]));
		System.out.println("\nCalls: " + calls);
	}
	
}
