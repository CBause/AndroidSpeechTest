package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.getViewModelFactory

class ExternalSpeechRecognitionFragment : Fragment() {
    private val viewModel by viewModels<ExternalSpeechRecognitionViewModel> {getViewModelFactory()}
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var buttonExternalSpeechRecognizerStart: ImageButton
    private lateinit var editTextExternalSpeechRecognizerOutput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.external_speechrecognitionfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonExternalSpeechRecognizerStart = view.findViewById(R.id.buttonStartExternalSpeechRecognition)
        editTextExternalSpeechRecognizerOutput = view.findViewById(R.id.editTextExternalSpeechRecognizerOutput)
        setupButtonExternalSpeechRecognizerStart()
    }

    private fun setupButtonExternalSpeechRecognizerStart() {

    }
}