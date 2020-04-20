/*
* Author: Alexander Merrill
* Implements Huffman's binary encoding algorithm
*/



import java.io.IOException;
import java.util.*;
import java.lang.Double;


public class Huffman {


	public static void main(String[] args) throws IOException{

		//Runs encode
		encode(args[0]);


	}

	public static String encode(String message){
		// Uses Huffman's algorithm to encode message into binary
		// Also prints a dictionary.

		Map<String, Double> probs = getProbMap(message);

		//create trees for each char and store them in trees arraylist
		ArrayList<BinaryTree> trees = new ArrayList<BinaryTree>();
		for(Map.Entry mapElement: probs.entrySet()) {
			String key = (String) mapElement.getKey();
			Double val = (Double) mapElement.getValue();
			trees.add(new BinaryTree(key, val));
		}

		//sort trees by weight in ascending order
		trees = bubbleSort(trees);


		//maybe do this:
		// ArrayList<BinaryTree> finalTree = constructTree(trees);
		//and put this stuff in constructTree()
		while(trees.size() > 0) {


		}



		String bString = "Hi";

		return bString;

	}

	public static Map<String, Double> getProbMap(String message) {
		//shortcut for message.length()
		int mLen = message.length();

		//Create a map
		//Key is char in input string
		//value is number of times key is present in input string
		Map<String, Double> probs = new HashMap<String, Double>();
		for(int i = 0; i < message.length(); i++) {
			String letter = String.valueOf(message.charAt(i));

			//adds letter to map, or increases value if letter is in Map
			if(probs.get(letter) == null) {
				double newChar = Double.valueOf(1);
				probs.put(letter, newChar);
			} else {
				double newChar = probs.get(letter);
				newChar = Double.valueOf(newChar + 1);
				probs.replace(letter, newChar);
			}
		}

		//replace values of map with probabilities instead of frequency
		for(Map.Entry mapElement: probs.entrySet()) {
			String key = (String) mapElement.getKey();
			Double val = (Double) mapElement.getValue();
			probs.replace(key, val/mLen);
		}

		return probs;
	}

	public static ArrayList<BinaryTree> bubbleSort(ArrayList<BinaryTree> trees) {
		boolean sorted = false;
		BinaryTree temp;
		while(!sorted) {
			sorted = true;
			for (int i = 0; i < trees.size() - 1; i++) {
				if (trees.get(i).weight > trees.get(i+1).weight) {
					temp = trees.get(i);
					trees.set(i, trees.get(i+1));
					trees.set(i+1, temp);
					sorted = false;
				}
			}
		}

		return trees;
	}


}
