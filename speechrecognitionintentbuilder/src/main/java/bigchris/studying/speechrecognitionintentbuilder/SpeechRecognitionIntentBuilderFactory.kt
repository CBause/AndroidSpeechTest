package bigchris.studying.speechrecognitionintentbuilder

class SpeechRecognitionIntentBuilderFactory {

        companion object {
                fun getInstance(): SpeechRecognitionIntentBuilder = DefaultSpeechRecognitionIntentBuilder()
        }
}