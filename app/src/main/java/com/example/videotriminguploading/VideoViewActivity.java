package com.example.videotriminguploading;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class VideoViewActivity extends AppCompatActivity {
    Uri uri;
    int duration;
    String filePrefix;
    String[] command;
    File dest;
    String originalPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        VideoView videoView = findViewById(R.id.videoView);
        final Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //getting uri from main page
        Intent i = getIntent();
        if (i != null) {
            String videoPath = i.getStringExtra("uri");
            uri = Uri.parse(videoPath);
        }
        //control of video view
        MediaController mediaController = new MediaController(this);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        //anchor set
        mediaController.setAnchorView(videoView);
        videoView.start();
    }
        //video detail
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                duration=mp.getDuration()/1000;
//                //trim button click
//                btnSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MediaPlayer mediaPlayer=new MediaPlayer();
//                        Toast.makeText(VideoViewActivity.this, ""+duration+"", Toast.LENGTH_LONG).show();
//                        trimVideo(0,10,"TrimedVideo");
//                        Intent intent=new Intent(VideoViewActivity.this,VideoTrimList.class);
//                        intent.putExtra("duration",duration);
//                        intent.putExtra("command",command);
//                        intent.putExtra("destination",dest.getAbsolutePath());
//                        startActivity(intent);
//                    }
//                });
//            }
//        });
//
//
//
//    }
//    public void trimVideo(int starTime,int endTime,String fileName){
//        File folder=new File(Environment.getExternalStorageState()+"/TrimVideos");
//        if(!folder.exists()){
//            folder.mkdirs();
//
//        }
//        String fileExt=".mp4";
//        dest= new File(folder,fileName+fileExt);
//        originalPath=getRealPathFromUri(getApplicationContext(),uri);
//        command =new String[]{"-ss",""+starTime/1000,"-y","-i",originalPath,"-t",""+(endTime-starTime)/1000,"-vcodec","mpeg4","-b:v","2097152","-b:a","48000","-ac","2","-ar","22050",dest.getAbsolutePath()};
//
//    }
//
//    private String getRealPathFromUri(Context context, Uri contentUri) {
//        Cursor cursor=null;
//        try{
//            String[] proj={(MediaStore.Images.Media.DATA)};
//            cursor=context.getContentResolver().query(contentUri,proj,null,null);
//            int columnIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(columnIndex);
//        }catch (Exception e){
//            e.printStackTrace();
//            return "";
//        }finally {
//            if(cursor!=null){
//                cursor.close();
//            }
//        }
//    }

}
