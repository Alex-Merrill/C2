/*
* Author: Alexander Merrill
* Implements Huffman's binary encoding algorithm
*/



import java.io.IOException;
import java.util.*;
import java.lang.Double;


public class Huffman {


	public static void main(String[] args) throws IOException{
		//concatenates all args to single string
		String message = "";
		for(int i = 0; i < args.length; i++) {
			if(args.length > 1 && i != args.length-1) {
				message += args[i] + " ";
			} else {
				message += args[i];
			}
		}

		//Runs encode
		encode(message);
	}

	// Uses Huffman's algorithm to encode message into binary
	// Also prints a dictionary.
	public static String encode(String message){
		//creates map of letters in message and their probabilities
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

		//merges two smallest trees together until there is only one tree
		while(trees.size() > 1) {
			BinaryTree t1 = trees.remove(0);
			BinaryTree t2 = trees.remove(0);
			BinaryTree newTree = BinaryTree.mergeTrees(t1, t2);
			trees.add(newTree);
			trees = bubbleSort(trees);
		}

		//Set finalTree equal to merged trees
		BinaryTree finalTree = trees.get(0);

		//create dictionary for set of letters
		Map<String, String> dictionary =  new HashMap<String, String>();
		for(int i = 0; i < message.length(); i++) {
			ArrayList<String> track = new ArrayList<String>();
			String currLet = String.valueOf(message.charAt(i));
			finalTree.search(finalTree.root, currLet, track);
			String code = "";
			for(int j = 0; j < track.size(); j++) {
				code += track.get(j);
			}
			dictionary.put(currLet, code);
		}

		//print dictionary
		System.out.println("\nDictionary:");
		for(Map.Entry mapElement: dictionary.entrySet()) {
			String key = (String) mapElement.getKey();
			String val = (String) mapElement.getValue();
			System.out.println(key + " - " +  val);
		}

		//encode message and prints it
		System.out.println("\nMessage Encoding:");
		String encoding = "";
		for(int i = 0; i < message.length(); i++) {
			String currLet = String.valueOf(message.charAt(i));
			String code = dictionary.get(currLet);
			encoding += code;
		}
		System.out.println(encoding);

		return encoding;
	}

	//creates map of letters and probabilities
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

	//bubblesort
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
				if(trees.get(i).weight.compareTo(trees.get(i+1).weight) == 0 && trees.get(i).height(trees.get(i).root) < trees.get(i+1).height(trees.get(i+1).root)) {
					temp = trees.get(i);
					trees.set(i, trees.get(i+1));
					trees.set (i+1, temp);
					sorted = false;
				}
			}
		}

		return trees;
	}


}
