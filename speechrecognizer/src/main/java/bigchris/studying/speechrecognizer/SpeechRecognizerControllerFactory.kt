package bigchris.studying.speechrecognizer

import android.content.Context

class SpeechRecognizerControllerFactory {

    companion object {
        fun getInstance(context: Context): SpeechRecognizerController {
            return DefaultSpeechRecognizerController(context)
        }
    }
}