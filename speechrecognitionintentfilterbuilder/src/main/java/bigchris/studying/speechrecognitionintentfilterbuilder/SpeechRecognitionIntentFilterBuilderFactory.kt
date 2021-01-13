package bigchris.studying.speechrecognitionintentfilterbuilder

class SpeechRecognitionIntentFilterBuilderFactory {

    companion object {
        fun getInstance(): SpeechRecognitionIntentFilterBuilder = DefaultSpeechRecognitionIntentFilterBuilder()
    }

}