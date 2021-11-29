/**
 * File: ScenarioDG.java
 * Creation: 7 nov. 2020, Jean-Philippe.Prost@univ-amu.fr
 * Template étudiants
 */
package pracHDVELH;

/**
 * @author prost
 *
 */
public class Scenario {
	private static final String MSG_EMPTY_SCENARIO = "Sorry, no scenario was found.";
	private static final String MSG_FINALE = "That's all folks!";
	private Event head;
	private GUIManager gui;

	/* TO BE COMPLETED */
	public Scenario() {
		this(new GUIManager(), null);
	}
	
	public Scenario(GUIManager GUIManager, Event startEvent) {
		this.gui = GUIManager;
		this.head = startEvent;
	}

	/* MAIN */
	public static void main(String[] args) {
		Scenario scenario;
		GUIManager gui = new GUIManager(System.in, System.out, System.err);

		// S
		// *1:event1
		// **1.1
		// ***S
		// **1.2
		// ***E
		// *2:event2
		// **2.1
		// ***1
		// **2.2
		// ***S
		/*
		Event startEvent = new Event(gui, "Go!\n" + "(1)1 (2)2");
		Event event1 = new Event(gui, "event1:\n" + "(1)1.1 (2)1.2");
		Event event2 = new Event(gui, "event2:\n" + "(1)2.1 (2)2.2");
		Event endEvent = new Event(gui, "End event: that's it :-)");
		startEvent.addDaughter(event1);
		startEvent.setDaughter(event2, 1);
		event1.addDaughter(startEvent);
		event1.addDaughter(endEvent);
		event2.addDaughter(event1);
		event2.addDaughter(startEvent);
		scenario = new Scenario(gui, startEvent);

		// *2
		// ...
		// **2.3:event3
		// ***E
		// ***event3

		Event event3 = new EventExactSolution(gui, "Wizard: how much is worth pi?", "3.14159");
		event2.setData(event2.getData() + " (3)2.3");
		event2.addDaughter(event3);
		event3.addDaughter(endEvent);
		event3.addDaughter(event3);

		/* ******* */
		// **2.3
		// ***event4
		// ****event2
		// ****E
		// ****event3
		// ...
		
		/*
		int[] mask = { 3, 6, 7 };
		Event event4 = new EventRandomSolution(gui, "Random choice of the next event...", mask, "Dice rolling... Roll=",
				"\nNext event is ");
		event3.setDaughter(event4, 0);
		event4.addDaughter(event2);
		event4.addDaughter(endEvent);
		event4.addDaughter(event3); */
		
		Event event1 = new Event(gui, "Hello there, can you press 1 ?");
		Event event2 = new Event(gui, "I guess we went far enough for this random test, maybe make a better story next time");
		event1.setDaughter(event2, 0);
		
		
		//Build the scenario
		scenario = new Scenario(gui, event1);
		
		System.out.println(event1);
		
		//Let's go !
		System.out.println(scenario.run());
	}
	
	/**
	 * Pretty much start the story
	 * And also the end 
	 */
	private String run() {
		if(head == null) {
			return MSG_EMPTY_SCENARIO;
		}
		
		// Progress the story
		Event currentEvent = head;
		while(currentEvent != null) {
			currentEvent = currentEvent.run();
		}
		
		// End of the story
		return MSG_FINALE;
	}
}

// eof