package com.johanneswolfgruber.learntolisten;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

class Sound {
    private SoundPool mSoundPool;
    private int soundIDSteps, soundIDWall, soundIDExercise, soundIDLadder, soundIDDoor,
            soundIDLadderDown, soundIDhigher1, soundIDhigher2, soundIDhigher3, soundIDlower1,
            soundIDlower2, soundIDlower3, soundIDRightAnswer, soundIDWrongAnswer, soundIDunison,
            soundIDminorSecond, soundIDmajorSecond, soundIDminorThird, soundIDmajorThird,
            soundIDfourth, soundIDtritone, soundIDfifth, soundIDminorSixth, soundIDmajorSixth,
            soundIDminorSeventh, soundIDmajorSeventh, soundIDoctave, soundIDcommon_chord,
            soundIDfirst_inversion, soundIDsecond_inversion, soundIDmajor1, soundIDminor1,
            soundIDdiminished1, soundIDaugmented1, soundIDintervalHigher1, soundIDintervalHigher2,
            soundIDintervalHigher3, soundIDintervalLower1, soundIDintervalLower2,
            soundIDintervalLower3, soundIDgameover, soundIDlevelfinish, soundIDnewgame,
            soundIDbutton;


    @SuppressWarnings("deprecation")
    void initSounds(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSoundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .build();
        } else {
            mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        soundIDSteps = mSoundPool.load(context, R.raw.sfx_movement_footstepsloop4_slow_steps, 0);
        soundIDWall = mSoundPool.load(context, R.raw.sfx_movement_dooropen2_wall, 0);
        soundIDDoor = mSoundPool.load(context, R.raw.door_knock, 0);
        soundIDExercise = mSoundPool.load(context, R.raw.sfx_alarm_loop6_exercise, 0);
        soundIDLadder = mSoundPool.load(context, R.raw.sfx_movement_ladder1loop_ladder, 0);
        soundIDLadderDown = mSoundPool.load(context, R.raw.ladder_down, 0);
        soundIDRightAnswer = mSoundPool.load(context, R.raw.sfx_sounds_powerup3_right, 0);
        soundIDWrongAnswer = mSoundPool.load(context, R.raw.sfx_sounds_error13_wrong, 0);
        soundIDgameover = mSoundPool.load(context, R.raw.sfx_sounds_falling5_gameover, 0);
        soundIDlevelfinish = mSoundPool.load(context, R.raw.sfx_sounds_fanfare1_level_finished, 0);
        soundIDnewgame = mSoundPool.load(context, R.raw.sfx_menu_select1_new_game, 0);
        soundIDbutton = mSoundPool.load(context, R.raw.sfx_menu_select2_click_button, 0);
        soundIDhigher1 = mSoundPool.load(context, R.raw.higher1_root_c3, 0);
        soundIDhigher2 = mSoundPool.load(context, R.raw.higher2_root_c3, 0);
        soundIDhigher3 = mSoundPool.load(context, R.raw.higher3_root_c3, 0);
        soundIDlower1 = mSoundPool.load(context, R.raw.lower1_root_c3, 0);
        soundIDlower2 = mSoundPool.load(context, R.raw.lower2_root_c3, 0);
        soundIDlower3 = mSoundPool.load(context, R.raw.lower3_root_c3, 0);
        soundIDunison = mSoundPool.load(context, R.raw.perfect_unison_root_c3, 0);
        soundIDminorSecond = mSoundPool.load(context, R.raw.minor_second_root_c3, 0);
        soundIDmajorSecond = mSoundPool.load(context, R.raw.major_second_root_c3, 0);
        soundIDminorThird = mSoundPool.load(context, R.raw.minor_third_root_c3, 0);
        soundIDmajorThird = mSoundPool.load(context, R.raw.major_third_root_c3, 0);
        soundIDfourth = mSoundPool.load(context, R.raw.perfect_fourth_root_c3, 0);
        soundIDtritone = mSoundPool.load(context, R.raw.tritone_root_c3, 0);
        soundIDfifth = mSoundPool.load(context, R.raw.perfect_fifth_root_c3, 0);
        soundIDminorSixth = mSoundPool.load(context, R.raw.minor_sixth_root_c3, 0);
        soundIDmajorSixth = mSoundPool.load(context, R.raw.major_sixth_root_c3, 0);
        soundIDminorSeventh = mSoundPool.load(context, R.raw.minor_seventh_root_c3, 0);
        soundIDmajorSeventh = mSoundPool.load(context, R.raw.major_seventh_root_c3, 0);
        soundIDoctave = mSoundPool.load(context, R.raw.perfect_octave_root_c3, 0);
        soundIDcommon_chord = mSoundPool.load(context, R.raw.common_chord_root_c3, 0);
        soundIDfirst_inversion = mSoundPool.load(context, R.raw.first_inversion_root_c3, 0);
        soundIDsecond_inversion = mSoundPool.load(context, R.raw.second_inversion_root_c3, 0);
        soundIDmajor1 = mSoundPool.load(context, R.raw.major1_root_c3, 0);
        soundIDminor1 = mSoundPool.load(context, R.raw.minor1_root_c3, 0);
        soundIDdiminished1 = mSoundPool.load(context, R.raw.diminished1_root_c3, 0);
        soundIDaugmented1 = mSoundPool.load(context, R.raw.augmented1_root_c3, 0);
        soundIDintervalHigher1 = mSoundPool.load(context, R.raw.interval_higher1_root_c3, 0);
        soundIDintervalHigher2 = mSoundPool.load(context, R.raw.interval_higher2_root_c3, 0);
        soundIDintervalHigher3 = mSoundPool.load(context, R.raw.interval_higher3_root_c3, 0);
        soundIDintervalLower1 = mSoundPool.load(context, R.raw.interval_lower1_root_c3, 0);
        soundIDintervalLower2 = mSoundPool.load(context, R.raw.interval_lower2_root_c3, 0);
        soundIDintervalLower3 = mSoundPool.load(context, R.raw.interval_lower3_root_c3, 0);
    }

    void playSound(int soundID){
        mSoundPool.play(soundID, 1.0f, 1.0f, 0, 0, 1);
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
