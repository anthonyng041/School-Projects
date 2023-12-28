import common.BaseThread;  

/**
 * Class Philosopher.
 * Outlines main subrutines of our virtual philosopher.  
 * 
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca  
 */
public class Philosopher extends BaseThread
{
	/**
	 * Max time an action can take (in milliseconds)   
	 */
	public static final long TIME_TO_WASTE = 1000; 

	/**
	 * The act of eating.
	 * - Print the fact that a given phil (their TID) has started eating. 
	 * - Then sleep() for a random interval.
	 * - The print that they are done eating.
	 */
	public void eat()
	{
		try
		{
			System.out.println("Philosopher " + getTID() + " has started eating." );
			sleep((long)(Math.random() * TIME_TO_WASTE));
			System.out.println("Philosopher " + getTID() + " has finished eating." );
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of thinking.
	 * - Print the fact that a given phil (their TID) has started thinking.
	 * - Then sleep() for a random interval.
	 * - The print that they are done thinking.
	 */
	public void think()
	{
		try
		{
			System.out.println("Philosopher " + getTID() + " has started thinking." );
			sleep((long)(Math.random() * TIME_TO_WASTE));
			System.out.println("Philosopher " + getTID() + " has finished thinking." );
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of talking.
	 * - Print the fact that a given phil (their TID) has started talking.
	 * - Say something brilliant at random
	 * - The print that they are done talking.
	 */
	public void talk()
	{
		System.out.println("Philosopher " + getTID() + " has started talking." );

		saySomething();

		System.out.println("Philosopher " + getTID() + " has finished talking." );
	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run()
	{
		for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++)
		{
			try {
				DiningPhilosophers.soMonitor.pickUp(getTID());
			} catch (InterruptedException e) {}

			eat();

			DiningPhilosophers.soMonitor.putDown(getTID());

			think();

			/*
			 * TODO:
			 * A decision is made at random whether this particular
			 * philosopher is about to say something terribly useful.
			 */
			if(Math.random()< 0.5) // A random decison
			{
				try {
					DiningPhilosophers.soMonitor.requestTalk();
				} catch (InterruptedException e) {}
				talk();
				DiningPhilosophers.soMonitor.endTalk();
			}


		}
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 * Feel free to add your own phrases.
	 */
	public void saySomething()
	{
		String[] astrPhrases =
		{
			"To live is to suffer, to survive is to find some meaning in the suffering.",
			"It is not death that a man should fear, but he should fear never beginning to live.",
			"Waste no more time arguing about what a good man should be. Be one.",
			"That which does not kill us makes us stronger.",
			"When you look into an abyss, the abyss also looks into you."
		};

		System.out.println
		(
			"Philosopher " + getTID() + " says: " +
			astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
}

// EOF
