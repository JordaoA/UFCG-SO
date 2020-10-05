import java.util.concurrent.Semaphore;

import java.util.ArrayList;

public class SemaphoreSolve extends Dining {
    
    private Semaphore mutex;
     
    public SemaphoreSolve (int allPhilosophers) {
        
        super();
        this.mutex = new Semaphore(1);
        this.allPhilosophers = allPhilosophers;
        this.PhilStates = new ArrayList<>(allPhilosophers);
        this.philosophers = new Semaphore[this.allPhilosophers];

        for(int i = 0; i < this.allPhilosophers; i++) {
			this.PhilStates.add(PhilStates.THINKING);
			this.philosophers[i] = new Semaphore(0);
        }
        
        System.out.println(PhilStates);
    }

    @Override
    public void takeCutlery (int pId) {
        
        try { mutex.acquire(); } 
        
        catch (InterruptedException e) { System.out.println("Interrupted Exception"); }
        
        PhilStates.set(pId, PhilPhilStates.HUNGRY);
        
        if (canEat(pId)) {
            ((Semaphore) philosophers[pId]).release();
            PhilStates.set(pId, PhilStates.EATING);
        
        }
        
        mutex.release();
        
        try {
            ((Semaphore) philosophers[pId]).acquire();
            System.out.println(PhilStates);
        } 

        catch (InterruptedException e) { System.out.println("Interrupted Exception"); }
    }

    @Override
    public void returnCutlery (int pId) {
        
        try { mutex.acquire(); } 
        
        catch (InterruptedException e) { System.out.println("Interrupted Exception"); }
        
        System.out.println(PhilStates);
        PhilStates.set(pId, PhilStates.THINKING);
        
        if (getRightState(pId) == PhilStates.HUNGRY &&
            getRightState(getRight(pId)) != PhilStates.EATING) {
                PhilStates.set(getRight(pId),  PhilStates.EATING);
                ((Semaphore) philosophers[getRight(pId)]).release();
        }
        
        if (getLeftState(pId) == PhilStates.HUNGRY &&
            getLeftState(getLeft(pId)) != PhilStates.EATING) {
                PhilStates.set(getLeft(pId), PhilStates.EATING);
                ((Semaphore) philosophers[getLeft(pId)]).release();
        }
        
        mutex.release();
    }
}