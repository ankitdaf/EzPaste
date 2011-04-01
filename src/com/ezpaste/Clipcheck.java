package com.ezpaste;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.ClipboardManager;
import android.widget.Toast;


public class Clipcheck extends Service{
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ClipboardManager clipman= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		
		if(clipman.hasText()){
			Toast.makeText(	getApplicationContext(),clipman.getText().toString(),Toast.LENGTH_LONG).show();
		}
		else {Toast.makeText(getApplicationContext(),"No text",Toast.LENGTH_LONG).show();}
		stopSelf();
		
	}

	
		@Override
		public void onDestroy() {
			//Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		}
	
		
		@Override
		public IBinder onBind(Intent arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	
    };