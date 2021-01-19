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

class DirectSpeechRecognitionViewModel : ViewModel(), Tagged {
    override val TAG = "DIRECTSPEECHRECOGNITIONVIEWMODEL"
}