package com.ezpaste;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class Btcommunication extends Thread{
        // Use a temporary object that is later assigned to mmSocket,
        // because mmSocket is final
		protected UUID myUUID = UUID.fromString("40aa695a-b295-4dff-9274-621627518ee8"); // My unique UUID
        BluetoothSocket sock = null;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        BluetoothDevice device = null;
        BluetoothAdapter btadapter = null;
     //   private InputStream instream;
        private OutputStream outstream;
        
        public Btcommunication(BluetoothDevice btdevice,BluetoothAdapter btadapt) 
        {
        //	instream = null;
        	btadapter = btadapt;
        	btadapter.cancelDiscovery();
        	device = btdevice;
        	outstream = null;
        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            sock = device.createRfcommSocketToServiceRecord(myUUID);          
	        try {
	            tmpIn = sock.getInputStream();
	            tmpOut = sock.getOutputStream();
	        } catch (IOException e) { }
	   //    instream = tmpIn;
	       outstream = tmpOut;
        } catch (IOException e) { }
        }
        
    public void write(byte[] bytes) {
        try {
            outstream.write(bytes);
        } catch (IOException e) { }
    }


    public void run() {
        // Cancel discovery because it will slow down the connection
    	btadapter.cancelDiscovery();
    	if(sock == null)
    	{
    	try {
			sock = device.createRfcommSocketToServiceRecord(myUUID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Null Socket");
		}

		
		}
    	
		try {
			sock.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't connect");
	}
        // Do work to manage the connection (in a separate thread)
       // manageConnectedSocket(mmSocket);
    }

    /** Will cancel an in-progress connection, and close the socket */
    public void close() {
        try {
            sock.close();
        } catch (IOException e) { 
        	e.printStackTrace();}
    }
    
    
}
