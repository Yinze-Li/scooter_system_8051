package entity;

import java.util.Calendar;
/**
 * 
 * @author Yinze Li
 * TODO
 */
public class User {
	private int ID;
	private	String name;
	private String email;
	private boolean isBanned;
	private boolean isUsing;
	private Calendar lastPick;
	private int[] todayUsingTime;
	public User(int ID, String name, String email) {
		this.ID = ID;
		this.name = name;
		this.email = email;
		this.isBanned = false;
		this.isUsing = false;
		this.todayUsingTime = new int[2];
		this.todayUsingTime[0] = 0;
		this.todayUsingTime[1] = 0;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the isBanned
	 */
	public boolean isBanned() {
		return isBanned;
	}
	/**
	 * @return the isUsing
	 */
	public boolean isUsing() {
		return isUsing;
	}
	
	/**
	 * @param isBanned the isBanned to set
	 */
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	/**
	 * @param isUsing the isUsing to set
	 */
	public void setUsing(boolean isUsing) {
		this.isUsing = isUsing;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + ", email=" + email + ", isBanned=" + isBanned + ", isUsing="
				+ isUsing + "]";
	}
	/**
	 * @return the lastPick
	 */
	public Calendar getLastPick() {
		return lastPick;
	}
	/**
	 * @param lastPick the lastPick to set
	 */
	public void setLastPick(Calendar lastPick) {
		this.lastPick = lastPick;
	}
	/**
	 * @return the todayUsingTime
	 */
	public int[] getTodayUsingTime() {
		return todayUsingTime;
	}
	/**
	 * todayUsingTime the todayUsingTime to set
	 * @param date day
	 * @param time usingtime
	 */
	public void setTodayUsingTime(int date, int time) {
		this.todayUsingTime[0] = date;
		this.todayUsingTime[1] = time;
	}
}