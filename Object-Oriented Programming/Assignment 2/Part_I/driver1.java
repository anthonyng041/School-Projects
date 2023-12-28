// -----------------------------------------------------
// Assignment 2
// Part: I
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

public class driver1 {

	/**
	 * The copyTheObjects() method takes an array of objects as a parameter and returns a copy of the array
	 * @parameter array
	 * @author Anthony Nguyen
	 */
	
	public static void findLeastAndMostExpensiveAircraft(Object array[]) {
		
		Aircraft most = null;	// These Aircraft objects store the least and most expensive aircrafts
		Aircraft least = null;
		
		for (Object obj : array)	// Iterating over each object in the array
			if (obj instanceof Aircraft) {	// Verifying if the object is an aircraft
				Aircraft aircraft = (Aircraft) obj;	// Casting aircraft on the object and assigning it to an Aircraft object so it has access to the Aircraft methods
				if (least == null || aircraft.getPrice() < least.getPrice())	// Finding the least and most expensive aircrafts
					least = aircraft;
				if (most == null || aircraft.getPrice() > most.getPrice())
					most = aircraft;
			}
			
		if (most == null && least == null)	// If none of the objects are aircrafts, then no operation has been made on them and therefore most and least remain null
			System.out.println("No aircrafts found.");
		
		else if (least == most)	// If most and least are equal, then there is only one aircraft
			System.out.println("Only one aircraft found: " + most.toString());
		
		else {
			System.out.println("Least expensive aircraft: " + least.toString());
			System.out.println("Most expensive aircraft: " + most.toString());
		}
	}

	public static void main(String[] args) {
		
		WheeledTransportation a = new WheeledTransportation(200, 8);	// Creating various objects and printing their respective toString() methods
		System.out.println(a.toString());
		WheeledTransportation b = new WheeledTransportation(300, 4);
		System.out.println(b.toString());
		Monowheel c = new Monowheel(100, 1, 200);
		System.out.println(c.toString());
		Monowheel d = new Monowheel(80, 1, 250);
		System.out.println(d.toString());
		Train e = new Train(250, 64, 16, "Montreal", "Quebec City");
		System.out.println(e.toString());
		Train f = new Train(400, 48, 12, "Toronto", "Montreal");
		System.out.println(f.toString());
		Metro g = new Metro(120, 56, 12, "Montmorency", " Cote-Vertu", 40);
		System.out.println(g.toString());
		Metro h = new Metro(100, 42, 10, "Angrignon", "Honore-Beaugrand", 28);
		System.out.println(h.toString());
		Tram i = new Tram(80, 28, 8, "Downtown", "Cote-des-Neiges", 18, 1956);
		System.out.println(i.toString());
		Tram j = new Tram(100, 36, 12,"Downtown", "West-Island", 24, 1972);
		System.out.println(j.toString());
		Ferry k = new Ferry(225, 2150);
		System.out.println(k.toString());
		Ferry l = new Ferry(310, 1500);
		System.out.println(l.toString());
		Aircraft m = new Aircraft(9000000, 6000);
		System.out.println(m.toString());
		Aircraft n = new Aircraft(2000000, 3000);
		System.out.println(n.toString());
		Aircraft o = new Aircraft(3000000, 3500);
		System.out.println(o.toString());
		Aircraft p = new Aircraft(6000000, 5000);
		System.out.println(p.toString());
		WWII q = new WWII(5000000, 3000, true);
		System.out.println(q.toString());
		WWII r = new WWII(5000000, 3000, true);
		System.out.println(r.toString());
	
		System.out.println(i.equals(o));	// Testing the equals() method for two objects of different classes, two of the same class and two identical objects respectively
		System.out.println(a.equals(b));
		System.out.println(q.equals(r));
		
		WheeledTransportation A = new WheeledTransportation(203, 2);	// Creating new objects for second array without Aircraft objects
		WheeledTransportation B = new WheeledTransportation(350, 3);
		WheeledTransportation C = new WheeledTransportation(700, 5);
		Monowheel D = new Monowheel(300, 1, 250);
		Monowheel E = new Monowheel(820, 1, 250);
		Monowheel F = new Monowheel(1005, 1, 203);
		Train G = new Train(250, 24, 22, "Laval", "Montreal");
		Train H = new Train(450, 78, 32, "Toronto", "Windsor");
		Train I = new Train(460, 38, 12, "Toronto", "Ottawa");
		Metro J = new Metro(130, 56, 52, "Laurier", " Vendome", 23);
		Metro K = new Metro(124, 66, 32, "Angrignon", "Vendome", 53);
		Metro L = new Metro(120, 44, 50, "Guy-Concordia", "Honore-Beaugrand", 28);
		Tram M = new Tram(80, 28, 6, "Mont-Royal", "NDG", 18, 1976);
		Tram N = new Tram(80, 28, 8, "Laval-sur-le-lac", "Chomedey", 12, 1926);
		Tram O = new Tram(100, 36, 12,"Downtown", "West-Island", 22, 1872);
		Ferry P = new Ferry(545, 2130);
		Ferry Q = new Ferry(235, 2140);
		Ferry R = new Ferry(350, 1200);
		
		
		Object[] array1 = {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r};	// Initializing array1 to first set of objects (including aircrafts)
		Object[] array2 = {A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R};	// Initializing array2 to second set of objects (no aircrafts)
		
		findLeastAndMostExpensiveAircraft(array1);	// Testing findLeastAndMostExpensiveAircraft method with both arrays
		findLeastAndMostExpensiveAircraft(array2);
		
		System.out.println("Test Over");
		
	}
}
