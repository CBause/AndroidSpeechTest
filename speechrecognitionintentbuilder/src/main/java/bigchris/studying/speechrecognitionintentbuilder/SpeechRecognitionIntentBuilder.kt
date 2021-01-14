package bigchris.studying.speechrecognitionintentbuilder

import android.content.Intent
import android.content.IntentFilter

interface SpeechRecognitionIntentBuilder {

    fun getResult() : Intent

    fun setActionHandsFreeSearch()

    fun setActionWebSearch()

    fun setActionDefaultRecognition()

    fun setExtraLanguage(language: String)

    fun setExtraPartialResults(partialResults: Boolean)

    fun setExtraPreferOffline(preferOffline: Boolean)

    fun setExtraPrompt(prompt: String)

    fun setLeastListenTime(milliseconds: Int)

    fun setWaitAfterLastSpeech(milliseconds: Int)

    fun setMinimumListenTime(milliseconds: Int)
}