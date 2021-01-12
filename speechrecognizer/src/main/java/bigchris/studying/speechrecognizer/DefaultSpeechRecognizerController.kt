package bigchris.studying.speechrecognizer

import android.content.Context
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer

internal class DefaultSpeechRecognizerController(val context: Context) : SpeechRecognizerController, RecognitionListener {
    val speechRecognizer: SpeechRecognizer by lazy {SpeechRecognizer.createSpeechRecognizer(context).apply { setRecognitionListener(this@DefaultSpeechRecognizerController) }}

    override fun startRecognition() {

    }

    override fun stopRecognition() {
        TODO("Not yet implemented")
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onBeginningOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onRmsChanged(p0: Float) {
        TODO("Not yet implemented")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onError(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onResults(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onPartialResults(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        TODO("Not yet implemented")
    }
}