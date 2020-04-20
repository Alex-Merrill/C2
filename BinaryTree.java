public class BinaryTree {

	Node root;
	Double weight;

	public BinaryTree(String value, Double weight) {
		this.root = new Node(value);
		this.weight = weight;
	}

	private Node addRecursive(Node current, String symbol) {
		if(current == null) {
			return new Node(symbol);
		}

		if(Integer.parseInt(symbol) < Integer.parseInt(current.symbol)) {
			current.left = addRecursive(current.left, symbol);
		} else if(Integer.parseInt(symbol) > Integer.parseInt(current.symbol)) {
			current.right = addRecursive(current.right, symbol);
		} else {
			return current;
		}

		return current;

	}

	private void add(String symbol) {
		root = addRecursive(root, symbol);
	}

}
