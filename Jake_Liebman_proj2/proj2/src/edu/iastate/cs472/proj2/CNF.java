package edu.iastate.cs472.proj2;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Jake Liebman
 *
 */
public class CNF {
	List<Clause> clauses = new LinkedList<Clause>();
	
	public CNF(List<Clause> c) {
		clauses = c;
	}

	public CNF() {
		clauses = new LinkedList<Clause>();
	}
}
