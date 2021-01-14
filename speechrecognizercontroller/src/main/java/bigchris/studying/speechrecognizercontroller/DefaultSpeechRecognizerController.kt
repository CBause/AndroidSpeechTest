package bigchris.studying.speechrecognizercontroller

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log

internal class DefaultSpeechRecognizerController(val speechRecognizer: SpeechRecognizer) : SpeechRecognizerController {
    private val TAG: String = "SPEECHRECOGNIZERCONTROLLER"
    private val registeredRecognitionListener = ArrayList<SpeechRecognitionListener>()

    init {
        speechRecognizer.setRecognitionListener(RecognizerListener())
    }

    private inner class RecognizerListener : RecognitionListener {

        override fun onReadyForSpeech(p0: Bundle?) {
            Log.i(TAG, "ready for speech")
            registeredRecognitionListener.forEach {
                it.onSpeechRecognitionStarted()
            }
        }

        override fun onBeginningOfSpeech() {
            Log.i(TAG, "beginning of speech")
        }

        override fun onRmsChanged(p0: Float) {
        }

        override fun onBufferReceived(p0: ByteArray?) {
        }

        override fun onEndOfSpeech() {
            Log.i(TAG, "end of speech")
            registeredRecognitionListener.forEach {
                it.onSpeechRecognitionStopped()
            }
        }

        override fun onError(p0: Int) {
            val errorString = with(p0) {
                when (this) {
                    SpeechRecognizer.ERROR_AUDIO -> "Error audio"
                    SpeechRecognizer.ERROR_CLIENT -> "Client error"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                    SpeechRecognizer.ERROR_NETWORK -> "Network error"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                    SpeechRecognizer.ERROR_NO_MATCH -> "No match"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognizer busy"
                    SpeechRecognizer.ERROR_SERVER -> "Server error"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Speech timeout"
                    else -> "Unknown error"
                }
            }
            Log.e(TAG, errorString)
            registeredRecognitionListener.forEach {
                it.onSpeechRecognitionError(p0, errorString)
            }
        }

        override fun onResults(p0: Bundle?) {
            registeredRecognitionListener.forEach {
                p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {list ->
                    it.onSpeechRecognitionResults(list)
                }
            }
        }

        override fun onPartialResults(p0: Bundle?) {
            registeredRecognitionListener.forEach {
                p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {list ->
                    it.onSpeechRecognitionPartialResults(list)
                }
            }
        }

        override fun onEvent(p0: Int, p1: Bundle?) {
        }
    }

    override fun startListening(intent: Intent) {
        speechRecognizer.startListening(intent)
    }

    override fun stopListening() {
        speechRecognizer.stopListening()
    }

    override fun registerSpeechRecognitionListener(listener: SpeechRecognitionListener) {
        registeredRecognitionListener.apply {
            if (!this.contains(listener))
                this.add(listener)
        }
    }

    override fun unregisterSpeechRecognitionListener(listener: SpeechRecognitionListener) {
        registeredRecognitionListener.apply {
            if (this.contains(listener))
                this.remove(listener)
        }
    }
}