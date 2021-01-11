package bigchris.studying.androidspeechtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bigchris.studying.androidspeechtest.speechrecognition.DirectSpeechRecognitionFragment
import bigchris.studying.androidspeechtest.speechrecognition.DirectSpeechRecognitionViewModel

class ViewModelFactory(val getMainViewModel: Boolean = false) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            getMainViewModel || isAssignableFrom(MainActivity::class.java) -> MainViewModel()
            isAssignableFrom(DirectSpeechRecognitionFragment::class.java) -> DirectSpeechRecognitionViewModel()
            else -> throw Exception("Undefined viewmodel")
        }
    } as T
}