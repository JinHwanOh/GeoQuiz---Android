package com.bignerdranch.android.geoquiz;

public class TrueFalse {
	private int mQuestion; //int because it holds the resource id of a string resource for the question
	
	private boolean mTrueQuestion;
	
	public TrueFalse(int question, boolean trueQuestion){
		mQuestion = question;
		mTrueQuestion = trueQuestion;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}
}
