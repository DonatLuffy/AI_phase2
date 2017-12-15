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

		Scanner in = new Scanner(System.in);

		int na = in.nextInt();
		// ... phase 2 code here ...
		State state = new State("simple2.map.txt");
		Search search = new Search(state);
		
		long p,q;
		switch(na) {
		case 1:
			 p = System.currentTimeMillis();
			Node bfs = search.BFS();
			 q = System.currentTimeMillis();
			 search.displaySolution(bfs);
			System.out.println("The time running: " + (q-p) + " milliseconds");
			System.out.println("The number of nodes expanded: " + search.getNumNodesExpanded());
			System.out.println("The path cost: " +bfs.getPath_cost());
			break;
		case 2:
			 p = System.currentTimeMillis();
			Node astar = search.A_star();
			 q = System.currentTimeMillis();
			 search.displaySolution(astar);
			 System.out.println("The time running: " + (q-p) + " milliseconds");
				System.out.println("The number of nodes expanded: " + search.getNumNodesExpanded());
				System.out.println("The path cost: " +astar.getPath_cost());
			break;
		case 3:
			State hc = search.hill_climbing();
			hc.display();
			break;
		}
//		search.writeActions("simple.map.command.txt",n);
//		n.getState().writeFinalMap(finalMap);
		
//		int n_args = str.length;
//		if (n_args != 5) {
//			System.out.println("ERROR: ILLEGAL NUMBER OF ARGUMENTS:");
//			System.out.println("Number of arguments must be 5.");
//			return;
//		}
//		String mode = str[0];
//		if (!mode.equals("s") && !mode.equals("c")) {
//			System.out.println("ERROR: ILLEGAL MODE:");
//			System.out.println("Mode must be 's' or 'c'.");
//			return;
//		}
//		if (mode.equals("c")) {
//			String MapFile = str[1];
//			String CommandFile = str[2];
//			String finalMapFile = str[3];
//			String logFile = str[4];
//
//			// ... phase 1 code here ..
//			State inital = new State(MapFile);
//			inital.run(CommandFile, finalMapFile, logFile);
//			
//		} else if (mode.equals("s")) {
//			int na = Integer.valueOf(str[1]);
//			String MapFile = str[2];
//			String actionsFile = str[3];
//			String finalMapFile = str[4];
//			
//			// ... phase 2 code here ...
//			State state = new State(MapFile);
//			Search search = new Search(state);
//			Node n = null;
//			switch(na) {
//			case 1:
//				n = search.BFS();
//				n.display();
//				break;
//			case 2:
//				n = search.A_star();
//				n.display();
//				break;
//			case 3:
//				State hc = search.hill_climbing();
//				hc.display();
//				break;
//			}
//			search.writeActions(actionsFile, n);
//			state.writeFinalMap(finalMapFile);
//		}

		// WRITE YOUR EXTRA CODE DOWN HERE:

		// ...

	}

}
