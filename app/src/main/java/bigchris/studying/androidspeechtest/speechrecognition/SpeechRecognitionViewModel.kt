package bigchris.studying.androidspeechtest.speechrecognition

import android.content.Intent
import androidx.lifecycle.ViewModel
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionBuilderFactory

class SpeechRecognitionViewModel : ViewModel() {

    fun getExternalSpeechRecognizerIntent() : Intent = with(SpeechRecognitionBuilderFactory.getInstance()) {
        this.setActionWebSearch()
        this.getResult()
    }
}