package com.example.videotriminguploading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        VideoView videoView = findViewById(R.id.videoView);

        //getting uri from main page
        Intent i= getIntent();
        if(i!=null){
            String videoPath= i.getStringExtra("uri");
            uri= Uri.parse(videoPath);
        }
        //control of video view
        MediaController mediaController= new MediaController(this);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        //anchor set
        mediaController.setAnchorView(videoView);
        videoView.start();

    }
}
