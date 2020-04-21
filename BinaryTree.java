
import java.util.ArrayList;

public class BinaryTree {

	Node root;
	Double weight;

	public BinaryTree(String value, Double weight) {
		this.root = new Node(value);
		this.weight = weight;
	}

	public static BinaryTree mergeTrees(BinaryTree t1, BinaryTree t2) {
		Double w1 = t1.weight;
		Double w2 = t2.weight;
		Node r1 = t1.root;
		Node r2 = t2.root;
		BinaryTree mergeTree = new BinaryTree("", w1 + w2);
		if(w1 < w2) {
			mergeTree.root.setLeft(r1);
			mergeTree.root.setRight(r2);
		} else {
			mergeTree.root.setLeft(r2);
			mergeTree.root.setRight(r1);
		}

		return mergeTree;
	}

	//took from https://www.geeksforgeeks.org/level-order-tree-traversal/
	public void printLevelOrder() {
		//ArrayList<String[]> allLevels = new ArrayList<String[]>();
		int h = height(this.root);
		for(int i = 1; i <= h; i++) {
			//allLevels.add(printGivenLevel(root, i));
			printGivenLevel(root, i);
		}
	}

	//took from https://www.geeksforgeeks.org/level-order-tree-traversal/
	public int height(Node root) {
		if (root == null) {
			return 0;
		} else {
			/* compute  height of each subtree */
			int lheight = height(root.left);
			int rheight = height(root.right);

			/* use the larger one */
			if (lheight > rheight) {
				return(lheight+1);
			} else {
				return(rheight+1);
			}
		}
	}

	//took from https://www.geeksforgeeks.org/level-order-tree-traversal/
	public void printGivenLevel(Node root, int level) {
		if(root == null) {
			return;
		}
		if(level == 1) {
			//String[] levelArr = new String[]
			System.out.println(root.symbol + " ");
		} else if(level > 1) {
			printGivenLevel(root.left, level-1);
			printGivenLevel(root.right, level-1);
		}
	}

	//took from https://stackoverflow.com/questions/20232858/find-path-to-node-in-tree
	public Boolean search(Node node, String value, ArrayList<String> track) {
        if (node == null) return false;

        if (node.symbol.equals(value)) {
            return true;
        }

        if (search(node.left, value, track)) {
            track.add(0, "0");
            return true;
        }
		if (search(node.right, value, track)) {
            track.add(0, "1");
            return true;
        }

        return false;
    }

}
