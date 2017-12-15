// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  Mohammed Alotaibi
// ID: 435103700

import java.io.*;
import java.util.*;

public class Agent {

	public static void main(String[] args) throws IOException {
		// allstudents2.map.txt , simple2.map.txt
		
		int n_args = args.length;
		if (n_args != 5) {
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

			// ... phase 1 code here ..
			State inital = new State(MapFile);
			inital.run(CommandFile, finalMapFile, logFile);
			
		} else if (mode.equals("s")) {
			int na = Integer.valueOf(args[1]);
			String MapFile = args[2];
			String actionsFile = args[3];
			String finalMapFile = args[4];
			
			// ... phase 2 code here ...
			State state = new State(MapFile);
			Search search = new Search(state);
			Node n = null;
			long p,q;
			switch(na) {
			case 1:
				 p = System.currentTimeMillis();
				n = search.BFS();
				 q = System.currentTimeMillis();
				search.displaySolution(n);
				System.out.println("The time running: " + (q-p) + " milliseconds");
				System.out.println("The number of nodes expanded: " + search.getNumNodesExpanded());
				System.out.println("The path cost: " +n.getPath_cost());
				break;
			case 2:
				 p = System.currentTimeMillis();
				n = search.A_star();
				 q = System.currentTimeMillis();
				search.displaySolution(n);
				System.out.println("The time running: " + (q-p) + " milliseconds");
				System.out.println("The number of nodes expanded: " + search.getNumNodesExpanded());
				System.out.println("The path cost: " +n.getPath_cost());
				break;
			case 3:
				State hc = search.hill_climbing();
				hc.display();
				break;
			}
			search.writeActions(actionsFile, n);
		n.getState().writeFinalMap(finalMapFile);
		}

		// WRITE YOUR EXTRA CODE DOWN HERE:

		// ...

	}

}
