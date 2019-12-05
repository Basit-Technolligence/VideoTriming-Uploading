package com.example.videotriminguploading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class VideoTrimList extends AppCompatActivity {

    int duration;
    String[] command;
    String path;

    ServiceConnection serviceConnection;
    FFMPEG ffmpeg;
    Integer res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_trim_list);

        final Intent i =getIntent();
        if(i!=null){
            duration=i.getIntExtra("duration",0);
            command=i.getStringArrayExtra("command");
            path=i.getStringExtra("destination");

            final Intent intent = new Intent(VideoTrimList.this,FFMPEG.class);
            intent.putExtra("duration", String.valueOf(duration));
            intent.putExtra("command",command);
            intent.putExtra("destination",path);
            startService(intent);

            serviceConnection =new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    FFMPEG.LocalBinder binder=(FFMPEG.LocalBinder) service;
                    ffmpeg=binder.getServiceInstance();
                    ffmpeg.registerClient(getParent());

                    final  Observer<Integer> resultObserver=new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer integer) {
                            res=integer;
                            if(res<100){

                            }
                            if (res == 100) {
                                stopService(intent);
                                Toast.makeText(VideoTrimList.this,"Video trimmed succesfully",Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    ffmpeg.getPercentage().observe(VideoTrimList.this,resultObserver);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
                bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);

        }
    }
}
