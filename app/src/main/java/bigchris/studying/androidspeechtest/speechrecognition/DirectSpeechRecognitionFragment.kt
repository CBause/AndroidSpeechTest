package bigchris.studying.androidspeechtest.speechrecognition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.ViewModelFactory
import bigchris.studying.speechrecognizer.SpeechRecognizerController
import bigchris.studying.speechrecognizer.SpeechRecognizerControllerFactory

class DirectSpeechRecognitionFragment : Fragment() {
    private val viewModel: DirectSpeechRecognitionViewModel by viewModels() {ViewModelFactory()}
    private val mainViewModel: MainViewModel by viewModels() {ViewModelFactory(true)}
    private lateinit var buttonSpeechRecognizerStart: Button
    private lateinit var buttonSpeechRecognizerStop: Button
    private lateinit var buttonSpeechRecognizerGetCurrrent: Button
    private lateinit var buttonClearTextView: Button
    private lateinit var editTextSpeechRecognizerOutput: EditText
    private val speechRecognizerController: SpeechRecognizerController by lazy { SpeechRecognizerControllerFactory().getInstance(requireActivity().baseContext) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.directspeechrecognition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSpeechRecognizerStart = view.findViewById(R.id.buttonSpeechRecognizerStart)
        buttonSpeechRecognizerGetCurrrent = view.findViewById(R.id.buttonSpeechRecognizerGetCurrentResult)
        buttonSpeechRecognizerStop = view.findViewById(R.id.buttonSpeechRecognizerStop)
        buttonClearTextView = view.findViewById(R.id.buttonClearView)
        editTextSpeechRecognizerOutput = view.findViewById(R.id.editTextDirectSpeechRecognizerOutput)
        setupClickListeners()
    }

    fun setupClickListeners() {
        setStartOnClickListener()
        setStopOnClickListener()
        setGetCurrentClickListener()
        setClearTextViewOnClickListener()
    }

    fun setStartOnClickListener() {

    }

    fun setStopOnClickListener() {

    }

    fun setGetCurrentClickListener() {

    }

    fun setClearTextViewOnClickListener() {

    }
}