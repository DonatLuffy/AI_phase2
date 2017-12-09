// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  Mohammed Alotaibi
// ID: 435103700

import java.io.*;
import java.util.*;

public class Agent {

	public static void main(String[] args) throws FileNotFoundException {

		//allstudents2.map.txt , simple2.map.txt
		State s1 = new State("simple2.map.txt");
//		Node ns = new Node(s1, null, 0, -1, 0);
//		s1.run("simple.map.command.txt", "finalMap.txt", "logsFile.txt");
		Search s = new Search(s1);
		Node n = s.doSearch();
		if(n != null)//this condition must be there, because may be the battrey is 
		s.displaySolution(n);
		
		PriorityQueue<Node> p = new PriorityQueue<>(3);
		Node n1 = new Node(s1, null, 0, -1, 0);
		p.offer(n1);
		Node n2 = new Node(new State(3,3,10), n1, 0, 0, 1);
		p.offer(n2);
		Node n3 = new Node(new State(3,3,11), n2, 0, 1, 2);
		p.offer(n3);
		p.poll().getState().display();
		int n_args = args.length;
		if (n_args!=5) {
			System.out.println("ERROR: ILLEGAL NUMBER OF ARGUMENTS:");
			System.out.println("Number of arguments must be 5.");
			return;
		}
		String mode = args[0];
		if (!mode.equals("s") && !mode.equals("c")) {
			System.out.println("ERROR: ILLEGAL MODE:");
			System.out.println("Mode must be 's' or 'c'.");
			return;
		}
		if (mode.equals("c")) {
			String MapFile = args[1];
			String CommandFile = args[2];
			String finalMapFile = args[3];
			String logFile = args[4];
			
			// ... phase 1 code here ...
		}
		else
		if (mode.equals("s")) {
			int na = Integer.valueOf(args[1]);
			String MapFile = args[2];
			String actionsFile = args[3];
			String finalMapFile = args[4];
			
			// ... phase 2 code here ...
			
		}

		// WRITE YOUR EXTRA CODE DOWN HERE:
		
		// ...
		
		
		
	}


	
}
