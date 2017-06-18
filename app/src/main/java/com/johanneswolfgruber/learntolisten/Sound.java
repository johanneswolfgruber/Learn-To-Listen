package com.johanneswolfgruber.learntolisten;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

class Sound {
    private SoundPool mSoundPool;
    private int soundIDSteps, soundIDwallLeft, soundIDExercise, soundIDLadder, soundIDDoor,
            soundIDLadderDown, soundIDhigher1, soundIDhigher2, soundIDhigher3, soundIDlower1,
            soundIDlower2, soundIDlower3, soundIDRightAnswer, soundIDWrongAnswer, soundIDunisonC3,
            soundIDminorSecondC3, soundIDmajorSecondC3, soundIDminorThirdC3, soundIDmajorThirdC3,
            soundIDfourthC3, soundIDtritoneC3, soundIDfifthC3, soundIDminorSixthC3, soundIDmajorSixthC3,
            soundIDminorSeventhC3, soundIDmajorSeventhC3, soundIDoctaveC3, soundIDunisonCis3,
            soundIDminorSecondCis3, soundIDmajorSecondCis3, soundIDminorThirdCis3, soundIDmajorThirdCis3,
            soundIDfourthCis3, soundIDtritoneCis3, soundIDfifthCis3, soundIDminorSixthCis3, soundIDmajorSixthCis3,
            soundIDminorSeventhCis3, soundIDmajorSeventhCis3, soundIDoctaveCis3, soundIDcommon_chord,
            soundIDfirst_inversion, soundIDsecond_inversion, soundIDcommon_chord_min,
            soundIDfirst_inversion_min, soundIDsecond_inversion_min, soundIDmajor1, soundIDminor1,
            soundIDdiminished1, soundIDaugmented1, soundIDintervalHigher1, soundIDintervalHigher2,
            soundIDintervalHigher3, soundIDintervalHigher4, soundIDintervalHigher5,
            soundIDintervalHigher6, soundIDintervalHigher7, soundIDintervalHigher8,
            soundIDintervalHigher9, soundIDintervalHigher10, soundIDintervalLower1,
            soundIDintervalLower2, soundIDintervalLower3, soundIDintervalLower4,
            soundIDintervalLower5, soundIDintervalLower6, soundIDintervalLower7,
            soundIDintervalLower8, soundIDintervalLower9, soundIDintervalLower10,
            soundIDgameover, soundIDlevelfinish, soundIDnewgame,
            soundIDbutton, soundIDwallRight, soundIDcountdown, soundIDexerciseDown,
            soundIDfinish, soundIDlouder, soundIDquieter, soundIDlonger, soundIDshorter,
            soundIDhigher, soundIDlower;

    
    void initSounds(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(attributes)
                    .build();

            loadSounds(context);
        } else {
            mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            loadSounds(context);
        }


    }

    private void loadSounds(Context context) {
        // Gamesounds
        soundIDSteps = mSoundPool.load(context, R.raw.steps, 0);
        soundIDwallLeft = mSoundPool.load(context, R.raw.left_wall, 0);
        soundIDwallRight = mSoundPool.load(context, R.raw.right_wall, 0);
        soundIDDoor = mSoundPool.load(context, R.raw.door, 0);
        soundIDExercise = mSoundPool.load(context, R.raw.exercise, 0);
        soundIDexerciseDown = mSoundPool.load(context, R.raw.exrercise_down, 0);
        soundIDLadder = mSoundPool.load(context, R.raw.ladder, 0);
        soundIDLadderDown = mSoundPool.load(context, R.raw.ladder_down, 0);
        soundIDRightAnswer = mSoundPool.load(context, R.raw.right, 0);
        soundIDWrongAnswer = mSoundPool.load(context, R.raw.wrong, 0);
        soundIDgameover = mSoundPool.load(context, R.raw.gameover, 0);
        soundIDlevelfinish = mSoundPool.load(context, R.raw.level_finished, 0);
        soundIDnewgame = mSoundPool.load(context, R.raw.new_game, 0);
        soundIDbutton = mSoundPool.load(context, R.raw.button, 0);
        soundIDcountdown = mSoundPool.load(context, R.raw.countdown, 0);
        soundIDfinish = mSoundPool.load(context, R.raw.finish, 0);

        // High_Low
        soundIDhigher1 = mSoundPool.load(context, R.raw.higher1_root_c3, 0);
        soundIDhigher2 = mSoundPool.load(context, R.raw.higher2_root_c3, 0);
        soundIDhigher3 = mSoundPool.load(context, R.raw.higher3_root_c3, 0);
        soundIDlower1 = mSoundPool.load(context, R.raw.lower1_root_c3, 0);
        soundIDlower2 = mSoundPool.load(context, R.raw.lower2_root_c3, 0);
        soundIDlower3 = mSoundPool.load(context, R.raw.lower3_root_c3, 0);

        // Seashore
        soundIDlouder = mSoundPool.load(context, R.raw.louder, 0);
        soundIDquieter = mSoundPool.load(context, R.raw.quieter, 0);
        soundIDlonger = mSoundPool.load(context, R.raw.longer, 0);
        soundIDshorter = mSoundPool.load(context, R.raw.shorter, 0);
        soundIDhigher = mSoundPool.load(context, R.raw.higher, 0);
        soundIDlower = mSoundPool.load(context, R.raw.lower, 0);

        // Interval
        soundIDunisonC3 = mSoundPool.load(context, R.raw.perfect_unison_root_c3, 0);
        soundIDminorSecondC3 = mSoundPool.load(context, R.raw.minor_second_root_c3, 0);
        soundIDmajorSecondC3 = mSoundPool.load(context, R.raw.major_second_root_c3, 0);
        soundIDminorThirdC3 = mSoundPool.load(context, R.raw.minor_third_root_c3, 0);
        soundIDmajorThirdC3 = mSoundPool.load(context, R.raw.major_third_root_c3, 0);
        soundIDfourthC3 = mSoundPool.load(context, R.raw.perfect_fourth_root_c3, 0);
        soundIDtritoneC3 = mSoundPool.load(context, R.raw.tritone_root_c3, 0);
        soundIDfifthC3 = mSoundPool.load(context, R.raw.perfect_fifth_root_c3, 0);
        soundIDminorSixthC3 = mSoundPool.load(context, R.raw.minor_sixth_root_c3, 0);
        soundIDmajorSixthC3 = mSoundPool.load(context, R.raw.major_sixth_root_c3, 0);
        soundIDminorSeventhC3 = mSoundPool.load(context, R.raw.minor_seventh_root_c3, 0);
        soundIDmajorSeventhC3 = mSoundPool.load(context, R.raw.major_seventh_root_c3, 0);
        soundIDoctaveC3 = mSoundPool.load(context, R.raw.perfect_octave_root_c3, 0);

        soundIDunisonCis3 = mSoundPool.load(context, R.raw.perfect_unison_root_cis3, 0);
        soundIDminorSecondCis3 = mSoundPool.load(context, R.raw.minor_second_root_cis3, 0);
        soundIDmajorSecondCis3 = mSoundPool.load(context, R.raw.major_second_root_cis3, 0);
        soundIDminorThirdCis3 = mSoundPool.load(context, R.raw.minor_third_root_cis3, 0);
        soundIDmajorThirdCis3 = mSoundPool.load(context, R.raw.major_third_root_cis3, 0);
        soundIDfourthCis3 = mSoundPool.load(context, R.raw.perfect_fourth_root_cis3, 0);
        soundIDtritoneCis3 = mSoundPool.load(context, R.raw.tritone_root_cis3, 0);
        soundIDfifthCis3 = mSoundPool.load(context, R.raw.perfect_fifth_root_cis3, 0);
        soundIDminorSixthCis3 = mSoundPool.load(context, R.raw.minor_sixth_root_cis3, 0);
        soundIDmajorSixthCis3 = mSoundPool.load(context, R.raw.major_sixth_root_cis3, 0);
        soundIDminorSeventhCis3 = mSoundPool.load(context, R.raw.minor_seventh_root_cis3, 0);
        soundIDmajorSeventhCis3 = mSoundPool.load(context, R.raw.major_seventh_root_cis3, 0);
        soundIDoctaveCis3 = mSoundPool.load(context, R.raw.perfect_octave_root_cis3, 0);

        // Inversions
        soundIDcommon_chord = mSoundPool.load(context, R.raw.common_chord_root_c3, 0);
        soundIDfirst_inversion = mSoundPool.load(context, R.raw.first_inversion_root_c3, 0);
        soundIDsecond_inversion = mSoundPool.load(context, R.raw.second_inversion_root_c3, 0);
        soundIDcommon_chord_min = mSoundPool.load(context, R.raw.common_chord_min_root_c3, 0);
        soundIDfirst_inversion_min = mSoundPool.load(context, R.raw.first_inversion_min_root_c3, 0);
        soundIDsecond_inversion_min = mSoundPool.load(context, R.raw.second_inversion_min_root_c3, 0);

        // Major_Minor
        soundIDmajor1 = mSoundPool.load(context, R.raw.major1_root_c3, 0);
        soundIDminor1 = mSoundPool.load(context, R.raw.minor1_root_c3, 0);
        soundIDdiminished1 = mSoundPool.load(context, R.raw.diminished1_root_c3, 0);
        soundIDaugmented1 = mSoundPool.load(context, R.raw.augmented1_root_c3, 0);

        // High_Low_Interval
        soundIDintervalHigher1 = mSoundPool.load(context, R.raw.interval_higher1_root_c3, 0);
        soundIDintervalHigher2 = mSoundPool.load(context, R.raw.interval_higher2_root_c3, 0);
        soundIDintervalHigher3 = mSoundPool.load(context, R.raw.interval_higher3_root_c3, 0);
        soundIDintervalHigher4 = mSoundPool.load(context, R.raw.interval_higher4_root_c3, 0);
        soundIDintervalHigher5 = mSoundPool.load(context, R.raw.interval_higher5_root_c3, 0);
        soundIDintervalHigher6 = mSoundPool.load(context, R.raw.interval_higher6_root_c3, 0);
        soundIDintervalHigher7 = mSoundPool.load(context, R.raw.interval_higher7_root_c3, 0);
        soundIDintervalHigher8 = mSoundPool.load(context, R.raw.interval_higher8_root_c3, 0);
        soundIDintervalHigher9 = mSoundPool.load(context, R.raw.interval_higher9_root_c3, 0);
        soundIDintervalHigher10 = mSoundPool.load(context, R.raw.interval_higher10_root_c3, 0);
        soundIDintervalLower1 = mSoundPool.load(context, R.raw.interval_lower1_root_c3, 0);
        soundIDintervalLower2 = mSoundPool.load(context, R.raw.interval_lower2_root_c3, 0);
        soundIDintervalLower3 = mSoundPool.load(context, R.raw.interval_lower3_root_c3, 0);
        soundIDintervalLower4 = mSoundPool.load(context, R.raw.interval_lower4_root_c3, 0);
        soundIDintervalLower5 = mSoundPool.load(context, R.raw.interval_lower5_root_c3, 0);
        soundIDintervalLower6 = mSoundPool.load(context, R.raw.interval_lower6_root_c3, 0);
        soundIDintervalLower7 = mSoundPool.load(context, R.raw.interval_lower7_root_c3, 0);
        soundIDintervalLower8 = mSoundPool.load(context, R.raw.interval_lower8_root_c3, 0);
        soundIDintervalLower9 = mSoundPool.load(context, R.raw.interval_lower9_root_c3, 0);
        soundIDintervalLower10 = mSoundPool.load(context, R.raw.interval_lower10_root_c3, 0);
    }

    void playSound(int soundID, float Vol){
        mSoundPool.play(soundID, Vol, Vol, 0, 0, 1);
    }

    SoundPool getSoundPool() {
        return mSoundPool;
    }

    int getSoundIDSteps() {
        return soundIDSteps;
    }

    int getSoundIDgameover() {
        return soundIDgameover;
    }

    int getSoundIDlevelfinish() {
        return soundIDlevelfinish;
    }

    int getSoundIDnewgame() {
        return soundIDnewgame;
    }

    int getSoundIDbutton() {
        return soundIDbutton;
    }

    int getSoundIDwallLeft() {
        return soundIDwallLeft;
    }

    int getSoundIDwallRight() {
        return soundIDwallRight;
    }

    int getSoundIDExercise() {
        return soundIDExercise;
    }

    int getSoundIDexerciseDown() {
        return soundIDexerciseDown;
    }

    int getSoundIDLadder() {
        return soundIDLadder;
    }

    int getSoundIDDoor() {
        return soundIDDoor;
    }

    int getSoundIDcountdown() {
        return soundIDcountdown;
    }

    int getSoundIDfinish() {
        return soundIDfinish;
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

    int getSoundIDlouder() {
        return soundIDlouder;
    }

    int getSoundIDquieter() {
        return soundIDquieter;
    }

    int getSoundIDlonger() {
        return soundIDlonger;
    }

    int getSoundIDshorter() {
        return soundIDshorter;
    }

    int getSoundIDhigher() {
        return soundIDhigher;
    }

    int getSoundIDlower() {
        return soundIDlower;
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

    int getSoundIDunisonC3() {
        return soundIDunisonC3;
    }

    int getSoundIDminorSecondC3() {
        return soundIDminorSecondC3;
    }

    int getSoundIDmajorSecondC3() {
        return soundIDmajorSecondC3;
    }

    int getSoundIDminorThirdC3() {
        return soundIDminorThirdC3;
    }

    int getSoundIDmajorThirdC3() {
        return soundIDmajorThirdC3;
    }

    int getSoundIDfourthC3() {
        return soundIDfourthC3;
    }

    int getSoundIDtritoneC3() {
        return soundIDtritoneC3;
    }

    int getSoundIDfifthC3() {
        return soundIDfifthC3;
    }

    int getSoundIDminorSixthC3() {
        return soundIDminorSixthC3;
    }

    int getSoundIDmajorSixthC3() {
        return soundIDmajorSixthC3;
    }

    int getSoundIDminorSeventhC3() {
        return soundIDminorSeventhC3;
    }

    int getSoundIDmajorSeventhC3() {
        return soundIDmajorSeventhC3;
    }

    int getSoundIDoctaveC3() {
        return soundIDoctaveC3;
    }

    int getSoundIDunisonCis3() {
        return soundIDunisonCis3;
    }

    int getSoundIDminorSecondCis3() {
        return soundIDminorSecondCis3;
    }

    int getSoundIDmajorSecondCis3() {
        return soundIDmajorSecondCis3;
    }

    int getSoundIDminorThirdCis3() {
        return soundIDminorThirdCis3;
    }

    int getSoundIDmajorThirdCis3() {
        return soundIDmajorThirdCis3;
    }

    int getSoundIDfourthCis3() {
        return soundIDfourthCis3;
    }

    int getSoundIDtritoneCis3() {
        return soundIDtritoneCis3;
    }

    int getSoundIDfifthCis3() {
        return soundIDfifthCis3;
    }

    int getSoundIDminorSixthCis3() {
        return soundIDminorSixthCis3;
    }

    int getSoundIDmajorSixthCis3() {
        return soundIDmajorSixthCis3;
    }

    int getSoundIDminorSeventhCis3() {
        return soundIDminorSeventhCis3;
    }

    int getSoundIDmajorSeventhCis3() {
        return soundIDmajorSeventhCis3;
    }

    int getSoundIDoctaveCis3() {
        return soundIDoctaveCis3;
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

    int getSoundIDcommon_chord_min() {
        return soundIDcommon_chord_min;
    }

    int getSoundIDfirst_inversion_min() {
        return soundIDfirst_inversion_min;
    }

    int getSoundIDsecond_inversion_min() {
        return soundIDsecond_inversion_min;
    }

    int getSoundIDintervalHigher4() {
        return soundIDintervalHigher4;
    }

    int getSoundIDintervalHigher5() {
        return soundIDintervalHigher5;
    }

    int getSoundIDintervalHigher6() {
        return soundIDintervalHigher6;
    }

    int getSoundIDintervalHigher7() {
        return soundIDintervalHigher7;
    }

    int getSoundIDintervalHigher8() {
        return soundIDintervalHigher8;
    }

    int getSoundIDintervalHigher9() {
        return soundIDintervalHigher9;
    }

    int getSoundIDintervalHigher10() {
        return soundIDintervalHigher10;
    }

    int getSoundIDintervalLower4() {
        return soundIDintervalLower4;
    }

    int getSoundIDintervalLower5() {
        return soundIDintervalLower5;
    }

    int getSoundIDintervalLower6() {
        return soundIDintervalLower6;
    }

    int getSoundIDintervalLower7() {
        return soundIDintervalLower7;
    }

    int getSoundIDintervalLower8() {
        return soundIDintervalLower8;
    }

    int getSoundIDintervalLower9() {
        return soundIDintervalLower9;
    }

    int getSoundIDintervalLower10() {
        return soundIDintervalLower10;
    }
}
