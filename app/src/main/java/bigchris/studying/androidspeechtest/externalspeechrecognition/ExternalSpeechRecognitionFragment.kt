package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.getViewModelFactory

class ExternalSpeechRecognitionFragment : Fragment() {
    private val viewModel by viewModels<ExternalSpeechRecognitionViewModel> { getViewModelFactory() }
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var editTextExternalSpeechRecognizerOutput: EditText
    private lateinit var buttonStartExternalSpeechRecognition: ImageButton
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
        if (this::editTextExternalSpeechRecognizerOutput.isInitialized) {
            editTextExternalSpeechRecognizerOutput.setText(result.data.toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.externalspeechrecognition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextExternalSpeechRecognizerOutput = view.findViewById(R.id.editTextExternalSpeechRecognizerOutput)
        buttonStartExternalSpeechRecognition = view.findViewById(R.id.buttonStartExternalSpeechRecognition)
        setupExternalSpeechRecognitionButton()
    }

    private fun setupExternalSpeechRecognitionButton() {
        buttonStartExternalSpeechRecognition.setOnClickListener {
            activityResultLauncher.launch(viewModel.getExternalSpeechRecognizerIntent())
        }
    }
}