package bigchris.studying.speechrecognitionintentbuilder

class SpeechRecognitionIntentBuilderFactory {

    companion object {

        fun getSpeechRecognitionIntentBuilder() : SpeechRecognitionIntentBuilder = DefaultSpeechRecognitionIntentBuilder()

    }
}
