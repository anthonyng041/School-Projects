// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package metro;

import rail.Train;

public class Metro extends Train {
	
	private int stopnum;

	public Metro() {
		super();
	}

	public Metro(double maxspeed, int wheelnum, int vehiclenum, String start, String destination, int stopnum) {
		super(maxspeed, wheelnum, vehiclenum, start, destination);
		this.stopnum = stopnum;
	}

	public Metro(Metro metro) {
		super(metro);
		this.stopnum = metro.stopnum;
	}
	
	public int getStopnum() {
		return stopnum;
	}

	public void setStopnum(int stopnum) {
		this.stopnum = stopnum;
	}

	@Override
	public String toString() {
		return "This metro has " + getWheelnum() + " wheels, a max speed of " + getMaxspeed() + " km/h, " + getVehiclenum() + " vehicles, its starting and destination stations are " + getStart() + " and " + getDestination() + " and its number of stops is " + getStopnum() + ".";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metro other = (Metro) obj;
		return super.equals(other) && this.stopnum == other.stopnum;
	}
	
}
