import java.util.ArrayList;


public class Node {
	
	private int x;
	private int y;
	private ArrayList<Node> neighbors;
	private boolean isBlocked;
	private boolean visited;
	private int dist;
	private Node previous;
	
	
	/**
	 * Constructor for node
	 * 
	 * @param xCoord	X coord of this node
	 * @param yCoord	Y coord of this node
	 */
	public Node(int xCoord, int yCoord){
		x=xCoord;
		y=yCoord;
		neighbors = new ArrayList<Node>();
		isBlocked=false;
		dist = 0;
	}
	
	/**
	 * returns coordinates of node, used for debugging purposes
	 * 
	 * @return returns the x and y coordinates of this node in a string in the form of "x y"
	 */
	public String toString(){
		return x+" "+y;
	}
	
	/**
	 * returns the x coordinate of the node
	 * @return	X coordinate of node
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * returns the y coordinate of the node
	 * @return	Y coordinate of node
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Adds another neighbor to the list of neighbors to this node
	 * 
	 * @param neighbor	neighboring node to  be added
	 */
	public void addNeighbor(Node neighbor){
		neighbors.add(neighbor);
	}
	
	/**
	 * Returns the list of neighbor nodes
	 * 
	 * @return	ArrayList filled with nodes neighboring this one
	 */
	public ArrayList<Node> getNeighbors(){
		return neighbors;
	}
	
	/**
	 * Sets whether or not this node is currently blocked
	 * 
	 * @param b	true if node should be set to blocked, false if node should be set to unblocked
	 */
	public void setBlocked(boolean b){
		isBlocked=b;
	}
	
	/**
	 * returns whether the node is blocked or not
	 * 
	 * @return	true if node is blocked, false if not
	 */
	public boolean isBlocked(){
		return isBlocked;
	}
	
	/**
	 * sets the distance between this node and the starting node
	 * 
	 * @param d	distance between starting node and this node
	 */
	public void setDist(int d){
		dist=d;
	}
	
	/**
	 * gets the current distance from this node to the starting node
	 * 
	 * @return	distance from this node to starting node
	 */
	public int getDist(){
		return dist;
	}
	
	/**
	 * sets the previous node in the path to get to this node
	 * 
	 * @param n	previous node to this one in path
	 */
	public void setPrevious(Node n){
		previous=n;
	}
	
	/**
	 * returns the previous node in the path to this one
	 * 
	 * @return	previous node to this one in path
	 */
	public Node getPrevious(){
		return previous;
	}
	
	/**
	 * sets whether this node has been visited by the pathfinding algorithm yet
	 * 
	 * @param b true if it has been visited
	 */
	public void setVisited(boolean b){
		visited=b;
	}
	
	/**
	 * returns whether the pathfinding algorithm has already visited this node
	 * 
	 * @return	true of node has been visited
	 */
	public boolean wasVisited(){
		return visited;
	}

}
