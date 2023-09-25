import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{

	private ArrayList<T> stack;
	private int elementsAllocated = 0;
	private int elementsAllowed = 0;
	
	MyStack() {
		elementsAllowed = 50;
		stack = new ArrayList<T>(50);
	}
	
	MyStack(int num) {
		elementsAllowed = num;
		stack = new ArrayList<T>(num);
	}

	
	public boolean isEmpty() {
		
		if (elementsAllocated == 0) {
			return true;
		}
		
		return false;
	}

	
	public boolean isFull() {
		
		if (elementsAllocated == elementsAllowed) {
			return true;
		}
		
		return false;
	}

	
	public T pop() throws StackUnderflowException {
		
		T popped;
		
		if (elementsAllocated == 0) {
			throw new StackUnderflowException();
		}
		else {
			popped = stack.get(elementsAllocated - 1);
			stack.remove(elementsAllocated - 1);
			elementsAllocated--;
		}
		
		
		return popped;
	}

	
	public T top() throws StackUnderflowException {
		if (elementsAllocated == 0) {
			throw new StackUnderflowException();
		}
		else {
		return stack.get(elementsAllocated - 1);
		}
	}

	
	public int size() {
		return elementsAllocated;
	}

	
	public boolean push(T e) throws StackOverflowException {
		
		if (elementsAllocated == elementsAllowed) {
			throw new StackOverflowException();
		}
		else {
			stack.add(e);
			elementsAllocated++;
		}
		
		return true;
	}

	
	public String toString() {
		String stackElements = "";
		
		for (int i = 0; i < stack.size(); i++) {
			stackElements += stack.get(i);
		}
		
		
		return stackElements;
	}
	
	public String toString(String delimiter) {
		String stackElements = "";
		int i = 0;
		
		stackElements += stack.get(0);
		i++;
		for (; i < stack.size(); i++) {
			stackElements += delimiter + stack.get(i);
		}
		
		return stackElements;
	}

	
	public void fill(ArrayList list) {
		
		ArrayList<T> copy = new ArrayList<T>(list);
		stack.addAll(copy);
		elementsAllocated = stack.size();
		
		
	}

}
