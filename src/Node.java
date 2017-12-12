// KING SAUD UNIVERSITY
// CCIS
// CSC 361

// NAME:  ...................
// ID: ...................

import java.io.*;
import java.util.*;

class Node {
	private State state; // the state
	private Node parent; // the parent node
	private int action; // the number of the action
	// that lead to this state
	private int path_cost; // the cost spent so far to reach this node
	private int depth; // the depth of the node in the tree

	// CONSTRUCTOR :
	// THIS CONSTRUCTOR WILL CREATE A NODE GIVEN A STATE
	Node(State st, Node pa, int a, int c, int d) {
		state = st;
		parent = pa;
		action = a;
		path_cost = c;
		depth = d;
	}

	// THIS METHOD RETURNS TRUE IS THE
	// NODE'S STATE IS THE SAME AS THE OTHER NODE'S STATE
	public boolean hasSameState(Node n) {
		return (state.equal(n.state));
	}

	// THIS METHOD WILL RETURN THE NEIGHBORING NODES
	// OF COURSE, YOU CAN & SHOULD CHANGE IT
	public Node[] expand() {
		Node next_nodes[] = new Node[5]; // there are 5 actions
		State next_states[] = state.successors();
		for (int i = 0; i < 5; i++) { // create nodes
			if (next_states[i] != null) {
				if (this.parent != null)
					next_nodes[i] = new Node(next_states[i], this, i, parent.path_cost+1, parent.depth + 1);// have parent
				else
					next_nodes[i] = new Node(next_states[i], this, i, path_cost+1, depth + 1);
			}
		}
		return next_nodes;
	}

	// GOAL TEST: THIS WILL TELL
	// WHETHER THE NODE'S STATE IS A GOAL.
	public boolean isGoal() {
		return state.foundTreasure();
	}
	public int Euclidean(Node goal) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < this.state.getN(); i++) {
			for (int j = 0; j < this.state.getM(); j++) {
				if (this.getState().getMap()[i][j] == 'T') {
					x = i;
					y = j;
				}
			}
		}
		return (int) Math.sqrt(Math.pow(x - goal.state.getX(), 2) + Math.pow(y - goal.state.getY(), 2));
	}
	// MANHATTAN DISTANCE HEURISTIC
	private int h_md(Node goal) {
		return Math.abs(this.getState().getX() - goal.getState().getX()) + Math.abs(this.getState().getY() - goal.getState().getY());
	}
	// A* HEURISRIC 
	public int h_A_star(Node goal){
		return path_cost + h_md(goal);
 	}
	public int key() {
		return getState().getX() * 13 + getState().getY() * 17 + getState().getBattery() * 19;
	}
	// DISPLAY THE NODE'S INFO
	public void display() {
		state.display();
	}

	public State getState() {
		return state;
	}

	public Node getParent() {
		return parent;
	}

	public int getAction() {
		return action;
	}

	public int getPath_cost() {
		return path_cost;
	}

	public int getDepth() {
		return depth;
	}

}
