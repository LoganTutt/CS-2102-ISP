
/**
 * This class runs the program on the EV3
 * 
 * @author Logan Tutt
 *
 */
public class GridNavigator implements CrossListener {

	private Comunicator coms;
	private Navigator n;
	
	public static void main(String[] args) {
		GridNavigator g=new GridNavigator();
		g.run();
	}
	
	
	/**
	 * sends the current pose of the robot to the controller
	 */
	public void atCrossAction(){
		coms.sendPose(n.getXCoord(), n.getYCoord(), n.getHeading());
	}
	
	/**
	 * sends the location of the blockage
	 */
	public void objectSeenAction(int x,int y){
		coms.sendObjectPose(x, y);
	}
	
	
	/**
	 * sets up all the robot functions and runs the program
	 */
	public void run(){
		coms = new Comunicator();
		n = new Navigator(1, 1, 0);
		n.addListener(this);
		while (true) {
			int[] coords = { -1, -1,-1 };
			while (coords[2]!=1) {
				coms.readMessage(coords);
			}
			n.moveTo(coords[0], coords[1]);
		}
	}
	
	
}
