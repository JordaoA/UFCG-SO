public class Main{
    
    private static final int PHILOSOPHERS_NUMBER = 5;
    private static final int PHILOSOPHERS_EATING_DURATION = 1000;
    private static final int PHILOSOPHERS_THINKING_DURATION = 1000;
    
    public static void main(String[] args) {

        Dininig dining;
		
		dinner = new SemaphoreSolve(PHILOSOPHERS_NUMBER);
		
		for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
			new Philosopher(i, PHILOSOPHERS_EATING_DURATION, PHILOSOPHERS_THINKING_DURATION, dinner);
        }
    }
}