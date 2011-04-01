package com.ezpaste;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;


public class Btsend extends Thread {

	private InputStream InStream;
    private OutputStream OutStream;

	BluetoothAdapter btadapter = BluetoothAdapter.getDefaultAdapter();

	protected boolean btenabled = false;
	protected String mac = "90:4C:E5:D6:12:2D";
	protected UUID myUUID = UUID.fromString("40aa695a-b295-4dff-9274-621627518ee8");
	//protected UUID whyUUID = UUID.fromString("0x0003");
	private BluetoothSocket sock = null;
	//private final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac);
    
	//Method m = mBluetoothAdapter.getClass().getMethod("createRfcommSocket", new Class[]{int.class});

	
	    
	     
	    public Btsend(BluetoothDevice device) {
	        // Use a temporary object that is later assigned to mmSocket,
	        // because mmSocket is final
	        BluetoothSocket tmp = null;
	        InputStream tmpIn = null;
	        OutputStream tmpOut = null;
	      //  mmOutStream = null;
	    //    mmInStream = null;
	        
	        // Get a BluetoothSocket to connect with the given BluetoothDevice
	        try {
	            // MY_UUID is the app's UUID string, also used by the server code
	            tmp = device.createRfcommSocketToServiceRecord(myUUID);
	            sock = tmp;
		        try {
		            tmpIn = sock.getInputStream();
		            tmpOut = sock.getOutputStream();
		        } catch (IOException e) { }

		    //    mmInStream = tmpIn;
		    //    mmOutStream = tmpOut;
		        
	        } catch (IOException e) { }
	        
	    }

	    public void write(byte[] bytes) {
	        try {
	            mmOutStream.write(bytes);
	        } catch (IOException e) { }
	    }


	    public void run() {
	        // Cancel discovery because it will slow down the connection
	    	

	        try {
	            // Connect the device through the socket. This will block
	            // until it succeeds or throws an exception
	            sock.connect();
	        } catch (IOException connectException) {
	            try {
	                sock.close();
	            } catch (IOException closeException) { }
	            return;
	        }

	        // Do work to manage the connection (in a separate thread)
	       // manageConnectedSocket(mmSocket);
	    }

	    /** Will cancel an in-progress connection, and close the socket */
	    public void cancel() {
	        try {
	            sock.close();
	        } catch (IOException e) { }
	    }
	}
	

