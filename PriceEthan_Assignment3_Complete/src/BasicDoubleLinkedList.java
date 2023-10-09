import java.util.ArrayList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Ethan Price
 *
 * Generic double linked list class
 */

public class BasicDoubleLinkedList<T> {
	
	Node head;
	Node tail;
	int size = 0;
	
	/**
	 * Base constructor for the linked list
	 */
	BasicDoubleLinkedList() {
		head = null;
		tail = null;
	}
	
	/**
	 * Method to add data to end of linked list
	 * @param data to add to end
	 */
	public void addToEnd(T data) {
		
		Node toAdd = new Node(data);
		
		if (size == 0) {
			head = toAdd;
		}
		else {
			tail.next = toAdd;
			toAdd.previous = tail;
		}
		tail = toAdd;
		++size;
		
	}
	
	/**
	 * Method to add data to front of linked list
	 * @param data to add
	 */
	public void addToFront(T data) {
		Node toAdd = new Node(data);
		
		if (size == 0) {
			tail = toAdd;
		}
		else {
			head.previous = toAdd;
			toAdd.next = head;
		}
		head = toAdd;
		++size;
		
	}
	
	/**
	 * Method to get the data of the first element in the linked list
	 * @return data
	 */
	public T getFirst() {
		return head.data;
	}
	
	/**
	 * Method to get the data of the last element in the linked list
	 * @return data
	 */
	public T getLast() {
		return tail.data;
	}
	
	/**
	 * Method to get the current amount of elements in the linked list
	 * @return size of list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Method that creates a linked list iterator
	 * @return iterator created 
	 */
	public ListIterator<T> iterator() {
		ListIterator<T> iterator = new DoubleLinkedListIterator();
		return iterator;
	}

	/**
	 * Method to remove a given piece of data from the linked list
	 * @param targetData to be removed
	 * @param comparator to compare elements of the linked list
	 * @return the node removed
	 */
	public Node remove (T targetData, Comparator<T>comparator) {
		Node currentNode = head;
		Node removedNode = null;
		
		for (int i = 0; i < size; i++) {
			
			if (comparator.compare(targetData, currentNode.data) == 0) {
				
				if (size == 1) {
					removedNode = currentNode;
					head = tail = null;
					--size;
					break;
				}
				else if (targetData == head.data) {
					removedNode = head;
					head.next.previous = null;
					head = head.next;
					--size;
					break;
				}
				else if (targetData == tail.data) {
					removedNode = tail;
					tail.previous.next = null;
					tail = tail.previous;
					--size;
					break;
				}
				else {
					removedNode = currentNode;
					currentNode.previous.next = currentNode.next;
					if (currentNode.next != null) {
						currentNode.next.previous = currentNode.previous;
					}
					--size;
					break;
				}
			}
			currentNode = currentNode.next;
		}
		
		
		return removedNode;
	}
	
	/**
	 * Method to retrieve and remove the first element of the linked list
	 * @return the element removed
	 */
	public T retrieveFirstElement() {
		if (size == 0) {
			return null;
		}
		else if (size == 1) {
			T data = head.data;
			head = tail = null;
			--size;
			return data;
		}
		else {
			T data = head.data;
			head.next.previous = null;
			head = head.next;
			--size;
			return data;
		}
	}
	
	/**
	 * Method to retrieve and remove the last element of the linked list
	 * @return element removed
	 */
	public T retrieveLastElement() {
		if (size == 0) {
			return null;
		}
		else if (size == 1) {
			T data = tail.data;
			head = tail = null;
			--size;
			return data;
		}
		else {
			T data = tail.data;
			tail.previous.next = null;
			tail = tail.previous;
			--size;
			return data;
		}
	}
	
	/**
	 * Method to add the data in the linked list to an ArrayList
	 * @return ArrayList created
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> data = new ArrayList<T>();
		Node currentNode = head;
		
		if (size == 1) {
			data.add(head.data);
		}
		else if (size == 0) {
			return data;
		}
		else {
			for (int i = 0; i < size; i++) {
				data.add(currentNode.data);
				currentNode = currentNode.next;
			}
			return data;
		}
		
		return data;
	}
	
	/**
	 * Class that describes a Node
	 * @author Ethan Price
	 *
	 */
	protected class Node {
		/**
		 * Node contains pointers to the previous and next node, as well as the data stored
		 */
		public Node previous;
		public Node next;
		public T data;
		
		/**
		 * Default constructor for a node, sets all to null
		 */
		Node() {
			previous = null;
			next = null;
			data = null;
		}
		
		/**
		 * Constructs a node with a given piece of data
		 * @param data to be added
		 */
		Node(T e) {
			data = e;
		}
		
	}
	
	/**
	 * Class that streamlines iterating through a doubly linked list
	 * @author Ethan Price
	 *
	 */
	protected class DoubleLinkedListIterator implements ListIterator<T>{

		private Node previousNode;
		private Node currentNode;
		
		/**
		 * Default constructor for an iterator
		 */
		DoubleLinkedListIterator() {
			previousNode = null;
			currentNode = head;
		}
			
		/**
		 * Method to determine if there is a node after the current node
		 * @return true if yes, false if no
		 */
		public boolean hasNext() {
			return currentNode != null;
		}


		/**
		 * Method to iterate through to next node
		 * @return data of node iterated through
		 */
		public T next() {
			T data;
			
			if (hasNext()) {
				data = currentNode.data;
				previousNode = currentNode;
				currentNode = currentNode.next;
				
				return data;
			}
			else
				throw new NoSuchElementException();
			
		}

		
		/**
		 * Method to determine if the current node has a node before it
		 * @return true if yes, false if no
		 */
		public boolean hasPrevious() {
			
			return previousNode != null;
		}

		/**
		 * Method to iterate through the previous node
		 * @return data of node iterated through
		 */
		public T previous() {
			T data;
			
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			else {
				currentNode = previousNode;
				previousNode = previousNode.previous;
				data = currentNode.data;
			}
			
			return data;
		}

		
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		
		public int previousIndex()throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		
		public void set(T e)throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		
		public void add(T e)throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
	}
	
}
