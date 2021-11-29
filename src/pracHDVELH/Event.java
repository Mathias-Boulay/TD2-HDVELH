/**
 * File: NodeMultipleEvents.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

import java.util.Scanner;

import myUtils.ErrorNaiveHandler;

/**
 * @author prost
 *
 */
public class Event extends NodeMultiple {
	public static final String ERROR_MSG_UNEXPECTED_END = "Sorry, for some unexpected reason the story ends here...";
	public static final String PROMPT_ANSWER = "Answer: ";
	public static final String WARNING_MSG_INTEGER_EXPECTED = "Please input a integer within range!";
	private static int nextEventID = 0; // Increment the ID to keep them unique when a new event is instantiated
	
	private int Id;
	private String playerAnswer;
	private int chosenPath;
	private Scanner reader = null; //Don't ask the logic behind the naming, thanks.	
	private GUIManager guiManager = null;
	
	// Default constructor, default GUI.
	public Event() {
		this(new GUIManager(), null);
	}
	
	public Event(GUIManager guiManager, String data) {
		super(data); 
		this.guiManager = guiManager;
		this.reader = guiManager.getInputReader();
		Id = nextEventID;
		nextEventID += 1;
	}

	/**
	 * @return the playerAnswer
	 */
	public String getPlayerAnswer() {
		return playerAnswer;
	}

	/**
	 * @param playerAnswer the playerAnswer to set
	 */
	public void setPlayerAnswer(String playerAnswer) {
		this.playerAnswer = playerAnswer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
		this.reader = reader;
	}

	/**
	 * @return the chosenPath
	 */
	public int getChosenPath() {
		return chosenPath;
	}

	/**
	 * @param chosenPath the chosenPath to set
	 */
	public void setChosenPath(int chosenPath) {
		this.chosenPath = chosenPath;
	}

	/* Methods */
	/**
	 * @see pracHDVELH.NodeMultiple#getData()
	 */
	public String getData() {
		// There should be a better way to handle this
		return (String) super.getData();
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setData(Object)
	 * @param data
	 */
	public void setData(String data) {
		super.setData(data);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#getDaughter(int)
	 */
	@Override
	public Event getDaughter(int i) {
		return (Event) super.getDaughter(i);
	}

	/**
	 * @see pracHDVELH.NodeMultiple#setDaughter(NodeMultiple, int)
	 * @param daughter
	 * @param i
	 */
	public void setDaughter(Event daughter, int i) {
		super.setDaughter(daughter, i);
	}

	/**
	 * @return the gui
	 */
	public GUIManager getGui() {
		return guiManager;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(GUIManager gui) {
		/* TO BE COMPLETED */
		guiManager = gui;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	
	@Override
	public String toString() {
		return String.format("Event #[%d] (%s): [%s]",getId(), getClass().toString(), getData().toString());
	}
	
	/**
	 * @return Whether this is the final event (no next events)
	 */
	public boolean isFinal() {
		return !hasDaughters();
	}
	
	/**
	 * @param index
	 * @return Whether the input corresponds to a daughter
	 */
	public boolean isInRange(int index) {
		return (index >= 0 && index < getDaughters().length) && getDaughter(index) != null;
	}
	
	/**
	 * Get the player answer and interpret it
	 * @return The index leading to the next event int the daughters
	 */
	public int interpretAnswer() {
		int playerInput = 0;
		
		boolean validAnswer = false;
		while(!validAnswer) {
			// Get the answer from the player
			playerAnswer = reader.next();
			try {
				playerInput = Integer.valueOf(playerAnswer) -1;
			}catch(NumberFormatException e) {
				//Kinda expected when the user start doing dogshit
				guiManager.output(WARNING_MSG_INTEGER_EXPECTED);
			}
			
			
			//Check the range
			if(!isInRange(playerInput)) {
				guiManager.output(WARNING_MSG_INTEGER_EXPECTED);
				continue;
			}
			
			validAnswer = true;
		}
		
		return playerInput;
	}

	/* Methods */
	/* TO BE COMPLETED */
	
	public Event run() {
		// Display the event content
		guiManager.outputln(this.toString());
		
		// Stop if this is the end of the story
		if(isFinal()) return null;
		
		// Visually ask the user for an input
		guiManager.output(PROMPT_ANSWER);
		
		//Wait for the next input and process the event
		chosenPath = interpretAnswer();
		
		//Get and execute the next event if available
		Event nextEvent = getDaughter(chosenPath);
		if(nextEvent == null) {
			guiManager.output(ERROR_MSG_UNEXPECTED_END);
			return null;
		}
		
		// Return the next event
		return nextEvent;
	}
	
}

// eof