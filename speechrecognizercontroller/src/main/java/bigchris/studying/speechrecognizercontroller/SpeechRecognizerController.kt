package bigchris.studying.speechrecognizercontroller

import android.content.Intent

interface SpeechRecognizerController {

    fun startListening(intent: Intent)

    fun stopListening()

    fun registerSpeechRecognizerControllerListener(listener: SpeechRecognizerControllerListener)

    fun unregisterSpeechRecognizerControllerListener(listener: SpeechRecognizerControllerListener)

}