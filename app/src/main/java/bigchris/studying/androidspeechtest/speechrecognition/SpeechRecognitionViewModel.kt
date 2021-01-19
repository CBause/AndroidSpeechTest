package bigchris.studying.androidspeechtest.speechrecognition

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionIntentBuilderFactory

class SpeechRecognitionViewModel : ViewModel(), Tagged {
    override val TAG = "SPEECHRECOGNITIONVIEWMODEL"

    fun getSpeechRecognizerIntent(context: Context? = null) : Intent = with(SpeechRecognitionIntentBuilderFactory.getInstance()) {
        this.setExtraLanguage("de-DE")
        this.setExtraPreferOffline(true)
        context?.let {
            this.setExtraPrompt(it.resources.getString(R.string.speechRecognitionPrompt))
        }
        this.getResult()
    }
}