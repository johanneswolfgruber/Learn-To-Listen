package com.johanneswolfgruber.learntolisten;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class GamesoundsActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private ImageButton mImageButton1, mImageButton2, mImageButton3, mImageButton4, mImageButton5,
            mImageButton6, mImageButton7, mImageButton8;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4, mTextView5, mTextView6,
            mTextView7, mTextView8;
    private int mButton1ID = 0, mButton2ID = 0, mButton3ID = 0, mButton4ID = 0, mButton5ID = 0,
            mButton6ID = 0, mButton7ID = 0, mButton8ID = 0;
    private Animation mAnimationBlendIn;
    private TextToSpeech mTTS;
    private Sound mSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesounds);

        mSound = new Sound();
        mSound.initSounds(this);

        mTTS = new TextToSpeech(this, this);
        mAnimationBlendIn = AnimationUtils.loadAnimation(this, R.anim.blend_in);

        mTextView1 = (TextView) findViewById(R.id.textView);
        mTextView2 = (TextView) findViewById(R.id.textView2);
        mTextView3 = (TextView) findViewById(R.id.textView3);
        mTextView4 = (TextView) findViewById(R.id.textView4);
        mTextView5 = (TextView) findViewById(R.id.textView5);
        mTextView6 = (TextView) findViewById(R.id.textView6);
        mTextView7 = (TextView) findViewById(R.id.textView7);
        mTextView8 = (TextView) findViewById(R.id.textView8);

        mImageButton1 = (ImageButton) findViewById(R.id.imageButton);
        mImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView1String = mTextView1.getText().toString();

                if(mButton1ID == 0) {
                    mButton1ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView1String, mButton1ID);
                } else if(mButton1ID == 1) {
                    mButton1ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDSteps(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        mImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView2String = mTextView2.getText().toString();

                if(mButton2ID == 0) {
                    mButton2ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView2String, mButton2ID);
                } else if(mButton2ID == 1) {
                    mButton2ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDExercise(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        mImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView3String = mTextView3.getText().toString();

                if(mButton3ID == 0) {
                    mButton3ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView3String, mButton3ID);
                } else if(mButton3ID == 1) {
                    mButton3ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDRightAnswer(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        mImageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView4String = mTextView4.getText().toString();

                if(mButton4ID == 0) {
                    mButton4ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView4String, mButton4ID);
                } else if(mButton4ID == 1) {
                    mButton4ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDWrongAnswer(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        mImageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView5String = mTextView5.getText().toString();

                if(mButton5ID == 0) {
                    mButton5ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView5String, mButton5ID);
                } else if(mButton5ID == 1) {
                    mButton5ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDwallLeft(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        mImageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView6String = mTextView6.getText().toString();

                if(mButton6ID == 0) {
                    mButton6ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView6String, mButton6ID);
                } else if(mButton6ID == 1) {
                    mButton6ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDLadder(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        mImageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView7String = mTextView7.getText().toString();

                if(mButton7ID == 0) {
                    mButton7ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView7String, mButton7ID);
                } else if(mButton7ID == 1) {
                    mButton7ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDLadderDown(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });

        mImageButton8 = (ImageButton) findViewById(R.id.imageButton8);
        mImageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTextView8String = mTextView8.getText().toString();

                if(mButton8ID == 0) {
                    mButton8ID = DoubleClick.doubleClick(GamesoundsActivity.this, mTTS,
                            mTextView8String, mButton8ID);
                } else if(mButton8ID == 1) {
                    mButton8ID = 0;
                    mSound.getSoundPool().play(mSound.getSoundIDDoor(), 1.0f, 1.0f, 0, 0, 1);
                }
            }
        });
    }

    @Override
    public void onInit(int status) {
        mTTS.setLanguage(Locale.US);
    }

    @Override
    public void onResume() {
        super.onResume();
        View v = findViewById(R.id.root_constraint_layout_gamesounds);
        v.startAnimation(mAnimationBlendIn);
    }

    @Override
    public void onDestroy(){
        mSound.getSoundPool().release();
        super.onDestroy();
    }
}
