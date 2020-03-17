package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import entity.Transaction;
/**
 * store and control all transactions
 * @author Yinze Li
 * TODO
 */
public class TransactionList {

	private ArrayList<Transaction> transList;
	private ArrayList<Transaction> userTrans;

	public TransactionList() {
		transList = null;
		userTrans = null;
	}
	
	/**
	 * when a user pick or return a scooter, generate a transaction and write in to
	 * file
	 * @param ID id
	 * @param dock docknumber
	 * @param slot slotnumber
	 * @param isPick pick or not
	 * @param isOverTime overdue or not
	 */
	public void newTrans(int ID, int dock, int slot, boolean isPick, boolean isOverTime) {
		Calendar cal = Calendar.getInstance();
		try {
			System.out.println("writing new trans to file..");
			File csv = new File("data/transInfo.csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
			bw.newLine();
			bw.write(ID + "," + dock + "," + slot + "," + isPick + "," + cal.get(Calendar.YEAR) + ","
					+ (cal.get(Calendar.MONTH) + 1) + "," + cal.get(Calendar.DATE) + "," + cal.get(Calendar.HOUR_OF_DAY)
					+ "," + cal.get(Calendar.MINUTE) +"," + isOverTime);
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * load all transactions from file to ArrayList
	 */
	public void loadTrans() {
		transList = new ArrayList<Transaction>();
		try {
			System.out.println("loading all trans...");
			BufferedReader reader = new BufferedReader(new FileReader("data/transInfo.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				int ID = Integer.parseInt(item[0]);
				int dock = Integer.parseInt(item[1]);
				int slot = Integer.parseInt(item[2]);
				boolean isPick = Boolean.parseBoolean(item[3]);
				int year = Integer.parseInt(item[4]);
				int month = Integer.parseInt(item[5]);
				int date = Integer.parseInt(item[6]);
				int hour = Integer.parseInt(item[7]);
				int min = Integer.parseInt(item[8]);
				boolean isOverTime = Boolean.parseBoolean(item[9]);
				Calendar cal = Calendar.getInstance();
				cal.set(year, month - 1, date, hour, min);
				Transaction trans = new Transaction(ID, dock, slot, isPick, cal, isOverTime);
				transList.add(trans);
				System.out.println(trans);
			}
			reader.close();
			System.out.println("loading finished");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * load a particular user's transaction into ArrayList
	 * @param ID id
	 */
	public void loadUserTrans(int ID) {
		loadTrans();
		userTrans = new ArrayList<Transaction>();
		System.out.println("load all transactions of " + ID);
		for (int i = 0; i < transList.size(); i++) {
			if (transList.get(i).getID() == ID) {
				Transaction trans = transList.get(i);
				System.out.println(trans);
				userTrans.add(trans);
			}
		}
		System.out.println("loading finished");
	}

	/**
	 * send Email by generate a file at "data/ID.csv"
	 * @param ID id
	 */
	public void sendEmail(int ID) {
		loadUserTrans(ID);
		try {
			System.out.println("sending email...");
			File csv = new File("data/" + ID + ".csv");
			BufferedWriter bw = new BufferedWriter(new FileWriter(csv, false));
			bw.write("ID,dock,slot,Pick or not,year,month,day,hour,min,isOverTime");
			bw.newLine();
			for (int i = 0; i < userTrans.size(); i++) {
				Transaction trans = userTrans.get(i);
				bw.write(trans.toString());
				System.out.println(trans);
				bw.newLine();
			}
			bw.close();
			System.out.println("sending finished");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * return all transaction by a String array
	 * @return all transactions
	 */
	public String[] allTrans() {
		loadTrans();
		String[] s = new String[transList.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = transList.get(i).transToString();
		}
		return s;
	}

	/**
	 * return all transaction of a user by a String array
	 * @param ID id
	 * @return all transactions
	 */
	public String[] allUserTrans(int ID) {
		loadUserTrans(ID);
		if(userTrans.size()==0) {
			return null;
		}
		String[] s = new String[userTrans.size()];
		for (int i = 0; i < s.length; i++) {
			s[i] = userTrans.get(i).userToString();
		}
		return s;
	}
}
