package bigchris.studying.androidspeechtest.speechrecognition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.getViewModelFactory

class SpeechRecognitionFragment : Fragment() {
    private val viewModel by viewModels<SpeechRecognitionViewModel> { getViewModelFactory() }
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var textViewSpeechRecognizerOutput: TextView
    private lateinit var buttonStartSpeechRecognition: ImageButton
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
        if (this::textViewSpeechRecognizerOutput.isInitialized) {
            textViewSpeechRecognizerOutput.setText(result.resultCode.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.speechrecognition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewSpeechRecognizerOutput = view.findViewById(R.id.textViewSpeechRecognizerOutput)
        buttonStartSpeechRecognition = view.findViewById(R.id.buttonStartSpeechRecognition)
        setupExternalSpeechRecognitionButton()
    }

    private fun setupExternalSpeechRecognitionButton() {
        buttonStartSpeechRecognition.setOnClickListener {
            activityResultLauncher.launch(viewModel.getExternalSpeechRecognizerIntent())
        }
    }
}