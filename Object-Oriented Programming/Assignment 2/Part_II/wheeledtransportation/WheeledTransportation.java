// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package wheeledtransportation;

public class WheeledTransportation {
	
	private double maxspeed;
	private int wheelnum;
	
	public WheeledTransportation() {
		
	}

	public WheeledTransportation(double maxspeed, int wheelnum) {
		this.wheelnum = wheelnum;
		this.maxspeed = maxspeed;
	}
	
	public WheeledTransportation(WheeledTransportation wheeledtransportation) {
		this.wheelnum = wheeledtransportation.wheelnum;
		this.maxspeed = wheeledtransportation.maxspeed;
	}

	public int getWheelnum() {
		return wheelnum;
	}

	public void setWheelnum(int wheelnum) {
		this.wheelnum = wheelnum;
	}

	public double getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(double maxspeed) {
		this.maxspeed = maxspeed;
	}

	@Override
	public String toString() {
		return "This wheeled transportation has " + getWheelnum() + " wheels and a max speed of " + getMaxspeed() + " km/h.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WheeledTransportation other = (WheeledTransportation) obj;
		return this.maxspeed == other.maxspeed && wheelnum == other.wheelnum;
	}
	
}
