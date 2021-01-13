package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModel
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionIntentBuilderFactory
import bigchris.studying.speechrecognitionintentfilterbuilder.SpeechRecognitionIntentFilterBuilderFactory

class ExternalSpeechRecognitionViewModel : ViewModel(), Tagged {
    override val TAG = "EXTERNALSPEECHRECOGNITIONVIEWMODEL"

    fun getExternalSpeechRecognitionIntent(): Intent = with(SpeechRecognitionIntentBuilderFactory.getInstance()) {
        this.setActionHandsFreeSearch()
        this.getResult()
    }

    fun getExternalSpeechRecognitionIntentFilter(): IntentFilter = SpeechRecognitionIntentFilterBuilderFactory.getInstance().getResult()
}