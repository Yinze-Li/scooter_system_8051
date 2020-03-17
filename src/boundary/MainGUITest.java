/**
 * 
 */
package boundary;

import control.DockList;
import control.TransactionList;
import control.UserList;

/**
 * @author dingfan
 *
 */
public class MainGUITest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DockList dockList = new DockList();
		UserList userList = new UserList();
		TransactionList transactionList = new TransactionList();
		MainGUI mainGUI= new MainGUI(dockList, userList, transactionList);
		
	}

}
