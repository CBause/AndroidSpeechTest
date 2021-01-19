package bigchris.studying.androidspeechtest

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bigchris.studying.androidspeechtest.directspeechrecognition.DirectSpeechRecognitionViewModel
import bigchris.studying.androidspeechtest.externalspeechrecognition.ExternalSpeechRecognitionViewModel
import bigchris.studying.androidspeechtest.speechrecognition.SpeechRecognitionViewModel

class ViewModelFactory(private val getMainViewModel: Boolean) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        val mainViewModel: MainViewModel by lazy {MainViewModel()}
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            getMainViewModel || isAssignableFrom(MainViewModel::class.java) -> ViewModelFactory.mainViewModel
            isAssignableFrom(DirectSpeechRecognitionViewModel::class.java) -> DirectSpeechRecognitionViewModel()
            isAssignableFrom(SpeechRecognitionViewModel::class.java) -> SpeechRecognitionViewModel()
            isAssignableFrom(ExternalSpeechRecognitionViewModel::class.java) -> ExternalSpeechRecognitionViewModel()
            else -> throw Exception("Undefined viewmodel")
        }
    } as T
}

fun Fragment.getViewModelFactory(getMainViewModel: Boolean = false) = ViewModelFactory(getMainViewModel)
fun AppCompatActivity.getViewModelFactory(getMainViewModel: Boolean = false) = ViewModelFactory(getMainViewModel)