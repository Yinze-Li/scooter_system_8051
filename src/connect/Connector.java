/**
 * 
 */
package connect;

import gnu.io.*;
import java.io.*;

/**
 * @author Yinze Li
 * @Date 2019年5月14日下午5:04:15 TODO
 */
public class Connector {
	static CommPortIdentifier portId;
	static CommPort com;
	static SerialPort ser;

	public Connector() {
		init();
	}

	private void init() {
		try {
			// TODO: identify the COM port from Windows' control panel
			portId = CommPortIdentifier.getPortIdentifier("COM3");

			com = portId.open("MCS51COM", 2000);
			ser = (SerialPort) com;
			// Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
			ser.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		// Wait for 1 second if 8051 needs time to initialize
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

//		try {
//			// Test TX: send out chars 'D', 'O', 'G', 'S'
//			OutputStream comOut = ser.getOutputStream();
//			comOut.write('D');
//            comOut.write('O');
//            comOut.write('G');
//            comOut.write('S');
//
//			// Test RX: display first 4 chars received
//			InputStream comIn = ser.getInputStream();
//            for (int i = 0; i < 4; i++){
//			// while (comIn.available() == 0);
//			byte c = (byte) comIn.read();
//                System.out.println(c);
//			 }
//
//			// close the streams
//			comIn.close();
//			comOut.close();
//		} catch (Exception e) {
//			e.printStackTrace(System.out);
//		}
//		// close the port
//		ser.close();
	}

	public void sendByte(byte data) {
		try {
			System.out.println("sending " + data + "....");
			OutputStream comOut = ser.getOutputStream();
			comOut.write(data);
			comOut.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public byte receive() {
		int count = 0;
		byte data = -1;
		System.out.println("receving...");
		try {
			InputStream comIn = ser.getInputStream();
			do {
				data = (byte) comIn.read();
				Thread.sleep(100);
				count ++;
			} while (data == -1 && count < 1000);
			comIn.close();
			System.out.println("received " + data);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return data;
	}

	public void close() {
		ser.close();
	}
}
