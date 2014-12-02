/**
 * Interface for actions that should be done when messages of different types are recieved
 * 
 * @author Logan Tutt
 *
 */
public interface MessageListener {
	
	/**
	 * Action that should be done when a robot location message is recieved
	 * 
	 * @param x	X coord of the robot
	 * @param y	Y coord of the robot
	 * @param heading	heading of the robot
	 */
	public void doRobotLocMessageAction(int x, int y, int heading);
	
	/**
	 * Action that should be done when a blockage location message is recieved
	 * 
	 * @param x	X coord of blockage
	 * @param y	Y coord of blockage
	 */
	public void doObjectLocMessageAction(int x, int y);
}
