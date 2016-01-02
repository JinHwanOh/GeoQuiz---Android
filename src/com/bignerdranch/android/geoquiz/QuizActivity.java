package com.bignerdranch.android.geoquiz;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class QuizActivity extends Activity{
	private static final String TAG = "CheatActivity";

	//Buttons
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private Button mPrevButton;
	private Button mCheatButton;

	//TextView
	private TextView mQuestionTextView;

	//Set up question bank
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_america, true),
			new TrueFalse(R.string.question_asia, true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_oceans, true)
	};

	private int mCurrentIndex = 0;
	private int mPrevIndex;

	private boolean mIsCheater;

	//Update and display question
	private void updateQuestion(){
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}

	//check for correct answer
	private void checkAnswer(boolean userPressedTrue){
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

		int messageResId = 0;

		if(mIsCheater){
			messageResId = R.string.judgment_toast;
		}
		else{
			if(userPressedTrue == answerIsTrue){
				messageResId = R.string.correct_toast;
			}
			else{
				messageResId = R.string.incorrect_toast;
			}
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(data == null){
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
	}

	//Main method
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		Log.d(TAG, "onCreate() called");

		//Checking the device's build version first
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			android.app.ActionBar actionBar = getActionBar();
			actionBar.setSubtitle("Bodies of Water");
		}
		mIsCheater = false;

		//Display question in text view
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

		//True button
		mTrueButton = (Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAnswer(true);
			}
		});

		//False button
		mFalseButton = (Button)findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkAnswer(false);
			}
		});

		//Next button
		mNextButton = (Button)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPrevIndex = mCurrentIndex;
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length; //to limit the question index
				updateQuestion();
			}
		});

		//Prev button
		mPrevButton = (Button)findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
				mCurrentIndex = mPrevIndex;
				updateQuestion();
			}
		}); 

		//Cheat button
		mCheatButton = (Button)findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				startActivityForResult(i, 0);
			}
		});
		updateQuestion();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}


	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}
}