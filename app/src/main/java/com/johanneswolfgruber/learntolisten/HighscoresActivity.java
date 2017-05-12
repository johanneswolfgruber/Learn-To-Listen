package com.johanneswolfgruber.learntolisten;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Locale;

public class HighscoresActivity extends AppCompatActivity {
    private Animation mAnimationBlendIn;
    private TextView mHigh1, mHigh2, mHigh3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        mAnimationBlendIn = AnimationUtils.loadAnimation(this, R.anim.blend_in);
        mHigh1 = (TextView) findViewById(R.id.first_place_text_view);
        mHigh2 = (TextView) findViewById(R.id.second_place_text_view);
        mHigh3 = (TextView) findViewById(R.id.third_place_text_view);

        SharedPreferences sharedPref = this
                .getSharedPreferences("com.johanneswolfgruber.learntolisten.HIGHSCORES",
                        Context.MODE_PRIVATE);
        long mHighscore = sharedPref.getInt(getString(R.string.highscore), 0);

        mHigh1.setText(String.format(Locale.getDefault(), "%d", mHighscore));
        mHigh2.setText(String.format(Locale.getDefault(), "%d", mHighscore));
        mHigh3.setText(String.format(Locale.getDefault(), "%d", mHighscore));

    }

    @Override
    public void onResume() {
        super.onResume();
        View v = findViewById(R.id.root_constraint_layout_highscores);
        v.startAnimation(mAnimationBlendIn);
    }
}
