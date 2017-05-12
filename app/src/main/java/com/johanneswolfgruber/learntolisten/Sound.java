package com.johanneswolfgruber.learntolisten;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

class Sound {
    private SoundPool mSoundPool;
    private int soundIDSteps, soundIDWall, soundIDExercise, soundIDLadder, soundIDDoor,
            soundIDhigher1, soundIDhigher2, soundIDhigher3, soundIDlower1, soundIDlower2,
            soundIDlower3, soundIDLadderUp, soundIDLadderDown, soundIDRightAnswer,
            soundIDWrongAnswer, soundIDunison, soundIDminorSecond, soundIDmajorSecond,
            soundIDminorThird, soundIDmajorThird, soundIDfourth, soundIDtritone, soundIDfifth,
            soundIDminorSixth, soundIDmajorSixth, soundIDminorSeventh, soundIDmajorSeventh,
            soundIDoctave, soundIDcommon_chord, soundIDfirst_inversion, soundIDsecond_inversion,
            soundIDmajor1, soundIDminor1, soundIDdiminished1, soundIDaugmented1,
            soundIDintervalHigher1, soundIDintervalHigher2, soundIDintervalHigher3,
            soundIDintervalLower1, soundIDintervalLower2, soundIDintervalLower3;

    @SuppressWarnings("deprecation")
    void initSounds(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .build();
        } else {
            mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        soundIDSteps = mSoundPool.load(context, R.raw.steps, 0);
        soundIDWall = mSoundPool.load(context, R.raw.wall, 1);
        soundIDDoor = mSoundPool.load(context, R.raw.door_knock, 1);
        soundIDExercise = mSoundPool.load(context, R.raw.exercise, 1);
        soundIDLadder = mSoundPool.load(context, R.raw.ladder_up, 1);
        soundIDhigher1 = mSoundPool.load(context, R.raw.higher1, 0);
        soundIDhigher2 = mSoundPool.load(context, R.raw.higher2, 0);
        soundIDhigher3 = mSoundPool.load(context, R.raw.higher3, 0);
        soundIDlower1 = mSoundPool.load(context, R.raw.lower1, 0);
        soundIDlower2 = mSoundPool.load(context, R.raw.lower2, 0);
        soundIDlower3 = mSoundPool.load(context, R.raw.lower3, 0);
        soundIDLadderUp = mSoundPool.load(context, R.raw.ladder_up, 0);
        soundIDLadderDown = mSoundPool.load(context, R.raw.ladder_down, 0);
        soundIDRightAnswer = mSoundPool.load(context, R.raw.right_answer, 0);
        soundIDWrongAnswer = mSoundPool.load(context, R.raw.wrong_answer, 0);
        soundIDunison = mSoundPool.load(context, R.raw.perfect_unison, 0);
        soundIDminorSecond = mSoundPool.load(context, R.raw.minor_second, 0);
        soundIDmajorSecond = mSoundPool.load(context, R.raw.major_second, 0);
        soundIDminorThird = mSoundPool.load(context, R.raw.minor_third, 0);
        soundIDmajorThird = mSoundPool.load(context, R.raw.major_third, 0);
        soundIDfourth = mSoundPool.load(context, R.raw.perfect_fourth, 0);
        soundIDtritone = mSoundPool.load(context, R.raw.tritone, 0);
        soundIDfifth = mSoundPool.load(context, R.raw.perfect_fifth, 0);
        soundIDminorSixth = mSoundPool.load(context, R.raw.minor_sixth, 0);
        soundIDmajorSixth = mSoundPool.load(context, R.raw.major_sixth, 0);
        soundIDminorSeventh = mSoundPool.load(context, R.raw.minor_seventh, 0);
        soundIDmajorSeventh = mSoundPool.load(context, R.raw.major_seventh, 0);
        soundIDoctave = mSoundPool.load(context, R.raw.perfect_octave, 0);
        soundIDcommon_chord = mSoundPool.load(context, R.raw.common_chord, 0);
        soundIDfirst_inversion = mSoundPool.load(context, R.raw.first_inversion, 0);
        soundIDsecond_inversion = mSoundPool.load(context, R.raw.second_inversion, 0);
        soundIDmajor1 = mSoundPool.load(context, R.raw.major1, 0);
        soundIDminor1 = mSoundPool.load(context, R.raw.minor1, 0);
        soundIDdiminished1 = mSoundPool.load(context, R.raw.diminished1, 0);
        soundIDaugmented1 = mSoundPool.load(context, R.raw.augmented1, 0);
        soundIDintervalHigher1 = mSoundPool.load(context, R.raw.interval_higher1, 0);
        soundIDintervalHigher2 = mSoundPool.load(context, R.raw.interval_higher2, 0);
        soundIDintervalHigher3 = mSoundPool.load(context, R.raw.interval_higher3, 0);
        soundIDintervalLower1 = mSoundPool.load(context, R.raw.interval_lower1, 0);
        soundIDintervalLower2 = mSoundPool.load(context, R.raw.interval_lower2, 0);
        soundIDintervalLower3 = mSoundPool.load(context, R.raw.interval_lower3, 0);
    }

    void playSound(int soundID){
        mSoundPool.play(soundID, 1.0f, 1.0f, 0, 0, 1);
    }

    int getSoundIDSteps() {
        return soundIDSteps;
    }

    int getSoundIDWall() {
        return soundIDWall;
    }

    int getSoundIDExercise() {
        return soundIDExercise;
    }

    int getSoundIDLadder() {
        return soundIDLadder;
    }

    int getSoundIDDoor() {
        return soundIDDoor;
    }

    SoundPool getSoundPool() {
        return mSoundPool;
    }

    int getSoundIDhigher1() {
        return soundIDhigher1;
    }

    int getSoundIDhigher2() {
        return soundIDhigher2;
    }

    int getSoundIDhigher3() {
        return soundIDhigher3;
    }

    int getSoundIDlower1() {
        return soundIDlower1;
    }

    int getSoundIDlower2() {
        return soundIDlower2;
    }

    int getSoundIDlower3() {
        return soundIDlower3;
    }

    int getSoundIDLadderUp() {
        return soundIDLadderUp;
    }

    int getSoundIDLadderDown() {
        return soundIDLadderDown;
    }

    int getSoundIDRightAnswer() {
        return soundIDRightAnswer;
    }

    int getSoundIDWrongAnswer() {
        return soundIDWrongAnswer;
    }

    int getSoundIDunison() {
        return soundIDunison;
    }

    int getSoundIDminorSecond() {
        return soundIDminorSecond;
    }

    int getSoundIDmajorSecond() {
        return soundIDmajorSecond;
    }

    int getSoundIDminorThird() {
        return soundIDminorThird;
    }

    int getSoundIDmajorThird() {
        return soundIDmajorThird;
    }

    int getSoundIDfourth() {
        return soundIDfourth;
    }

    int getSoundIDtritone() {
        return soundIDtritone;
    }

    int getSoundIDfifth() {
        return soundIDfifth;
    }

    int getSoundIDminorSixth() {
        return soundIDminorSixth;
    }

    int getSoundIDmajorSixth() {
        return soundIDmajorSixth;
    }

    int getSoundIDminorSeventh() {
        return soundIDminorSeventh;
    }

    int getSoundIDmajorSeventh() {
        return soundIDmajorSeventh;
    }

    int getSoundIDoctave() {
        return soundIDoctave;
    }

    int getSoundIDcommon_chord() {
        return soundIDcommon_chord;
    }

    int getSoundIDfirst_inversion() {
        return soundIDfirst_inversion;
    }

    int getSoundIDsecond_inversion() {
        return soundIDsecond_inversion;
    }

    int getSoundIDmajor1() {
        return soundIDmajor1;
    }

    int getSoundIDminor1() {
        return soundIDminor1;
    }

    int getSoundIDdiminished1() {
        return soundIDdiminished1;
    }

    int getSoundIDaugmented1() {
        return soundIDaugmented1;
    }

    int getSoundIDintervalHigher1() {
        return soundIDintervalHigher1;
    }

    int getSoundIDintervalHigher2() {
        return soundIDintervalHigher2;
    }

    int getSoundIDintervalHigher3() {
        return soundIDintervalHigher3;
    }

    int getSoundIDintervalLower1() {
        return soundIDintervalLower1;
    }

    int getSoundIDintervalLower2() {
        return soundIDintervalLower2;
    }

    int getSoundIDintervalLower3() {
        return soundIDintervalLower3;
    }
}
