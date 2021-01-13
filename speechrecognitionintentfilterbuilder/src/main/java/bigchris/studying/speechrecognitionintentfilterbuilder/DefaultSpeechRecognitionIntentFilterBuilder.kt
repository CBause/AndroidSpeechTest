package bigchris.studying.speechrecognitionintentfilterbuilder

import android.content.IntentFilter
import android.speech.RecognizerResultsIntent

internal class DefaultSpeechRecognitionIntentFilterBuilder : SpeechRecognitionIntentFilterBuilder {
    private val resultIntentFilter: IntentFilter by lazy {IntentFilter().apply {addAction(RecognizerResultsIntent.ACTION_VOICE_SEARCH_RESULTS)}}

    override fun getResult(): IntentFilter = resultIntentFilter
}