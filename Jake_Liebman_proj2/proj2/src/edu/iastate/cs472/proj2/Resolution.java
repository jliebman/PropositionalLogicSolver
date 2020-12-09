package edu.iastate.cs472.proj2;
/**
 * 
 * @author Jake Liebman
 *
 */
public class Resolution {

	public static boolean Resolve(KnowledgeBase kb, Clause a, String output)
	{
		for(int i = 0; i < kb.atomic.size(); i++)
		{
			Clause c = kb.atomic.get(i);
			
			for(Literal lit : a.literals)
			{
				if(c.hasNegations(lit))
				{
					kb.atomic.remove(c);
					i=-1;
					
					output += a.toString();
					output += "\n" + c.toString();
					output += "\n" + "----------------------";
					
					Clause clone = new Clause(c.literals);
					a.literals.addAll(clone.literals);
					
					int indexLit = a.literals.indexOf(lit);
					a.literals.remove(indexLit);
					lit.negated = !lit.negated;
					a.literals.removeIf(l -> (l.val.equals(lit.val) && l.negated == lit.negated));
					
					output += "\n" + a.toString();
					output += "\n\n";

					
					if(a.literals.size() == 0)
					{
						output += "Empty Literal\n";
						System.out.println(output);
						return true;
					}
						
					break;
				}
			}
		}
		System.out.println(output);
		return false;
	}
}
