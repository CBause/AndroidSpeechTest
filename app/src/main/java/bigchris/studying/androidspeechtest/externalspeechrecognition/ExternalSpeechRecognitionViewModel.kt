package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.lifecycle.ViewModel
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionBuilderFactory

class ExternalSpeechRecognitionViewModel : ViewModel() {

    fun getExternalSpeechRecognizerIntent() : Intent = with(SpeechRecognitionBuilderFactory.getInstance()) {
        this.setExtraLanguage("de-DE")
        this.setExtraPrompt("Bitte spreche jetzt!")
        this.getResult()
    }
}