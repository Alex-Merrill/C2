public class Node {

	String symbol;
	Node left;
	Node right;

	public Node(String symbol) {
		this.symbol = symbol;
		right = null;
		left = null;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

}
