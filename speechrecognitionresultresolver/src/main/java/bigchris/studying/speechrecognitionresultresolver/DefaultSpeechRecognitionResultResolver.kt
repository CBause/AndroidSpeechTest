package bigchris.studying.speechrecognitionresultresolver

import android.content.Intent
import android.speech.RecognizerIntent

internal class DefaultSpeechRecognitionResultResolver(val resultIntent: Intent) : SpeechRecognitionResultResolver {

    override fun getText() : String = with(StringBuilder()) {
        val resultStrings = getTextsOrNull()
        if (resultStrings != null) {
            resultStrings.forEach {string->
                this.append(string)
                this.append(";")
            }
            return this.toString()
        }
        return ""
    }

    override fun getTextsOrNull() = resultIntent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

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

    override fun getConfidenceScoresOrNull(): FloatArray? {
        return resultIntent.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES)
    }


}