import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{
	
	private int elementsAllocated = 0;
	private ArrayList<T> queue;
	private int elementsAllowed = 0;
	private int front = 0;
	private int back = 0;
	
	MyQueue() {
		elementsAllowed = 50;
		queue = new ArrayList<T>(elementsAllowed);
	}
	
	MyQueue(int num) {
		elementsAllowed = num;
		queue = new ArrayList<T>(num);
	}

	
	public boolean isEmpty() {
		if (elementsAllocated == 0) {
			return true;
		}
		
		return false;
	}
	
	public boolean isFull() {
		
		if (elementsAllowed == elementsAllocated) {
			return true;
		}
		
		return false;
	}

	
	public T dequeue() throws QueueUnderflowException {
		
		T dequeued;
		
		if (elementsAllocated == 0) {
			throw new QueueUnderflowException();
		}
		else {
			dequeued = queue.get(0);
			queue.remove(0);
			front++;
			elementsAllocated--;
		}
		
		return dequeued;
	}

	
	public int size() {
		return elementsAllocated;
	}

	
	public boolean enqueue(T e) throws QueueOverflowException {
		
		if (elementsAllowed == elementsAllocated) {
			throw new QueueOverflowException();
		}
		else {
			queue.add(back, e);
			back++;
			elementsAllocated++;
		}
		
		return true;
	}

	
	public String toString() {
		String queueElements = "";
		
		for (int i = 0; i < queue.size(); i++) {
			queueElements += queue.get(i);
		}
		
		
		return queueElements;
	}
	
	public String toString(String delimiter) {
		String queueElements = "";
		int i = 0;
		
		queueElements += queue.get(0);
		++i;
		for (; i < queue.size(); i++) {
			queueElements += delimiter + queue.get(i);
		}
		
		
		return queueElements;
	}
	
	public void fill(ArrayList<T> list) {
		
		ArrayList<T> copy = new ArrayList<T>(list);
		queue.addAll(copy);
		elementsAllocated = queue.size();
		
		
	}

}
