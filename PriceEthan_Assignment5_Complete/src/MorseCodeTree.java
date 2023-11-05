import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	TreeNode<String> root;
	
	MorseCodeTree() {
		root = null;
		buildTree();
	}
	
	public TreeNode<String> getRoot() {
		return root;
	}

	
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
	}

	
	public void insert(String code, String letter) {
		if (root != null) {
			addNode(root, code, letter);
		}
		else if (root == null) {
			root = new TreeNode<String>(letter);
		}
	}

	
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() > 1) {
			if (code.charAt(0) == '.') {
				addNode(root.leftChild, code.substring(1), letter);
			}
			else if (code.charAt(0) == '-') {
				addNode(root.rightChild, code.substring(1), letter);
			}
		}
		else {
			if (code.charAt(0) == '.') {
				root.leftChild = new TreeNode<String>(letter);
			}
			else if (code.charAt(0) == '-') {
				root.rightChild = new TreeNode<String>(letter);
			}
		}
		
	}

	
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	
	public String fetchNode(TreeNode<String> root, String code) {
		String letter = "";
		
		if (code.length() > 1) {
			if (code.charAt(0) == '.') {
				letter = fetchNode(root.leftChild, code.substring(1));
			}
			else if (code.charAt(0) == '-') {
				letter = fetchNode(root.rightChild, code.substring(1));
			}
		}
		else if (code.length() == 1) {
			if (code.charAt(0) == '.') {
				letter = root.leftChild.getData();
			}
			else if (code.charAt(0) == '-') {
				letter = root.rightChild.getData();
			}
		}
		
		return letter;
	}

	
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	
	public void buildTree() {
		// Root
		insert("","");
		
		// First level
		insert(".", "E");
		insert("-", "T");
		
		// Second level
		//Children of E
		insert ("..", "I");
		insert(".-", "A");
		//Children of T
		insert("-.", "N");
		insert("--", "M");
		
		// Third level
		//Children of I
		insert("...", "S");
		insert("..-", "U");
		//Children of A
		insert(".-.", "R");
		insert(".--", "W");
		//Children of N
		insert("-..", "D");
		insert("-.-", "K");
		//Children of M
		insert("--.", "G");
		insert("---", "O");
		
		// Fourth level
		//Children of S
		insert("....", "H");
		insert("...-", "V");
		//Children of U
		insert("..-.", "F");
		//Children of R
		insert(".-..", "L");
		//Children of W
		insert(".--.", "P");
		insert(".---", "J");
		//Children of D
		insert("-...", "B");
		insert("-..-", "X");
		//Children of K
		insert("-.-.", "C");
		insert("-.--", "Y");
		//Children of G
		insert("--..", "Z");
		insert("--.-", "Q");
	}

	
	public ArrayList<String> toArrayList() {
		ArrayList<String> toList = new ArrayList<String>();
		LNRoutputTraversal(root, toList);
		return toList;
	}

	
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		if (root.leftChild == null && root.rightChild == null) { // Base case
			list.add(root.getData());
		}
		
		if (root.leftChild != null) {
			LNRoutputTraversal(root.leftChild, list);
			list.add(root.getData());
		}
		
		if (root.rightChild != null) {
			LNRoutputTraversal(root.rightChild, list);
		}
	}

}
