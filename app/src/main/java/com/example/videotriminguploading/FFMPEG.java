package com.example.videotriminguploading;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler.Callback;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.util.concurrent.ExecutorService;

public class FFMPEG extends Service {

    FFmpeg fFmpeg;
    int duration;
    String[] command;
    Callback activity;

    public  MutableLiveData<Integer> percentage;
    IBinder iBinder=new LocalBinder();

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
            duration =Integer.parseInt(intent.getStringExtra("duration"));
            command= intent.getStringArrayExtra("command");
            try {
                loadFFMPEGBinary();
                execFFMPEGCommand();
            } catch (FFmpegNotSupportedException e) {
                e.printStackTrace();
            } catch (FFmpegCommandAlreadyRunningException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void execFFMPEGCommand() throws FFmpegCommandAlreadyRunningException {
        fFmpeg.execute(command,new ExecuteBinaryResponseHandler()
        {
            @Override
            public void onFailure(String message) {
                super.onFailure(message);
            }

            @Override
            public void onSuccess(String message) {
                super.onSuccess(message);
            }

            @Override
            public void onProgress(String message) {
                super.onProgress(message);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                percentage.setValue(100);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            loadFFMPEGBinary();
        } catch (FFmpegNotSupportedException e) {
            e.printStackTrace();
        }
        percentage=new MutableLiveData<>();
    }

    private void loadFFMPEGBinary() throws FFmpegNotSupportedException {
    if(fFmpeg==null){
        fFmpeg=FFmpeg.getInstance(this);

    }
    fFmpeg.loadBinary(new LoadBinaryResponseHandler()
    {
        @Override
        public void onFailure() {
            super.onFailure();
        }

        @Override
        public void onSuccess() {
            super.onSuccess();
        }
    });
    }

    public FFMPEG(){
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public  class LocalBinder extends Binder{
        public  FFMPEG getServiceInstance(){
            return FFMPEG.this;
        }
    }
    public  void registerClient(Activity activity){
        this.activity=(Callback)activity;
    }
    public  MutableLiveData<Integer> getPercentage(){
        return percentage;
    }
    public interface Callback{
        void updateClient(float data);

    }
}
