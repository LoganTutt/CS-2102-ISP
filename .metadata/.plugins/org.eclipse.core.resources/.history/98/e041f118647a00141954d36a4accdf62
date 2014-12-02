
/**
 * Runs as a separate thread from the main GUI. This class will continuously check for 
 * incoming messages and forward the nessacary info to the given listener
 * 
 * @author Logan Tutt
 *
 */

public class ReadThread extends Thread {
	
	private ComputerComms coms;
	private MessageListener listener;
	
	
	/**
	 * Constructor for ReadThread
	 * 
	 * @param c	The ComputerComs that is currently being used for comunication
	 * @param l	The MessageListener that should be activated on incoming messages
	 */
	public ReadThread(ComputerComms c, MessageListener l){
		coms = c;
		listener=l;
	}
	
	
	/**
	 * Continuously checks the input stream for incoming data, and runs the MessageListener method
	 * if there is
	 */
	public void run(){
		while(true){
				int[] values={-1,-1,-1,-1,-1};
				coms.readInput(values);
				if(values[4]==1){
					switch(values[0]){
					case 0:
						listener.doRobotLocMessageAction(values[1],values[2],values[3]);
						break;
					case 1:
						listener.doObjectLocMessageAction(values[1], values[2]);
						break;
					}

				}
		}
	}
}
