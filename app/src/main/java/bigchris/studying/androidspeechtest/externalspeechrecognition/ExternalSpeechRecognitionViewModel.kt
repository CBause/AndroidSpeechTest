package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.lifecycle.ViewModel

class ExternalSpeechRecognitionViewModel : ViewModel() {
    val speechRecognitionRequestCode = 1234

    fun getExternalSpeechRecognizerIntent() = Intent().apply {
        setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    }
}