package com.fullsail.android.broadcastreceiverdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {
	
	public static final String ACTION_SHOW_TOAST = "com.fullsail.android.ACTION_SHOW_TOAST";
	public static final String ACTION_SHOW_DIALOG = "com.fullsail.android.ACTION_SHOW_DIALOG";
	
	DialogReceiver mReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.toast_button).setOnClickListener(this);
		findViewById(R.id.dialog_button).setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		mReceiver = new DialogReceiver();
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION_SHOW_DIALOG);
		registerReceiver(mReceiver, filter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		unregisterReceiver(mReceiver);
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.toast_button) {
			Intent intent = new Intent(ACTION_SHOW_TOAST);
			sendBroadcast(intent);
		} else if(v.getId() == R.id.dialog_button) {
			Intent intent = new Intent(ACTION_SHOW_DIALOG);
			sendBroadcast(intent);
		}
	}
	
	public void showDialog() {
		new AlertDialog.Builder(this)
		.setTitle("Broadcast Received")
		.setMessage("Received ACTION_SHOW_DIALOG broadcast.")
		.setPositiveButton("OK", null)
		.show();
	}
	
	private class DialogReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			showDialog();
		}
		
	}
}
