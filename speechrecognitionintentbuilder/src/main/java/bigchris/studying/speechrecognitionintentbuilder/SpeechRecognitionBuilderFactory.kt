package bigchris.studying.speechrecognitionintentbuilder

class SpeechRecognitionBuilderFactory {

        companion object {
                fun getInstance(): SpeechRecognitionIntentBuilder =
                        DefaultSpeechRecognitionBuilder()
        }
}