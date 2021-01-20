package bigchris.studying.androidspeechtest.directspeechrecognition

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.speechrecognitionintentbuilder.SpeechRecognitionIntentBuilderFactory
import bigchris.studying.speechrecognizercontroller.SpeechRecognizerController
import bigchris.studying.speechrecognizercontroller.SpeechRecognizerControllerFactory
import bigchris.studying.speechrecognizercontroller.SpeechRecognizerControllerListener

class DirectSpeechRecognitionViewModel : ViewModel(), Tagged, SpeechRecognizerControllerListener {
    override val TAG = "DIRECTSPEECHRECOGNITIONVIEWMODEL"
    var speechRecognizerController: SpeechRecognizerController? = null
    val speechRecognizerStarted: LiveData<Boolean> = MutableLiveData(false)
    val currentResultsList: LiveData<List<String>> = MutableLiveData(arrayListOf())
    val currentPartialResultsList: LiveData<List<String>> = MutableLiveData(arrayListOf())
    val speechRecognitionError: LiveData<Pair<Int, String>?> = MutableLiveData<Pair<Int, String>?>(null)

    fun getSpeechRecognitionIntent() : Intent = with(SpeechRecognitionIntentBuilderFactory.getSpeechRecognitionIntentBuilder()) {
        this.setExtraPartialResults(true)
        this.getResult()
    }

    fun initializeSpeechRecognizerController(context: Context) {
        if (speechRecognizerController == null)
            speechRecognizerController = SpeechRecognizerControllerFactory.getSpeechRecognizerController(context).also {
                it.registerSpeechRecognizerControllerListener(this)
            }
    }

    fun removeSpeechRecognizerController() {
        if (speechRecognizerController != null) {
            speechRecognizerController?.unregisterSpeechRecognizerControllerListener(this)
            speechRecognizerController = null
        }
    }

    fun startSpeechRecognition() {
        speechRecognizerController?.startListening(getSpeechRecognitionIntent())
    }

    fun stopSpeechRecognition() {
        speechRecognizerController?.stopListening()
    }


    override fun onSpeechRecognitionError(errorCode: Int, errorString: String) {
        (speechRecognitionError as MutableLiveData).value = Pair(errorCode, errorString)
    }

    override fun onSpeechRecognitionResults(resultStrings: ArrayList<String>) {
        (currentResultsList as MutableLiveData).value = arrayListOf(resultStrings[0])
    }

    override fun onSpeechRecognitionPartialResults(resultStrings: ArrayList<String>) {
        if (resultStrings.size > 0 && resultStrings[0].isNotEmpty() && (currentPartialResultsList.value!!.isEmpty() || resultStrings[0].length >
                    currentPartialResultsList.value!![0].length))
            (currentPartialResultsList as MutableLiveData).value = resultStrings
    }

    override fun onSpeechRecognitionStarted() {
        (speechRecognizerStarted as MutableLiveData).value = true
    }

    override fun onSpeechRecognitionStopped() {
        (speechRecognizerStarted as MutableLiveData).value = false
        (currentPartialResultsList.value as ArrayList<String>).clear()
    }
}