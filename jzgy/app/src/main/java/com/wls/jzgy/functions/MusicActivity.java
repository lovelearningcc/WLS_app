package com.wls.jzgy.functions;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wls.jzgy.R;

/**
 * description ：
 * project name：jzgy
 * author : gcc
 * creation date: 2019\8\6 0006 11:03
 *
 * @version 1.0
 */

public class MusicActivity extends Activity {


    private Button music;

    private MediaPlayer mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        mm = MediaPlayer.create(this, R.raw.dong);
        mm.start();

//        music = findViewById(R.id.bnt_music);
//
//        music.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("点击播放音乐", "---------》");
//                Intent it = new Intent(Intent.ACTION_VIEW);
//                it.setDataAndType(Uri.parse("http://47.107.140.34/dmrbell/cs.mp3"), "audio/MP3");
//                startActivity(it);
//
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory() + "/cs.mp4"), "video/mp4");
////                startActivity(intent);
//            }
//        });
//
    }
}
