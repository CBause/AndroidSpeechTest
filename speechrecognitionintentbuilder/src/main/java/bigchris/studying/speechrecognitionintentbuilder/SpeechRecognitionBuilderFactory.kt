package bigchris.studying.speechrecognitionintentbuilder
import android.content.Intent

class SpeechRecognitionBuilderFactory {

        companion object {
                fun getInstance(resultIntent: Intent): SpeechRecognitionIntentBuilder = DefaultSpeechRecognitionBuilder()
        }
}