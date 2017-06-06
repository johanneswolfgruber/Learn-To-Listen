package com.johanneswolfgruber.learntolisten;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private ImageButton mUpButton, mDownButton, mUpButton2, mDownButton2, mLeftButton, mRightButton;
    private boolean mUpClickable = false, mDownClickable = false,
            mLeftClickable = false, mRightClickable = true;
    //private int mUpButtonID = 0, mDownButtonID = 0, mLeftButtonID = 0, mRightButtonID = 0;
    private int mIndex = 0;
    private int mClickID = 0;
    private TextToSpeech mTTS;
    private Animation mAnimationBlendIn;
    private Animation mAnimationRotate;
    private ImageView mIcon;
    private CountDownTimer mTimer;
    private TextView mTimeRemainingTextView, mScoreTextView, mLevelTextView;
    private Dialog mGameOverDialog;
    private Dialog mNextLevelDialog;
    private int mRandHighLow, mRandInterval, mRandInversion, mRandIntervalHighLow, mRandMajorMinor;
    private int[] highLowIDs, intervalIDs, inversionsIDs, intervalHighLowIDs, majorMinorIDs;
    private int mPoints = 0;
    private int mLevelID = 1;
    private Sound mSound;
    private Vibrator mVibrator;
    private Level mLevel;
    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        mTTS = new TextToSpeech(this, this);
        mSound = new Sound();
        mSound.initSounds(this);
        initSoundIDs();
        mLevel = new Level();
        startLevel(mLevelID);

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            hideBars();
        }
    }

    private void hideBars() {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }


    private void startLevel(int level) {
        //Methods for Initializing
        setTitle(String.format(Locale.getDefault(), "Level %d", mLevelID));

        initAnimations();
        initWidgets();
        initTimer();
        hideBars();

        mLevelTextView.setText(String.format(Locale.getDefault(), "Level %d", mLevelID));
        mIndex = 0;
        mClickID = 0;

        mLevel.setLevelIndex(level);

        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpClickable){
                    mIndex += mLevel.numberOfFieldsPerRow();
                    //Toast mToast = Toast.makeText(GameActivity.this, ""+mIndex, Toast.LENGTH_SHORT);
                    //mToast.show();
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                    mDownClickable = true;
                    mDownButton.setAlpha(1.0f);
                    mDownButton2.setAlpha(1.0f);
                }
            }
        });

        mDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDownClickable){
                    mIndex -= mLevel.numberOfFieldsPerRow();
                    //Toast mToast = Toast.makeText(GameActivity.this, ""+mIndex, Toast.LENGTH_SHORT);
                    //mToast.show();
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                    mUpClickable = true;
                    mUpButton.setAlpha(1.0f);
                    mUpButton2.setAlpha(1.0f);
                }
            }
        });

        mUpButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpClickable){
                    mIndex += mLevel.numberOfFieldsPerRow();
                    //Toast mToast = Toast.makeText(GameActivity.this, ""+mIndex, Toast.LENGTH_SHORT);
                    //mToast.show();
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                    mDownClickable = true;
                    mDownButton.setAlpha(1.0f);
                    mDownButton2.setAlpha(1.0f);
                }
            }
        });

        mDownButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDownClickable){
                    mIndex -= mLevel.numberOfFieldsPerRow();
                    //Toast mToast = Toast.makeText(GameActivity.this, ""+mIndex, Toast.LENGTH_SHORT);
                    //mToast.show();
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                    mUpClickable = true;
                    mUpButton.setAlpha(1.0f);
                    mUpButton2.setAlpha(1.0f);
                }
            }
        });

        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLeftClickable){
                    mIndex--;
                    //Toast mToast = Toast.makeText(GameActivity.this, ""+mIndex, Toast.LENGTH_SHORT);
                    //mToast.show();
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                }
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRightClickable) {
                    mIndex++;
                    //Toast mToast = Toast.makeText(GameActivity.this, ""+mIndex, Toast.LENGTH_SHORT);
                    //mToast.show();
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                }
            }
        });
    }

    private void testAnswerHighLow(String answer) {
        if(Objects.equals(answer, "Higher") && (mRandHighLow == mSound.getSoundIDhigher1() ||
                mRandHighLow == mSound.getSoundIDhigher2() ||
                mRandHighLow == mSound.getSoundIDhigher3())) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Lower") && (mRandHighLow == mSound.getSoundIDlower1() ||
                mRandHighLow == mSound.getSoundIDlower2() ||
                mRandHighLow == mSound.getSoundIDlower3())) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer());
        }
    }

    private void testAnswerInterval(String answer){
        if(Objects.equals(answer, "Perfect Unison") && mRandInterval == mSound.getSoundIDunison()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Second") &&
                mRandInterval == mSound.getSoundIDminorSecond()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Second") &&
                mRandInterval == mSound.getSoundIDmajorSecond()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Third") &&
                mRandInterval == mSound.getSoundIDminorThird()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Third") &&
                mRandInterval == mSound.getSoundIDmajorThird()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Perfect Fourth") &&
                mRandInterval == mSound.getSoundIDfourth()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Tritone") &&
                mRandInterval == mSound.getSoundIDtritone()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Perfect Fifth") &&
                mRandInterval == mSound.getSoundIDfifth()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Sixth") &&
                mRandInterval == mSound.getSoundIDminorSixth()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Sixth") &&
                mRandInterval == mSound.getSoundIDmajorSixth()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Seventh") &&
                mRandInterval == mSound.getSoundIDminorSeventh()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Seventh") &&
                mRandInterval == mSound.getSoundIDmajorSeventh()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Perfect Octave") &&
                mRandInterval == mSound.getSoundIDoctave()){
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer());
        }


    }

    private void testAnswerInversions(String answer) {
        if (Objects.equals(answer, "Common chord") &&
                mRandInversion == mSound.getSoundIDcommon_chord()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 200;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "First inversion") &&
                mRandInversion == mSound.getSoundIDfirst_inversion()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 200;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Second inversion") &&
                mRandInversion == mSound.getSoundIDsecond_inversion()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 200;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else {
            Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer());
        }
    }

    private void testAnswerMajorMinor(String answer) {
        if (Objects.equals(answer, "Major") &&
                mRandMajorMinor == mSound.getSoundIDmajor1()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Minor") &&
                mRandMajorMinor == mSound.getSoundIDminor1()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Augmented") &&
                mRandMajorMinor == mSound.getSoundIDaugmented1()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Diminished") &&
                mRandMajorMinor == mSound.getSoundIDdiminished1()) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else {
            Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer());
        }
    }

    private void testAnswerIntervalHigherLower(String answer) {
        if(Objects.equals(answer, "Bigger") && (mRandIntervalHighLow == mSound.getSoundIDintervalHigher1() ||
                mRandIntervalHighLow == mSound.getSoundIDintervalHigher2() ||
                mRandIntervalHighLow == mSound.getSoundIDintervalHigher3())) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Smaller") && (mRandIntervalHighLow == mSound.getSoundIDintervalLower1() ||
                mRandIntervalHighLow == mSound.getSoundIDintervalLower2() ||
                mRandIntervalHighLow == mSound.getSoundIDintervalLower3())) {
            Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer());
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer());
        }
    }

    @SuppressWarnings("deprecation")
    public void switchKey(String currentFieldValue){
        switch (currentFieldValue) {
            case "LEFT_W":
                mSound.getSoundPool().play(mSound.getSoundIDWall(), 1.0f, 0.0f, 0, 0, 1);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = false;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(.2f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = false;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(.2f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = false;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(.2f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "HIGH_LOW":
                mSound.getSoundPool().play(mSound.getSoundIDExercise(), 1.0f, 1.0f, 0, 0, 1);
                mIcon.startAnimation(mAnimationRotate);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandHighLow = highLowIDs[randNumber(highLowIDs.length)];
                            mSound.getSoundPool().play(mRandHighLow, 1.0f, 1.0f, 0, 0, 1);
                            DialogFragment mDialog = new HigherLowerDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mLevel.setCurrentFieldValue(mIndex);
                            mClickID = 1;
                        }
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "INTERVAL":
                mSound.getSoundPool().play(mSound.getSoundIDExercise(), 1.0f, 1.0f, 0, 0, 1);
                mIcon.startAnimation(mAnimationRotate);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandInterval = intervalIDs[randNumber(intervalIDs.length)];
                            mSound.playSound(mRandInterval);
                            DialogFragment mDialog = new IntervalDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mLevel.setCurrentFieldValue(mIndex);
                            mClickID = 1;
                        }
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "INVERSION":
                mSound.getSoundPool().play(mSound.getSoundIDExercise(), 1.0f, 1.0f, 0, 0, 1);
                mIcon.startAnimation(mAnimationRotate);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandInversion = inversionsIDs[randNumber(inversionsIDs.length)];
                            mSound.getSoundPool().play(mRandInversion, 1.0f, 1.0f, 0, 0, 1);
                            DialogFragment mDialog = new InversionsDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mLevel.setCurrentFieldValue(mIndex);
                            mClickID = 1;
                        }
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "INT_HIGH_LOW":
                mSound.getSoundPool().play(mSound.getSoundIDExercise(), 1.0f, 1.0f, 0, 0, 1);
                mIcon.startAnimation(mAnimationRotate);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandIntervalHighLow =
                                    intervalHighLowIDs[randNumber(intervalHighLowIDs.length)];
                            mSound.getSoundPool().play(mRandIntervalHighLow, 1.0f, 1.0f, 0, 0, 1);
                            DialogFragment mDialog = new IntervalHigherLowerDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mLevel.setCurrentFieldValue(mIndex);
                            mClickID = 1;
                        }
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "MAJOR_MINOR":
                mSound.getSoundPool().play(mSound.getSoundIDExercise(), 1.0f, 1.0f, 0, 0, 1);
                mIcon.startAnimation(mAnimationRotate);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandMajorMinor = majorMinorIDs[randNumber(majorMinorIDs.length)];
                            mSound.getSoundPool().play(mRandMajorMinor, 1.0f, 1.0f, 0, 0, 1);
                            DialogFragment mDialog = new MajorMinorDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mLevel.setCurrentFieldValue(mIndex);
                            mClickID = 1;
                        }
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "LADDER":
                mTTS.speak("LADDER", TextToSpeech.QUEUE_FLUSH, null);
                //Sound.getSoundPool().play(Sound.getSoundIDLadder(), 1.0f, 1.0f, 0, 0, 1);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = true;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(1.0f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(1.0f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = true;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(1.0f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(1.0f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = true;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(1.0f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(1.0f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "RIGHT_W":
                mSound.getSoundPool().play(mSound.getSoundIDWall(), 0.0f, 1.0f, 0, 0, 1);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = false;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(.2f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = false;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(.2f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = false;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(.2f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "EMPTY":
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow()) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(mIndex - mLevel.numberOfFieldsPerRow()),
                            "LADDER")) {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = true;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(1.0f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(1.0f);
                    } else {
                        mLeftClickable = true;
                        mRightClickable = true;
                        mUpClickable = false;
                        mDownClickable = false;
                        mLeftButton.setAlpha(1.0f);
                        mRightButton.setAlpha(1.0f);
                        mUpButton.setAlpha(.2f);
                        mDownButton.setAlpha(.2f);
                        mUpButton2.setAlpha(.2f);
                        mDownButton2.setAlpha(.2f);
                    }
                } else {
                    mLeftClickable = true;
                    mRightClickable = true;
                    mUpClickable = false;
                    mDownClickable = false;
                    mLeftButton.setAlpha(1.0f);
                    mRightButton.setAlpha(1.0f);
                    mUpButton.setAlpha(.2f);
                    mDownButton.setAlpha(.2f);
                    mUpButton2.setAlpha(.2f);
                    mDownButton2.setAlpha(.2f);
                }
                break;
            case "FINISH":
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                mTimer.cancel();
                String mRemainingTime = mTimeRemainingTextView.getText().toString();
                int mRemainingTimeBonusPoints = Integer.parseInt(mRemainingTime)*10;
                mPoints += mRemainingTimeBonusPoints;
                mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
                setResult(mPoints);
                mLeftClickable = false;
                mRightClickable = false;
                mUpClickable = false;
                mDownClickable = false;
                mLeftButton.setAlpha(.2f);
                mRightButton.setAlpha(.2f);
                mUpButton.setAlpha(.2f);
                mDownButton.setAlpha(.2f);
                mUpButton2.setAlpha(.2f);
                mDownButton2.setAlpha(.2f);
                mVibrator.vibrate(500);
                mLevelID += 1;
                if (mLevelID < 3) {
                    mNextLevelDialog = new Dialog(GameActivity.this,
                            R.style.DialogTheme);
                    mNextLevelDialog.setContentView(R.layout.level_dialog);
                    mNextLevelDialog.show();
                    TextView mLevelNumber = (TextView) mNextLevelDialog.
                            findViewById(R.id.level_finished_text_view);
                    mLevelNumber.setText(String.format(Locale.getDefault(), "LEVEL %d FINISHED!",
                            mLevelID-1));
                    TextView mCurrentScore = (TextView) mNextLevelDialog.
                            findViewById(R.id.current_level_score);
                    mCurrentScore.setText(String.format(Locale.getDefault(), "Score: %d", mPoints));
                    Button mMainButton3 = (Button) mNextLevelDialog.
                            findViewById(R.id.main_menu_button3);
                    Button mNextLevelButton = (Button) mNextLevelDialog.
                            findViewById(R.id.next_level_button);
                    mMainButton3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    mNextLevelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startLevel(mLevelID);
                            mNextLevelDialog.dismiss();
                        }
                    });
                } else {
                    Dialog mWinnerDialog = new Dialog(GameActivity.this,
                            R.style.DialogTheme);
                    mWinnerDialog.setContentView(R.layout.winning);
                    mWinnerDialog.show();
                    TextView mFinishScore = (TextView) mWinnerDialog.
                            findViewById(R.id.finished_score);
                    mFinishScore.setText(String.format(Locale.getDefault(), "Score: %d", mPoints));
                    Button mMainButton = (Button) mWinnerDialog.findViewById(R.id.main_menu_button);
                    mMainButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
                break;
        }
    }

    private static int randNumber(int n){
        Random random = new Random();
        return random.nextInt(n);
    }

    private void initAnimations(){
        mAnimationBlendIn = AnimationUtils.loadAnimation(this, R.anim.blend_in);
        mAnimationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
    }

    private void initWidgets(){
        mTimeRemainingTextView = (TextView) findViewById(R.id.time_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
        mLevelTextView = (TextView) findViewById(R.id.level_text_view);

        mIcon = (ImageView) findViewById(R.id.image_view_icon_big);

        mUpButton = (ImageButton) findViewById(R.id.up_button);
        mDownButton = (ImageButton) findViewById(R.id.down_button);
        mUpButton2 = (ImageButton) findViewById(R.id.up_button2);
        mDownButton2 = (ImageButton) findViewById(R.id.down_button2);
        mLeftButton = (ImageButton) findViewById(R.id.left_button);
        mRightButton = (ImageButton) findViewById(R.id.right_button);

        mUpButton.setAlpha(.2f);
        mDownButton.setAlpha(.2f);
        mUpButton2.setAlpha(.2f);
        mDownButton2.setAlpha(.2f);
        mLeftButton.setAlpha(.2f);
        mRightButton.setAlpha(1.0f);
        mRightClickable = true;

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mDecorView = getWindow().getDecorView();
    }

    private void initTimer(){
        mTimer = new CountDownTimer(mLevel.getLevelTime(mLevelID), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeRemainingTextView.setText(String.format(Locale.getDefault(),
                        "%d", millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                setResult(mPoints);
                mTimeRemainingTextView.setText(R.string.time_up_string);
                mGameOverDialog = new Dialog(GameActivity.this,
                        R.style.DialogTheme);
                mGameOverDialog.setContentView(R.layout.gameover);
                mGameOverDialog.show();
                TextView mGameOverScore = (TextView) mGameOverDialog.
                        findViewById(R.id.gameover_score);
                mGameOverScore.setText(String.format(Locale.getDefault(), "Score: %d", mPoints));
                Button mMainButton2 = (Button) mGameOverDialog.findViewById(R.id.main_menu_button2);
                mMainButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }.start();
    }

    private void initSoundIDs() {
        highLowIDs = new int[]{mSound.getSoundIDhigher1(), mSound.getSoundIDhigher2(),
                mSound.getSoundIDhigher3(), mSound.getSoundIDlower1(),
                mSound.getSoundIDlower2(), mSound.getSoundIDlower3()};

        intervalIDs = new int[]{mSound.getSoundIDunison(), mSound.getSoundIDminorSecond(),
                mSound.getSoundIDmajorSecond(), mSound.getSoundIDminorThird(),
                mSound.getSoundIDmajorThird(), mSound.getSoundIDfourth(),
                mSound.getSoundIDtritone(), mSound.getSoundIDfifth(),
                mSound.getSoundIDminorSixth(), mSound.getSoundIDmajorSixth(),
                mSound.getSoundIDminorSeventh(), mSound.getSoundIDmajorSeventh(),
                mSound.getSoundIDoctave()};

        inversionsIDs = new int[]{mSound.getSoundIDcommon_chord(),
                mSound.getSoundIDfirst_inversion(), mSound.getSoundIDsecond_inversion()};

        intervalHighLowIDs = new int[]{mSound.getSoundIDintervalHigher1(),
                mSound.getSoundIDintervalHigher2(), mSound.getSoundIDintervalHigher3(),
                mSound.getSoundIDintervalLower1(), mSound.getSoundIDintervalLower2(),
                mSound.getSoundIDintervalLower3()};

        majorMinorIDs = new int[]{mSound.getSoundIDmajor1(), mSound.getSoundIDminor1(),
                mSound.getSoundIDaugmented1(), mSound.getSoundIDdiminished1()};
    }

    @Override
    public void onInit(int status) {
        mTTS.setLanguage(Locale.US);
    }

    @Override
    public void onResume() {
        super.onResume();
        View v = findViewById(R.id.root_constraint_layout_game);
        v.startAnimation(mAnimationBlendIn);
        hideBars();
    }

    @Override
    public void onDestroy(){
        mSound.getSoundPool().release();
        super.onDestroy();
    }

    @Override
    public void onPause(){
        super.onPause();
        hideBars();
    }

    public void onUserSelectValue(String answer) {
        switch (answer) {
            case "Higher":
            case "Lower":
                testAnswerHighLow(answer);
                break;
            case "Perfect Unison":
            case "Minor Second":
            case "Major Second":
            case "Minor Third":
            case "Major Third":
            case "Perfect Fourth":
            case "Tritone":
            case "Perfect Fifth":
            case "Minor Sixth":
            case "Major Sixth":
            case "Minor Seventh":
            case "Major Seventh":
            case "Perfect Octave":
                testAnswerInterval(answer);
                break;
            case "Common chord":
            case "First inversion":
            case "Second inversion":
                testAnswerInversions(answer);
                break;
            case "Major":
            case "Minor":
            case "Augmented":
            case "Diminished":
                testAnswerMajorMinor(answer);
                break;
            case "Bigger":
            case "Smaller":
                testAnswerIntervalHigherLower(answer);
                break;
        }
    }
}
