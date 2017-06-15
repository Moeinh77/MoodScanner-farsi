package com.hasani.moein.taan.moodscanner;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    private ImageView fingerpt;
    private ImageView background;
    private AnimationDrawable animationDrawable;
    private Runnable mRunnable;
    private String[] moods;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moods = new String[]{
                "تقریبا سرحال !","تنها...", "بی حوصله", "تنها...","تقریبا سرحال!" ,"نگران", "تاریک...", "خسته...", "بی تفاوت", "بی حس"
                , "پر استرس", "تنها...", "بی تفاوت","کمی ناراحت :(","کمی ناراحت:(","خسته","تقریبا سرحال !","تو ابرا !"
        };

        mediaPlayer=new MediaPlayer();

        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.n);

        result = (TextView) findViewById(R.id.result);
        fingerpt = (ImageView) findViewById(R.id.thumb);
        fingerpt.setBackgroundResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) fingerpt.getBackground();//new
        fingerpt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                animationDrawable.start();
                onclick_func();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer=null;
        super.onDestroy();
    }

    public void onclick_func() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * moods.length);
                animationDrawable.stop();
                result.setText(moods[rand]);
                mediaPlayer.start();
            }

        };
        Handler handler = new Handler();
        handler.postDelayed(mRunnable, 2800);


    }
}
