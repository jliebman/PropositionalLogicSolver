package edu.iastate.cs472.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Jake Liebman
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathName = "C:\\Users\\Jake Liebman\\COMS362\\proj2\\src\\edu\\iastate\\cs472\\proj2\\sample.txt";
		KnowledgeBase kb = ReadInFile(pathName);
		
		String output = "";
		System.out.println("\nknowledge base in clauses:\n");
		for(Clause c : kb.atomic)
		{
			System.out.println(c.toString() + "\n");
		}
		
		for(int i = 0; i < kb.connectives.size(); i++)
		{
			
			System.out.println("****************");
			System.out.println("Goal Sentence " + (i+1) + "\n");
			System.out.println(kb.connectives.get(i).toString());
			System.out.println("****************\n");
			
			System.out.println("Negated goal in clauses: \n");
			Clause goalClause = new Clause(kb.connectives.get(i).literals);
			Clause negGoalClause = kb.connectives.get(i);
			negGoalClause.literals.get(0).negated = !negGoalClause.literals.get(0).negated;
			System.out.println(negGoalClause.toString() + "\n");
			
			System.out.println("Proof by refutation: ");
			if(Resolution.Resolve(kb, negGoalClause, output))
			{
				System.out.println("The KB Entails " + goalClause.toString());
			}
			else
			{
				System.out.println("No new clauses are added.\n" + "The KB Does Not Entail " + kb.connectives.get(i).toString());
			}
			System.out.println(output);
		}
		
	}
public static KnowledgeBase ReadInFile(String path) {
	
	KnowledgeBase kb = new KnowledgeBase();
	File f = new File(path);
	try {
		Scanner scan = new Scanner(f);
		while(scan.hasNextLine())
		{
			String scanLine = scan.nextLine().trim();
			if(scanLine.equalsIgnoreCase("Knowledge Base:"))
			{
				break;
			}
		}
		scan.nextLine();
		
		String retString = "";
		while(scan.hasNextLine())
		{
			
			String scanLine = scan.nextLine().trim();
			if(scanLine.equalsIgnoreCase("Prove the following sentences by refutation:"))
			{
				break;
			}
			else if(scanLine.length() >= 1)
			{
				retString += scanLine;
			}
			else
			{
				CNF ret = inputToCnf(retString);
				kb.atomic.addAll(ret.clauses);
				retString = "";
			}
		}
		scan.nextLine();
		
		while(scan.hasNextLine())
		{
			String scanLine = scan.nextLine().trim();
			if(scanLine.length() >= 1)
			{
				retString += scanLine;
			}
			else 
			{
				CNF ret = inputToCnf(retString);
				kb.connectives.addAll(ret.clauses);
				retString = "";
			}
		}
		scan.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return kb;
}

public static CNF inputToCnf(String input)
{
	CNF endCNF = new CNF();
    String pattern = "((~)|(&&)|(<=>)|(=>)|(\\|\\|)|(\\))|\\()|(\\w+)";
    Matcher match = Pattern.compile(pattern).matcher(input);

    List<String> matches = new ArrayList<String>();
    while (match.find()) 
    {
       matches.add(match.group());
    }
    List<String> postFix = PostFixConversion.ConvertToPostFix(matches);
    System.out.println(postFix.toString());
    TreeNode tree = ExpressionTreeConversion.BuildTree(postFix);
    tree.printTree();
    //NEED TO FIX HOW "(" is getting handled
    //FIXED I think
    endCNF = CNFConversion.ConvertCNF(tree);
	return endCNF;
}

}
