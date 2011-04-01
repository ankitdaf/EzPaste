package com.ezpaste;

import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.text.ClipboardManager;
import android.widget.Toast;

public class Accel extends Service {
	
	@Override
	public void onCreate() {
	// TODO Auto-generated method stub
	super.onCreate();
	getsensors();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub

		return null;
	}

	private SensorManager sensman;
	private List<Sensor> sensors;
	private Sensor accSensor;
	private long lastUpdate = -1;
	private float oldX, oldY, oldZ = 0f;
	public Btcommunication btcomm = new Btcommunication(Btinit.device,Btinit.btadapter);
	private String clipdata = "";

    private static final int SHAKE_THRESHOLD = 700;    

    void getsensors(){
    	sensman = (SensorManager) getSystemService(SENSOR_SERVICE);
    	sensors = sensman.getSensorList(Sensor.TYPE_ACCELEROMETER);

    	if(sensors.size() > 0)
        {
          accSensor = sensors.get(0);
        }
    	
    	else
    	{
    		Toast.makeText(getApplicationContext(), "No Sensors found.Exiting", Toast.LENGTH_SHORT);
    	}
    	
    	// Suitable sampling rate is delay normal. UI is too slow, GAME is too fast
    	sensman.registerListener(mySensorListener, accSensor, SensorManager.SENSOR_DELAY_NORMAL); 
    }
    
    
    
    void getclip(){
    	ClipboardManager clipman= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
 
		if(clipman.hasText()){
			clipdata = clipman.getText().toString();
		}
		
		else
		{
			clipdata = "";
			Toast.makeText(getApplicationContext(),"No text",Toast.LENGTH_LONG).show();
		}
    	
		
    	}
		
    
    void onshake(float x,float y, float z,long diffTime) {

    	{
    		float relevant_speed =  (Math.abs(x+y -oldX- oldY) / diffTime * 10000);
    		//float irrelevant_speed = (Math.abs(z - oldZ) / diffTime * 10000);
    		if (relevant_speed >= SHAKE_THRESHOLD) {
    			getclip();
    			Toast.makeText(getApplicationContext(), clipdata, Toast.LENGTH_LONG);
    	        if(Btinit.btadapter.isEnabled())
    	        {
					if(btcomm != null && (Btinit.btenabled == false))
					{
						btcomm = null;
						btcomm = new Btcommunication(Btinit.device,Btinit.btadapter);
						Btinit.btenabled = true;
					}
					else if(btcomm != null && (Btinit.btenabled ==true))
					{
						btcomm.close();
						btcomm = null;
						btcomm = new Btcommunication(Btinit.device,Btinit.btadapter);
					}
					
    	        	btcomm.run();
    	        	btcomm.write(clipdata.getBytes());
    	        	//btcomm.close();
    	        	
    	      // 	else 
    	        	{
    	        //		Toast.makeText(getApplicationContext(), "detect" ,Toast.LENGTH_SHORT).show();
    	        	}
    	        }
    	        else
    	        {
    	        	Toast.makeText(getApplicationContext(), "Please enable Bluetooth", Toast.LENGTH_SHORT).show();
    		
    	        }
		    // yes, this is a shake action! Do something about it!
		}

        oldX = (oldX + x)/2;	// When new values replace old, restoring original position suddenly
        oldY = (oldY + y)/2;	// causes an event, which should be avoided. Otherwise multiple
        oldZ = (oldZ + z)/2;	// events occur for each shake
 
    }
    }
		
    private final SensorEventListener mySensorListener = new SensorEventListener()
    {
     public void onSensorChanged(SensorEvent event)
     {
    	 long curTime = System.currentTimeMillis();
 	    // only allow one update every 100ms.
 	    if ((curTime - lastUpdate) >= 100) {
 	    //if ((curTime-eventtime) >= 1000)
 	    {
 	    	//flag=1;
 	    }
 	    long diffTime = (curTime - lastUpdate);
 		lastUpdate = curTime;
    	onshake(event.values[0], event.values[1], event.values[2],diffTime);
     }
     }
     
     public void onAccuracyChanged(Sensor sensor, int accuracy) {}


    };
    
    @Override
    	public int onStartCommand(Intent intent, int flags, int startId) {
    		// TODO Auto-generated method stub
    	getsensors();
    		return super.onStartCommand(intent, flags, startId);
    	}
    {
     
           
    }
   
@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
	sensman.unregisterListener(mySensorListener);
	Toast.makeText(getApplicationContext(), "Killed" ,Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}	 
	} 
	
