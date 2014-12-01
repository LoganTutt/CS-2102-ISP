
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;


/**
 * This sensor contains 2 light sensors and will return values based
 * on where the line is under them
 * 
 * @author Logan Tutt
 *
 */
public class LineTrackSensor {
	
	
	private SampleProvider lSensorValue;
	private SampleProvider rSensorValue;
	private EV3ColorSensor lSensor;
	private EV3ColorSensor rSensor;
	private float leftNormal;
	private float rightNormal;
	private float leftMaxValue=Float.MIN_VALUE;
	private float leftMinValue=Float.MAX_VALUE;
	private float rightMaxValue=Float.MIN_VALUE;
	private float rightMinValue=Float.MAX_VALUE;
	
	
	/**
	 * Constructor for  LineTrackSensor. creates the line sensor objects that
	 * will be used for tracking
	 * 
	 * @param	lPort	 the port that the left sensor is on
	 * @param 	rPort	 the port that the right sensor is on 
	 */
	public LineTrackSensor(Port lPort, Port rPort){
		lSensor = new EV3ColorSensor(lPort);
		rSensor = new EV3ColorSensor(rPort);
		lSensorValue = lSensor.getRedMode();
		rSensorValue = rSensor.getRedMode();
	}
	
	
	/**
	 * Takes values form each sensor and checks if they are higher
	 * or lower than the current max/min values respectivly.
	 * The method then recalculates the normalization values
	 * based on the current max/min values. Values from each sensor
	 * are normalized to be between 0 and 1, where 0 is the min
	 * value and 1 is the max. 
	 * 
	 * Each time this method is called the calibration accuracy of 
	 * the robot will be increased
	 */
	public void calibrate(){

		LCD.drawString("Starting Calibration", 0, 0);
		
		float tempLeft = pollSensor(lSensorValue);
		float tempRight = pollSensor(rSensorValue);
		
		if(leftMaxValue<tempLeft){
			leftMaxValue=tempLeft;
		}else if (leftMinValue>tempLeft){
			leftMinValue=tempLeft;
		}
		
		if(rightMaxValue<tempRight){
			rightMaxValue=tempRight;
		}else if (rightMinValue>tempRight){
			rightMinValue=tempRight;
		}
		
		leftNormal=1/(leftMaxValue-leftMinValue);
		rightNormal=1/(rightMaxValue-rightMinValue);
	}
		
	
	/**
	 * returns a normalized value based on the location of the 
	 * line under the sensors. 
	 * 
	 * @return	returns a value from -100 to 100, where -100 is the line is fully under the left sensor, 0 is the line is centered, and 100 is fully under the right sensor
	 */
	public int getValue(){
		float leftSample = pollSensor(lSensorValue);
		float rightSample = pollSensor(rSensorValue);
		float normalizedLeft = (leftSample-leftMinValue)*leftNormal;
		float normalizedRight = (rightSample-rightMinValue)*rightNormal;
		return (int)((normalizedLeft-normalizedRight)*100);
	}
	
	
	/**
	 * returns true or false based on if the robot is at a cross
	 * 
	 * @return	returns true if the robot is at a cross, else returns false
	 */
	public boolean atCross(){
		float leftSample = pollSensor(lSensorValue);
		float rightSample = pollSensor(rSensorValue);
		float normalizedLeft = (leftSample-leftMinValue)*leftNormal;
		float normalizedRight = (rightSample-rightMinValue)*rightNormal;
		return ((normalizedLeft<.5)&&(normalizedRight<.5));
	}
	
	
	/**
	 * polls a sensor and returns the value read
	 * 
	 * @param	s	this is the SampleProvider that will be polled
	 * @return		returns the value that the sensor read. Will be a float between 0 and 1;
	 */
	private float pollSensor(SampleProvider s){
		float[] temp = new float[s.sampleSize()];
		s.fetchSample(temp, 0);
		return temp[0];
	}

}
