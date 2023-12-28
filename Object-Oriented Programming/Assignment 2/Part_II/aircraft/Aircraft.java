// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package aircraft;

public class Aircraft {

	private double price;
	private double maxelevation;
	
	public Aircraft() {
	}

	public Aircraft(double price, double maxelevation) {
		this.price = price;
		this.maxelevation = maxelevation;
	}
	
	public Aircraft(Aircraft aircraft) {
		this.price = aircraft.price;
		this.maxelevation = aircraft.maxelevation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMaxelevation() {
		return maxelevation;
	}

	public void setMaxelevation(double maxelevation) {
		this.maxelevation = maxelevation;
	}

	@Override
	public String toString() {
		return "This aircraft costs $" + getPrice() + " and has maximum elevation of " + getMaxelevation() + " m.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aircraft other = (Aircraft) obj;
		return this.maxelevation == other.maxelevation && this.price == other.price;
	}
	
}
