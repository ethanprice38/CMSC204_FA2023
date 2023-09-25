
public class Notation {
	
	Notation() {
	}

	
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {
		MyStack<String> postfixResult = new MyStack<String>();
		char[] postfixChars = postfix.toCharArray();
		
		try {
			for (int i = 0; i < postfixChars.length; i++) {
				if (postfixChars[i] == ' ') {
					continue;
				}
				if (Character.isDigit(postfixChars[i])) {
					postfixResult.push(String.valueOf(postfixChars[i]));
				}
				else if (postfixResult.size() < 2) {
					throw new InvalidNotationFormatException();
				}
				else {
					String secondOperand = postfixResult.pop();
					String firstOperand = postfixResult.pop();
					
					switch (postfixChars[i]) {
					case '+':
						postfixResult.push(Double.toString(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand)));
						break;
					case '-':
						postfixResult.push(Double.toString(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand)));
						break;
					case '*':
						postfixResult.push(Double.toString(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand)));
						break;
					case '/':
						postfixResult.push(Double.toString(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand)));
						break;
					default: 
						System.out.println("Operator cannot be detected.");
						break;
					}
				}
			}
			
			if (postfixResult.size() > 1) {
				throw new InvalidNotationFormatException();
			}
			
			return Double.parseDouble(postfixResult.top());
		}
				
		catch(StackOverflowException e) {
			e.printStackTrace();
		}
		catch(StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static String convertPostfixToInfix(String postfix)throws InvalidNotationFormatException {
		char[] postfixChars = postfix.toCharArray();
		MyStack<String> infixResult = new MyStack<String>();
		try {
			for (int i = 0; i < postfixChars.length; i++) {
				if (postfixChars[i] == ' ') {
					continue;
				}
				else if (Character.isDigit(postfixChars[i])) {
					infixResult.push(String.valueOf(postfixChars[i]));
				}
				else if (postfixChars[i] == '+' || postfixChars[i] == '-' || postfixChars[i] == '*' || postfixChars[i] == '/') {
					if (infixResult.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String secondOperand = infixResult.pop();
					String firstOperand = infixResult.pop();
					String infix = "(" + firstOperand + String.valueOf(postfixChars[i]) + secondOperand + ")";
					infixResult.push(infix);
				}
				
			}
			if (infixResult.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch (StackOverflowException e) {
			e.printStackTrace();
		}
		catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		
		return infixResult.toString();
	}
	
	public static String convertInfixToPostfix(String infix)throws InvalidNotationFormatException {
		char[] infixChars = infix.toCharArray();
		MyQueue<String> postfixSolutionQueue = new MyQueue<String>();
		MyStack<String> infixStack = new MyStack<String>();
		
		try {
			for (int i = 0; i < infixChars.length; i++) {
				if (infixChars[i] == ' ') {
					continue;
				}
				else if (Character.isDigit(infixChars[i])) {
					postfixSolutionQueue.enqueue(String.valueOf(infixChars[i]));
				}
				else if (infixChars[i] == '(') {
					infixStack.push(String.valueOf(infixChars[i]));
				}
				else if (infixChars[i] == '+' || infixChars[i] == '-') {
					
					if (!infixStack.isEmpty()) {
						while (infixStack.top() == String.valueOf('+') || infixStack.top() == String.valueOf('-') ||
								infixStack.top() == String.valueOf('*') || infixStack.top() == String.valueOf('/')) {
							postfixSolutionQueue.enqueue(infixStack.pop());
						}
					}
					infixStack.push(String.valueOf(infixChars[i]));
				}
				else if (infixChars[i] == '*' || infixChars[i] == '/') {
					if (!infixStack.isEmpty()) {
						while (infixStack.top() == String.valueOf('*') || infixStack.top() == String.valueOf('/')) {
							postfixSolutionQueue.enqueue(infixStack.pop());
						}
					}
					infixStack.push(String.valueOf(infixChars[i]));
				}
				else if (infixChars[i] == ')') {
					while (!infixStack.isEmpty() && !infixStack.top().equals("(")) {
						postfixSolutionQueue.enqueue(infixStack.pop());
					}
					if (infixStack.isEmpty() || !infixStack.top().equals("(")) {
						throw new InvalidNotationFormatException();
					}
					if (!infixStack.isEmpty() && infixStack.top().equals("(")) {
						infixStack.pop();
					}
				}
				
			}
			
			for (int i = 0; i < infixStack.size(); i++) {
				postfixSolutionQueue.enqueue(infixStack.pop());
			}
			
			return postfixSolutionQueue.toString();
		}
		catch (StackOverflowException e) {
			e.printStackTrace();
		}
		catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		catch (QueueOverflowException e) {
			e.printStackTrace();
		}
		
		return infix;
	}
	
}
