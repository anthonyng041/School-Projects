// -----------------------------------------------------
// Assignment 2
// Part: II
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
import aircraft.Aircraft;
import aircraft.WWII;
import ferry.Ferry;
import metro.Metro;
import monowheel.Monowheel;
import rail.Train;
import rail.Tram;
import wheeledtransportation.WheeledTransportation;

public class driver2 {

	/**
	 * The copyTheObjects() method takes an array of objects as a parameter and returns a copy of the array
	 * 
	 * @parameter array
	 * @author Anthony Nguyen
	 */
	 
	public static Object[] copyTheObjects(Object array[]) {
		Object[] copy = new Object[array.length];	// Creating an array of same size
	    for (int i = 0; i < array.length; i++) {	// Iterating over array
	    	if (array[i] instanceof Tram)	// Verifying the type of the object in the array
	    		copy[i] = new Tram((Tram) array[i]);	// If the object is of a certain type, then a copy of that object is put in the copy array at the same position
	    	else if (array[i] instanceof Metro)
	    		copy[i] = new Metro((Metro) array[i]);
	    	else if (array[i] instanceof Train) 
	            copy[i] = new Train((Train) array[i]);
	    	else if (array[i] instanceof Monowheel)
	            copy[i] = new Monowheel((Monowheel) array[i]);
	        else if (array[i] instanceof WheeledTransportation)			
		        copy[i] = new WheeledTransportation((WheeledTransportation) array[i]);
	        else if (array[i] instanceof Ferry) 
	        	copy[i] = new Ferry((Ferry) array[i]);
	        else if (array[i] instanceof WWII)
		        copy[i] = new WWII((WWII) array[i]);
	        else if (array[i] instanceof Aircraft) 
	            copy[i] = new Aircraft((Aircraft) array[i]);
	    }
	    return copy;
	}
	
	public static void main(String[] args) {
		
		WheeledTransportation a = new WheeledTransportation(260, 10);	// Creating various objects
		WheeledTransportation b = new WheeledTransportation(303, 6);
		Monowheel c = new Monowheel(100, 1, 250);
		Monowheel d = new Monowheel(189, 1, 220);
		Train e = new Train(450, 57, 13, "Gatineau", "Windsor");
		Train f = new Train(406, 27, 25, "Laval", "Mirabelle");
		Metro g = new Metro(134, 44, 15, "Atwater", " Cote-Vertu", 30);
		Metro h = new Metro(126, 44, 42, "Jean-Talon", "Honore-Beaugrand", 18);
		Tram i = new Tram(180, 48, 28, "Downtown", "Cote-des-Neiges", 18, 1968);
		Tram j = new Tram(150, 16, 22,"Downtown", "West-Island", 24, 1944);
		Ferry k = new Ferry(725, 2180);
		Ferry l = new Ferry(330, 1970);
		Aircraft m = new Aircraft(9006000, 6030);
		Aircraft n = new Aircraft(2300000, 2000);
		Aircraft o = new Aircraft(7000000, 5500);
		Aircraft p = new Aircraft(3060000, 5330);
		WWII q = new WWII(5000000, 3000, true);
		WWII r = new WWII(2000000, 1500, false);
		
		Object[] array1 = {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r};	// Creating array initialized with objects
		
		for (Object obj : array1)	// Displaying contents of array1
			System.out.println(obj.toString());
		
		Object[] array2 = copyTheObjects(array1); // Storing the copy of array1 into new array
		
		System.out.println("\nCopy:\n");
		
		for (Object obj : array2)	// Displaying contents of copy array
			System.out.println(obj.toString());
		
	}

}