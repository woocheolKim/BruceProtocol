package com.example.bruceprotocol.Tools;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.example.bruceprotocol.R;

import java.util.Locale;

public class TTS {
    private static TTS instance;
    private TextToSpeech tts;

    private TTS(Context context){
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.getDefault());
                }
            }
        });
    }

    private static TTS getInstance(Context context){
        if (instance == null){
            instance = new TTS(context);
        }

        return instance;
    }

    public static void speak(Context context, String msg){
        getInstance(context).tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, context.getString(R.string.app_name));
    }
}
