package com.qene.android.geoquiz;

import java.io.Serializable;

/**
 * Created by dave-5cof on 11/24/16.
 */

class Question  implements Serializable{

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mCheatedOn = false;

    public boolean isCheatedOn() {
        return mCheatedOn;
    }

    public void setCheatedOn(boolean cheatedOn) {
        mCheatedOn = cheatedOn;
    }

    public Question (int textResId, boolean answerTrue){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}