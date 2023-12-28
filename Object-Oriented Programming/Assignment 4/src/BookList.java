// -----------------------------------------------------
// Assignment 4
// Part: BookList Class
// Written by: Anthony Nguyen - 40210667
// -----------------------------------------------------

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The BookList class represents a circular linked list of books. Its only attribute is head which represents the first Node of a BookList.
 * @author Anthony Nguyen
 */

public class BookList {
	
	/**
	 * The Node class represents a Node in the BookList class. It contains a book and a Node called next that represents the next Node in the list.
	 * @author Anthony Nguyen
	 */
	
	private class Node {
		private Book b;
		private Node next;
		
		public Node(Book b, Node next) {
			this.b = b;
			this.next = next;
		}
	}
	
	private Node head;
	
	public BookList() {
		head = null;
	}
	
	/**
	 * Adds a Node containing the Book object to the start of the BookList.
	 * @parameter b
	 * @author Anthony Nguyen
	 */
	
	public void addToStart(Book b) {
		if (head == null) {	// If list is empty, then make a new Node containing b as the head and make it point to head (itself).
			head = new Node(b, null);
			head.next = head;
		}
		else {
			Node current = head;	// Store the head in a new Node.
			while(current.next != head) {	// Finding the last Node of the list by iterating over the list until the Node that points to head is found.
				current = current.next;
			}
			head = new Node(b, head);	// Make a new Node as the head containing b and pointing to the previous head. 
			current.next = head; // Make the last Node point to the new head.
		}
	}
	
	/**
	 * Adds a Node containing the Book object to the end of the BookList.
	 * @parameter b
	 * @author Anthony Nguyen
	 */
	
	public void addToEnd(Book b) {
		if (head == null) {	// If list is empty, then make a new Node containing b as the head and make it point to head (itself).
			head = new Node(b, null);
			head.next = head;
		}
		else {
			Node current = head;	// Store the head in a new Node.
			while(current.next != head) {	// Finding the last Node of the list by iterating over the list until the Node that points to head is found.
				current = current.next;
			}
			current.next = new Node(b, head);	// Make that last Node point a new head containing b and pointing to the head.
		}
	}
	
	/**
	 * Creates a text file containing all the records published in the given year.
	 * @parameter yr
	 * @author Anthony Nguyen
	 */
	
	public void storeRecordsByYear(int yr) throws IOException {
		String buffer = ""; // Buffer String containing all records that match yr.
		Node current = head;
		do {
			if (current.b.getYear() == yr) {
				buffer += current.b.toString() + "\n";	// If year corresponds to yr, write record to buffer.
			}		
			current = current.next;	
		} while (current != head);
		
		if (buffer.equals("")) {	// If buffer is empty,do not create a file.
			System.out.println("No records found for year " + yr + ".");
			return;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(yr + ".txt")); // Otherwise, open yr.txt and write buffer to it.
		bw.write(buffer);
		bw.close();
		System.out.println("File created for year " + yr + ".");
	}
	
	/**
	 * Inserts the given Book object in front of the Node with the passed ISBN.
	 * @parameter long, Book
	 * @return boolean
	 * @author Anthony Nguyen
	 */
	
	public boolean insertBefore(long isbn, Book b) {
		if (head.b.getIsbn() == isbn) {		// If head's isbn matches.
			Node current = head;	
			while(current.next != head) {	// Finding the last Node of the list by iterating over the list until the Node that points to head is found.
				current = current.next;
			}
			current.next = new Node (b, head);	// Making the former last Node point to a new Node that points to head.
			return true;
		}
		
		Node current = head;
        do {
        	if (current.next.b.getIsbn() == isbn) {	// If isbn of book of next Node matches, then insert Node after current Node.
        		current.next = new Node(b, current.next);	// Making a new Node containing b that points the the current Node's next.
        		return true;
        	}
        	current = current.next;
        } while (current.next != head);
        return false;
	}
	
	/**
	 * Inserts the given Book object between the two passed ISBNs.
	 * @parameter long, long, Book
	 * @return boolean
	 * @author Anthony Nguyen
	 */
	
	public boolean insertBetween(long isbn1, long isbn2, Book b) {
		Node current = head;
        do {
        	if (current.b.getIsbn() == isbn1 && current.next.b.getIsbn() == isbn2) {	// If isbn of book of next Node matches, then insert Node after current Node.
        		current.next = new Node(b, current.next);	// Making a new Node containing b that points the the current Node's next.
        		return true;								// and making that Node the current's next.
        	}
        	current = current.next;
        } while (current != head);
        return false;
	}
	
	/**
	 * Displays the content of the BookList.
	 * @author Anthony Nguyen
	 */
	
	public void displayContent() {
		if (head == null) {
            System.out.println("List is empty");
            return;
        }
		System.out.println("Here are the contents  of the list\n" + "==================================");
        Node current = head;
        do {
            System.out.println(current.b.toString() + " ==>");
            current = current.next;
        } while (current != head);
        System.out.println("==> head");
	}
	
	/**
	 * Deletes consecutive repeated records in the BookList.
	 * @return boolean
	 * @author Anthony Nguyen
	 */
	
	public boolean delConsecutiveRepeatedRecords() {
	    boolean recordDeleted = false; // This boolean value records if at least 1 record was deleted.
	    Node current = head;
	    while (current.next != head) {
	        while (current.next.b.equals(current.b)) { // Verifying if Nodes preceding current Node are equal to current Node.
	            if (current.next == head)		// If the head is a repeated record after the tail, make the Node after head the new head.
	            	head = current.next.next;
	        	current.next = current.next.next; // Removing the duplicate node
	            recordDeleted = true;
	        }
	        current = current.next;
	    }
	    return recordDeleted;
	}
	
	/**
	 * Returns Booklist that contains the records with the passed author.
	 * @parameter String
	 * @return Booklist
	 * @author Anthony Nguyen
	 */
	
	public BookList extractAuthList(String aut) {
		BookList authLst = new BookList();		
		Node current = head;
        do {
        	if (current.b.getAuthor().equals(aut)) {	// If author of current book corresponds to aut, add current book to authLst.
        		authLst.addToEnd(current.b);
        	}
        	current = current.next;
        } while (current != head);
        return authLst;
	}
	
	/**
	 * Swaps two books in the BookList.
	 * @parameter long, long
	 * @return boolean
	 * @author Anthony Nguyen
	 */
	
	public boolean swap(long isbn1, long isbn2) {
		Node node1 = null;
		Node node2 = null;
        Node current = head;	
        do {
        	if (current.b.getIsbn() == isbn1) {
        		node1 = current;						// Stores the node that contains isbn1 into node1.
        		break;									// Find first corresponding isbn and break.
        	}
        	current = current.next;
        } while (current != head);
        
        current = head;
        do {
        	if (current.b.getIsbn() == isbn2) {		
        		node2 = current;						// Stores the node that contains isbn2 into node2.
        		break;
        	}
        	current = current.next;
        } while (current != head);
        
        if (node1 == null || node2 == null)			// If either isbn does not exist, then return false.
        	return false;
        
        Book temp = node1.b;
        node1.b = node2.b;			// Swap the books.
        node2.b = temp;
     
        return true;
	}
	
	/**
	 * Creates a file named Updated_Books containing the records in the BookList.
	 * @author Anthony Nguyen
	 */
	
	public void commit() throws IOException {
		if (head == null) {	// If list is empty, leave.
			System.out.println("Error: List is empty.");
			return;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("Update_Books.txt"));
		Node current = head;
        do {
        	bw.write(current.b.toString() + "\n");
        	current = current.next;
        } while (current != head);
        bw.close();
	}
}
