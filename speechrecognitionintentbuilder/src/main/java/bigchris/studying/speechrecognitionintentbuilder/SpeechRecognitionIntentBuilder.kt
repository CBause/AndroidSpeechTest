package bigchris.studying.speechrecognitionintentbuilder

import android.content.Intent

interface SpeechRecognitionIntentBuilder {

    fun getResult() : Intent

    fun setActionHandsFreeSearch()

    fun setActionWebSearch()

    fun setActionDefaultRecognition()

    fun setExtraLanguage(language: String)

    fun setExtraPartialResults(partialResults: Boolean)

    fun setExtraPreferOffline(preferOffline: Boolean)

    fun setExtraPrompt(prompt: String)
}