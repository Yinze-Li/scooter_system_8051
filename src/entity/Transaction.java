package entity;

import java.util.Calendar;
/**
 * 
 * @author Yinze Li
 * TODO
 */
public class Transaction {
	private int ID;
	private int dock;
	private int slot;
	private boolean isPick;
	private Calendar cal;
	private boolean isOverTime;

	public Transaction(int ID, int dock, int slot, boolean isPick, Calendar cal, boolean isOverTime) {
		this.ID = ID;
		this.dock = dock;
		this.slot = slot;
		this.isPick = isPick;
		this.cal = cal;
		this.isOverTime = isOverTime;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return the dock
	 */
	public int getDock() {
		return dock;
	}

	/**
	 * @return the slot
	 */
	public int getSlot() {
		return slot;
	}

	/**
	 * @return the isPick
	 */
	public boolean isPick() {
		return isPick;
	}

	/**
	 * @return the cal
	 */
	public Calendar getCal() {
		return cal;
	}

	public String toString() {
		return ID + "," + dock + "," + slot + "," + isPick + "," + cal.get(Calendar.YEAR) + ","
				+ (cal.get(Calendar.MONTH) + 1) + "," + cal.get(Calendar.DATE) + "," + cal.get(Calendar.HOUR_OF_DAY)
				+ "," + cal.get(Calendar.MINUTE) + "," + isOverTime;
	}

	public String transToString() {
		return ID + "        " + dock + "          " + slot + "          " + isPick + "       " + cal.get(Calendar.YEAR)
				+ "       " + (cal.get(Calendar.MONTH) + 1) + "         " + cal.get(Calendar.DATE) + "         "
				+ cal.get(Calendar.HOUR_OF_DAY) + "          " + cal.get(Calendar.MINUTE) + "\t" +isOverTime +"\n";
	}

	public String userToString() {
		String picked = " ";
		if (isPick == true) {
			picked = "Pick";
		} else if (isPick == false) {
			picked = "Return";
		}
		return "          " + picked + "\t        " + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
				+ "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE)
				+ "            " + dock + "                " + slot + "                " + isOverTime + "\n";
	}

	/**
	 * @return the isOverTime
	 */
	public boolean isOverTime() {
		return isOverTime;
	}

	/**
	 * @param isOverTime the isOverTime to set
	 */
	public void setOverTime(boolean isOverTime) {
		this.isOverTime = isOverTime;
	}
}
