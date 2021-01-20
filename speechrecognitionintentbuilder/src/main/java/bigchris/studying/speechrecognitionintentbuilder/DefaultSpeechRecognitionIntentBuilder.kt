package bigchris.studying.speechrecognitionintentbuilder

import android.content.Intent
import android.speech.RecognizerIntent

internal class DefaultSpeechRecognitionIntentBuilder : SpeechRecognitionIntentBuilder {
    private val resultIntent = Intent()

    init {
        resultIntent.action = RecognizerIntent.ACTION_RECOGNIZE_SPEECH
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    override fun getResult(): Intent = resultIntent

    override fun setExtraLanguage(language: String) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language)
    }

    override fun setExtraPartialResults(partialResults: Boolean) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, partialResults)
    }

    override fun setExtraPrompt(prompt: String) {
        resultIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt)
    }
}