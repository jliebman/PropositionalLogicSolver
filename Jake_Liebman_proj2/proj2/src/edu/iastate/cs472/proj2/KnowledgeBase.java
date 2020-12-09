package edu.iastate.cs472.proj2;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Jake Liebman
 *
 */
public class KnowledgeBase {
	public List<Clause> atomic;
	public List<Clause> connectives;
	
	public KnowledgeBase(List<Clause> a, List<Clause> c) 
	{
		atomic = a;
		connectives = c;
	}

	public KnowledgeBase() 
	{
		atomic = new ArrayList<Clause>();
		connectives = new ArrayList<Clause>();
	}

}
