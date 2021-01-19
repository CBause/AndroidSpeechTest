package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.content.Intent
import androidx.lifecycle.ViewModel
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionIntentBuilderFactory

class ExternalSpeechRecognitionViewModel : ViewModel(), Tagged {
    override val TAG = "EXTERNALSPEECHRECOGNITIONVIEWMODEL"

    fun getExternalSpeechRecognitionIntent(): Intent = with(SpeechRecognitionIntentBuilderFactory.getInstance()) {
        this.setActionWebSearch()
        this.getResult()
    }

}