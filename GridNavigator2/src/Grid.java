import java.util.ArrayList;


public class Grid {

	private ArrayList<Node> grid;
	private int xSize;
	private int ySize;
	
	/**
	 * Constructor for grid
	 * 
	 * @param x	number of columns in grid
	 * @param y	number of rows in grid
	 */
	public Grid(int x, int y) {
		xSize = x;
		ySize = y;
		grid = new ArrayList<Node>();
		for (int i = 1; i <= ySize; i++) {
			for (int j = 1; j <= xSize; j++) {
				grid.add(new Node(j, i));
			}
		}
		for (Node n : grid) {
			for (Node m : grid) {
				if ((Math.abs(m.getX() - n.getX()) == 1 || Math.abs(m.getX()
						- n.getX()) == 0)
						&& (Math.abs(m.getY() - n.getY()) == 1 || Math.abs(m
								.getY() - n.getY()) == 0) && n != m && (Math.abs(m.getX() - n.getX())!=(Math.abs(m.getY() - n.getY())))) {
					n.addNeighbor(m);
				}
			}
		}
//		addBlockage(2, 2);
//		addBlockage(3, 2);
//		addBlockage(3, 7);
	}
	
	/**
	 * Generates the shortest path from the start point to the end point while avoiding obstacles
	 *  
	 * @param destX	X coord of destination
	 * @param destY	Y coord of destination
	 * @param startX	X coord of destination
	 * @param startY	Y coord of destination
	 * @return	returns and ArrayList of the nodes to get to the destination in order. position 0 is the starting node and 
	 * nodes in the list are in order with the destination at the end
	 */
	public ArrayList<Node> getPath(int destX, int destY, int startX, int startY) {
		ArrayList<Node> path = new ArrayList<Node>();
		Node startNode = null;
		Node endNode = null;
		for (Node n : grid) {
			if (n.getX() == startX && n.getY() == startY) {
				startNode = n;
			}
			if (n.getX() == destX && n.getY() == destY) {
				endNode = n;
			}
			n.setDist(Integer.MAX_VALUE);
			n.setPrevious(null);
			n.setVisited(false);
		}
		if(endNode.isBlocked()){
			return path;
		}
		startNode.setDist(0);
		startNode.setVisited(true);
		for (Node n : startNode.getNeighbors()) {
			n.setDist(1);
			n.setPrevious(startNode);
		}
		while (true) {
			Node nodeToCheck = new Node(-1, -1);
			nodeToCheck.setDist(Integer.MAX_VALUE);
			for (Node n : grid) {
				if (!n.isBlocked() && !n.wasVisited()
						&& n.getDist() < nodeToCheck.getDist()) {
					nodeToCheck = n;
				}
			}
			if(nodeToCheck.getDist()==Integer.MAX_VALUE){
				return path;
			}
			if(nodeToCheck==endNode){
				break;
			}
			for(Node n:nodeToCheck.getNeighbors()){
				if(n.getDist()>nodeToCheck.getDist()+1){
					n.setDist(nodeToCheck.getDist()+1);
					n.setPrevious(nodeToCheck);
				}
			}
			nodeToCheck.setVisited(true);
		}
		path.add(endNode);
		while(path.get(0).getPrevious()!=null){
			path.add(0,path.get(0).getPrevious());
		}
		return path;
	}
	
	/**
	 * Sets a node to blocked
	 * 
	 * @param x	X coord of node that is blocked
	 * @param y	Y coord of node that is blocked
	 */
	public void addBlockage(int x, int y){
		for(Node n: grid){
			if(n.getX()==x&&n.getY()==y){
				n.setBlocked(true);
				return;
			}
		}
	}
}
