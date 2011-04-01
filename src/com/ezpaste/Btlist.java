package com.ezpaste;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


public class Btlist extends Activity {
	
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	protected boolean btenabled = false;
	protected String[] devicelist = new String[]{""};
	Adapter_bt btadapt;
	List<Btinfo> bdlist = new ArrayList<Btinfo>();
	
	// Create a BroadcastReceiver for ACTION_FOUND
	final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            // Add the name and address to an array adapter to show in a ListView
	            Btinfo bdinfo = new Btinfo();
			       bdinfo.setName(device.getName());
			       bdinfo.setMac(device.getAddress());
			       bdlist.add(bdlist.size(),bdinfo);
	        }
	    }
	};
	
	
	
	
	private void enablebt()
{
	if (!mBluetoothAdapter.isEnabled()) {
	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	    startActivityForResult(enableBtIntent, 3);
	      btenabled=true;
	   }
	}
	
	private void querybt(boolean btenabled)
	{
		 //DE-COMMENT FOLLOWING CODE
		if(btenabled)
		{
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		// If there are paired devices
		 int i=0;
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		       
		    	
			   Btinfo bdinfo = new Btinfo();
		       bdinfo.setName(device.getName());
		       bdinfo.setMac(device.getAddress());
		       bdlist.add(i,bdinfo);
		       i=i+1;
		    }}
		}
		// COMMENT FOLLOWING CODE
		/*for(int i = 0;i<3;i++){
			Btinfo bdinfo = new Btinfo();
			bdinfo.setName("Dev"+i);
			bdinfo.setMac("add"+i);
			bdlist.add(i,bdinfo);
			
			}*/
	}
	
	
/*	private void discoverbt(boolean btenabled)
	{
		if(btenabled)
		{
			mBluetoothAdapter.startDiscovery();	
		}
		else
		{
			Toast.makeText(this, "Bluetooth is off", Toast.LENGTH_LONG).show();
		}
		

}*/
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	  /*s  BluetoothDevice hxm = BluetoothAdapter.getDefaultAdapter().getRemoteDevice("90:4C:E5:D6:12:2D");
	    Method m = null;
		try {
			m = hxm.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BluetoothSocket sock = null;
		try {
			sock = (BluetoothSocket)m.invoke(hxm, Integer.valueOf(1));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			sock.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    String strdat = "Just checking";

	    byte[] strbyt = strdat.getBytes();
	    BluetoothDevice btdev =  mBluetoothAdapter.getRemoteDevice("90:4C:E5:D6:12:2D");
	    Btsend bt1 = new Btsend(btdev);
	    //bt1.mBluetoothAdapter.cancelDiscovery();
	    bt1.run();
	    bt1.write(strbyt);
	    Toast.makeText(this, "Querying done", Toast.LENGTH_LONG).show();
	    setContentView(R.layout.btlist);
	    //ListView list = (ListView) findViewById(R.id.ListView01);
	    
	    //REMOVE COMMENT :P
	   // enablebt();
	    //bt1.run();
	    //Toast.makeText(this, "Querying done", Toast.LENGTH_LONG).show();
	   // IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	   // registerReceiver(mReceiver,filter);
	    //querybt(btenabled);
	   // discoverbt(btenabled);
	   // Adapter_bt adapter = new Adapter_bt(this, bdlist, R.layout.item);
	   // list.setAdapter(adapter); 
	    
	    // Make BT Discoverable
	    /*
			Intent discoverableIntent = new	Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}*/
	    
	    
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//unregisterReceiver(mReceiver);
	}
	
	
}