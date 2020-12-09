package edu.iastate.cs472.proj2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
/**
 * 
 * @author Jake Liebman
 *
 */
public class PostFixConversion {
	static List<String> operators = new ArrayList<String>(Arrays.asList("~", "&&", "||", "=>", "<=>", "("));
	
	public static List<String> ConvertToPostFix(List<String> s)
	{
		Stack<String> stack = new Stack<String>();
		List<String> postFix = new ArrayList<String>();
		
		for(String str : s)
		{
			if(operators.contains(str) && !str.equals(")"))
			{
				if(!(stack.isEmpty() || InputRank(str) > StackRank(stack.peek()))) 
				{
					while(!stack.isEmpty() && (InputRank(str) < StackRank(stack.peek())))
					{
						postFix.add(stack.pop());
					}
				}
				stack.push(str);
			}
			else if(str.equals(")"))
			{
				while(!stack.peek().equals("("))	
				{
					postFix.add(stack.pop());
				}
				if(stack.empty())
				{
					return null;
				}
				stack.pop();
			}
			else {
				postFix.add(str);
			}
		}
		
		while(!stack.isEmpty())
		{
			if(stack.peek().equals("("))
			{
				return null;
			}
			postFix.add(stack.pop());
		}
		
		return postFix;
	}
	
	public static int StackRank(String s)
	{
		switch(s) {
		case "~": return 5;
		case "&&": return 4;
		case "||": return 3;
		case "=>": return 2;
		case "<=>": return 1;
		case "(": return -1;
		default: return -100;
		}
	}
	public static int InputRank(String s)
	{
		switch(s) {
		case "~": return 5;
		case "&&": return 4;
		case "||": return 3;
		case "=>": return 2;
		case "<=>": return 1;
		case "(": return 6;
		default: return -100;
		}
	}
}
