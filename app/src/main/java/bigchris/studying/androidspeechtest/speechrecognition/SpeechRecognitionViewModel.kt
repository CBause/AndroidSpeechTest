package bigchris.studying.androidspeechtest.speechrecognition

import android.content.Context
import androidx.lifecycle.ViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionIntentBuilderFactory

class SpeechRecognitionViewModel : ViewModel(), Tagged {
    override val TAG = "SPEECHRECOGNITIONVIEWMODEL"

    fun getSpeechRecognitionIntent(context: Context? = null) = with(SpeechRecognitionIntentBuilderFactory.getSpeechRecognitionIntentBuilder()) {
        this.setExtraLanguage("de-DE")
        context?.let {
            this.setExtraPrompt(context.resources.getString(R.string.speechRecognitionPrompt))
        }
        this.getResult()
    }

}