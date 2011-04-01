package com.ezpaste;

import java.util.UUID;

import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;


public class Main_activity extends Activity //implements OnClickListener
{
	
	protected UUID myUUID = UUID.fromString("40aa695a-b295-4dff-9274-621627518ee8"); // My unique UUID
	
	//final static BluetoothDevice device = btadapter.getRemoteDevice(mac);
//	private BluetoothSocket socket = null;
	//public Btcommunication btcomm = new Btcommunication(device);
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.disp);
	    startService(new Intent(this,Accel.class));
	    Intent myIntent = new Intent(getApplicationContext(), Btinit.class);
        startActivity(myIntent);

        }
         
	  }
