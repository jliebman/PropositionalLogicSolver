package edu.iastate.cs472.proj2;
/**
 * 
 * @author Jake Liebman
 *
 */
public class TreeNode {
	public String value;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(String v, TreeNode l, TreeNode r)
	{
		this.value = v;
		this.left = l;
		this.right = r;
	}
	  
	public void printTree()
	{
	        System.out.println(this.value);

	        if(this.left != null)
	            this.left.printHelper("", this.right == null);
	        if(this.right != null)
	            this.right.printHelper("", true);
	}

	public void printHelper(String prefix, boolean tail)
	{
	        System.out.println(prefix + (tail ? "'-- " : "|-- ") + this.value);

	        if(this.left != null)
	            this.left.printHelper(prefix + (tail ? "    " : "|   "), this.right == null);
	        if(this.right != null)
	            this.right.printHelper(prefix + (tail ?"    " : "|   "), true);
	}
}
