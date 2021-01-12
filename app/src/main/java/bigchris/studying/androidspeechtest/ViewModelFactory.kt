package bigchris.studying.androidspeechtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import bigchris.studying.androidspeechtest.directspeechrecognition.DirectSpeechRecognitionViewModel
import bigchris.studying.androidspeechtest.externalspeechrecognition.ExternalSpeechRecognitionViewModel

class ViewModelFactory(owner: SavedStateRegistryOwner,
                       private val getMainViewModel: Boolean = false,
                       arguments: Bundle? = null) : AbstractSavedStateViewModelFactory(owner, arguments) {

    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle) = with(modelClass) {
        when {
            getMainViewModel || isAssignableFrom(MainViewModel::class.java) -> MainViewModel()
            isAssignableFrom(DirectSpeechRecognitionViewModel::class.java) -> DirectSpeechRecognitionViewModel()
            isAssignableFrom(ExternalSpeechRecognitionViewModel::class.java) -> ExternalSpeechRecognitionViewModel()
            else -> throw Exception("Undefined viewmodel")
        }
    } as T
}

fun Fragment.getViewModelFactory(getMainViewModel: Boolean = false) = ViewModelFactory(this, getMainViewModel)
fun AppCompatActivity.getViewModelFactory(getMainViewModel: Boolean = false) = ViewModelFactory(this, getMainViewModel)
