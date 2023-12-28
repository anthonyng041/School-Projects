// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package ferry;

public class Ferry {

	private double maxspeed;
	private double maxload;
	
	public Ferry() {
	}

	public Ferry(double maxspeed, double maxload) {
		this.maxspeed = maxspeed;
		this.maxload = maxload;
	}
	
	public Ferry(Ferry ferry) {
		this.maxspeed = ferry.maxspeed;
		this.maxload = ferry.maxload;
	}

	public double getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(double maxspeed) {
		this.maxspeed = maxspeed;
	}

	public double getMaxload() {
		return maxload;
	}

	public void setMaxload(double maxload) {
		this.maxload = maxload;
	}

	@Override
	public String toString() {
		return "This ferry has a maximum load of " + getMaxload() + " kg and a max speed of " + getMaxspeed() + " km/h.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ferry other = (Ferry) obj;
		return this.maxload == other.maxload && this.maxspeed == other.maxspeed;
	}
	
}
