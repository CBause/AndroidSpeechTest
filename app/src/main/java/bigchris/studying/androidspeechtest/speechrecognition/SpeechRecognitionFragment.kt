package bigchris.studying.androidspeechtest.speechrecognition

import android.os.Bundle
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
import bigchris.studying.speechrecognitionresultresolver.SpeechRecognitionResultResolver
import bigchris.studying.speechrecognitionresultresolver.SpeechRecognitionResultResolverFactory
import com.google.android.material.snackbar.Snackbar

class SpeechRecognitionFragment : Fragment(), Tagged {
    override val TAG = "SPEECHRECOGNITIONFRAGMENT"
    private val viewModel by viewModels<SpeechRecognitionViewModel> { getViewModelFactory() }
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var textViewSpeechRecognizerOutput: TextView
    private lateinit var buttonStartSpeechRecognition: ImageButton
    private val activityResultLauncher = getSpeechRecognitionActivityLauncher()

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
                activityResultLauncher.launch(viewModel.getSpeechRecognizerIntent(context))
        }
    }

    private fun getSpeechRecognitionActivityLauncher() = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
        val speechRecgnitionResultResolver: SpeechRecognitionResultResolver = SpeechRecognitionResultResolverFactory.getInstance(result.data)
        if (this::textViewSpeechRecognizerOutput.isInitialized && result.data != null) {
            textViewSpeechRecognizerOutput.text = speechRecgnitionResultResolver.getText()
            speechRecgnitionResultResolver.getConfidencePerStringMapOrNull()?.let {
                logPairList(it)
            }
        } else {
            Snackbar.make(requireView(), speechRecgnitionResultResolver.getResultCodeString(result.resultCode), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun logPairList(pairList: List<Pair<Any, Any>>) {
        for ((index, currentPair) in pairList.withIndex()) {
            Log.v(TAG, "$index. ${currentPair.first.toString()} : ${currentPair.second.toString()}")
        }
    }
}