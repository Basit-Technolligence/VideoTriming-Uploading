package com.example.videotriminguploading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class VideoView extends AppCompatActivity {
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        Intent i= getIntent();
        if(i!=null){
            String imgPath= i.getStringExtra("uri");
            uri= Uri.parse(imgPath);
        }
    }
}
