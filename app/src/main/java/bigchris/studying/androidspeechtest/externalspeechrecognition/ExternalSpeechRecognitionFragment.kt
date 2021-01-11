package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.ViewModelFactory
import bigchris.studying.androidspeechtest.speechrecognition.DirectSpeechRecognitionViewModel

class ExternalSpeechRecognitionFragment : Fragment() {
    private val viewModel: DirectSpeechRecognitionViewModel by viewModels() {ViewModelFactory()}
    private val mainViewModel: MainViewModel by viewModels() {ViewModelFactory(true)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.externalspeechrecognition_fragment, container, false)
    }
}