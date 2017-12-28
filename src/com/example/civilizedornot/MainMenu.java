package com.example.civilizedornot;

import com.example.civilizedornot.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends FragmentActivity {
	private MainFragment mainFragment;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		TextView t = (TextView) findViewById(R.id.textView2);
		Typeface font = Typeface.createFromAsset(getAssets(),"fonts/Roboto/Roboto-Thin.ttf");
		t.setTypeface(font);
		if (savedInstanceState == null) {
			mainFragment = new MainFragment();
			getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
		} else {
			mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
		}
	}
	public void onPlayClick(View view) {
		int activityID = 0x100;
		Intent intent = new Intent(MainMenu.this, Game.class);
		intent.putExtra("loggedIn", mainFragment.loggedIn);
		startActivityForResult(intent, activityID);
	}

	public void onHelpClick(View view) {
		Intent intent = new Intent(MainMenu.this, Help.class);
		startActivity(intent);

	}
	@Override
	public void onBackPressed(){
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
