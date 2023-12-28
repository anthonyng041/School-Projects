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

package ca.concordia.a4;
import java.io.FileNotFoundException;

import main.stuff.*;

public class HashTablePro extends MyHash {

	public HashTablePro(String filename) throws FileNotFoundException {
		super(filename);
	}

	@Override
	protected int hash(String str) {
	    int hash = 0;
	    int prime = 31;

	    for (int i = 0; i < str.length(); i++) {
	        hash = (hash * prime + str.charAt(i)) % getTableSize();
	    }

	    return Math.abs(hash);
	}

	@Override
	protected String find(String str) throws NotFoundException, TooFullException {
	    int index = hash(str);
	    int startIndex = index;
	    int tries = 0;

	    while (true) {
	        String record = get(index);
	        if (record == null) {
	            throw new NotFoundException(index);
	        }
	        if (record.equals(str)) {
	            return record;
	        }
	        index = (index + 1) % getTableSize();

	        tries++;
	        if (tries > getTableSize()) {
	            throw new TooFullException(str);
	        }
	        if (index == startIndex) {
	            break; // Reached the initial index again, all slots checked
	        }
	    }

	    throw new NotFoundException(index);
	}

	@Override
	public void insert(String str) throws TooFullException {
	    int index = hash(str);
	    int startIndex = index;
	    int tries = 0;

	    while (true) {
	        String record = get(index);
	        if (record == null) {
	            put(index, str);
	            incSize();
	            return;
	        }
	        if (record.equals(str)) {
	            return; // Duplicate element found, no need to insert
	        }
	        index = (index + 1) % getTableSize();

	        tries++;
	        if (tries > getTableSize()) {
	            throw new TooFullException(str);
	        }
	        if (index == startIndex) {
	            break; // Reached the initial index again, all slots checked
	        }
	    }
	}

	@Override
	protected int determineTableSize(int i) {
	    int tableSize = 1;
	    while (tableSize < i) {
	        tableSize <<= 1; // Equivalent to multiplying by 2
	    }
	    return tableSize;
	}
	
}
