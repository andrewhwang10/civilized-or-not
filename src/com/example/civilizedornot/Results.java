package com.example.civilizedornot;

import com.example.civilizedornot.R;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends FragmentActivity {
	boolean[] userResponses;
	boolean loggedIn;
	TextView[] results = new TextView[10];
	private SecondaryFragment fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		Bundle extras = getIntent().getExtras();
		loggedIn = extras.getBoolean("loggedIn");
		userResponses = extras.getBooleanArray("name");
		Button share = (Button) findViewById(R.id.shareButton);
		if (!loggedIn){
			share.setVisibility(View.INVISIBLE);
			share.requestLayout();
			share.refreshDrawableState();
		}
		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			fragment = new SecondaryFragment(userResponses, loggedIn, share);
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
		} else {
			// Or set the fragment from restored state info
			fragment = (SecondaryFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
		}
		for (int i = 0; i < 10; i++){
			results[i] = (TextView) findViewById(R.id.question1 + i);
			if (userResponses[i]){
				results[i].setText((i + 1) + ". Correct");
			} else {
				results[i].setText((i + 1) + ". Incorrect");        		
			}
		}
		Log.i("Sharebutton", "" + loggedIn);

	}
	public void onClick(View view){
		if (loggedIn){
			fragment.onClick();
		} 
	}
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(Results.this, MainMenu.class);
		startActivity(intent);
	}

}
