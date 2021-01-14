package bigchris.studying.speechrecognitionintentbuilder

import android.content.Intent
import android.content.IntentFilter
import android.speech.RecognizerIntent
import android.speech.RecognizerResultsIntent

internal class DefaultSpeechRecognitionIntentBuilder : SpeechRecognitionIntentBuilder {
    private val resultIntent = Intent()

    init {
        resultIntent.action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    override fun getResult() = resultIntent

    override fun setActionHandsFreeSearch() {
        resultIntent.action = RecognizerIntent.ACTION_VOICE_SEARCH_HANDS_FREE
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    override fun setActionWebSearch() {
        resultIntent.action = RecognizerIntent.ACTION_WEB_SEARCH
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
    }

    override fun setActionDefaultRecognition() {
        resultIntent.action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    override fun setExtraLanguage(language: String) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
    }

    override fun setExtraPartialResults(partialResults: Boolean) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, partialResults)
    }

    override fun setExtraPreferOffline(preferOffline: Boolean) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, preferOffline)
    }

    override fun setExtraPrompt(prompt: String) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt)
    }

    override fun setLeastListenTime(milliseconds: Int) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, milliseconds)
    }

    override fun setWaitAfterLastSpeech(milliseconds: Int) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, milliseconds)
    }

    override fun setMinimumListenTime(milliseconds: Int) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, milliseconds)
    }
}