
public class TreeNode<T> {
	private T data;
	TreeNode<T> leftChild;
	TreeNode<T> rightChild;
	
	TreeNode(T data) {
		this.data = data;
		leftChild = null;
		rightChild = null;
	}
	
	TreeNode(TreeNode<T> node) {
		data = node.getData();
		leftChild = node.leftChild;
		rightChild = node.rightChild;
	}
	
	public T getData() {
		return data;
	}
}
