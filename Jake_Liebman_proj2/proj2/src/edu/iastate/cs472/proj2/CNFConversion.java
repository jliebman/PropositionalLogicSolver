package edu.iastate.cs472.proj2;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Jake Liebman
 *
 */
public class CNFConversion {

	public static CNF ConvertCNF(TreeNode node)
	{
		if(node == null)
		{
			return null;
		}
		
		CNF left = ConvertCNF(node.left);
		CNF right = ConvertCNF(node.right);
		if(left == null && right == null)
		{
			Literal l = new Literal(node.value);
			List<Literal> litList = new LinkedList<Literal>();
			litList.add(l);
			Clause c = new Clause(litList);
			CNF toRet = new CNF();
			toRet.clauses.add(c);
			return toRet;
		}
		
		switch(node.value) 
		{
		case "<=>": return IFF(left, right);
		case "=>": return Implies(left, right);
        case "&&": return And(left, right);	
        case "||": return Or(left, right);
        case "~": return Negate(left);
        case "(":
        case ")":
        default: return null;
		}
	}

	private static CNF IFF(CNF c1, CNF c2) 
	{
		CNF left = Implies(c1, c2);
		CNF right = Implies(c2, c1);
		
		return And(left, right);
	}

	private static CNF Implies(CNF c1, CNF c2) 
	{
		CNF left = Negate(c1);
		CNF right = new CNF(c2.clauses);
		
		return Or(left, right);
	}

	private static CNF And(CNF c1, CNF c2) 
	{
		CNF toAnd = new CNF();
		toAnd.clauses.addAll(c1.clauses);
		toAnd.clauses.addAll(c2.clauses);
		
		return toAnd;
	}
	
	private static CNF Or(CNF c1, CNF c2) 
	{
		CNF toOr = new CNF();
		for(Clause cl1 : c1.clauses)
		{
			for(Clause cl2 : c2.clauses)
			{
				Clause newClause = new Clause(cl1.literals);
				newClause.literals.addAll(cl2.literals);
				
				toOr.clauses.add(newClause);
			}
		}
		return toOr;
	}
	
	private static CNF Negate(CNF toNegate) 
	{
		List<Clause> negClauses = toNegate.clauses;
		CNF negated = new CNF();
		for(Literal lit : negClauses.get(0).literals)
		{
			Literal newLit = new Literal(lit.val, !lit.negated);
			List<Literal> newLitList = new LinkedList<Literal>();
			newLitList.add(newLit);
			Clause newClause = new Clause(newLitList);
			negated.clauses.add(newClause);
		}
		
		for(Clause clause : negClauses.subList(1, negClauses.size()))
		{
			CNF toOr = new CNF();
			for(Literal lit : clause.literals)
			{
				Literal newLit = new Literal(lit.val, !lit.negated);
				List<Literal> newLitList = new LinkedList<Literal>();
				newLitList.add(newLit);
				Clause newClause = new Clause(newLitList);
				toOr.clauses.add(newClause);
			}
			negated = Or(toOr, negated);
		}
		return negated;
	}
}
