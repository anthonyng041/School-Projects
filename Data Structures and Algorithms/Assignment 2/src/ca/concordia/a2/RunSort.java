// Name: Anthony Nguyen
// Student ID: 40210667
// A2
// 
// Q2) How did the structuring pass you performed, specifically the reversals chosen, affect swaps
// and comparisons? Was anything else affected? Answer in less than 100 words.
// 
// The number of swaps is lesser, since the reversal of descending runs effectively sorts the array to some degree. We know that insertion sort is very fast for almost sorted arrays,
// with a time complexity of 0 in the best case and n squared in the average case. The number of comparisons can also be reduced, but not to the same level as swaps.
//
// Q3) How do you feel the size of the specific runs you recorded (ASCENDING order of length 3) impacted performance? Answer in less than 100 words.
//
// The size of the recorded ascending runs of length 3 does not directly impact the performance of the sorting algorithm.
// The impact on performance is minimal since it only involves counting the number of such runs after the structuring pass, which is a simple traversal of the sorted array.
//
// Q4) What would implementing this as a Doubly Linked List do? How would the specified structuring affect results? Answer in less than 100 words.
//
// Implementing the sorting algorithm using a Doubly Linked List instead of an array would allow for more efficient insertion and reversal operations. The structuring pass would still 	
// identify ascending and descending runs, but the reversal process would be optimized by leveraging the linked list's ability to change the direction of traversal. Reversing a run in a 
// Doubly Linked List can be done in constant time by updating the previous and next pointers of the affected nodes. This would improve the efficiency of the algorithm and potentially 
// reduce the number of swaps compared to the array-based implementation.






package ca.concordia.a2;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RunSort {
	private static int inc_runs = 0;
	private static int revs = 0;
	private static int structuring_comp = 0;
	private static int other_comp = 0;
	private static int swaps = 0;
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		String fileName = args[0];
		Scanner scanner  = new Scanner(new FileInputStream(fileName));
		
		// Determining size of array to be made.
		int size = 0;
		while (scanner.hasNextInt()) {
            scanner.nextInt();
            size++;
        }
		
        scanner.close();
        
        int[] sequence = new int[size];
        
        // Inserting inputs into sequence array.
        scanner  = new Scanner(new FileInputStream(fileName));
        for (int i = 0; i < size; i++) {
            sequence[i] = scanner.nextInt();
        }

        scanner.close();
         
        printArray(sequence);
        structuring_pass(sequence);
        insertion_sort(sequence);
        printInfo();
        printArray(sequence);
	}
	
	public static void structuring_pass(int[] array) {
		int aLength;
        int dLength;
        int startIndex;
        int endIndex;
        
        for (int i = 1; i < array.length; i++) {
        	startIndex=i-1;
        	aLength = 1;
        	dLength = 1;
        	
        	if (array[i-1] < array[i]) {
        		structuring_comp++;
        		while (array[i-1] < array[i]) {
        			structuring_comp++;
        			aLength++;
        			i++;
        			if (i >= array.length)
        				break;
        		}
        	}
        	
        	else {
        		structuring_comp++;
        		while (array[i-1] > array[i]) {
        			structuring_comp++;
        			dLength++;
        			i++;
        			if (i >= array.length)
        				break;
        		}
        	}
        	
       		endIndex = i-1;
          	
       		if (dLength > 1) {
       			while (startIndex < endIndex) {
           			int temp = array[startIndex];
           			array[startIndex] = array[endIndex];
           			array[endIndex] = temp;
           			startIndex++;
           			endIndex--;
           			swaps++;
           		}
       			revs++;
       		}
       		
       		if (aLength == 3)
    			inc_runs++;
        }
        structuring_comp--;
	}
	
	public static void insertion_sort(int[] array) {
        for (int i = 1; i < array.length; ++i) {
            int key = array[i];
            int j = i - 1;
            
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                other_comp++;
                swaps++;
                if (j < 0)
                	other_comp--;
            }
            array[j + 1] = key;
            other_comp++;
        }
    }
	
	public static void printArray(int[] array) {
        for (int i = 0; i < array.length; ++i)
            System.out.print(array[i] + " ");
        System.out.println();
    }
	
	public static void printInfo() {
		System.out.println("We sorted in INC order");
		System.out.println("We counted " + inc_runs + " INC runs of length 3");
		System.out.println("We performed " + revs + " reversals of runs in DECR order");
		System.out.println("We performed " + structuring_comp + " compares during structuring");
		System.out.println("We performed " + (other_comp + structuring_comp) + " compares overall");
		System.out.println("We performed " + swaps + " swaps overall");
	}
}
