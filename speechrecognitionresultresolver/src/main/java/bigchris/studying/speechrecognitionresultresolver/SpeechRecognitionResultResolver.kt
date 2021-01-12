package bigchris.studying.speechrecognitionresultresolver

interface SpeechRecognitionResultResolver {

    fun getText() : String

    fun getTextsOrNull() : ArrayList<String>?

    fun getResultCodeString(resultCode: Int) : String

    fun getConfidenceScoresOrNull() : FloatArray?
}