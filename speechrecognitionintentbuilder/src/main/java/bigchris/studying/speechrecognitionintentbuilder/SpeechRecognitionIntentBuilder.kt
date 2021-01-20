package bigchris.studying.speechrecognitionintentbuilder

import android.content.Intent

interface SpeechRecognitionIntentBuilder {

    fun getResult() : Intent

    fun setExtraLanguage(language: String)

    fun setExtraPartialResults(partialResults: Boolean)

    fun setExtraPrompt(prompt: String)

}