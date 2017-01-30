import java.util.HashMap;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.Set;

class WhetherThereIsRoute {
	// Given a directed graph, design an algorithm to find out whether 
	// there is a rout between two nodes.

	// @Zijiao: Can be done with either DFS or BFS, here I use DFS

	public static class DAGraph {
        // directed acyclic graph

		int vNum; // total amount of vertices
		int eNum; // total amout of edges
		HashMap<Integer, ArrayList<Integer>> adjList; // use adjacency list to store the nodes
        
        public DAGraph(int vNum, int eNum, HashMap<Integer, ArrayList<Integer>> adjList) {
        	// note that here we do NOT check whether the valicity of DAG or 
        	// correctness of vNum and eNum

        	this.vNum = vNum;
        	this.eNum = eNum;
        	this.adjList = adjList;
        }

        public DAGraph() {
        	this.vNum = 0;
        	this.eNum = 0;
        	this.adjList = new HashMap<Integer, ArrayList<Integer>>();
        }

        public DAGraph(HashMap<Integer, ArrayList<Integer>> adjList) {
        	// manually count eNum and vNum
        	// did not check whether there is circle in the graph

        	this.adjList = adjList;
        	this.eNum = adjList.size();
        	this.vNum = 0;

        	for (ArrayList<Integer> edges: adjList.values()) {
        		this.vNum += edges.size();
        	}
        }

        public void add(int vertex, ArrayList<Integer> edges) {
        	// add a vertext and the vertices it points to
        	
            this.adjList.put(vertex, edges);
            this.vNum += 1;
            this.eNum += edges.size();
            return;
        }

        public int getENum() {
        	return this.eNum;
        }

        public int getvNum() {
        	return this.vNum;
        }

        public HashMap<Integer, ArrayList<Integer>> getAdjacencyList() {
        	return this.adjList;
        }

        public String toString() {
            if (this.eNum == 0) {
            	return "";
            }

            StringBuffer string = new StringBuffer();
            for (int vertex: this.adjList.keySet()) {
            	string.append(vertex + " : ");
            	for (int edge: adjList.get(vertex)) {
            		string.append(edge + " ");
             	}
            	string.append('\n');
            }
            return string.toString();
        }
	}

	public static boolean connectedDFS(DAGraph graph, int vertex1, int vertex2) {
		// check whether 2 given vertices are connected using DFS
		// does not check the validity of the input graph
		// in other words, the graph itself must be "correct"

        Set<Integer> vertices = graph.adjList.keySet();

		if (!vertices.contains(vertex1) || !vertices.contains(vertex1)) {
			throw new IllegalArgumentException("The graph does not contain both vertices.");
		}

        HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>(vertices.size());
        for (int vertex: vertices) {
        	visited.put(vertex, false);
        }

        Stack<Integer> stack = new Stack<Integer>();
        visited.put(vertex1, true); 
        for (int vertex: graph.adjList.get(vertex1)) {
        	stack.push(vertex);
        }

        int currentVertex;
        while (!stack.empty()) {
            currentVertex = stack.pop();
            visited.put(currentVertex, true);
            for (int vertex: graph.adjList.get(currentVertex)) {
            	stack.push(vertex);
            }
        }

        return visited.get(vertex2);
        
	}


	public static void main(String[] args) {
		// test class Graph

		DAGraph graph = new DAGraph();
		for (int i = 0; i < 5; i ++) {
			ArrayList<Integer> tempList = new ArrayList<Integer>();
			for (int j = i + 1; j < 5; j ++) {
                tempList.add(j);
			}
            graph.add(i, tempList);
		}
		System.out.println(connectedDFS(graph, 0, 4));
	}
}








