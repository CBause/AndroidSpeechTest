package bigchris.studying.speechrecognizercontroller

import android.content.Context
import android.speech.SpeechRecognizer

class SpeechRecognizerControllerFactory {

    companion object {

        fun getSpeechRecognizerController(context: Context) : SpeechRecognizerController =
                            DefaultSpeechRecognizerController(SpeechRecognizer.createSpeechRecognizer(context))

    }

}