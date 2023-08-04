package com.nurnahian.birdanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView im = findViewById(R.id.imageView);

        im.setBackgroundResource(R.drawable.birdsequence);

        ((AnimationDrawable)im.getBackground()).start();

        Animation x = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.my_rotation);
        im.setAnimation(x);

    }
}