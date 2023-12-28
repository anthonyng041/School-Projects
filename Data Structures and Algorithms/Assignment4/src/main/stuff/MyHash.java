// Name: Anthony Nguyen
// Student ID: 40210667
// A4
// 
// 1. Discuss the time complexity of your hash table operations: insertion, deletion, and lookup. How
// do these complexities compare to those of the TRHASH version?
// 
// For both the TRHASH version and my version, the hash function has a time complexity of n. The find method from the TRHASH version uses linear probing.
// In the worst case, where all slots are filled or the element is not present, the time complexity is n. In my version, find also uses linear probing, but it
// breaks the loop when it reaches the initial index again, avoiding unnecessary iterations beyond the table size. The average time complexity might be lower due to the reduced number of iterations in most cases.
// 
// 2. How does load factor influence the performance of a hash table? How did you manage the load
// factor in your implementation?
//
// As the load factor increases, the probability of collisions also increases. Higher collision frequency can lead to degraded performance as it requires more time to resolve collisions and handle them appropriately.
// A higher load factor means the hash table is more densely populated, requiring a larger number of slots to store the elements. This can lead to increased memory usage, potentially affecting the overall memory efficiency of the data structure.
// By determining the initial table size based on the number of elements and using a hash function that distributes elements evenly across the table, the load factor can be controlled to some extent.
//
// 3. What is collision in hashing and what strategies did you employ in your MyHash subclass to handle
// it? Discuss the pros and cons of your chosen strategy.
//
// In hashing, collision occurs when two different keys or elements map to the same hash value or index in the hash table. Collisions are inevitable in hash tables because the number of possible
// keys or elements is typically larger than the number of available hash table slots. In my implementation, I employed the strategy of linear probing to handle collisions.
// It is simple to implement compared to other collision resolution strategies and is space efficient since it doesn't require additional data structures. However, it has poor
// worst-case performance.

package main.stuff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class MyHash {
	private String[] table;
	private int size = 0;

	// Okay, so I need a way to check if the table is full. This should do it,
	// right?
	// Comparing the number of elements with the table's length... that's gotta be
	// right.
	public boolean isFull() {
		return size == table.length;
	}

	// And what's a hash table without knowing its size? It's nothing, I tell ya!
	public int getTableSize() {
		return table.length;
	}

	// Yeah, let's keep track of how many elements we insert. We're responsible like
	// that.
	public void incSize() {
		++size;
	}

	// Oh, and we might want to know the current size too, right? Yes, definitely.
	public int getSize() {
		return size;
	}

	// Now, here comes the big stuff - constructing the hash table from a file.
	// Reading the file twice? I guess that's okay... as long as it's not too big.
	// Gosh, what if the file is huge? Maybe I should reconsider this approach.
	public MyHash(String filename) {
		super();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"), filename).getAbsolutePath()));
			String line;
			int totalsize = 0;

			// Count the number of non-empty lines in the file
			while ((line = reader.readLine()) != null) {
				String str = line.trim();
				if (!str.isEmpty()) {
					totalsize++;
				}
			}

			// Create the hash table
			System.out.println("Our hash table will be " + determineTableSize(totalsize) + " long for " + totalsize
					+ " elements.");
			this.table = new String[determineTableSize(totalsize)];
			reader.close();
			reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"), filename).getAbsolutePath()));
			while ((line = reader.readLine()) != null) {
				String str = line.trim();
				if (str.isEmpty())
					continue;
				insert(str);
			}

			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		} catch (TooFullException e) {
			System.err.println("Error filling hash table: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// These getter and setter, though... I mean, they're simple, but they're
	// crucial.
	// Mess these up, and everything falls apart. No pressure, right?
	protected String get(int index) {
		return table[index];
	}

	protected void put(int index, String record) {
		table[index] = record;
	}

	// Here are our abstract methods, our promises of functionality to our future
	// subclasses.
	// We're setting the bar here - hash, find, insert - the core of a hash table.
	// They better not let us down...
	abstract protected int hash(String str);

	abstract protected String find(String str) throws NotFoundException, TooFullException;

	abstract protected void insert(String str) throws TooFullException;

	// I'm not sure what's the best size for the table yet, so let's leave it up to
	// our subclasses.
	// It's a big responsibility, but I believe in them. They've got this!
	abstract protected int determineTableSize(int i);

}
