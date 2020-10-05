import java.util.ArrayList;

public abstract class Dinner {

	protected ArrayList<PhilStates> PhilStates;
	protected Object[] philosophers;
    protected int allPhilosophers;

    public void takeCutlery(int pId) {};

    public void returnCutlery(int pId) {};

    protected boolean canEat (int pId) {
		return (getRightState(pId) != PhilStates.EATING &&
				getLeftState(pId) != PhilStates.EATING);
	}

	protected PhilStates getRightState (int pId) {
		return PhilStates.get(getRight(pId));
	}

	protected int getRight (int position) {
		return (position + 1) % allPhilosophers;
	}

	protected PhilStates getLeftState (int pId) {
		return PhilStates.get(getLeft((pId)));
	}

	protected int getLeft (int position) {
		return (position + allPhilosophers - 1) % allPhilosophers;
	}

}