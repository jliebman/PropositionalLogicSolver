package edu.iastate.cs472.proj2;
/**
 * 
 * @author Jake Liebman
 *
 */
public class Literal {
	public String val;
	public boolean negated;
	
	public Literal(String v)
	{
		val = v;
		negated = false;
	}
	
	public Literal(String v, boolean neg)
	{
		val = v;
		negated = neg;
	}
}
