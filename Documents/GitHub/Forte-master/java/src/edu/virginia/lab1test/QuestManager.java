package edu.virginia.lab1test;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.lab1test.CoinPickedUp;

public class QuestManager implements IEventListener {
	@Override
	public void handleEvent(Event event) {
		if (event instanceof CoinPickedUp) {
			if (((CoinPickedUp) event).isCompleted()) {
				((CoinPickedUp) event).showMessage();
			}
		}
	}
}
