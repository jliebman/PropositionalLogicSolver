package edu.iastate.cs472.proj2;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Jake Liebman
 *
 */
public class Clause {
public List<Literal> literals = new LinkedList<Literal>();

	public Clause(List<Literal> lits)
	{
		literals = lits;
	}
	
	public boolean hasNegations(Literal l)
	{
		for(Literal lit : this.literals) {
			if(lit.val.equalsIgnoreCase(l.val) && lit.negated != l.negated)
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		String ret = "";
		for(Literal l : this.literals)
		{
			ret += l.negated ? "~" + l.val + " || ": l.val + " || ";
		}
		if(ret.length() > 3)
		{
		return ret.substring(0, ret.length() - 3);
		}
		else
		{
			return ret;
		}
	}

}
