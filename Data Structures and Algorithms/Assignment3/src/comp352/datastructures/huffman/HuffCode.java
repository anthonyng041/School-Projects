// Name: Anthony Nguyen
// Student ID: 40210667
// A3
// 
// Q2) What is the purpose of using a priority queue in Huffman coding, and how does it help to generate an
// optimal code?
// 
// The priority queue helps ensure that the characters with the lowest frequencies are given higher priority during tree construction. Huffman coding aims to assign shorter codes to more frequent 
// characters, thus shortening the resulting code. Without the priority queue, finding the lowest frequency nodes would require a linear search each time, resulting in a higher time complexity.
//
// Q3) How does the length of a Huffman code relate to the frequency of the corresponding symbol, and why
// is this useful for data compression?
//
// In Huffman coding, the length of a code is inversely proportional to the frequency of the character. Since frequent characters have shorter codes, during data compression, the resulting code
// is also shorter resulting in a more compact representation of data. 
//
// Q4) What is the time complexity of building a Huffman code, and how can you optimize it?
//
// The time complexity is nlogn, as insertion and removal from the priority queue takes logn time, for n elements in the queue. To optimize a Huffman code, an efficient way to count the frequency
// of characters is important as it can take a lot of time for large text files to be processed. Using arrays or hash maps would be ideal to keep track of the frequencies.

package comp352.datastructures.huffman;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.*;

class HuffNode {
  int freq;
  char c;
  HuffNode left;
  HuffNode right;
  }

class HuffComparator implements Comparator<HuffNode> {
	  public int compare(HuffNode x, HuffNode y) {
	    return x.freq - y.freq;
	  }
}

public class HuffCode {
	private static Map<Character, String> codes = new HashMap<>();

	private static void storeCodes(HuffNode root, String str) {
	    if (root == null) {
	        return;
	    }
	    if (root.freq != '$') {
	        codes.put(root.c, str);
	    }
	    storeCodes(root.left, str + "0");
	    storeCodes(root.right, str + "1");
	}
	
	private static String encodeString(String text, Map<Character, String> codeMap) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            String code = codeMap.get(c);
            if (code != null) {
                encodedText.append(code);
            } else {
                System.out.println("No code found for character: " + c);
            }
        }
        return encodedText.toString();
    }
	
	private static String decodeString(String encodedText, Map<Character, String> codeMap) {
        StringBuilder decodedText = new StringBuilder();
        int startIndex = 0;
        int endIndex = 1;
        while (endIndex <= encodedText.length()) {
            String code = encodedText.substring(startIndex, endIndex);
            for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
                if (entry.getValue().equals(code)) {
                    decodedText.append(entry.getKey());
                    startIndex = endIndex;
                    break;
                }
            }
            endIndex++;
        }
        return decodedText.toString();
    }
	
	public static void main(String[] args) throws IOException {
		String fname = args[0];
		String code = args[1];
		
		BufferedReader br = new BufferedReader (new FileReader(fname));
		
		int c;
		int count = 0;
		
		while ((c = br.read()) != -1) {	// Counts the number of characters in the file.
			count++;
		}
		
		br.close();
		
		char[] characters = new char[count];
		int[] frequency = new int[count];
		
		br = new BufferedReader (new FileReader(fname));
		
		char character;
		
		for (int i = 0; i < count; i++) {
			character = (char) br.read();
			for (int j = 0; j < i; j++) {
				if (character == characters[j]) {
					frequency[j]++;
					break;
				}
				if (j == i-1)
					characters[i] = character;
			}
		}
		
		PriorityQueue<HuffNode> queue = new PriorityQueue<HuffNode>(count, new HuffComparator());

	    for (int i = 0; i < count; i++) {
	      HuffNode node = new HuffNode();

	      node.c = characters[i];
	      node.freq = frequency[i];

	      node.left = null;
	      node.right = null;

	      queue.add(node);
	    }

	    HuffNode root = null;

	    while (queue.size() > 1) {

	      HuffNode x = queue.peek();
	      queue.poll();

	      HuffNode y = queue.peek();
	      queue.poll();

	      HuffNode n = new HuffNode();

	      n.freq = x.freq + y.freq;
	      n.c = '-';
	      n.left = x;
	      n.right = y;
	      root = n;

	      queue.add(n);
	    }
	    
	    storeCodes(root, "");
	    
	    Scanner scan = new Scanner(System.in);
	    
		if (code.equals("encode")) {
			String text = scan.next();
			text.toLowerCase();
			System.out.println(encodeString(text, codes));
		}
		
		if (code.equals("decode")) {
			String sequence = scan.next();
			System.out.println(decodeString(sequence, codes));
		}	
	}

}