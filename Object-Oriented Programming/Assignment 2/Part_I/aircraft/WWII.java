// -----------------------------------------------------
// Assignment 2
// Part: I
// Written by: Anthony Nguyen, 40210667
// -----------------------------------------------------
package aircraft;

public class WWII extends Aircraft {

	private boolean twinengine;

	public WWII() {
		super();
	}

	public WWII(double price, double maxelevation, boolean twinengine) {
		super(price, maxelevation);
		this.twinengine = twinengine;
	}
	
	public WWII(WWII wwII) {
		super(wwII);
		this.twinengine = wwII.twinengine;
	}

	public boolean isTwinengine() {
		return twinengine;
	}

	public void setTwinengine(boolean twinengine) {
		this.twinengine = twinengine;
	}

	@Override
	public String toString() {
		if (this.twinengine == true)
			return "This WWII aircraft costs $" + getPrice() + ", has maximum elevation of " + getMaxelevation() + " m and has a twin engine.";
		else
			return "This WWII aircraft costs $" + getPrice() + ", has maximum elevation of " + getMaxelevation() + " m and does not have a twin engine.";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WWII other = (WWII) obj;
		return super.equals(other) && this.twinengine == other.twinengine;
	}
	
}
