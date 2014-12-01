
public class GridNavigator implements CrossListener {

	private Comunicator coms;
	private Navigator n;
	
	public static void main(String[] args) {
		GridNavigator g=new GridNavigator();
		g.run();
	}
	
	public void atCrossAction(){
		coms.sendPose(n.getXChoord(), n.getYChoord(), n.getHeading());
	}
	
	public void run(){
		coms = new Comunicator();
		n = new Navigator(1, 1, 0);
		n.addListener(this);
		n.moveTo(3, 3);
		n.moveTo(4, 2);
		n.moveTo(2, 4);
		n.moveTo(1, 1);
	}
	
	
}
