/**
 * 
 */
package entity;

/**
 * @author dingfan
 *
 */

public class Slot {
	private boolean isScooter;
	private boolean isLock;
	
	public Slot(boolean scooter) {
		this.isScooter = scooter;
		this.isLock = true;
	}

	/**
	 * @return the isScooter
	 */
	public boolean isScooter() {
		return isScooter;
	}

	/**
	 * @return the isLock
	 */
	public boolean isLock() {
		return isLock;
	}

	/**
	 * @param isScooter the isScooter to set
	 */
	public void setScooter(boolean isScooter) {
		this.isScooter = isScooter;
	}

	/**
	 * @param isLock the isLock to set
	 */
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

}
