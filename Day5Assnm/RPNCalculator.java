package ssa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RPNCalculator {
	// Menu
	public static final String MENU = "Please enter a properly formatted RPN problem and then press enter " +
									  "OR press just enter to exit.\n" +
									  "Examples of properly formated problems:\n" +
									  "\t1 2 + 3 +\n" +
									  "\t10 - 12 * -4\n" +
									  "\t-3 7 - 2 ^\n";
	
	public static final String MENU_ENTER_PROBLEM = "Enter a problem: ";
	public static final String MENU_GOODBYE = "Thanks for stopping by!";
	
	public static final String MODE_COMMAND_LINE = "Command line argument detected - attempting to read file:\n";
	public static final String MODE_INTERACTIVE = "No command line argument - proceeding with interactive mode:\n";
	
	public static final String NO_ENTRY = "";
	
	public static final String ERR_FILE_NOT_FOUND = "Error - file not found! ";
	public static final String ERR_READ_FILE = "Error reading file: ";
	public static final String ERR_READ_KEYBOARD = "Error reading from the keyboard!";
	
	// Possible operators
	public static final String OP_PLUS = "+";
	public static final String OP_MINUS = "-";
	public static final String OP_TIMES = "*";
	public static final String OP_DIV = "/";
	public static final String OP_EXP = "^";
	public static final String OP_MOD = "%";

	// For file reading and keyboard inputs
	private static BufferedReader br = null;
	
	public static void main(String[] args) {
		// Operates in two modes:
		// 		1) Command line argument: supply the name of a file containing properly formatted 
		//         RPN problems, 1 per line
		//      2) Interactive: enter a properly formatted RPN problem to solve from the keyboard
		//
		//		A properly formatted RPN problem is of the form: <#> <#> <op> <#> <op> ...
		//      There MUST be a space between each number and operand!
		
		if(args.length == 1) {
			System.out.println(MODE_COMMAND_LINE);
			
			List<String> problems = fetchProblems(args[0]);
			
			for(String problem : problems) {
				solveProblem(problem);
			}
		} else {
			System.out.println(MODE_INTERACTIVE);
			
			String problem = "";
			
			do {
				System.out.println(MENU);
				System.out.print(MENU_ENTER_PROBLEM);
			
				problem = getProblemInput();
						
				// Make sure we have a problem to solve!
				if(!problem.equals(NO_ENTRY)) {
					solveProblem(problem);					
				}
							
			} while (!problem.equals(NO_ENTRY));
			
			System.out.println(MENU_GOODBYE);
		}		
	}
	
	// Display the original equation, the steps, and finally the answer for each problem
	public static void solveProblem(String problem) {
		System.out.println("Calculate: " + problem + "\n");
		int result = calculateAnswer(problem);
		System.out.println("Answer: " + result);
		System.out.println("====================");
	}
	
	// Solves any problem presented in the format "<#> <#> <op> <#> <op> ..."
	// Note the use of spaces in between numbers and operands
	public static int calculateAnswer(String problem) {
		
		String[] parsedProblem = parseProblem(problem);
		
		Stack<String> stack = new Stack<String>();
		
		for(int idx = parsedProblem.length - 1; idx >= 0; idx--) {
			stack.push(String.valueOf(parsedProblem[idx]));
		}
				
		return doStackCalculations(stack);
	}
	
	// Solves the problem by using the following algorithm
	// 		-While the size of the stack is more than 1
	//			-Pull off the top 3 items - first 2 will be #s and 3rd will be the operation
	//			-Do the calculation according to the operation
	//          -Push the result
	//      -Return the one value on the stack as the answer to the problem
	public static int doStackCalculations(Stack<String> stack) {													
		int result = 0;
						
		System.out.println("Steps:");
		
		// When one item is on the stack, it is the answer; otherwise, keep pulling values
		// off the stack
		while(stack.size() > 1) {
							
			// Assuming a properly formatted RPN expression: num num op
			int operand1 = Integer.parseInt(stack.pop());
			int operand2 = Integer.parseInt(stack.pop());
			String operation = stack.pop();

			// Calculate the result of the current iteration based on the operand 
			switch(operation) {
				case OP_PLUS:
					result = operand1 + operand2;
					break;
				case OP_MINUS:
					result = operand1 - operand2;
					break;
				case OP_TIMES:
					result = operand1 * operand2;
					break;
				case OP_DIV:
					result = operand1 / operand2;
					break;
				case OP_EXP:
					result = (int) Math.pow(operand1, operand2);
					break;
				case OP_MOD:
					result = operand1 % operand2;
					break;
			}
			
			System.out.println("\t" + operand1 + " " + operand2 + " " + operation + " => " +
			                   operand1 + " " + operation + " " + operand2 + " = " + result);
			
			// Result of the intermediate calculation
			stack.push(String.valueOf(result));
		}
		System.out.println();				
		
		// Answer
		return(Integer.parseInt(stack.pop()));
	}
	