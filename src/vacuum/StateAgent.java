package vacuum;

import static edu.princeton.cs.introcs.StdRandom.uniform;

public class StateAgent extends AbstractAgent {
	
	private int cleanSquares;
	private Action current;
	
	public StateAgent(){
		cleanSquares = 0;
		current = Action.SUCK;
	}

	@Override
	public Action react(boolean dirty) {
		if(dirty){
			cleanSquares = 0;
			return Action.SUCK;
		} else {
			cleanSquares++;
			if(cleanSquares == 3){
				current = selectAction();
				cleanSquares = 0;
			}
			return current;
		}
	}
	
	private Action selectAction(){
		int y = uniform(2);
		if(current == Action.UP || current == Action.DOWN){
			current = y == 0 ? Action.RIGHT : Action.LEFT;
		} else {
			current = y == 0 ? Action.UP : Action.DOWN;
		}
		return current;
	}

}
