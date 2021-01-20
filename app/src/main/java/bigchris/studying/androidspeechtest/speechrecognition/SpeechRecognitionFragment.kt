package bigchris.studying.androidspeechtest.speechrecognition

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.androidspeechtest.getViewModelFactory
import bigchris.studying.speechrecognitionresultmediator.SpeechRecognitionResultMediatorFactory
import com.google.android.material.snackbar.Snackbar

class SpeechRecognitionFragment : Fragment(), Tagged {
    override val TAG = "SPEECHRECOGNITIONFRAGMENT"
    private val viewModel by viewModels<SpeechRecognitionViewModel> { getViewModelFactory() }
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var textViewSpeechRecognizerOutput: TextView
    private lateinit var buttonStartSpeechRecognition: ImageButton
    private val speechRecognitionActivityLauncher = getSpeechRecognitionActivityLauncher()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.speechrecognition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewSpeechRecognizerOutput = view.findViewById(R.id.textViewSpeechRecognizerOutput)
        textViewSpeechRecognizerOutput.movementMethod = ScrollingMovementMethod()
        textViewSpeechRecognizerOutput.setHorizontallyScrolling(true)
        buttonStartSpeechRecognition = view.findViewById(R.id.buttonStartSpeechRecognition)
        setupExternalSpeechRecognitionButton()
    }

    private fun setupExternalSpeechRecognitionButton() {
        buttonStartSpeechRecognition.setOnClickListener {
            speechRecognitionActivityLauncher.launch(viewModel.getSpeechRecognitionIntent())
        }
    }

    private fun getSpeechRecognitionActivityLauncher() = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.data != null && this::textViewSpeechRecognizerOutput.isInitialized) {
            val speechRecognitionResultMediator = SpeechRecognitionResultMediatorFactory.getSpeechRecognitionResultMediator(it.data!!)
            textViewSpeechRecognizerOutput.text = speechRecognitionResultMediator.getTextsOrNull()?.get(0)
            speechRecognitionResultMediator.getConfidencePerStringOrNull()?.let {
                logPairList(it)
            }
        } else {
            Snackbar.make(requireView(), "Error happened. Result code: ${it.resultCode.toString()}", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun logPairList(pairList: List<Pair<Any, Any>>) {
        for ((index, currentPair) in pairList.withIndex()) {
            Log.v(TAG, "$index, ${currentPair.first.toString()} : ${currentPair.second.toString()}")
        }
    }

}