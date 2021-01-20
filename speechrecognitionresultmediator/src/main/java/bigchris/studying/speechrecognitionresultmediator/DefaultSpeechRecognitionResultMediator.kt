package bigchris.studying.speechrecognitionresultmediator

import android.content.Intent
import android.speech.RecognizerIntent

internal class DefaultSpeechRecognitionResultMediator(val resultIntent: Intent) : SpeechRecognitionResultMediator {

    override fun getTextsOrNull(): ArrayList<String>? = resultIntent.getStringArrayListExtra(
        RecognizerIntent.EXTRA_RESULTS)

    override fun getResultCodeString(resultCode: Int) = with(resultCode) {
        when (this) {
            RecognizerIntent.RESULT_AUDIO_ERROR -> "Audio error"
            RecognizerIntent.RESULT_CLIENT_ERROR -> "Client error"
            RecognizerIntent.RESULT_NETWORK_ERROR -> "Network error"
            RecognizerIntent.RESULT_NO_MATCH -> "No match"
            RecognizerIntent.RESULT_SERVER_ERROR -> "Server"
            else -> "Unknown error"
        }
    }

    override fun getConfidenceScoresOrNull(): FloatArray? = resultIntent.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES)

    override fun getConfidencePerStringOrNull(): List<Pair<Float, String>>? {
        val stringList: ArrayList<String>? = getTextsOrNull()
        val confidenceArray: FloatArray? = getConfidenceScoresOrNull()
        var resultList: List<Pair<Float, String>>? = null
        if (stringList != null && confidenceArray != null) {
            resultList = confidenceArray.toCollection(ArrayList<Float>()).zip(stringList)
        }
        return resultList
    }
}