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
//		s1.setBattery(12);

		Hashtable<Integer, Integer> h  =new Hashtable<>(10);
//		s1.run("simple.map.command.txt", "finalMap.txt", "logsFile.txt");
		Search s = new Search(s1);
		Node n = s.A_star();
//		if(n != null)//this condition must be there, because may be the battrey is 
//		s.displaySolution(n);
		System.out.println(n.getPath_cost());
//		
		PriorityQueue<Node> p = new PriorityQueue<>(5, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.getPath_cost()  < o2.getPath_cost())
					return -1;
				else if(o1.getPath_cost() > o2.getPath_cost())
					return 1;
				else
					return 0;
			}
		});
		Node n1 = new Node(s1, null, 0, 0, 0);
//		p.offer(n1);
//		n1.getState().move_E();
//		p.offer(n1);
////		p.poll().display();
//		Node nn[] = n1.expand();
//		for(int i =0; i< nn.length;i++) {
//			if(nn[i] != null) {
//				p.offer(nn[i]);
//				System.out.println(nn[i].getPath_cost());				
//			}
//		}
//		System.out.println(p.poll().h_A_star(s.getClosestGoal()));

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

