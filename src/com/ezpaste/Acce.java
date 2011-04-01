package com.ezpaste;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
 
/**
 * Android accelerometer sensor tutorial
 * @author antoine vianey
 * under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 */
public class Acce extends Service 
        implements AccelerometerListener {
 
    private static Context CONTEXT;
 
    public static Context getContext() {
        return CONTEXT;
    }
 
    /**
     * onShake callback
     */
    public void onShake(float force) {
        Toast.makeText(this, "Phone shook : " + force, Toast.LENGTH_LONG).show();
    }
 
    /**
     * onAccelerationChanged callback
     */
    public void onAccelerationChanged(float x, float y, float z) {
    	Toast.makeText(	getApplicationContext(),String.valueOf(x),Toast.LENGTH_LONG).show();
    	//Toast.makeText(	getApplicationContext(),String.valueOf(y),Toast.LENGTH_LONG).show();
    	//Toast.makeText(	getApplicationContext(),String.valueOf(y),Toast.LENGTH_LONG).show();
        
    }

    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    	Toast.makeText(this, "Acce started : " , Toast.LENGTH_LONG).show();
    }
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
 
}