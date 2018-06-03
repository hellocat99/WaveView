package com.example.yangdan.wave

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById(R.id.avatorImg) as ImageView
        val waveView = findViewById(R.id.waveView) as WaveView

        val layoutParams = imageView.layoutParams as RelativeLayout.LayoutParams

        waveView.setmListener(object : WaveView.OnWaveAnimationListener {
            override fun onWaveAinmation(y: Float) {
                // 方法一
                //layoutParams.setMargins(0,0,0, (int) (y + 20));
                //imageView.setLayoutParams(layoutParams);
                //方法二
                //imageView.setTranslationY(-20 - y);
                imageView.post { imageView.offsetTopAndBottom((-20 - y).toInt()) }
            }
        })
    }
}