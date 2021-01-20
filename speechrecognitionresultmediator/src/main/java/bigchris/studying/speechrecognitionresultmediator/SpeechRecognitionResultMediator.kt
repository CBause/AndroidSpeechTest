package bigchris.studying.speechrecognitionresultmediator

interface SpeechRecognitionResultMediator {

    fun getTextsOrNull() : ArrayList<String>?

    fun getResultCodeString(resultCode: Int) : String

    fun getConfidenceScoresOrNull() : FloatArray?

    fun getConfidencePerStringOrNull() : List<Pair<Float, String>>?
}