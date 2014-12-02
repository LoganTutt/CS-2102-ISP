import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * Draws all of the graphics for the GUI
 * 
 * @author Logan Tutt
 *
 */
public class DrawGrid extends JComponent {
	
	private int gridXsize;
	private int gridYsize;
	
	private int robotXCoord;
	private int robotYCoord;
	private int robotHeading;
	private ArrayList<int[]> objects;
	
	
	private final int BASE_GRID_SPACING;
	private final int ROBOT_SCALE=10;
	private final int OBJECT_SCALE=20;
	private final int BASE_X_SIZE=500;
	private final int BASE_Y_SIZE=500;
	
	
	/**
	 * Constructor for DrawGrid. Sets starting params of the robot
	 * 
	 * @param gridXSize Number of cols in grid
	 * @param gridYSize Number of rows in grid
	 * @param startX	Starting X position of robot
	 * @param startY	Starting Y position of robot
	 * @param startHeading	Starting heading of robot
	 */
	public DrawGrid(int gridXSize, int gridYSize,int startX,int startY,int startHeading){
		gridXsize=gridXSize;
		gridYsize=gridYSize;
		if (BASE_X_SIZE/gridXsize<BASE_Y_SIZE/gridYsize){
			BASE_GRID_SPACING=BASE_X_SIZE/gridXsize;
		}else
			BASE_GRID_SPACING = BASE_Y_SIZE/gridYsize;
		robotXCoord=startX-1;
		robotYCoord=9-startY;
		robotHeading=startHeading;
		objects = new ArrayList<int[]>();
	}
	
	/**
	 * Draws the grid and all objects that are in the grid
	 * 
	 * @param g The graphics object that will be used to draw the grid
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Rectangle r = this.getBounds();
		int width = r.width;
		int height = r.height;
		
		
		int spacing;
		if (width/gridXsize<height/gridYsize){
			spacing=width/gridXsize;
		}else
			spacing = height/gridYsize;
		
		int xOffset =(width-spacing*(gridXsize-1))/2;
		int yOffset =(height-spacing*(gridYsize-1))/2;
		
		for (int i = 0; i < gridXsize; i++) {
			g2.drawLine(i * spacing + xOffset, yOffset, i * spacing + xOffset, height-yOffset);
		}
		for (int i = 0; i < gridYsize; i++) {
			g2.drawLine(xOffset, i * spacing + yOffset, width-xOffset, i * spacing+yOffset);
		}
		
		int objectScaled =OBJECT_SCALE*spacing/BASE_GRID_SPACING;
		g2.setColor(new Color(0,255,0));
		if(objects.size()>0){
			for(int[] i:objects){
				g2.fillOval((i[0]-1)*spacing+xOffset-objectScaled/2, (9-i[1])*spacing+yOffset-objectScaled/2, objectScaled, objectScaled);
			}
		}
		
		int robotXOrigin=xOffset+robotXCoord*spacing;
		int robotYOrigin=yOffset+robotYCoord*spacing;
		int robotScaled = ROBOT_SCALE*spacing/BASE_GRID_SPACING;
		GeneralPath robot = new GeneralPath();
		robot.moveTo(robotXOrigin+Math.sin(robotHeading*Math.PI/180)*robotScaled,robotYOrigin-Math.cos(robotHeading*Math.PI/180)*robotScaled);
		robot.lineTo(robotXOrigin+Math.sin(((robotHeading+120)%360)*Math.PI/180)*robotScaled, robotYOrigin-Math.cos(((robotHeading+120)%360)*Math.PI/180)*robotScaled);
		robot.lineTo(robotXOrigin+Math.sin(((robotHeading+180)%360)*Math.PI/180)*robotScaled/4, robotYOrigin-Math.cos(((robotHeading+180)%360)*Math.PI/180)*robotScaled/4);
		robot.lineTo(robotXOrigin+Math.sin(((robotHeading+240)%360)*Math.PI/180)*robotScaled, robotYOrigin-Math.cos(((robotHeading+240)%360)*Math.PI/180)*robotScaled);
		robot.closePath();
		g2.setColor(new Color(255,0,0));
		g2.fill(robot);
	}
	
	
	/**
	 * Sets the current pose of the robot
	 * 
	 * @param x	X coord of the robot
	 * @param y	Y coord of the robot
	 * @param heading Heading of the robot
	 */
	public void setRobotPose(int x,int y,int heading){
		robotXCoord=x-1;
		robotYCoord=9-y;
		robotHeading=heading;
	}
	
	/**
	 * Adds an object to the list of objects
	 * 
	 * @param x	X coord of new object
	 * @param y	Y coord of new object
	 */
	public void addObject(int x, int y){
		int[] coords = {x,y};
		objects.add(coords);
	}
	
	/**
	 * returns the list of all objects in the grid
	 * 
	 * @return	the list of all objects in the grid
	 */
	public ArrayList<int[]> getObjects(){
		return objects;
	}
}
