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
//		State s1 = new State("allstudents2.map.txt");
//		Search s = new Search(s1);
//		System.out.println(s.getNumNodesExpanded());
////		Node ns = new Node(s1, null, 0, 0, 0);
////		ns.getState().move_S();
////		System.out.println(ns.Obj_fun(s.getClosestGoal()));
////		 s1.setBattery(12);
//		// s1.run("simple.map.command.txt", "finalMap.txt", "logsFile.txt");
//		long p = System.currentTimeMillis();
//		Node n1 = s.BFS();
////		Node n1 = s.A_star();
//		long q = System.currentTimeMillis();
////		System.out.println(q-p);
////		 State ss =s.hill_climbing();
////		 ss.display();
////		if (n1 != null) // this condition must be there, because may be the battrey is
////			s.displaySolution(n1);
////		System.out.println("path cost: "+ n1.getPath_cost());
////		System.out.println("depth of tree: " + n1.getDepth());
//		System.out.println("# of nodes expaneded " + s.getNumNodesExpanded());
		Scanner in = new Scanner(System.in);
//		String str[] = new String[5];
//		str[0] = in.next();
//		str[1] = in.next();
//		str[2] = in.next();
//		str[3] = in.next();
//		str[4] = in.next();

		int na = in.nextInt();
//		String MapFile = str[2];
//		String actionsFile = str[3];
//		String finalMapFile = str[4];
		
		// ... phase 2 code here ...
		State state = new State("simple2.map.txt");
		Search search = new Search(state);
		Node n = null;
		long p,q;
		switch(na) {
		case 1:
			 p = System.currentTimeMillis();
			n = search.BFS();
			 q = System.currentTimeMillis();
			System.out.println(q-p);
			System.out.println(search.getNumNodesExpanded());
			System.out.println(n.getPath_cost());
			break;
		case 2:
			 p = System.currentTimeMillis();
			n = search.A_star();
			 q = System.currentTimeMillis();
			 System.out.println(q-p);
				System.out.println(search.getNumNodesExpanded());
				System.out.println(n.getPath_cost());
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
