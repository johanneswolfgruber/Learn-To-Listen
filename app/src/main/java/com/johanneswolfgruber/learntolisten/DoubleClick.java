package com.johanneswolfgruber.learntolisten;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

class DoubleClick{

    static int doubleClick(Context context, TextToSpeech mTTS, String ttsString, int ID){
        if(ID == 0){
            mTTS.speak(ttsString, TextToSpeech.QUEUE_FLUSH, null);
            return 1;
        }else{
            Toast mToast = Toast.makeText(context, ttsString,
                    Toast.LENGTH_SHORT);
            mToast.show();
            return 0;
        }
    }

}
