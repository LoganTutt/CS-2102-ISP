import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * Represents a distance sensor on the robot. Can check for blocked intersections
 * 
 * 
 * @author Logan Tutt
 *
 */
public class DistanceSensor {
	
	private SampleProvider sensor;
	private final int CUTOFF_VALUE=20;
	
	/**
	 * Constructor for DistanceSensor
	 *  
	 * @param sensorPort	Sensor port that the ultrasonic sensor is on
	 */
	public DistanceSensor(Port sensorPort){
		sensor=(new EV3UltrasonicSensor(sensorPort)).getDistanceMode();
	}
	
	/**
	 * returns whether the intersection directly in front of the robot is blocked
	 * 
	 * @return	returns true if the intersection in front of the robot is blocked
	 */
	public boolean inersectionBlocked(){
		float[] values = new float[sensor.sampleSize()];
		sensor.fetchSample(values, 0);
		return values[0]*100<CUTOFF_VALUE;
	}
}
