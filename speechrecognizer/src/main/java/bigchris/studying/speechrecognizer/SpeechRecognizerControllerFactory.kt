package bigchris.studying.speechrecognizer

import android.content.Context

class SpeechRecognizerControllerFactory {

    fun getInstance(context: Context) : SpeechRecognizerController {
        return DefaultSpeechRecognizerController(context)
    }
}