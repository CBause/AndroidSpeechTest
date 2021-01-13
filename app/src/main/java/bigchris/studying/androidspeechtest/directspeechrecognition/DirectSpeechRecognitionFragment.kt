package bigchris.studying.androidspeechtest.directspeechrecognition

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
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.androidspeechtest.getViewModelFactory

class DirectSpeechRecognitionFragment : Fragment(), Tagged {
    override val TAG: String = "DIRECTSPEECHRECOGNITIONFRAGMENT"
    private val viewModel by viewModels<DirectSpeechRecognitionViewModel> {getViewModelFactory()}
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var buttonDirectSpeechRecognizerStart: Button
    private lateinit var buttonDirectSpeechRecognizerStop: Button
    private lateinit var buttonDirectSpeechRecognizerGetCurrrent: Button
    private lateinit var buttonClearTextView: Button
    private lateinit var editTextDirectSpeechRecognizerOutput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.directspeechrecognition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonDirectSpeechRecognizerStart = view.findViewById(R.id.buttonSpeechRecognizerStart)
        buttonDirectSpeechRecognizerGetCurrrent = view.findViewById(R.id.buttonSpeechRecognizerGetCurrentResult)
        buttonDirectSpeechRecognizerStop = view.findViewById(R.id.buttonSpeechRecognizerStop)
        buttonClearTextView = view.findViewById(R.id.buttonClearView)
        editTextDirectSpeechRecognizerOutput = view.findViewById(R.id.editTextDirectSpeechRecognizerOutput)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        setStartOnClickListener()
        setStopOnClickListener()
        setGetCurrentClickListener()
        setClearTextViewOnClickListener()
    }

    private fun setStartOnClickListener() {

    }

    private fun setStopOnClickListener() {

    }

    private fun setGetCurrentClickListener() {

    }

    private fun setClearTextViewOnClickListener() {

    }
}