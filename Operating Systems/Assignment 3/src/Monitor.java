import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.LinkedList;
import java.util.Queue;

//This deadlock-free solution to the dining philosophers problem was inspired by the one given in the textbook.

/** 
 * Class Monitor 
 * To synchronize dining philosophers. 
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca  
 */
public class Monitor   
{
	/*
	 * ------------    
	 * Data members 
	 * ------------
	 */
	int NumberOfPhilosophers;
	enum State {THINKING, HUNGRY, EATING}
	State[] state;		// Array containing the state of each philosopher.
	Lock lock;				// Lock for the implementation of the condition variables.	
	Condition available[];	// Array of condition variables to indicate if a philosopher can eat or has to wait.
	boolean talking = false;	// Indicates if someone is talking.
	Queue<Integer> waitingQueue = new LinkedList<>();	// Queue for philosophers that have waited for too long.
	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		NumberOfPhilosophers = piNumberOfPhilosophers;
		state = new State[NumberOfPhilosophers];
        lock = new ReentrantLock();
        available = new Condition[NumberOfPhilosophers];
		for (int i = 0; i < NumberOfPhilosophers; i++) {
			state[i] = State.THINKING;			// Initializing all philosopher states to THINKING.
            available[i] = lock.newCondition();
		}
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */
	
	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait(). This deadlock-free solution to the dining philosophers problem was inspired by the one given in the textbook.
	 * @throws InterruptedException 
	 */
	public void pickUp(final int piTID) throws InterruptedException
	{
		lock.lock();		// Acquiring lock in order to use the await() or signal() methods from condition variables, otherwise IllegalMonitorStateException
        try {
            state[piTID] = State.HUNGRY;	// hange state to HUNGRY.
            test(piTID);		// Test if philosopher can pick up chopsticks and eat.
            if (state[piTID] != State.EATING)		// If chopsticks not available.
            	if (available[piTID].awaitNanos(1000000000) <= 0) {			// Make philosopher wait at most 1 second. If a philosopher has waited for too long, add to waitingQueue.
            		System.out.println("Philosopher " + piTID + " is getting hungry...");
            		waitingQueue.add(piTID);
            	}
        } finally {
            lock.unlock();
        }
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		lock.lock();
        try {
            state[piTID] = State.THINKING;		// Change state back to THINKING.
            if (!waitingQueue.isEmpty()) {		// If there is a philosopher in the waitingQueue. 
            	int hungryPhilosopher = waitingQueue.element();
            	test(hungryPhilosopher);
            	if (state[hungryPhilosopher] != State.EATING)	// If not eating, leave philosopher at head of queue and return.
            		return;
            	else {		// Otherwise remove philosopher from queue. More in depth explanation in task 4.
            		waitingQueue.poll();
            	}
            }
            test((piTID + NumberOfPhilosophers - 1) % NumberOfPhilosophers);	// Test if neighbors can eat now that chopsticks are available.
            test((piTID + 1) % NumberOfPhilosophers);
        } finally {
            lock.unlock();
        }
	}

	public synchronized void test(final int piTID) 
	{
		if (state[(piTID + NumberOfPhilosophers - 1) % NumberOfPhilosophers] != State.EATING &&		// If hungry and neighbors are not eating (chopsticks are available), change state to eating and signal() that philosopher can eat.
	            state[piTID] == State.HUNGRY &&
	            state[(piTID + 1) % NumberOfPhilosophers] != State.EATING) {
	            state[piTID] = State.EATING;
	            available[piTID].signal();
	        }
	}
	
	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 * @throws InterruptedException 
	 */
	public synchronized void requestTalk() throws InterruptedException
	{
		if (talking)
			wait();			// If anyone is talking, wait until they stop and notify.
		talking = true;		// Set talking to true before talking
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		talking = false;	// Set talking to false after talking.
		notify();		// Notify done talking.
	}
}

// EOF
