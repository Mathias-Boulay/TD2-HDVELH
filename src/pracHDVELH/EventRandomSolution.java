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
		
		//TODO check inputs now 
		if(!isPartitionValid()) {
			manager.output(ERROR_MSG_BAD_SETTINGS);
		}
		
		// The number of faces should be the highest possible number
		dice = this.partition[partition.length - 1];
		randomGenerator = new Random();
		randomSolution = DEFAULT_RANDOM_SOLUTION;
	}
	
	@Override
	public int interpretAnswer() {
		int solution = DEFAULT_RANDOM_SOLUTION;
		
		// Roll the solution
		randomSolution = randomGenerator.nextInt(dice);
		getGui().output(waitingMsg + randomSolution);
		
		// Lookup the equivalent event entry
		for(int i=0; i< partition.length; ++i) {
			if(partition[i] >= randomSolution) return i;
		}
				
		getGui().output(solutionAnnoucement + solution);
		
		return solution;
	}
	
	/**
	 * @return Whether the partition is valid. 
	 * Doesn't account for negative values as of now.
	 */
	private boolean isPartitionValid() {
		if(partition.length == 1) return true;
		
		for(int i=0; i < partition.length -1; ++i) {
			if (partition[i] >= partition[i+1]) return false;
		}
		
		return true;
	}

}
