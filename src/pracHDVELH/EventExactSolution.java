package pracHDVELH;

public class EventExactSolution extends Event {
	
	private String exactAnswer = null;
	
	/* Constructors, is the first one needed ? */
	public EventExactSolution() {}
	
	public EventExactSolution(GUIManager manager, String data, String exactAnswer) {
		super(manager, data);
		this.exactAnswer = exactAnswer;
	}
	
	
	/**
	 * Ask for one precise answer.
	 * @return 0 if good answer, 1 otherwise.
	 */
	@Override
	public int interpretAnswer() {
		//TODO that's far from robust my boy ! And no input checking ?
		//I mean, generics could help in making this shit a bit more robust
		
		setPlayerAnswer(getReader().next());
		
		return getPlayerAnswer().equals(exactAnswer) ? 0 : 1;
	} 
}
