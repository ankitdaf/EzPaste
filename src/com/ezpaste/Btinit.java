package com.ezpaste;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;



public class Btinit extends Activity {
	
	public static BluetoothAdapter btadapter = BluetoothAdapter.getDefaultAdapter();
	protected static boolean btenabled= false;
	public static String mac = "90:4C:E5:D6:12:2D";	// My phone's mac, change this for other phones
	final static BluetoothDevice device = btadapter.getRemoteDevice(mac);
	
	private void enablebt()
	{
		if (! btadapter.isEnabled()) {
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivity(enableBtIntent);
		      btenabled=true;
		   }
	}
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    btadapter.cancelDiscovery();
	    enablebt();
	    finish();
	}
	
	
}
