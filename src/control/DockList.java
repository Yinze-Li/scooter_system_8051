package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import entity.Dock;

/**
 * store and control all docks
 * @author Yinze Li
 * TODO
 */
public class DockList {

	private ArrayList<Dock> dockList;
	private int maxSlot;

	public DockList() {
		init();
	}

	/**
	 * initiate the dockList from file
	 * 
	 */
	public void init() {
		dockList = new ArrayList<Dock>();
		maxSlot = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/dockInfo.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				Dock dock = new Dock(item);
				System.out.println(dock.toString());
				dockList.add(dock);
				if (item.length > maxSlot) {
					maxSlot = item.length;
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * save dock state to file
	 */
	public void dockSave() {
		try {
			File csv = new File("data/dockInfo.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			for (int i = 1; i <= maxSlot; i++) {
				bw.write("slot" + i + ",");
			}
			bw.newLine();
			for (int i = 0; i < dockList.size(); i++) {
				bw.write(dockList.get(i).toString());
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * release the lock of particular slot
	 * @param dock docknumber
	 * @param slot slotnumber
	 */
	public void release(int dock, int slot) {
		dockList.get(dock).release(slot);
	}
	
	/**
	 * lock particular slot
	 * @param dock docknumber
	 * @param slot slotnumber
	 */
	public void lock(int dock, int slot) {
		dockList.get(dock).lock(slot);
	}
	
	/**
	 * when a scooter is returned, call this method to change the slot's state
	 * @param dock docknumber
	 * @param slot slotnumber
	 */
	public void pickScooter(int dock, int slot) {
		dockList.get(dock).pickScooter(slot);
	}
	
	/**
	 * when a scooter is picked up, call this method to change the slot's state
	 * @param dock docknumber
	 * @param slot slotnumber
	 */
	public void returnScooter(int dock,int slot) {
		dockList.get(dock).returnScooter(slot);
	}
	/**
	 * randomly choose a slot for returning a scooter, 
	 * if no slot available, return -1
	 * @param dock docknumber
	 * @return slotnumber
	 */
	public int pickWhich(int dock) {
		return dockList.get(dock).pickWhich();
	}
	
	/**
	 * randomly choose a slot for picking up a scooter, 
	 * if no scooter here, return -1
	 * @param dock docknumber
	 * @return slotnumber
	 */
	public int returnWhich(int dock) {
		return dockList.get(dock).returnWhich();
	}
	
	/**
	 * return how many scooters in the dock
	 * @param dock docknumber
	 * @return number of scooters
	 */
	public int howManyScooter(int dock) {
		System.out.println("how many scooters?");
		return dockList.get(dock).getAvailable();
	}
	
	/**
	 * return all the slots' states in a dock 
	 * @param dock docknumber
	 * @return dockstate
	 */
	public boolean[] dockState(int dock) {
		return dockList.get(dock).dockState();
	}
}
