package com.johanneswolfgruber.learntolisten;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
//import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("ConstantConditions")
public class GameActivity extends AppCompatActivity{
    private ImageButton mUpButton, mDownButton, mUpButton2, mDownButton2, mLeftButton, mRightButton;
    private boolean mUpClickable = false, mDownClickable = false,
            mLeftClickable = false, mRightClickable = true;
    private int mIndex = 0;
    private int mClickID = 0;
    private int mTempPoints = 0;
    private int mField = 0;
    private int mRight = 0;
    private int mWrong = 0;
    private int mDoorID = 0;
    //private TextToSpeech mTTS;
    private Animation mAnimationBlendIn, mAnimationBlink, mAnimationBlinking;
    private ImageView mIcon;
    private CountDownTimer mTimer;
    private TextView mTimeRemainingTextView, mScoreTextView, mLevelTextView, mFieldCounter,
            mExerciseCounter;
    private Dialog mGameOverDialog, mNextLevelDialog;
    private int mRandInterval, mRandInversion, mRandIntervalHighLow, mRandMajorMinor,
            mRandSeashoreHighLow, mRandSeashoreLoudQuiet, mRandSeashoreLongShort;
    private int[] intervalIDs, inversionsIDs, intervalHighLowIDs, majorMinorIDs,
            seashoreHighLowIDs, seashoreLoudQuietIDs, seashoreLongShortIDs;
    private static String[] sLevelOne, sLevelTwo, sLevelThree;
    private int mPoints = 0;
    private int mLevelID = 1;
    private Sound mSound;
    private Vibrator mVibrator;
    private Level mLevel;
    private final float VOL_EXERCISE = 1.0f;
    private final float VOL_GAME = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        //mTTS = new TextToSpeech(this, this);
        mSound = MainMenuActivity.getSounds();
        //mSound.initSounds(getApplicationContext());
        initSoundIDs();
        mLevel = new Level();
        startLevel(mLevelID);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        hideBars();
    }

    private void hideBars() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    static String[] getLevelOne() {
        return sLevelOne;
    }

    static String[] getLevelTwo() {
        return sLevelTwo;
    }

    static String[] getLevelThree() {
        return sLevelThree;
    }

    private void startLevel(int level) {
        hideBars();
        //Methods for Initializing
        sLevelOne = getResources().getStringArray(R.array.LEVEL_ONE);
        sLevelTwo = getResources().getStringArray(R.array.LEVEL_TWO);
        sLevelThree = getResources().getStringArray(R.array.LEVEL_THREE);

        initAnimations();
        initWidgets();
        if(MainMenuActivity.sModeID == 0) {
            initTimer();
        } else {
            mTimeRemainingTextView.setText(R.string.infinity);
        }

        mLevelTextView.setText(String.format(Locale.getDefault(), "Level %d", mLevelID));
        mIndex = 0;
        mClickID = 0;

        mLevel.setLevelIndex(level);

        mField = mLevel.getFieldCount(level);
        mRight = mLevel.getExerciseCount(level);
        mWrong = 0;
        mDoorID = 0;

        fieldCounter();
        exerciseCounter();

        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpClickable){
                    mIndex += mLevel.numberOfFieldsPerRow(mLevelID);
                    mSound.playSound(mSound.getSoundIDSteps(), VOL_GAME);
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
                    mIndex -= mLevel.numberOfFieldsPerRow(mLevelID);
                    mSound.playSound(mSound.getSoundIDSteps(), VOL_GAME);
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
                    mIndex += mLevel.numberOfFieldsPerRow(mLevelID);
                    mSound.playSound(mSound.getSoundIDSteps(), VOL_GAME);
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
                    mIndex -= mLevel.numberOfFieldsPerRow(mLevelID);
                    mSound.playSound(mSound.getSoundIDSteps(), VOL_GAME);
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
                    mSound.playSound(mSound.getSoundIDSteps(), VOL_GAME);
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
                    mSound.playSound(mSound.getSoundIDSteps(), VOL_GAME);
                    String mCurrentFieldValue = mLevel.getCurrentFieldValue(mIndex);
                    switchKey(mCurrentFieldValue);
                }
            }
        });
    }

    private void testAnswerInterval(String answer){
        if(Objects.equals(answer, "Perfect Unison") && (
                mRandInterval == mSound.getSoundIDunisonC3() ||
                mRandInterval == mSound.getSoundIDunisonFis2() ||
                mRandInterval == mSound.getSoundIDunisonCis3() ||
                mRandInterval == mSound.getSoundIDunisonF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Second") && (
                mRandInterval == mSound.getSoundIDminorSecondC3() ||
                mRandInterval == mSound.getSoundIDminorSecondFis2() ||
                mRandInterval == mSound.getSoundIDminorSecondCis3() ||
                mRandInterval == mSound.getSoundIDminorSecondF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Second") && (
                mRandInterval == mSound.getSoundIDmajorSecondC3() ||
                mRandInterval == mSound.getSoundIDmajorSecondFis2() ||
                mRandInterval == mSound.getSoundIDmajorSecondCis3() ||
                mRandInterval == mSound.getSoundIDmajorSecondC3())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Third") && (
                mRandInterval == mSound.getSoundIDminorThirdC3() ||
                mRandInterval == mSound.getSoundIDminorThirdFis2() ||
                mRandInterval == mSound.getSoundIDminorThirdCis3() ||
                mRandInterval == mSound.getSoundIDminorThirdF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Third") && (
                mRandInterval == mSound.getSoundIDmajorThirdC3() ||
                mRandInterval == mSound.getSoundIDmajorThirdFis2() ||
                mRandInterval == mSound.getSoundIDmajorThirdCis3() ||
                mRandInterval == mSound.getSoundIDmajorThirdF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Perfect Fourth") && (
                mRandInterval == mSound.getSoundIDfourthC3() ||
                mRandInterval == mSound.getSoundIDfourthFis2() ||
                mRandInterval == mSound.getSoundIDfourthCis3() ||
                mRandInterval == mSound.getSoundIDfourthF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Tritone") && (
                mRandInterval == mSound.getSoundIDtritoneC3() ||
                mRandInterval == mSound.getSoundIDtritoneFis2() ||
                mRandInterval == mSound.getSoundIDtritoneCis3() ||
                mRandInterval == mSound.getSoundIDtritoneF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Perfect Fifth") && (
                mRandInterval == mSound.getSoundIDfifthC3() ||
                mRandInterval == mSound.getSoundIDfifthFis2() ||
                mRandInterval == mSound.getSoundIDfifthCis3() ||
                mRandInterval == mSound.getSoundIDfifthF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Sixth") && (
                mRandInterval == mSound.getSoundIDminorSixthC3() ||
                mRandInterval == mSound.getSoundIDminorSixthFis2() ||
                mRandInterval == mSound.getSoundIDminorSixthCis3() ||
                mRandInterval == mSound.getSoundIDminorSixthF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Sixth") && (
                mRandInterval == mSound.getSoundIDmajorSixthC3() ||
                mRandInterval == mSound.getSoundIDmajorSixthFis2() ||
                mRandInterval == mSound.getSoundIDmajorSixthCis3() ||
                mRandInterval == mSound.getSoundIDmajorSixthF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Minor Seventh") && (
                mRandInterval == mSound.getSoundIDminorSeventhC3() ||
                mRandInterval == mSound.getSoundIDminorSeventhFis2() ||
                mRandInterval == mSound.getSoundIDminorSeventhCis3() ||
                mRandInterval == mSound.getSoundIDminorSeventhF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Major Seventh") && (
                mRandInterval == mSound.getSoundIDmajorSeventhC3() ||
                mRandInterval == mSound.getSoundIDmajorSeventhFis2() ||
                mRandInterval == mSound.getSoundIDmajorSeventhCis3() ||
                mRandInterval == mSound.getSoundIDmajorSeventhF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Perfect Octave") && (
                mRandInterval == mSound.getSoundIDoctaveC3() ||
                mRandInterval == mSound.getSoundIDoctaveFis2() ||
                mRandInterval == mSound.getSoundIDoctaveCis3() ||
                mRandInterval == mSound.getSoundIDoctaveF2())){
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 500;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            //Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }


    }

    private void testAnswerInversions(String answer) {
        if (Objects.equals(answer, "Common chord") && (
                    mRandInversion == mSound.getSoundIDcommon_chord() ||
                    mRandInversion == mSound.getSoundIDcommon_chordA2() ||
                    mRandInversion == mSound.getSoundIDcommon_chordDis3() ||
                    mRandInversion == mSound.getSoundIDcommon_chord_minA2() ||
                    mRandInversion == mSound.getSoundIDcommon_chord_minDis3() ||
                    mRandInversion == mSound.getSoundIDcommon_chord_min())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 200;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "First inversion") && (
                    mRandInversion == mSound.getSoundIDfirst_inversion() ||
                    mRandInversion == mSound.getSoundIDfirst_inversionA2() ||
                    mRandInversion == mSound.getSoundIDfirst_inversionDis3() ||
                    mRandInversion == mSound.getSoundIDfirst_inversion_minA2() ||
                    mRandInversion == mSound.getSoundIDfirst_inversion_minDis3() ||
                    mRandInversion == mSound.getSoundIDfirst_inversion_min())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 200;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Second inversion") && (
                    mRandInversion == mSound.getSoundIDsecond_inversion() ||
                    mRandInversion == mSound.getSoundIDsecond_inversionA2() ||
                            mRandInversion == mSound.getSoundIDsecond_inversionDis3() ||
                            mRandInversion == mSound.getSoundIDsecond_inversion_minA2() ||
                            mRandInversion == mSound.getSoundIDsecond_inversion_minDis3() ||
                    mRandInversion == mSound.getSoundIDsecond_inversion_min())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 200;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            //Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }
    }

    private void testAnswerMajorMinor(String answer) {
        if (Objects.equals(answer, "Major") && (
                mRandMajorMinor == mSound.getSoundIDmajor1() ||
                mRandMajorMinor == mSound.getSoundIDmajorA2() ||
                mRandMajorMinor == mSound.getSoundIDmajorDis3())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Minor") && (
                mRandMajorMinor == mSound.getSoundIDminor1() ||
                mRandMajorMinor == mSound.getSoundIDminorA2() ||
                mRandMajorMinor == mSound.getSoundIDminorDis3())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Augmented") && (
                mRandMajorMinor == mSound.getSoundIDaugmented1() ||
                mRandMajorMinor == mSound.getSoundIDaugmentedA2() ||
                mRandMajorMinor == mSound.getSoundIDaugmentedDis3())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else if (Objects.equals(answer, "Diminished") && (
                mRandMajorMinor == mSound.getSoundIDdiminished1() ||
                mRandMajorMinor == mSound.getSoundIDdiminishedA2() ||
                mRandMajorMinor == mSound.getSoundIDdiminishedDis3())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 150;
            mScoreTextView.setText(String.format(Locale.getDefault(), "%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            //Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }
    }

    private void testAnswerIntervalHigherLower(String answer) {
        if(Objects.equals(answer, "Bigger") && (
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher1() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher2() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher3() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher4() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher5() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher6() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher7() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher8() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher9() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalHigher10())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Smaller") && (
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower1() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower2() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower3() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower4() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower5() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower6() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower7() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower8() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower9() ||
                    mRandIntervalHighLow == mSound.getSoundIDintervalLower10())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            //Toast.makeText(GameActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            //Toast.makeText(GameActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }
    }

    private void testAnswerSeashoreHighLow(String answer) {
        if(Objects.equals(answer, "Higher") &&
                (mRandSeashoreHighLow == mSound.getSoundIDhigher())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Lower") &&
                (mRandSeashoreHighLow == mSound.getSoundIDlower() )) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }
    }

    private void testAnswerSeashoreLongShort(String answer) {
        if(Objects.equals(answer, "Longer") &&
                (mRandSeashoreLongShort == mSound.getSoundIDlonger())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Shorter") &&
                (mRandSeashoreLongShort == mSound.getSoundIDshorter() )) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }
    }

    private void testAnswerSeashoreLoudQuiet(String answer) {
        if(Objects.equals(answer, "Louder") &&
                (mRandSeashoreLoudQuiet == mSound.getSoundIDlouder())) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else if(Objects.equals(answer, "Quieter") &&
                (mRandSeashoreLoudQuiet == mSound.getSoundIDquieter() )) {
            mLevel.setCurrentFieldValue(mIndex, "CORRECT");
            mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
            exerciseCounter();
            mSound.playSound(mSound.getSoundIDRightAnswer(), VOL_GAME);
            mPoints += 100;
            mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
        } else {
            mLevel.setCurrentFieldValue(mIndex, "WRONG");
            mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
            wrongCounter();
            mSound.playSound(mSound.getSoundIDWrongAnswer(), VOL_GAME);
        }
    }

    public void switchKey(String currentFieldValue){
        switch (currentFieldValue) {
            case "LEFT_W":
                mSound.playSound(mSound.getSoundIDwallLeft(), VOL_GAME);
                mIcon.setImageResource(android.R.color.transparent);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                rightClickable();
                fieldCounter();
                break;
            case "INTERVAL":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandInterval = intervalIDs[randNumber(intervalIDs.length)];
                            mSound.playSound(mRandInterval, VOL_EXERCISE);
                            DialogFragment mDialog = new IntervalDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "INVERSION":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandInversion = inversionsIDs[randNumber(inversionsIDs.length)];
                            mSound.playSound(mRandInversion, VOL_EXERCISE);
                            DialogFragment mDialog = new InversionsDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "INT_HIGH_LOW":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandIntervalHighLow =
                                    intervalHighLowIDs[randNumber(intervalHighLowIDs.length)];
                            mSound.playSound(mRandIntervalHighLow, VOL_EXERCISE);
                            DialogFragment mDialog = new IntervalHigherLowerDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "MAJOR_MINOR":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandMajorMinor = majorMinorIDs[randNumber(majorMinorIDs.length)];
                            mSound.playSound(mRandMajorMinor, VOL_EXERCISE);
                            DialogFragment mDialog = new MajorMinorDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "HIGH_LOW_SEASHORE":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandSeashoreHighLow =
                                    seashoreHighLowIDs[randNumber(seashoreHighLowIDs.length)];
                            mSound.playSound(mRandSeashoreHighLow, VOL_EXERCISE);
                            DialogFragment mDialog = new SeashoreHighLowDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "LONG_SHORT_SEASHORE":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandSeashoreLongShort =
                                    seashoreLongShortIDs[randNumber(seashoreLongShortIDs.length)];
                            mSound.playSound(mRandSeashoreLongShort, VOL_EXERCISE);
                            DialogFragment mDialog = new SeashoreLongShortDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "LOUD_QUIET_SEASHORE":
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDexerciseDown(), VOL_GAME);
                } else {
                    mSound.playSound(mSound.getSoundIDExercise(), VOL_GAME);
                }
                mIcon.setImageResource(R.drawable.ic_audiotrack_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mClickID == 0) {
                            mRandSeashoreLoudQuiet =
                                    seashoreLoudQuietIDs[randNumber(seashoreLoudQuietIDs.length)];
                            mSound.playSound(mRandSeashoreLoudQuiet, VOL_EXERCISE);
                            DialogFragment mDialog = new SeashoreLoudQuietDialogFragment();
                            mDialog.show(getFragmentManager(), "DialogFragment");
                            mClickID = 1;
                        }
                    }
                });
                fieldCounter();
                break;
            case "LADDER":
                mSound.playSound(mSound.getSoundIDLadder(), VOL_GAME);
                mIcon.setImageResource(R.drawable.ic_arrow_upward_black_48dp);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(mIndex > mLevel.numberOfFieldsPerRow(mLevelID)) {
                    if (Objects.equals(mLevel.getCurrentFieldValue(
                            mIndex - mLevel.numberOfFieldsPerRow(mLevelID)), "LADDER")) {
                        allClickable();
                    } else {
                        allButDownClickable();
                    }
                } else {
                    allButDownClickable();
                }
                fieldCounter();
                break;
            case "DOOR":
                mSound.playSound(mSound.getSoundIDDoor(), VOL_GAME);
                mIcon.setImageResource(R.drawable.ic_lock_black_48dp);
                mIcon.startAnimation(mAnimationBlinking);
                mVibrator.vibrate(100);
                mClickID = 0;
                nothingClickable();
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTempPoints = mPoints;
                        if(mClickID == 0) {
                            if(mDoorID <= 2) {
                                int mRandExercise = randNumber(6);
                                startExerciseDoor(mRandExercise);
                            } else {
                                mClickID = 1;
                            }
                        }
                    }
                });
                fieldCounter();
                break;
            case "DOOR_LOCKED":
                mSound.playSound(mSound.getSoundIDDoor(), VOL_GAME);
                mIcon.setImageResource(R.drawable.ic_lock_black_48dp);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                leftClickable();
                mTempPoints = mPoints;
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                fieldCounter();
                break;
            case "DOOR_UNLOCKED":
                mIcon.setImageResource(R.drawable.ic_lock_open_black_48dp);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDLadderDown(), VOL_GAME);
                }
                fieldCounter();
                break;
            case "CORRECT":
                mIcon.setImageResource(R.drawable.ic_event_available_black_48dp);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDLadderDown(), VOL_GAME);
                }
                fieldCounter();
                break;
            case "WRONG":
                mIcon.setImageResource(R.drawable.ic_event_busy_black_48dp);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDLadderDown(), VOL_GAME);
                }
                fieldCounter();
                break;
            case "RIGHT_W":
                mSound.playSound(mSound.getSoundIDwallRight(), VOL_GAME);
                mIcon.setImageResource(android.R.color.transparent);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                leftClickable();
                fieldCounter();
                break;
            case "EMPTY":
                mIcon.setImageResource(android.R.color.transparent);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDLadderDown(), VOL_GAME);
                }
                fieldCounter();
                break;
            case "FINISH":
                mIcon.setImageResource(android.R.color.transparent);
                mIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                if(MainMenuActivity.sModeID == 0) {
                    mTimer.cancel();
                    String mRemainingTime = mTimeRemainingTextView.getText().toString();
                    int mRemainingTimeBonusPoints = Integer.parseInt(mRemainingTime)*10;
                    mPoints += mRemainingTimeBonusPoints;
                }
                mSound.playSound(mSound.getSoundIDlevelfinish(), VOL_GAME);
                mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
                setResult(mPoints);
                nothingClickable();
                fieldCounter();
                mVibrator.vibrate(500);
                mLevelID += 1;
                if (mLevelID < 4) {
                    mNextLevelDialog = new Dialog(GameActivity.this,
                            R.style.DialogTheme);
                    mNextLevelDialog.setContentView(R.layout.level_dialog);
                    mNextLevelDialog.getWindow().addFlags(
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    mNextLevelDialog.show();
                    TextView mLevelNumber = (TextView) mNextLevelDialog.
                            findViewById(R.id.level_finished_text_view);
                    mLevelNumber.setText(String.format(Locale.getDefault(), "LEVEL %d FINISHED!",
                            mLevelID-1));
                    TextView mCurrentScore = (TextView) mNextLevelDialog.
                            findViewById(R.id.current_level_score);
                    mCurrentScore.setText(String.format(Locale.getDefault(), "Score: %d", mPoints));
                    TextView mSolvedText = (TextView) mNextLevelDialog.findViewById(R.id.solved);
                    mSolvedText.setText(String.format(Locale.getDefault(), "Solved: %d",
                            mLevel.getExerciseCount(mLevelID-1)-mRight-1));
                    TextView mWrongText = (TextView) mNextLevelDialog.findViewById(R.id.wrong);
                    mWrongText.setText(String.format(Locale.getDefault(), "Wrong: %d", mWrong));
                    TextView mNotSolvedText = (TextView) mNextLevelDialog.
                            findViewById(R.id.not_solved);
                    mNotSolvedText.setText(String.format(Locale.getDefault(), "Not Solved: %d",
                            mRight-mWrong));
                    Button mMainButton3 = (Button) mNextLevelDialog.
                            findViewById(R.id.main_menu_button3);
                    Button mNextLevelButton = (Button) mNextLevelDialog.
                            findViewById(R.id.next_level_button);
                    mMainButton3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSound.playSound(mSound.getSoundIDbutton(), VOL_GAME);
                            finish();
                        }
                    });
                    mNextLevelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSound.playSound(mSound.getSoundIDbutton(), VOL_GAME);
                            startLevel(mLevelID);
                            mNextLevelDialog.dismiss();
                        }
                    });
                } else {
                    Dialog mWinnerDialog = new Dialog(GameActivity.this,
                            R.style.DialogTheme);
                    mWinnerDialog.setContentView(R.layout.winning);
                    mWinnerDialog.getWindow().addFlags(
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    mWinnerDialog.show();
                    TextView mFinishScore = (TextView) mWinnerDialog.
                            findViewById(R.id.finished_score);
                    mFinishScore.setText(String.format(Locale.getDefault(), "Score: %d", mPoints));
                    TextView mSolvedText = (TextView) mWinnerDialog.findViewById(R.id.solved2);
                    mSolvedText.setText(String.format(Locale.getDefault(), "Solved: %d",
                            mLevel.getExerciseCount(mLevelID-1)-mRight-1));
                    TextView mWrongText = (TextView) mWinnerDialog.findViewById(R.id.wrong2);
                    mWrongText.setText(String.format(Locale.getDefault(), "Wrong: %d", mWrong));
                    TextView mNotSolvedText = (TextView) mWinnerDialog.
                            findViewById(R.id.not_solved2);
                    mNotSolvedText.setText(String.format(Locale.getDefault(), "Not Solved: %d",
                            mRight-mWrong));
                    Button mMainButton = (Button) mWinnerDialog.findViewById(R.id.main_menu_button);
                    mMainButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSound.playSound(mSound.getSoundIDbutton(), VOL_GAME);
                            finish();
                        }
                    });
                }
                break;
        }
    }

    private boolean checkForLadder() {
        if(mIndex > mLevel.numberOfFieldsPerRow(mLevelID)) {
            if (Objects.equals(mLevel.getCurrentFieldValue(
                    mIndex - mLevel.numberOfFieldsPerRow(mLevelID)), "LADDER")) {
                allButUpClickable();
                return true;
            } else {
                leftAndRightClickable();
                return false;
            }
        } else {
            leftAndRightClickable();
            return false;
        }
    }

    private static int randNumber(int n){
        Random random = new Random();
        return random.nextInt(n);
    }

    private void initAnimations(){
        mAnimationBlendIn = AnimationUtils.loadAnimation(this, R.anim.blend_in);/**/
        mAnimationBlink = AnimationUtils.loadAnimation(this, R.anim.blink);
        mAnimationBlinking = AnimationUtils.loadAnimation(this, R.anim.blinking);
    }

    private void initWidgets(){
        mTimeRemainingTextView = (TextView) findViewById(R.id.time_text_view);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
        mLevelTextView = (TextView) findViewById(R.id.level_text_view);
        mFieldCounter = (TextView) findViewById(R.id.fieldcount);
        mExerciseCounter = (TextView) findViewById(R.id.exercisecount);

        mIcon = (ImageView) findViewById(R.id.image_view_icon_big);
        mIcon.setImageResource(android.R.color.transparent);

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
    }

    private void initTimer(){
        mTimer = new CountDownTimer(mLevel.getLevelTime(mLevelID), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeRemainingTextView.setText(String.format(Locale.getDefault(),
                        "%d", millisUntilFinished/1000));
                if(millisUntilFinished/1000 <= 10) {
                    mTimeRemainingTextView.startAnimation(mAnimationBlink);
                    mSound.playSound(mSound.getSoundIDcountdown(), VOL_GAME);
                }

            }

            @Override
            public void onFinish() {
                setResult(mPoints);
                mSound.playSound(mSound.getSoundIDgameover(), VOL_GAME);
                mGameOverDialog = new Dialog(GameActivity.this,
                        R.style.DialogTheme);
                mGameOverDialog.setContentView(R.layout.gameover);
                mGameOverDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
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
        intervalIDs = new int[]{mSound.getSoundIDunisonC3(), mSound.getSoundIDminorSecondC3(),
                mSound.getSoundIDmajorSecondC3(), mSound.getSoundIDminorThirdC3(),
                mSound.getSoundIDmajorThirdC3(), mSound.getSoundIDfourthC3(),
                mSound.getSoundIDtritoneC3(), mSound.getSoundIDfifthC3(),
                mSound.getSoundIDminorSixthC3(), mSound.getSoundIDmajorSixthC3(),
                mSound.getSoundIDminorSeventhC3(), mSound.getSoundIDmajorSeventhC3(),
                mSound.getSoundIDoctaveC3(), mSound.getSoundIDunisonCis3(), mSound.getSoundIDminorSecondCis3(),
                mSound.getSoundIDmajorSecondCis3(), mSound.getSoundIDminorThirdCis3(),
                mSound.getSoundIDmajorThirdCis3(), mSound.getSoundIDfourthCis3(),
                mSound.getSoundIDtritoneCis3(), mSound.getSoundIDfifthCis3(),
                mSound.getSoundIDminorSixthCis3(), mSound.getSoundIDmajorSixthCis3(),
                mSound.getSoundIDminorSeventhCis3(), mSound.getSoundIDmajorSeventhCis3(),
                mSound.getSoundIDoctaveCis3(), mSound.getSoundIDunisonF2(), mSound.getSoundIDminorSecondF2(),
                mSound.getSoundIDmajorSecondF2(), mSound.getSoundIDminorThirdF2(),
                mSound.getSoundIDmajorThirdF2(), mSound.getSoundIDfourthF2(),
                mSound.getSoundIDtritoneF2(), mSound.getSoundIDfifthF2(),
                mSound.getSoundIDminorSixthF2(), mSound.getSoundIDmajorSixthF2(),
                mSound.getSoundIDminorSeventhF2(), mSound.getSoundIDmajorSeventhF2(),
                mSound.getSoundIDoctaveF2(), mSound.getSoundIDunisonFis2(), mSound.getSoundIDminorSecondFis2(),
                mSound.getSoundIDmajorSecondFis2(), mSound.getSoundIDminorThirdFis2(),
                mSound.getSoundIDmajorThirdFis2(), mSound.getSoundIDfourthFis2(),
                mSound.getSoundIDtritoneFis2(), mSound.getSoundIDfifthFis2(),
                mSound.getSoundIDminorSixthFis2(), mSound.getSoundIDmajorSixthFis2(),
                mSound.getSoundIDminorSeventhFis2(), mSound.getSoundIDmajorSeventhFis2(),
                mSound.getSoundIDoctaveFis2()};

        inversionsIDs = new int[]{mSound.getSoundIDcommon_chord(),
                mSound.getSoundIDfirst_inversion(), mSound.getSoundIDsecond_inversion(),
                mSound.getSoundIDcommon_chord_min(), mSound.getSoundIDfirst_inversion_min(),
                mSound.getSoundIDsecond_inversion_min(), mSound.getSoundIDcommon_chordA2(),
                mSound.getSoundIDfirst_inversionA2(), mSound.getSoundIDsecond_inversionA2(),
                mSound.getSoundIDcommon_chord_minA2(), mSound.getSoundIDfirst_inversion_minA2(),
                mSound.getSoundIDsecond_inversion_minA2(), mSound.getSoundIDcommon_chordDis3(),
                mSound.getSoundIDfirst_inversionDis3(), mSound.getSoundIDsecond_inversionDis3(),
                mSound.getSoundIDcommon_chord_minDis3(), mSound.getSoundIDfirst_inversion_minDis3(),
                mSound.getSoundIDsecond_inversion_minDis3()};

        intervalHighLowIDs = new int[]{mSound.getSoundIDintervalHigher1(),
                mSound.getSoundIDintervalHigher2(), mSound.getSoundIDintervalHigher3(),
                mSound.getSoundIDintervalHigher4(), mSound.getSoundIDintervalHigher5(),
                mSound.getSoundIDintervalHigher6(), mSound.getSoundIDintervalHigher7(),
                mSound.getSoundIDintervalHigher8(), mSound.getSoundIDintervalHigher9(),
                mSound.getSoundIDintervalHigher10(), mSound.getSoundIDintervalLower1(),
                mSound.getSoundIDintervalLower2(), mSound.getSoundIDintervalLower3(),
                mSound.getSoundIDintervalLower4(), mSound.getSoundIDintervalLower5(),
                mSound.getSoundIDintervalLower6(), mSound.getSoundIDintervalLower7(),
                mSound.getSoundIDintervalLower8(), mSound.getSoundIDintervalLower9(),
                mSound.getSoundIDintervalLower10()};

        majorMinorIDs = new int[]{mSound.getSoundIDmajor1(), mSound.getSoundIDminor1(),
                mSound.getSoundIDaugmented1(), mSound.getSoundIDdiminished1(),
                mSound.getSoundIDmajorA2(), mSound.getSoundIDminorA2(),
                mSound.getSoundIDaugmentedA2(), mSound.getSoundIDdiminishedA2(),
                mSound.getSoundIDmajorDis3(), mSound.getSoundIDminorDis3(),
                mSound.getSoundIDaugmentedDis3(), mSound.getSoundIDdiminishedDis3()};

        seashoreHighLowIDs = new int[]{mSound.getSoundIDhigher(), mSound.getSoundIDlower()};

        seashoreLongShortIDs = new int[]{mSound.getSoundIDlonger(), mSound.getSoundIDshorter()};

        seashoreLoudQuietIDs = new int[]{mSound.getSoundIDlouder(), mSound.getSoundIDquieter()};
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
        mSound = MainMenuActivity.getSounds();
        View v = findViewById(R.id.root_constraint_layout_game);
        v.startAnimation(mAnimationBlendIn);
        hideBars();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mTimer.cancel();
    }

    @Override
    public void onPause(){
        super.onPause();
        hideBars();
        mTimer.cancel();
    }

    @Override
    public void onStop(){
        super.onStop();
        mTimer.cancel();
    }

    public void onUserSelectValue(String answer) {
        switch (answer) {
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
            case "Higher":
            case "Lower":
                testAnswerSeashoreHighLow(answer);
                break;
            case "Louder":
            case "Quieter":
                testAnswerSeashoreLoudQuiet(answer);
                break;
            case "Longer":
            case "Shorter":
                testAnswerSeashoreLongShort(answer);
                break;
        }
    }

    public void onUserDismissedDialog() {
        if(mPoints > mTempPoints) {
            if(mDoorID == 2) {
                mIcon.setImageResource(R.drawable.ic_lock_open_black_48dp);
                mLevel.setCurrentFieldValue(mIndex, "DOOR_UNLOCKED");
                mPoints += 500;
                mScoreTextView.setText(String.format(Locale.getDefault(),"%d", mPoints));
                if(checkForLadder()) {
                    mSound.playSound(mSound.getSoundIDLadderDown(), VOL_GAME);
                }
                mClickID = 1;
            } else {
                mIcon.startAnimation(mAnimationBlinking);
                mDoorID += 1;
            }
        } else {
            mDoorID = 3;
            mLevel.setCurrentFieldValue(mIndex, "DOOR_LOCKED");
            leftClickable();
        }
    }

    private void leftClickable() {
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

    private void rightClickable() {
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

    private void leftAndRightClickable() {
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

    private void allButUpClickable() {
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
    }

    private void allButDownClickable() {
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

    private void nothingClickable() {
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
    }

    private void allClickable() {
        mLeftClickable = true;
        mRightClickable = true;
        mUpClickable = true;
        mDownClickable = true;
        mLeftButton.setAlpha(1.0f);
        mRightButton.setAlpha(1.0f);
        mUpButton.setAlpha(1.0f);
        mDownButton.setAlpha(1.0f);
        mUpButton2.setAlpha(1.0f);
        mDownButton2.setAlpha(1.0f);
    }

    private void fieldCounter() {
        mField -= mIndex;
        mFieldCounter.setText(String.format(Locale.getDefault(), "Field: %d", mField));
        if(mField == 1) {
            mSound.playSound(mSound.getSoundIDfinish(), VOL_GAME);
        }
        mField = mLevel.getFieldCount(mLevelID);
    }

    private void exerciseCounter() {
        mRight -= 1;
        mExerciseCounter.setText(String.format(Locale.getDefault(), "Quests: %d", mRight));
    }

    private void wrongCounter() {
        mWrong += 1;
    }

    void startExerciseDoor(int exercise) {
        mRandInterval = intervalIDs[randNumber(intervalIDs.length)];
        mRandInversion = inversionsIDs[randNumber(inversionsIDs.length)];
        mRandIntervalHighLow =
                intervalHighLowIDs[randNumber(intervalHighLowIDs.length)];
        mRandMajorMinor = majorMinorIDs[randNumber(majorMinorIDs.length)];
        mRandSeashoreHighLow = seashoreHighLowIDs[randNumber(seashoreHighLowIDs.length)];
        mRandSeashoreLoudQuiet = seashoreLoudQuietIDs[randNumber(seashoreLoudQuietIDs.length)];
        mRandSeashoreLongShort = seashoreLongShortIDs[randNumber(seashoreLongShortIDs.length)];
        switch (exercise) {
            case 0:
                mSound.playSound(mRandSeashoreLoudQuiet, VOL_EXERCISE);
                DialogFragment mDialog0 = new DoorDialog0Fragment();
                mDialog0.show(getFragmentManager(), "DialogFragment");
                break;
            case 1:
                mSound.playSound(mRandInterval, VOL_EXERCISE);
                DialogFragment mDialog1 = new DoorDialog1Fragment();
                mDialog1.show(getFragmentManager(), "DialogFragment");
                break;
            case 2:
                mSound.playSound(mRandInversion, VOL_EXERCISE);
                DialogFragment mDialog2 = new DoorDialog2Fragment();
                mDialog2.show(getFragmentManager(), "DialogFragment");
                break;
            case 3:
                mSound.playSound(mRandIntervalHighLow, VOL_EXERCISE);
                DialogFragment mDialog3 = new DoorDialog3Fragment();
                mDialog3.show(getFragmentManager(), "DialogFragment");
                break;
            case 4:
                mSound.playSound(mRandMajorMinor, VOL_EXERCISE);
                DialogFragment mDialog4 = new DoorDialog4Fragment();
                mDialog4.show(getFragmentManager(), "DialogFragment");
                break;
            case 5:
                mSound.playSound(mRandSeashoreHighLow, VOL_EXERCISE);
                DialogFragment mDialog5 = new DoorDialog5Fragment();
                mDialog5.show(getFragmentManager(), "DialogFragment");
                break;
            case 6:
                mSound.playSound(mRandSeashoreLongShort, VOL_EXERCISE);
                DialogFragment mDialog6 = new DoorDialog6Fragment();
                mDialog6.show(getFragmentManager(), "DialogFragment");
                break;
        }
    }
}
