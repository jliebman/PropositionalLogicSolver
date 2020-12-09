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
public class ExpressionTreeConversion {
	static List<String> operators = new ArrayList<String>(Arrays.asList("~", "&&", "||", "=>", "<=>", "("));

	public static TreeNode BuildTree(List<String> expressions) 
	{
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		for(String s : expressions)
		{
			if(s.equals("~"))
			{
				stack.push(new TreeNode(s, stack.pop(), null));
			}
			else if(operators.contains(s))
			{
				TreeNode right = stack.pop();
				TreeNode left = stack.pop();
				stack.push(new TreeNode(s, left, right));
			}
			else 
			{
				stack.push(new TreeNode(s, null, null));
			}
		}
		return stack.pop();
	}
}
