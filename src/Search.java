// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  Mohammed Alotaibi
// ID: 435103700

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Search {

	// CONSTANTS:
	private static final int CLOSED_MAX_SIZE = 100000;
	private static final int FRINGE_MAX_SIZE = 100000;

	// ATTRIBUTES:
	private Node root; // the root node
	private Queue<Node> fringe; // the BFS frings: change it for the other searches.
	private PriorityQueue<Node> fringeA;
	private LinkedList<Node> goals; // the goal node
	private int numNodesExpanded; // number of nodes expanded

	// ALTHOUGH YOU ARE NOT REQUIRED TO, BUT IT IS
	// HIGHLY RECOMMENDED THAT YOU IMPLEMENT THE
	// CLOSED LIST MECHANISM (THE GRAPH SEARCH).
	// THE NEED FOR IT IS DUE TO THE VERY LARGE NUMBER OF
	// REDUNDANT STATES THAT YOU WILL GENERATE BEFORE
	// REACHING THE GOAL.
	// THE FOLLOWING IS A SIMPLE IMPLEMENTATION OF THE CLOSED
	// LIST, REPRESENTED AS A SIMPLE ARRAY OF NODES:

	private Node closed[]; // the closed list containing // visited nodes
	private int n_closed; // size of closed list

	// CONSTRUCTOR 1:
	// THIS CONSTRUCTOR WILL CREATE A SEARCH OBJECT.
	Search(State init_state) {
		root = new Node(init_state, null, -1, 0, 0); // make the root node
		fringe = new ArrayBlockingQueue<>(FRINGE_MAX_SIZE); // initialize Queue
		closed = null;
		n_closed = 0;
		goals = new LinkedList<>();
		int n = init_state.getN(), m = init_state.getM();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (init_state.getMap()[i][j] == 'T') {
					State onGoal = new State(init_state);
					onGoal.setX(i);
					onGoal.setY(j);
					goals.add(new Node(onGoal, null, -1, -1, -1)); // goal doesn't have actually path cost , depth or
																	// specific action
				}
			}
		}

		fringeA = new PriorityQueue<>(FRINGE_MAX_SIZE, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.h_A_star(getClosestGoal()) > o2.h_A_star(getClosestGoal()))
					return 1;
				else if (o1.h_A_star(getClosestGoal()) < o2.h_A_star(getClosestGoal()))
					return -1;
				else
					return 0;
			}
		});
		numNodesExpanded = 0;

		// ...

	}

	public Node getClosestGoal() {
		Node minPathCost = goals.removeFirst();
		goals.addLast(minPathCost);
		for (int i = 0; i < goals.size() - 1; i++) {
			if (!goals.isEmpty()) {
				Node tmp = goals.removeFirst();
				goals.addLast(tmp);
				if (tmp.getPath_cost() < minPathCost.getPath_cost())
					minPathCost = tmp;
			}
		}
		return minPathCost;
	}

	// THIS METHOD INITIALIZES THE CLOSED LIST
	private void initialize_closed() {
		if (closed == null)
			closed = new Node[CLOSED_MAX_SIZE];
		n_closed = 0;
	}

	// THIS METHOD TESTS WHETHER THE NODE WAS
	// VISITED OR NOT USING A SIMPLE FOR LOOP.
	// YOU CAN CHANGE IT.
	private boolean visited(Node n) {
		for (int i = 0; i < n_closed; i++) {
			if (closed[i].hasSameState(n))////////////////////////// this is must change #_#
				return true;
		}
		return false;
	}

	// THIS METHOD ADDS A NODE TO THE CLOSED LIST.
	// IT SIMPLY ADDS IT TO THE ND OF THE LIST. YOU
	// CAN CHANGE IT TO A MORE SOPHISTICATED METHOD.
	private void mark_as_visited(Node n) {
		// if the list is full do a left shift:
		if (n_closed == CLOSED_MAX_SIZE) {
			for (int i = 0; i < CLOSED_MAX_SIZE - 1; i++)
				closed[i] = closed[i + 1];
		} else {
			closed[n_closed] = n;
			n_closed++;
		}
	}

	// THIS METHOD WILL DO THE SEARCH AND CAN
	// RETURN THE GOAL NODE. YOU CAN EXTRACT
	// THE SOLUTION BY FOLLOWING THE PARENT NODES
	public Node BFS() {
		initialize_closed();
		numNodesExpanded = 0;
		Node nodesList[];
		Node current = root;

		fringe.offer(current);
		while (!fringe.isEmpty()) {
			current = fringe.poll();
			
			if (current.isGoal()) {
				return current;
			}
			nodesList = current.expand();
			numNodesExpanded++;
			if (!visited(current))
				mark_as_visited(current);
			for (int i = 0; i < 5; i++) { // we have 5 actions
				if (nodesList[i] != null && !visited(nodesList[i])) {
					mark_as_visited(nodesList[i]);
					fringe.offer(nodesList[i]);
				}
			}
		}
		return null; // goal not found

	}

	public Node A_star() {
		initialize_closed();
		numNodesExpanded = 0;
		Node nodesList[];
		Node current = root;

		fringeA.offer(current);
		while (!fringeA.isEmpty()) {
			current = fringeA.poll();

			if (current.isGoal()) {
				return current;
			}
			nodesList = current.expand();
			numNodesExpanded++;
			if (!visited(current))
				mark_as_visited(current);
			for (int i = 0; i < 5; i++) { // we have 5 actions
				if (nodesList[i] != null && !visited(nodesList[i])) {
					fringeA.offer(nodesList[i]);
				}
			}
		}
		return null; // goal not found
	}

	public State hill_climbing() {
		Node current = root;
		Node neighbor = highest_Valued_Successor(current);
		while (-neighbor.Obj_fun(getClosestGoal()) <= -current.Obj_fun(getClosestGoal())) {
			current = neighbor;
			neighbor = highest_Valued_Successor(current);
			if (current.Obj_fun(getClosestGoal()) == neighbor.Obj_fun(getClosestGoal()))
				current = random_move(neighbor);
		}
		return current.getState();
	}

	private Node highest_Valued_Successor(Node current) {
		numNodesExpanded = 0;
		Node[] nodes = current.expand();
		Node highest = current;
		numNodesExpanded++;
		for (int i = 1; i < nodes.length - 1; i++) {
			if (nodes[i] != null) {
				if (-nodes[i].Obj_fun(getClosestGoal()) <= -highest.Obj_fun(getClosestGoal())) {
					highest = nodes[i];
				}
			}
		}
		return highest;
	}

	private Node random_move(Node n) {
		Node[] nodes = n.expand();
		numNodesExpanded++;
		boolean status_action = false;
		int i = new Random().nextInt(nodes.length);
		while (!status_action && nodes[i] == null) {
			switch (i) {
			case 0:
				if (nodes[i] != null)
					status_action = nodes[i].getState().move_N();
				if (status_action)
					return nodes[i];
				break;
			case 1:
				if (nodes[i] != null)
					status_action = nodes[i].getState().move_S();
				if (status_action)
					return nodes[i];
				break;
			case 2:
				if (nodes[i] != null)
					status_action = nodes[i].getState().move_W();
				if (status_action)
					return nodes[i];
				break;
			case 3:
				if (nodes[i] != null)
					status_action = nodes[i].getState().move_E();
				if (status_action)
					return nodes[i];
				break;
			case 4:
				if (nodes[i] != null)
					status_action = nodes[i].getState().recharge();
				if (status_action)
					return nodes[i];
				break;
			}
			i = new Random().nextInt(nodes.length);
		}
		nodes[i].display();
		return nodes[i];
	}

	// GIVEN THE GOAL NODE, THIS METHOD WILL EXTRACT
	// THE SOLUTION, WHICH IS A SEQUENCE OF ACTIONS.
	public String[] extractSolution(Node goalNode) {

		// first find solution length;
		int len = 0;
		Node n = goalNode;
		while (n != null) {
			n = n.getParent();
			len++;
		}

		// declare an array of strings
		String sol[] = new String[len - 1];

		n = goalNode;
		for (int i = len - 2; i >= 0; i--) {
			switch (n.getAction()) {
			case 0:
				sol[i] = new String("move-N");
				break;
			case 1:
				sol[i] = new String("move-S");
				break;
			case 2:
				sol[i] = new String("move-W");
				break;
			case 3:
				sol[i] = new String("move-E");
				break;
			case 4:
				sol[i] = new String("recharge");
			}
			n = n.getParent();
		}
		return sol;
	}

	// THIS METHOD WILL DISPLAY THE SOLUTION
	public void displaySolution(Node goalNode) {
		String sol[] = extractSolution(goalNode);
		for (int i = 0; i < sol.length; i++)
			System.out.println(sol[i]);
	}

	// public Node getGoal() {
	// return goal;
	// }
	//
	// public void setGoal(Node goal) {
	// this.goal = goal;
	// }

	public int getNumNodesExpanded() {
		return numNodesExpanded;
	}
	public void writeActions(String file,Node goal) throws IOException {
		FileWriter f = new FileWriter(new File(file));
//		String str = extractSolution(goal).toString();
//		System.out.println(str);
		String str[] = extractSolution(goal);
		for(int i=0; i< str.length;i++)
			f.write(str[i]+"\n");
		
		f.close();
	}
}
