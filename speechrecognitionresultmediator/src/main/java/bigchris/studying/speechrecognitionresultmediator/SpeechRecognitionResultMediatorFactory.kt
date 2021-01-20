package bigchris.studying.speechrecognitionresultmediator

import android.content.Intent

class SpeechRecognitionResultMediatorFactory {

    companion object {

        fun getSpeechRecognitionResultMediator(resultIntent: Intent) : SpeechRecognitionResultMediator= DefaultSpeechRecognitionResultMediator(resultIntent)

    }

}