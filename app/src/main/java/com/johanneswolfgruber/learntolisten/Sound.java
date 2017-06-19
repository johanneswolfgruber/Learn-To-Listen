package com.johanneswolfgruber.learntolisten;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

class Sound {
    private SoundPool mSoundPool;
    private int soundIDSteps, soundIDwallLeft, soundIDExercise, soundIDLadder, soundIDDoor,
            soundIDLadderDown, soundIDRightAnswer, soundIDWrongAnswer, soundIDgameover,
            soundIDlevelfinish, soundIDnewgame, soundIDbutton, soundIDwallRight, soundIDcountdown,
            soundIDexerciseDown, soundIDfinish;
    private int soundIDunisonC3, soundIDminorSecondC3, soundIDmajorSecondC3, soundIDminorThirdC3,
            soundIDmajorThirdC3, soundIDfourthC3, soundIDtritoneC3, soundIDfifthC3,
            soundIDminorSixthC3, soundIDmajorSixthC3, soundIDminorSeventhC3, soundIDmajorSeventhC3,
            soundIDoctaveC3, soundIDunisonCis3, soundIDminorSecondCis3, soundIDmajorSecondCis3,
            soundIDminorThirdCis3, soundIDmajorThirdCis3, soundIDfourthCis3, soundIDtritoneCis3,
            soundIDfifthCis3, soundIDminorSixthCis3, soundIDmajorSixthCis3, soundIDminorSeventhCis3,
            soundIDmajorSeventhCis3, soundIDoctaveCis3, soundIDunisonF2, soundIDminorSecondF2,
            soundIDmajorSecondF2, soundIDminorThirdF2, soundIDmajorThirdF2, soundIDfourthF2,
            soundIDtritoneF2, soundIDfifthF2, soundIDminorSixthF2, soundIDmajorSixthF2,
            soundIDminorSeventhF2, soundIDmajorSeventhF2, soundIDoctaveF2, soundIDunisonFis2,
            soundIDminorSecondFis2, soundIDmajorSecondFis2, soundIDminorThirdFis2,
            soundIDmajorThirdFis2, soundIDfourthFis2, soundIDtritoneFis2, soundIDfifthFis2,
            soundIDminorSixthFis2, soundIDmajorSixthFis2, soundIDminorSeventhFis2,
            soundIDmajorSeventhFis2, soundIDoctaveFis2;
    private int soundIDcommon_chord, soundIDfirst_inversion, soundIDsecond_inversion,
            soundIDcommon_chord_min, soundIDfirst_inversion_min, soundIDsecond_inversion_min,
            soundIDcommon_chordA2, soundIDfirst_inversionA2, soundIDsecond_inversionA2,
            soundIDcommon_chord_minA2, soundIDfirst_inversion_minA2, soundIDsecond_inversion_minA2,
            soundIDcommon_chordDis3, soundIDfirst_inversionDis3, soundIDsecond_inversionDis3,
            soundIDcommon_chord_minDis3, soundIDfirst_inversion_minDis3,
            soundIDsecond_inversion_minDis3;
    private int soundIDmajor1, soundIDminor1, soundIDdiminished1, soundIDaugmented1,
            soundIDmajorA2, soundIDminorA2, soundIDdiminishedA2, soundIDaugmentedA2,
            soundIDmajorDis3, soundIDminorDis3, soundIDdiminishedDis3, soundIDaugmentedDis3;
    private int soundIDintervalHigher1, soundIDintervalHigher2, soundIDintervalHigher3,
            soundIDintervalHigher4, soundIDintervalHigher5, soundIDintervalHigher6,
            soundIDintervalHigher7, soundIDintervalHigher8, soundIDintervalHigher9,
            soundIDintervalHigher10, soundIDintervalLower1, soundIDintervalLower2,
            soundIDintervalLower3, soundIDintervalLower4, soundIDintervalLower5,
            soundIDintervalLower6, soundIDintervalLower7, soundIDintervalLower8,
            soundIDintervalLower9, soundIDintervalLower10;
    private int soundIDlouder, soundIDquieter, soundIDlonger, soundIDshorter, soundIDhigher,
            soundIDlower;


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

        // Seashore
        soundIDlouder = mSoundPool.load(context, R.raw.louder, 0);
        soundIDquieter = mSoundPool.load(context, R.raw.quieter, 0);
        soundIDlonger = mSoundPool.load(context, R.raw.longer, 0);
        soundIDshorter = mSoundPool.load(context, R.raw.shorter, 0);
        soundIDhigher = mSoundPool.load(context, R.raw.higher, 0);
        soundIDlower = mSoundPool.load(context, R.raw.lower, 0);

        // Interval
        soundIDunisonF2 = mSoundPool.load(context, R.raw.perfect_unison_root_f2, 0);
        soundIDminorSecondF2 = mSoundPool.load(context, R.raw.minor_second_root_f2, 0);
        soundIDmajorSecondF2 = mSoundPool.load(context, R.raw.major_second_root_f2, 0);
        soundIDminorThirdF2 = mSoundPool.load(context, R.raw.minor_third_root_f2, 0);
        soundIDmajorThirdF2 = mSoundPool.load(context, R.raw.major_third_root_f2, 0);
        soundIDfourthF2 = mSoundPool.load(context, R.raw.perfect_fourth_root_f2, 0);
        soundIDtritoneF2 = mSoundPool.load(context, R.raw.tritone_root_f2, 0);
        soundIDfifthF2 = mSoundPool.load(context, R.raw.perfect_fifth_root_f2, 0);
        soundIDminorSixthF2 = mSoundPool.load(context, R.raw.minor_sixth_root_f2, 0);
        soundIDmajorSixthF2 = mSoundPool.load(context, R.raw.major_sixth_root_f2, 0);
        soundIDminorSeventhF2 = mSoundPool.load(context, R.raw.minor_seventh_root_f2, 0);
        soundIDmajorSeventhF2 = mSoundPool.load(context, R.raw.major_seventh_root_f2, 0);
        soundIDoctaveF2 = mSoundPool.load(context, R.raw.perfect_octave_root_f2, 0);

        soundIDunisonFis2 = mSoundPool.load(context, R.raw.perfect_unison_root_fis2, 0);
        soundIDminorSecondFis2 = mSoundPool.load(context, R.raw.minor_second_root_fis2, 0);
        soundIDmajorSecondFis2 = mSoundPool.load(context, R.raw.major_second_root_fis2, 0);
        soundIDminorThirdFis2 = mSoundPool.load(context, R.raw.minor_third_root_fis2, 0);
        soundIDmajorThirdFis2 = mSoundPool.load(context, R.raw.major_third_root_fis2, 0);
        soundIDfourthFis2 = mSoundPool.load(context, R.raw.perfect_fourth_root_fis2, 0);
        soundIDtritoneFis2 = mSoundPool.load(context, R.raw.tritone_root_fis2, 0);
        soundIDfifthFis2 = mSoundPool.load(context, R.raw.perfect_fifth_root_fis2, 0);
        soundIDminorSixthFis2 = mSoundPool.load(context, R.raw.minor_sixth_root_fis2, 0);
        soundIDmajorSixthFis2 = mSoundPool.load(context, R.raw.major_sixth_root_fis2, 0);
        soundIDminorSeventhFis2 = mSoundPool.load(context, R.raw.minor_seventh_root_fis2, 0);
        soundIDmajorSeventhFis2 = mSoundPool.load(context, R.raw.major_seventh_root_fis2, 0);
        soundIDoctaveFis2 = mSoundPool.load(context, R.raw.perfect_octave_root_fis2, 0);

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

        soundIDcommon_chordA2 = mSoundPool.load(context, R.raw.common_chord_root_a2, 0);
        soundIDfirst_inversionA2 = mSoundPool.load(context, R.raw.first_inversion_root_a2, 0);
        soundIDsecond_inversionA2 = mSoundPool.load(context, R.raw.second_inversion_root_a2, 0);
        soundIDcommon_chord_minA2 = mSoundPool.load(context, R.raw.common_chord_min_root_a2, 0);
        soundIDfirst_inversion_minA2 = mSoundPool.load(context, R.raw.first_inversion_min_root_a2, 0);
        soundIDsecond_inversion_minA2 = mSoundPool.load(context, R.raw.second_inversion_min_root_a2, 0);

        soundIDcommon_chordDis3 = mSoundPool.load(context, R.raw.common_chord_root_dis3, 0);
        soundIDfirst_inversionDis3 = mSoundPool.load(context, R.raw.first_inversion_root_dis3, 0);
        soundIDsecond_inversionDis3 = mSoundPool.load(context, R.raw.second_inversion_root_dis3, 0);
        soundIDcommon_chord_minDis3 = mSoundPool.load(context, R.raw.common_chord_min_root_dis3, 0);
        soundIDfirst_inversion_minDis3 = mSoundPool.load(context, R.raw.first_inversion_min_root_dis3, 0);
        soundIDsecond_inversion_minDis3 = mSoundPool.load(context, R.raw.second_inversion_min_root_dis3, 0);

        // Major_Minor
        soundIDmajor1 = mSoundPool.load(context, R.raw.major1_root_c3, 0);
        soundIDminor1 = mSoundPool.load(context, R.raw.minor1_root_c3, 0);
        soundIDdiminished1 = mSoundPool.load(context, R.raw.diminished1_root_c3, 0);
        soundIDaugmented1 = mSoundPool.load(context, R.raw.augmented1_root_c3, 0);

        soundIDmajorA2 = mSoundPool.load(context, R.raw.major1_root_a2, 0);
        soundIDminorA2 = mSoundPool.load(context, R.raw.minor1_root_a2, 0);
        soundIDdiminishedA2 = mSoundPool.load(context, R.raw.diminished1_root_a2, 0);
        soundIDaugmentedA2 = mSoundPool.load(context, R.raw.augmented1_root_a2, 0);

        soundIDmajorDis3 = mSoundPool.load(context, R.raw.major1_root_dis3, 0);
        soundIDminorDis3 = mSoundPool.load(context, R.raw.minor1_root_dis3, 0);
        soundIDdiminishedDis3 = mSoundPool.load(context, R.raw.diminished1_root_dis3, 0);
        soundIDaugmentedDis3 = mSoundPool.load(context, R.raw.augmented1_root_dis3, 0);

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

    int getSoundIDunisonF2() {
        return soundIDunisonF2;
    }

    int getSoundIDminorSecondF2() {
        return soundIDminorSecondF2;
    }

    int getSoundIDmajorSecondF2() {
        return soundIDmajorSecondF2;
    }

    int getSoundIDminorThirdF2() {
        return soundIDminorThirdF2;
    }

    int getSoundIDmajorThirdF2() {
        return soundIDmajorThirdF2;
    }

    int getSoundIDfourthF2() {
        return soundIDfourthF2;
    }

    int getSoundIDtritoneF2() {
        return soundIDtritoneF2;
    }

    int getSoundIDfifthF2() {
        return soundIDfifthF2;
    }

    int getSoundIDminorSixthF2() {
        return soundIDminorSixthF2;
    }

    int getSoundIDmajorSixthF2() {
        return soundIDmajorSixthF2;
    }

    int getSoundIDminorSeventhF2() {
        return soundIDminorSeventhF2;
    }

    int getSoundIDmajorSeventhF2() {
        return soundIDmajorSeventhF2;
    }

    int getSoundIDoctaveF2() {
        return soundIDoctaveF2;
    }

    int getSoundIDunisonFis2() {
        return soundIDunisonFis2;
    }

    int getSoundIDminorSecondFis2() {
        return soundIDminorSecondFis2;
    }

    int getSoundIDmajorSecondFis2() {
        return soundIDmajorSecondFis2;
    }

    int getSoundIDminorThirdFis2() {
        return soundIDminorThirdFis2;
    }

    int getSoundIDmajorThirdFis2() {
        return soundIDmajorThirdFis2;
    }

    int getSoundIDfourthFis2() {
        return soundIDfourthFis2;
    }

    int getSoundIDtritoneFis2() {
        return soundIDtritoneFis2;
    }

    int getSoundIDfifthFis2() {
        return soundIDfifthFis2;
    }

    int getSoundIDminorSixthFis2() {
        return soundIDminorSixthFis2;
    }

    int getSoundIDmajorSixthFis2() {
        return soundIDmajorSixthFis2;
    }

    int getSoundIDminorSeventhFis2() {
        return soundIDminorSeventhFis2;
    }

    int getSoundIDmajorSeventhFis2() {
        return soundIDmajorSeventhFis2;
    }

    int getSoundIDoctaveFis2() {
        return soundIDoctaveFis2;
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

    int getSoundIDmajorA2() {
        return soundIDmajorA2;
    }

    int getSoundIDminorA2() {
        return soundIDminorA2;
    }

    int getSoundIDdiminishedA2() {
        return soundIDdiminishedA2;
    }

    int getSoundIDaugmentedA2() {
        return soundIDaugmentedA2;
    }

    int getSoundIDmajorDis3() {
        return soundIDmajorDis3;
    }

    int getSoundIDminorDis3() {
        return soundIDminorDis3;
    }

    int getSoundIDdiminishedDis3() {
        return soundIDdiminishedDis3;
    }

    int getSoundIDaugmentedDis3() {
        return soundIDaugmentedDis3;
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

    int getSoundIDcommon_chordA2() {
        return soundIDcommon_chordA2;
    }

    int getSoundIDfirst_inversionA2() {
        return soundIDfirst_inversionA2;
    }

    int getSoundIDsecond_inversionA2() {
        return soundIDsecond_inversionA2;
    }

    int getSoundIDcommon_chord_minA2() {
        return soundIDcommon_chord_minA2;
    }

    int getSoundIDfirst_inversion_minA2() {
        return soundIDfirst_inversion_minA2;
    }

    int getSoundIDsecond_inversion_minA2() {
        return soundIDsecond_inversion_minA2;
    }

    int getSoundIDcommon_chordDis3() {
        return soundIDcommon_chordDis3;
    }

    int getSoundIDfirst_inversionDis3() {
        return soundIDfirst_inversionDis3;
    }

    int getSoundIDsecond_inversionDis3() {
        return soundIDsecond_inversionDis3;
    }

    int getSoundIDcommon_chord_minDis3() {
        return soundIDcommon_chord_minDis3;
    }

    int getSoundIDfirst_inversion_minDis3() {
        return soundIDfirst_inversion_minDis3;
    }

    int getSoundIDsecond_inversion_minDis3() {
        return soundIDsecond_inversion_minDis3;
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
