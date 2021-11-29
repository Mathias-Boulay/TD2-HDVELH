package pracHDVELH;

import java.util.Random;

public class EventRandomSolution extends Event {
	//Damn, I have yet to understand what I'm reading
	
	public static final int ERROR_STATUS_BAD_SETTINGS = -1;
	public static final int DEFAULT_RANDOM_SOLUTION = 0;
	public static final String ERROR_MSG_BAD_SETTINGS = "WTH BRO, THE SETTINGS ARE BROKEN";
	
	private int dice;
	private int randomSolution;
	private Random randomGenerator;
	private int partition[];
	private String waitingMsg;
	private String solutionAnnoucement;
	
	/* Constructors */
	public EventRandomSolution() {
		//TODO Deal with this I guess
	}
	
	public EventRandomSolution(GUIManager manager, String data,
			int[] partition,String waitingMsg, String solutionAnnoucement ) {
		super(manager, data);
		this.partition = partition;
		this.waitingMsg = waitingMsg;
		this.solutionAnnoucement = solutionAnnoucement;
		
		//TODO check inputs now ?
	}

}
