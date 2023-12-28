// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package rail;

import metro.Metro;

public class Tram extends Metro {

	private int year;

	public Tram() {
		super();
	}

	public Tram(double maxspeed, int wheelnum, int vehiclenum, String start, String destination, int stopnum, int year) {
		super(maxspeed, wheelnum, vehiclenum, start, destination, stopnum);
		this.year = year;
	}

	public Tram(Tram tram) {
		super(tram);
		this.year = tram.year;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "This tram has " + getWheelnum() + " wheels, a max speed of " + getMaxspeed() + " km/h, " + getVehiclenum() + " vehicles, its starting and destination stations are " + getStart() + " and " + getDestination() + ", its number of stops is " + getStopnum() + " and its year of creation is " + getYear() + ".";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tram other = (Tram) obj;
		return super.equals(other) && this.year == other.year;
	}
	
}
