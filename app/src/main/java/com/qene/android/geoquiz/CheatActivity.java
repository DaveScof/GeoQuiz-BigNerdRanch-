package com.qene.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE =
            "com.qene.android.geoquiz.answer_is_true";

    public static Intent newIntent (Context packageContext, boolean answerIsTrue){

        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        final boolean answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        final TextView answerTextView = (TextView) findViewById(R.id.answerTextView);
        Button showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answerIsTrue)
                    answerTextView.setText(R.string.true_button);
                else
                    answerTextView.setText(R.string.false_button);
            }
        });
    }
}
