package com.qene.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.qene.android.geoquiz.answer_is_true";

    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    private static final String KEY_IS_CHEATING = "isCheating";

    private boolean mIsCheating = false;

    public static Intent newIntent (Context packageContext, boolean answerIsTrue){

        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    private void setAnswerShown (){


        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mIsCheating);

        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent data){
        boolean wasAnswerShown = data.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
        return wasAnswerShown;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null){
            mIsCheating = savedInstanceState.getBoolean(KEY_IS_CHEATING);
            if (mIsCheating)
                setAnswerShown();
        }

        TextView apiTextView = (TextView) findViewById(R.id.apiTextView);
        apiTextView.setText( "API level " + String.format("%d",Build.VERSION.SDK_INT));

        final boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        final TextView answerTextView = (TextView) findViewById(R.id.answerTextView);
        final Button showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answerIsTrue)
                    answerTextView.setText(R.string.true_button);
                else
                    answerTextView.setText(R.string.false_button);

                mIsCheating = true;
                setAnswerShown();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = showAnswerButton.getWidth() / 2;
                    int cy = showAnswerButton.getHeight() / 2;
                    float radius = showAnswerButton.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(showAnswerButton, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            answerTextView.setVisibility(View.VISIBLE);
                            showAnswerButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else {
                    answerTextView.setVisibility(View.VISIBLE);
                    showAnswerButton.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_CHEATING, mIsCheating);
    }
}
