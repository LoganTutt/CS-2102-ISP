import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import lejos.hardware.lcd.LCD;

/**
 * Handles all comunication between the robot and the computer.
 * 
 * @author Logan Tutt
 *
 */
public class Comunicator {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private final int Robot=0;
	private final int Object=1;
	
	/**
	 * Constructor for Comunicator. Establishes the comunication with the computer.
	 */
	public Comunicator(){
		try{
		socket = new Socket("10.0.1.11",1111);
		in=new DataInputStream(socket.getInputStream());
		out=new DataOutputStream(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a message with the given parameters
	 * 
	 * @param type	The type of message to be sent
	 * @param x		The X coordinate to be sent
	 * @param y		The Y coordinate to be sent
	 * @param heading	The heading to be sent
	 */
	private void sendMessage(int type, int x, int y, int heading){
		try{
		LCD.drawString(x+" "+y+" "+heading,0,3);
		out.writeInt(type);
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(heading);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends the robot pose to the computer
	 * 
	 * @param x	Robot X coord
	 * @param y	Robot Y coord
	 * @param heading	Robot heading
	 */
	public void sendPose(int x, int y, int heading){
		sendMessage(Robot, x, y, heading);
	}
	
	
	/**
	 * Sends the blockage location to the computer
	 * 
	 * @param x	Blockage X coord
	 * @param y	Blockage Y coord
	 */
	public void sendObjectPose(int x,int y){
		sendMessage(Object,x,y,-1);
	}
	
	/**
	 * reads the incoming data stream and stores incoming variables in array
	 * 
	 * @param coords	Array to store incoming values in
	 */
	public void readMessage(int[] coords){
		try{
			coords[0]=in.readInt();
			coords[1]=in.readInt();
			coords[2]=1;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
