import java.util.ListIterator;
import java.util.Comparator;


/**
 * Class that inherits methods from a normal double linked list, but sorts elements automatically
 * @author Ethan Price
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	Comparator<T> compare;
	
	/**
	 * Constructor for sorted double linked list
	 * @param comparator
	 */
	SortedDoubleLinkedList(Comparator<T> comp) {
		compare = comp;
	}
	
	/**
	 * Method to add a node with the given data in sorted order
	 * @param data
	 */
	public void add(T data) {
		Node toAdd = new Node(data);
		Node currentNode = head;

		if (size == 0) {
			super.addToEnd(data);
		}
		else if (size == 1) {
			if (compare.compare(data, currentNode.data) > 0) {
				super.addToEnd(data);
			}
			else {
				super.addToFront(data);
			}
		}
		else if (compare.compare(data, head.data) < 0) {
			super.addToFront(data);
		}
		else if (compare.compare(data, tail.data) > 0) {
			super.addToEnd(data);
		}
		else {
			int i = 0;
			while (compare.compare(data, currentNode.data) > 0 && i < size) {
				currentNode = currentNode.next;
				i++;
			}
				currentNode.previous.next = toAdd;
				toAdd.previous = currentNode.previous;
				currentNode.previous = toAdd;
				toAdd.next = currentNode;
				++size;
		}
	}
	
	public void addToEnd(T data) {
		throw new UnsupportedOperationException();
	}
	
	public void addToFront(T data) {
		throw new UnsupportedOperationException();
	}
	
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	
	public Node remove(T targetData, Comparator<T> comp) {
		return super.remove(targetData, comp);
	}
}
