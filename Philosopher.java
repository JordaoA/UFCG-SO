public class Filosofo extends Runnable(){

	private int pId;
	private int sleepTime;
	private int eatTime;
	private Dining dining;

	public Filosofo(int pId, int sleepTime, int eatTime, Dining dining){
		this.pID = pId;
		this.sleepTime = sleepTime;
		this.eatTime = eatTime;
		this.dining = dining;

	}
	 @Override
	public void run() {
		while(true) {
			think();
			takeCutlery();
			eat();
			returnCutlery();
		}
    }
    
	private void eat() {
		
		try { Thread.sleep(this.eatTime); } 

		catch (InterruptedException e) { e.printStackTrace(); }
	
	}
	
    private void think() {
		
		try { Thread.sleep(this.thinkingDuration); } 
		
		catch (InterruptedException e) { e.printStackTrace(); }
	
	}

	private void takeCutlery () {
		dining.takeCutlery(this.pId);
	}

	private void returnCutlery () {
		dining.returnCutlery(this.pId);
	}


}