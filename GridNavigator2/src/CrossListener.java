/**
 * Interface for listeners that will be activated as the robot moves around the grid
 * 
 * 
 * @author Logan Tutt
 *
 */
public interface CrossListener {
	
	/**
	 * Action that should be done when teh robot reaches a cross 
	 */
	public void atCrossAction();
	
	/**
	 * Action that should be done when the robot sees a blockage
	 * 
	 * @param x	X coord of blockage
	 * @param y	Y coord of blockage
	 */
	public void objectSeenAction(int x, int y);
	
	
}
