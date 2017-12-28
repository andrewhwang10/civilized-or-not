package com.example.civilizedornot;

import java.util.Arrays;
import java.util.Random;

import com.example.civilizedornot.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Game extends Activity {
	String[] trueQuestions;
	String[] falseQuestions;
	String[] actualQuestions = new String[10];
	boolean[] answers = new boolean[10];
	boolean[] userResponse = new boolean[10];
	int[] pictureOrder = {R.drawable.huckend, R.drawable.huckbegin, R.drawable.judge, R.drawable.duke, R.drawable.watson, R.drawable.king, R.drawable.jim, R.drawable.huckdad, R.drawable.widow, R.drawable.grangerfords};
	TextView questionBox; 
	ImageView picture;
	int[] indexesUsedTrue = new int[5];
	int[] indexesUsedFalse = new int[5];
	int questionIndex= 0;
	boolean loggedIn;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		loggedIn = extras.getBoolean("loggedIn");
		setContentView(R.layout.activity_game);
		picture = (ImageView) findViewById(R.id.imageViewPicture);
		questionBox = (TextView) findViewById(R.id.questionTextView);
		trueQuestions = getResources().getStringArray(R.array.civilized);
		falseQuestions = getResources().getStringArray(R.array.not_civilized);
//		for (int x = 0; x < 5; x++){
//			indexesUsedTrue[x] = 10;
//			indexesUsedFalse[x] = 10;
//		}
		int trueQ = 0;
		int falseQ = 0;
		for (int x = 0; x <= 9; x++){
//
//			double trueOrFalse = Math.random();
//			if (trueOrFalse > .5 && trueQ < 5){
//				int randomQuestion = getRandomIndex(true);
//				indexesUsedTrue[trueQ] = randomQuestion;
//				actualQuestions[x] = trueQuestions[randomQuestion];
//				answers[x] = true;
//				trueQ++;
//			} else if (falseQ < 5){
//				int randomQuestion = getRandomIndex(false);
//				indexesUsedFalse[falseQ] = randomQuestion;
//				actualQuestions[x] = falseQuestions[randomQuestion];
//				answers[x] = false;
//				falseQ++;
//			} else {
//				int randomQuestion = getRandomIndex(true);
//				indexesUsedTrue[trueQ] = randomQuestion;
//				actualQuestions[x] = trueQuestions[randomQuestion];
//				answers[x] = true;
//				trueQ++;
//			}
			if (x % 2 == 0){
				actualQuestions[x] = trueQuestions[trueQ];
				answers[x] = true;
				trueQ++;
			} else {
				actualQuestions[x] = falseQuestions[falseQ];
				answers[x] = false;
				falseQ++;
			}
		}


		questionBox.setText(actualQuestions[questionIndex]);
		picture.setImageResource(pictureOrder[questionIndex]);
	}
	private int getRandomIndex(boolean trueQuestion){
		Random r = new Random();
		int index = 0;
		if (trueQuestion){
			index = trueQuestions.length;
			while (Arrays.binarySearch(indexesUsedTrue, index) >= 0 || index == 5){
				index = r.nextInt(5);
			}
		} else {
			index = falseQuestions.length;
			while (Arrays.binarySearch(indexesUsedFalse, index) >= 0 || index == 5){
				index = r.nextInt(5);
			}			
		}
		return index;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	@Override
	public void onBackPressed(){
	}
	public void onClick(View view){
		TextView question = (TextView) view;
		if (((String) question.getText()).equalsIgnoreCase("civilized") && answers[questionIndex] ||((String) question.getText()).equalsIgnoreCase("not") && answers[questionIndex] == false){
			userResponse[questionIndex] = true;
		} else {
			userResponse[questionIndex] = false;
		} 
		questionIndex++;
		if (questionIndex < actualQuestions.length){
			questionBox.setText(actualQuestions[questionIndex]);
			picture.setImageResource(this.pictureOrder[questionIndex]);
		} else {
			int activityID = 0x100;
			Intent intent;
			intent = new Intent().setClass(this, Results.class);
			intent.putExtra("name",userResponse);
			intent.putExtra("loggedIn", loggedIn);
			startActivityForResult(intent, activityID);		
		}
	}

}
