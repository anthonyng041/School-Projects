// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package rail;

import wheeledtransportation.WheeledTransportation;

public class Train extends WheeledTransportation {

	private int vehiclenum;
	private String start;
	private String destination;
	
	public Train() {
		super();
	}
	
	public Train(double maxspeed, int wheelnum, int vehiclenum, String start, String destination) {
		super(maxspeed, wheelnum);
		this.vehiclenum = vehiclenum;
		this.start = start;
		this.destination = destination;
	}
	
	public Train(Train train) {
		super(train);
		this.vehiclenum = train.vehiclenum;
		this.start = train.start;
		this.destination = train.destination;
	}

	public int getVehiclenum() {
		return vehiclenum;
	}

	public void setVehiclenum(int vehiclenum) {
		this.vehiclenum = vehiclenum;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return "This train has " + getWheelnum() + " wheels, a max speed of " + getMaxspeed() + " km/h, " + getVehiclenum() + " vehicles and its starting and destination stations are " + getStart() + " and " + getDestination() + ".";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		return super.equals(other) && this.vehiclenum == other.vehiclenum && this.start == other.start && this.destination == other.destination;
	}

}
