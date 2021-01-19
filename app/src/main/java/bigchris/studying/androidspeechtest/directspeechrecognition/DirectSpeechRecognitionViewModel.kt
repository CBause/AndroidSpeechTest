package bigchris.studying.androidspeechtest.directspeechrecognition

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionIntentBuilderFactory
import bigchris.studying.speechrecognizercontroller.SpeechRecognitionListener
import bigchris.studying.speechrecognizercontroller.SpeechRecognizerController
import bigchris.studying.speechrecognizercontroller.SpeechRecognizerControllerFactory

class DirectSpeechRecognitionViewModel : ViewModel(), Tagged, SpeechRecognitionListener {
    override val TAG = "DIRECTSPEECHRECOGNITIONVIEWMODEL"
    private var speechRecognizerController: SpeechRecognizerController? = null
    val speechRecognizerStarted: LiveData<Boolean> = MutableLiveData(false)
    val currentResultsList: LiveData<List<String>> = MutableLiveData(arrayListOf())
    val currentPartialResultsList: LiveData<List<String>> = MutableLiveData(arrayListOf())
    val speechRecognitionError: LiveData<Pair<Int, String>?> = MutableLiveData<Pair<Int, String>?>(null)

    private fun getRecognizerIntent(): Intent = with(SpeechRecognitionIntentBuilderFactory.getInstance()) {
        this.setExtraPartialResults(true)
        this.setExtraLanguage("de-DE")
        this.getResult()
    }

    fun initializeSpeechRecognizerController(context: Context) {
        if (speechRecognizerController == null) {
            speechRecognizerController = SpeechRecognizerControllerFactory.getInstance(context).also {
                it.registerSpeechRecognitionListener(this)
            }
        }
    }

    fun removeSpeechRecognizerController() {
        if (speechRecognizerController != null) {
            speechRecognizerController?.unregisterSpeechRecognitionListener(this)
            speechRecognizerController = null
        }
    }

    fun startSpeechRecognition() {
        speechRecognizerController?.startListening(getRecognizerIntent())
    }

    fun stopSpeechRecognition() {
        speechRecognizerController?.stopListening()
    }

    override fun onSpeechRecognitionError(errorCode: Int, errorString: String) {
        (speechRecognitionError as MutableLiveData<Pair<Int, String>?>).value = Pair(errorCode, errorString)
    }

    override fun onSpeechRecognitionResults(resultStrings: ArrayList<String>) {
        (currentResultsList as MutableLiveData<List<String>>).value = arrayListOf(resultStrings[0])
    }

    override fun onSpeechRecognitionPartialResults(resultStrings: ArrayList<String>) {
        if (resultStrings.size > 0 && resultStrings[0].isNotEmpty() &&
                (currentPartialResultsList.value!!.isEmpty() ||resultStrings[0].length > currentPartialResultsList.value!![0].length))
            (currentPartialResultsList as MutableLiveData).value = resultStrings
    }

    override fun onSpeechRecognitionStarted() {
        (speechRecognizerStarted as MutableLiveData<Boolean>).value = true
    }

    override fun onSpeechRecognitionStopped() {
        (speechRecognizerStarted as MutableLiveData<Boolean>).value = false
        (currentPartialResultsList.value as ArrayList<String>).clear()
    }
}