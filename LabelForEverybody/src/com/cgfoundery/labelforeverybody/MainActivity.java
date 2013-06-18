package com.cgfoundery.labelforeverybody;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements
		TextToSpeech.OnInitListener {

	private TextToSpeech tts;
	private ImageButton btnSpeak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tts = new TextToSpeech(this, this);
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
		btnSpeak.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				btnSpeak.setEnabled(true);
				speakOut();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(Locale.FRENCH);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				//
			}
		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}

	private void speakOut() {
		tts.speak("Jus orange 3 euros 50", TextToSpeech.QUEUE_FLUSH, null);
	}
}
