package bigchris.studying.speechrecognizercontroller

import android.content.Intent

interface SpeechRecognizerController {

    fun startListening(intent: Intent)

    fun stopListening()

    fun registerSpeechRecognitionListener(listener: SpeechRecognitionListener)

    fun unregisterSpeechRecognitionListener(listener: SpeechRecognitionListener)
}