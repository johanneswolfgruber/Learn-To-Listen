package com.johanneswolfgruber.learntolisten;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity{

    //private TextToSpeech mTTS;
    private Animation mAnimationBlendIn, mAnimationBlinking;
    private TextView mHigh;
    private Switch mSwitch;
    private Sound mSound;
    private float mVol = 1.0f;
    static int sModeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Initialize Buttons, TextToSpeech, Animation
        //set OnClickListeners for Buttons
        //mTTS = new TextToSpeech(this, this);
        mAnimationBlendIn = AnimationUtils.loadAnimation(this, R.anim.blend_in);
        mAnimationBlinking = AnimationUtils.loadAnimation(this, R.anim.blinking);
        mHigh = (TextView) findViewById(R.id.highscore_text_view);
        mSwitch = (Switch) findViewById(R.id.switch_tutorial_mode);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mSwitch.setText("Tutorial Mode  ");
                    sModeID = 1;
                } else {
                    mSwitch.setText("Normal Mode  ");
                    sModeID = 0;
                }
            }
        });

        mSound = new Sound();
        mSound.initSounds(this);

        Button mNewGameButton = (Button) findViewById(R.id.new_game_button);
        mNewGameButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSound.playSound(mSound.getSoundIDnewgame(), mVol);
                //start GameActivity.java
                Intent newGameIntent = new Intent(MainMenuActivity.this,
                        GameActivity.class);
                startActivityForResult(newGameIntent, 1);
            }
        });

        Button mTutorialButton = (Button) findViewById(R.id.tutorial_button);
        mTutorialButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSound.playSound(mSound.getSoundIDbutton(), mVol);
                //start TutorialActivity.java
                Intent tutorialIntent = new Intent(MainMenuActivity.this,
                        TutorialActivity.class);
                startActivity(tutorialIntent);
            }
        });

        Button mGamesoundsButton = (Button) findViewById(R.id.gamesounds_button);
        mGamesoundsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSound.playSound(mSound.getSoundIDbutton(), mVol);
                //start GamesoundsActivity.java
                Intent gamesoundsIntent = new Intent(MainMenuActivity.this,
                        GamesoundsActivity.class);
                startActivity(gamesoundsIntent);
            }
        });

        Button mResetHighscoreButton = (Button) findViewById(R.id.reset_highscore_button);
        mResetHighscoreButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSound.playSound(mSound.getSoundIDbutton(), mVol);
                DialogFragment mDialog = new ResetHighscore();
                mDialog.show(getFragmentManager(), "DialogFragment");
            }
        });
    }

    public void onUserPositiveClick() {
        deleteHighscore();
    }

    /*
    @Override
    public void onInit(int status) {
        mTTS.setLanguage(Locale.US);
    }
    */

    @Override
    public void onResume() {
        super.onResume();
        View v = findViewById(R.id.root_constraint_layout_main_menu);
        v.startAnimation(mAnimationBlendIn);
        mHigh.setText(String.format(Locale.getDefault(), "%d", readHighscore()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && sModeID == 0) {
            if(resultCode > readHighscore()) {
                writeHighscore(resultCode);
                mHigh.startAnimation(mAnimationBlinking);
            }
        }
    }

    private void writeHighscore(int highscore) {
        SharedPreferences sharedPref = getSharedPreferences("com.johanneswolfgruber.learntolisten",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.highscore), highscore);
        editor.apply();
    }

    private int readHighscore() {
        SharedPreferences sharedPref = getSharedPreferences("com.johanneswolfgruber.learntolisten",
                        Context.MODE_PRIVATE);
        return sharedPref.getInt(getString(R.string.highscore), 0);
    }

    private void deleteHighscore() {
        SharedPreferences sharedPref = getSharedPreferences("com.johanneswolfgruber.learntolisten",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(getString(R.string.highscore));
        editor.apply();
        onResume();
    }
}
