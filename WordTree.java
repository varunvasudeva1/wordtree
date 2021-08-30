import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Class that allows storage of words in a k-ary tree ADT
 * Words starting with the same letters share roots, rendering the space efficiency
 * of this ADT better than if we chose to store each word individually.
 * @author Varun Vasudeva
 *
 */


public class WordTree {
	
	private Node root;
	
	/**
	 * Default constructor for WordTree
	 */
	public WordTree() {
		root = new Node('*');
	}	
	
	/**
	 * Adds a word to the tree
	 * Word is added as long as it is non-empty and not already in the tree
	 * @param word Word to be added
	 * @return True if word is added, false otherwise
	 */
	boolean add(String word) {
		if (word.equals("") || contains(word) == true)
			return false;
		else {
			Node curr = root;
			int i = 0;
			while (i < word.length()) {
				if (curr.getChild(word.charAt(i)) == null) {
					curr = curr.addChild(word.charAt(i));
					
					if (i == word.length() - 1)
						curr.end = true;
				}
				else {
					curr = curr.getChild(word.charAt(i));
					if (i == word.length() - 1)
						curr.end = true;
				}
				i++;
			}
		}
		return true;
	}
	
	/**
	 * Checks whether the tree contains the specified word
	 * @param word Word to check the tree for
	 * @return True if tree contains word, false otherwise
	 */
	boolean contains(String word) {
		Node curr = root;
		int i = 0;
		while (i < word.length()) {
			if (curr.getChild(word.charAt(i)) == null)
				return false;
			if (i == word.length() - 1)
				if (curr.getChild(word.charAt(i)).end == false)
					return false;
			curr = curr.getChild(word.charAt(i));
			i++;
		}
		return true;
	}
	
	/**
	 * Removes the specified word from the tree
	 * Removing the word will fail if the tree doesn't contain the word, tree is empty,
	 * or word is empty
	 * @param word Word to be removed from the tree
	 * @return True if word is removed, false otherwise
	 */
	boolean remove(String word) {
		if (root.children.size() == 0 || word.equals("") || !contains(word))
			return false;
		
		
		Set<String> words = allWords();
		clear();
		words.remove(word);
		for (String s : words) {
			add(s);
		}
		

		return true;
	}

	/**
	 * Returns the number of nodes in the tree
	 * @return Number of nodes in the tree
	 */
	int nodeCount() {
		return nodeCount(root);
	}
	
	// Private recursive helper method
	private int nodeCount(Node top) {
		if (top == null)
			return 0;
		int count = 1;
		for (Node child : top.children) {
			count += nodeCount(child);
		}
		return count;
	}
	
	/**
	 * Returns the number of words stored in the tree
	 * @return Number of words stored in the tree
	 */
	int wordCount() {
		Queue<Node> q = new LinkedList<>();
		int count = 0;
		q.add(root);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.end == true)
				count++;
			for (Node child : temp.children) {
				q.add(child);
			}
		}
		return count;
	}

	/**
	 * Returns the number of letters saved by using the tree as compared to storing
	 * each word individually
	 * @return Number of letters saved by using tree
	 */
	int lettersSaved() {
		Set<String> words = allWords();
		int val = 0;
		for (String word : words) {
			val += word.length();
		}
		val -= nodeCount() - 1; // -1 for root, since nothing is stored
		return Math.abs(val);
	}
	
	/**
	 * Clears the tree of all words
	 */
	void clear() {
		root.children.clear();
	}
	
	/**
	 * Returns a set of all words stored in the tree
	 * @return Set of all words stored in the tree
	 */
	
	Set<String> allWords() {
		Set<String> words = new HashSet<>();
		allWords(root, "", words);
		return words;
	}
	
	Set<String> allWords(Node top, String wordSoFar, Set<String> wordsSoFar) {
		if (top.end == true)
			wordsSoFar.add(wordSoFar);
		for (Node child : top.children) {
			allWords(child, wordSoFar + child.data, wordsSoFar);
		}
		return wordsSoFar;
	}


	/**
	 * Returns a set of all words stored in the tree starting with the specified prefix
	 * @param prefix Prefix to check words in the tree for
	 * @return Number of words starting with prefix
	 */
	Set<String> allStartingWith(String prefix) {
		Set<String> words = allWords();
		Set<String> wordsStartingWith = new HashSet<>();
		
		if (prefix.equals(""))
			return words;
		
		for (String word : words) {
			if (word.startsWith(prefix))
				wordsStartingWith.add(word);
		}
		return wordsStartingWith;
	}
	
	/**
	 * Returns a map with characters within the tree as keys and a set of the words they form
	 * as values
	 * @return Map of type <Character, Set<String>>
	 */
	Map<Character, Set<String>> wordMap() {
		Map<Character, Set<String>> wordMap = new HashMap<>();
		
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int i = 0;
		
		while (i < alphabet.length()) {
			Set<String> words = allStartingWith(alphabet.charAt(i) + "");
			if (words.size() != 0)
				wordMap.put(alphabet.charAt(i), words);
			i++;
		}
		
		return wordMap;
	}

	/**
	 * Returns a String containing the contents of the tree
	 */
	public String toString() {
		return root.toString();
		
	}
	
	// Private inner Node class
	private class Node {
		private Set<Node> children;
		private char data;
		private boolean end;
		
		
		private Node(char d) {
			data = d;
			children = new HashSet<>();
		}
		
		private Node() {
			children = new HashSet<>();
		}
		
		
		// Method to get child of node
		private Node getChild(char letter) {
			Node letterNode = null;

			for (Node child : children) {
				if (child.data == letter)
					letterNode = child;
			}
			return letterNode;
		}

		
		// Method to add child to node
		private Node addChild(char letter) {
			Node letterNode = new Node(letter);
			boolean changed = false;

			for (Node child : children) {
				if (child.data == letter) {
					letterNode = child;
					changed = true;
				}
			}
			if (changed == false) {
				children.add(letterNode);
			}
			return letterNode;
		}
		
		// Returns String of the contents of the node and its children
		public String toString() {
			return (end ? (data + "").toUpperCase() : data) + " " + children;
			
		}
		
		
	}
}
