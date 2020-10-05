import java.util.ArrayList;

public class DinnerWithMonitor extends Dinner{
	
	public DinnerWithMonitor(int allPhilosophers) {
		super();
		this.allPhilosophers = allPhilosophers;
		this.PhilStates = new ArrayList<>(this.allPhilosophers);
		this.philosophers = new Object[this.allPhilosophers];
		
		for(int i = 0; i < this.allPhilosophers; i++) {
			this.PhilStates.add(PhilStates.THINKING);
			this.philosophers[i] = new Object();
		}

		System.out.println(PhilStates);
	}
	
	@Override
	public void takeCutlery(int pId) {
		PhilStates.set(pId, PhilStates.HUNGRY);
		synchronized (philosophers[pId]) {
			if (canEat(pId)) {
				PhilStates.set(pId, PhilStates.EATING);
			} else {
				try {
                    this.philosophers[pId].wait();
                } catch (InterruptedException e) {
					System.out.println("Interrupted Exception");
                }
			}
			//System.out.println("Philosopher " + pId + " take the cutlery and started do eat.");
			System.out.println(PhilStates);
		}
	}

	@Override
	public void returnCutlery(int pId) {
		PhilStates.set(pId, PhilStates.THINKING);
		//System.out.println("Philosopher " + pId + " returned the cutlery.");
		System.out.println(PhilStates);
		
		if(getRightPhilStates(pId) == PhilStates.HUNGRY &&
			getRightPhilStates(getRight(pId)) != PhilStates.EATING) {
			PhilStates.set(getRight(pId), PhilStates.EATING);
			synchronized (philosophers[getRight(pId)]) {
				philosophers[getRight(pId)].notify();
			}
		}
		if(getLeftPhilStates(pId) == PhilStates.HUNGRY &&
			getLeftPhilStates(getLeft(pId)) != PhilStates.EATING) {
			PhilStates.set(getLeft(pId), PhilStates.EATING);
			synchronized (philosophers[getLeft(pId)]) {
				philosophers[getLeft(pId)].notify();
			}
		}
	}
}