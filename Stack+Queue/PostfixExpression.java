package cs2321;

public class PostfixExpression {
	
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */
	public static int evaluate(String exp) {
		LinkedListStack<Integer> s = new LinkedListStack();
		String[] input= exp.split(" ");
		int op1;       //The two temporary variables to do operation onto
		int op2;
		int value;    //The value after the operation has been done
		for( int i = 0; i < input.length; i ++) {
			if(input[i].equals("+")) {
				op1 = s.pop();
				op2 = s.pop();
				value = op2 + op1;
				s.push(value);
			}
			else if(input[i].equals("-")) {
				op1 = s.pop();
				op2 = s.pop();
				value = op2 - op1;
				s.push(value);
			}
			else if(input[i].equals("*")) {
				op1 = s.pop();
				op2 = s.pop();
				value = op2 * op1;
				s.push(value);
			}
			else if(input[i].equals("/")) {
				op1 = s.pop();
				op2 = s.pop();
				value = op2 / op1;
				s.push(value);
			}
			else{
				s.push(Integer.parseInt(input[i]));    //If input is not an operator, push into the stack
			}
			
		}
		return s.top();
	}
	
	
}
