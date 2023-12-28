// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package monowheel;

import wheeledtransportation.WheeledTransportation;

public class Monowheel extends WheeledTransportation {

	private double maxweight;

	public Monowheel() {
		super();
	}

	public Monowheel(double maxspeed, int wheelnum, double maxweight) {
		super(maxspeed, wheelnum);
		this.maxweight = maxweight;
	}

	public Monowheel(Monowheel monowheel) {
		super(monowheel);
		this.maxweight = monowheel.maxweight;
	}

	public double getMaxweight() {
		return maxweight;
	}

	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
	}

	@Override
	public String toString() {
		return "This monowheel has " + getWheelnum() + " wheels, a max speed of " + getMaxspeed() + " km/h and a max weight capacity of " + getMaxweight() + " kg.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monowheel other = (Monowheel) obj;
		return super.equals(other) && this.maxweight == other.maxweight;
	}
	
}
