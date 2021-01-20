package bigchris.studying.speechrecognizercontroller

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

internal class DefaultSpeechRecognizerController(private val speechRecognizer: SpeechRecognizer) : SpeechRecognizerController {
    private val speechRecognizerControllerListeners = ArrayList<SpeechRecognizerControllerListener>()

    init {
        speechRecognizer.setRecognitionListener(SpeechRecognizerListener())
    }

    inner class SpeechRecognizerListener : RecognitionListener {

        override fun onReadyForSpeech(params: Bundle?) {
            speechRecognizerControllerListeners.forEach {
                it.onSpeechRecognitionStarted()
            }
        }

        override fun onBeginningOfSpeech() {
        }

        override fun onRmsChanged(rmsdB: Float) {
        }

        override fun onBufferReceived(buffer: ByteArray?) {
        }

        override fun onEndOfSpeech() {
            speechRecognizerControllerListeners.forEach {
                it.onSpeechRecognitionStopped()
            }
        }

        override fun onError(error: Int) {
            val errorString = with(error) {
                when(this) {
                    SpeechRecognizer.ERROR_CLIENT -> "Error client"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                    SpeechRecognizer.ERROR_NETWORK -> "Network error"
                    else -> "Error occurred!"
                }
            }
            speechRecognizerControllerListeners.forEach {
                it.onSpeechRecognitionError(error, errorString)
                it.onSpeechRecognitionStopped()
            }
        }

        override fun onResults(results: Bundle?) {
            results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {resultStringArray ->
                speechRecognizerControllerListeners.forEach {
                    it.onSpeechRecognitionResults(resultStringArray)
                }
            }
        }

        override fun onPartialResults(partialResults: Bundle?) {
            partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {resultStringArray ->
                speechRecognizerControllerListeners.forEach {
                    it.onSpeechRecognitionPartialResults(resultStringArray)
                }
            }
        }

        override fun onEvent(eventType: Int, params: Bundle?) {
        }
    }


    override fun startListening(intent: Intent) {
        speechRecognizer.startListening(intent)
    }

    override fun stopListening() {
        speechRecognizer.stopListening()
    }

    override fun registerSpeechRecognizerControllerListener(listener: SpeechRecognizerControllerListener) {
        if (!speechRecognizerControllerListeners.contains(listener))
            speechRecognizerControllerListeners.add(listener)
    }

    override fun unregisterSpeechRecognizerControllerListener(listener: SpeechRecognizerControllerListener) {
        if (speechRecognizerControllerListeners.contains(listener))
            speechRecognizerControllerListeners.remove(listener)
    }
}