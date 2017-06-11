package com.johanneswolfgruber.learntolisten;

//import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

//import java.util.Locale;

public class TutorialActivity extends AppCompatActivity{
    private Animation mAnimationBlendIn;
    //private String mTutorialString;
    //private int mID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        mAnimationBlendIn = AnimationUtils.loadAnimation(this, R.anim.blend_in);
        //mTTS = new TextToSpeech(this, this);
        TextView mTextView = (TextView) findViewById(R.id.tutorial_text_view);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mTutorialString = mTextView.getText().toString();
                /*
                if(mID == 0) {
                    mID = DoubleClick.doubleClick(TutorialActivity.this, mTTS, mTutorialString,
                            mID);
                }else if(mID == 1) {
                    mTTS.stop();
                    mID = 0;
                }*/
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        View v = findViewById(R.id.root_constraint_layout_tutorial);
        v.startAnimation(mAnimationBlendIn);
    }

    /*
    @Override
    public void onInit(int status) {
        mTTS.setLanguage(Locale.US);
    }
    */
}
