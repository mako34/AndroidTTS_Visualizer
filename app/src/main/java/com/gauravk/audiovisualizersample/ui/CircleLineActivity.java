package com.gauravk.audiovisualizersample.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gauravk.audiovisualizer.model.PaintStyle;
import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;
import com.gauravk.audiovisualizersample.R;
import com.gauravk.audiovisualizersample.utils.AudioPlayer;

import java.util.Locale;

public class CircleLineActivity extends AppCompatActivity {
    public static final int DEFAULT_STREAM = AudioManager.STREAM_MUSIC;

    private TextToSpeech myTTS;
    TextToSpeech tts;
    String text;

    String message = "How may I help you?";
    String mostRecentUtteranceID;

    private AudioPlayer mAudioPlayer;
    private CircleLineVisualizer mVisualizer;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_line);
        mVisualizer = findViewById(R.id.blob);
        mAudioPlayer = new AudioPlayer();
        mVisualizer.setDrawLine(true);




    }
    @Override
    protected void onStart() {
        super.onStart();

//        startPlayingAudio(R.raw.sample);



//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//        int audioSessionId = mediaPlayer.getAudioSessionId();
//        if (audioSessionId != AudioManager.ERROR) {
//            mVisualizer.setAudioSessionId(mediaPlayer.getAudioSessionId());
//        }


        mVisualizer.setAudioSessionId(AudioManager.STREAM_MUSIC);


        Log.i("XXX", "was onDone() called on a background thread? : " );



        tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
//                        ConvertTextToSpeech();


//                        mVisualizer.setAudioSessionId(DEFAULT_STREAM);

//                        startPlayingAudio(R.raw.sample);
//                        mVisualizer.setAudioSessionId(DEFAULT_STREAM);


//                        mVisualizer.setAudioSessionId(AudioManager.STREAM_MUSIC);

                        text = "mako  mako  mako  mako  mako  mako ";
                        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);


                        //



                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayingAudio();
    }

    private void startPlayingAudio(int resId) {
        mAudioPlayer.play(this, resId, new AudioPlayer.AudioPlayerEvent() {
            @Override
            public void onCompleted() {
                if (mVisualizer != null)
                    mVisualizer.hide();
            }
        });
        int audioSessionId = mAudioPlayer.getAudioSessionId();
        if (audioSessionId != -1)
            mVisualizer.setAudioSessionId(audioSessionId);

    }

    private void stopPlayingAudio() {
        if (mAudioPlayer != null)
            mAudioPlayer.stop();
        if (mVisualizer != null)
            mVisualizer.release();
    }


}
