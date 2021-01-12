package bigchris.studying.speechrecognitionresultresolver

import android.content.Intent

class SpeechRecognitionResultResolverFactory {

    companion object {
        fun getInstance(resultIntent: Intent) : SpeechRecognitionResultResolver = DefaultSpeechRecognitionResultResolver(resultIntent)
    }
}