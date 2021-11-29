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
	 * @return 0 if good answer, 1 otherwise
	 */
	@Override
	public int interpretAnswer() {
		//TODO that's far from robust my boy ! And no input checking ?
		
		// Ask the user for an input
		getGui().output(PROMPT_ANSWER);
		
		setPlayerAnswer(getReader().next());
		
		return getPlayerAnswer() == exactAnswer ? 0 : 1;
	} 
}
