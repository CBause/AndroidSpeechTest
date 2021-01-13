package bigchris.studying.speechrecognitionresultresolver

import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.RecognizerResultsIntent

internal class DefaultSpeechRecognitionResultResolver(val resultIntent: Intent?) : SpeechRecognitionResultResolver {

    override fun getText() : String = with(StringBuilder()) {
        val resultStrings = getTextsOrNull()
        if (resultStrings != null) {
            for ((index, element) in resultStrings.withIndex()) {
                this.append(element)
                    if (index < resultStrings.size - 1)
                        this.append(";")
            }
            return this.toString()
        }
        return ""
    }

    override fun getTextsOrNull() = resultIntent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

    override fun getResultCodeString(resultCode: Int) : String = with(resultCode) {
        when(this) {
            RecognizerIntent.RESULT_AUDIO_ERROR -> "Audio Error"
            RecognizerIntent.RESULT_CLIENT_ERROR -> "Client Error"
            RecognizerIntent.RESULT_NETWORK_ERROR -> "Network Error"
            RecognizerIntent.RESULT_NO_MATCH -> "No match"
            RecognizerIntent.RESULT_SERVER_ERROR -> "Server error"
            -1 -> "Successful"
            else -> ""
        }
    }

    override fun getConfidenceScoresOrNull(): FloatArray? = resultIntent?.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES)


    override fun getConfidencePerStringMapOrNull(): List<Pair<Float, String>>? {
        val stringList: ArrayList<String>? = getTextsOrNull()
        val confidenceArray: FloatArray? = getConfidenceScoresOrNull()
        var resultMap: List<Pair<Float, String>>? = null
        if (stringList != null && confidenceArray != null) {
            resultMap = confidenceArray.toCollection(ArrayList<Float>()).zip(stringList)
        }
        return resultMap
    }

    override fun getVoiceSearchResultStringsOrNull(): ArrayList<String>? = resultIntent?.getStringArrayListExtra(RecognizerResultsIntent.EXTRA_VOICE_SEARCH_RESULT_STRINGS)


    override fun getVoiceSearchHtmlResultStringsOrNull(): ArrayList<String>? = resultIntent?.getStringArrayListExtra(RecognizerResultsIntent.EXTRA_VOICE_SEARCH_RESULT_HTML)

}