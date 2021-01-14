package bigchris.studying.androidspeechtest.directspeechrecognition

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.androidspeechtest.getViewModelFactory
import com.google.android.material.snackbar.Snackbar

class DirectSpeechRecognitionFragment : Fragment(), Tagged {
    override val TAG: String = "DIRECTSPEECHRECOGNITIONFRAGMENT"
    private val viewModel by viewModels<DirectSpeechRecognitionViewModel> {getViewModelFactory()}
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var buttonDirectSpeechRecognizerStart: Button
    private lateinit var buttonDirectSpeechRecognizerStop: Button
    private lateinit var buttonClearTextView: Button
    private lateinit var editTextDirectSpeechRecognizerOutput: EditText
    private var gotNeededPermissions = false
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        gotNeededPermissions = it
    }

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
        buttonDirectSpeechRecognizerStop = view.findViewById(R.id.buttonSpeechRecognizerStop)
        buttonClearTextView = view.findViewById(R.id.buttonClearView)
        editTextDirectSpeechRecognizerOutput = view.findViewById(R.id.editTextDirectSpeechRecognizerOutput)
        viewModel.initializeSpeechRecognizerController(requireContext())
        setupClickListeners()
        setupViewModelObservations()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.removeSpeechRecognizerController()
    }

    private fun checkForPermissions() {
        if (!gotNeededPermissions)
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    private fun setupClickListeners() {
        setStartOnClickListener()
        setStopOnClickListener()
        setClearTextViewOnClickListener()
    }

    private fun setStartOnClickListener() {
        buttonDirectSpeechRecognizerStart.setOnClickListener {
            checkForPermissions()
            viewModel.startSpeechRecognition()
        }
    }

    private fun setStopOnClickListener() {
        buttonDirectSpeechRecognizerStop.setOnClickListener {
            checkForPermissions()
            viewModel.stopSpeechRecognition()
        }
    }

    private fun setClearTextViewOnClickListener() {
        buttonClearTextView.setOnClickListener {
            if (this::editTextDirectSpeechRecognizerOutput.isInitialized)
                editTextDirectSpeechRecognizerOutput.text.clear()
        }
    }

    private fun setupViewModelObservations() {
        viewModel.speechRecognitionError.observe(viewLifecycleOwner, getSpeechRecognizerErrorObserver())
        viewModel.speechRecognizerStarted.observe(viewLifecycleOwner, getSpeechRecognizerStartedObserver())
        viewModel.currentPartialResultsList.observe(viewLifecycleOwner, getSpeechRecognitionPartialResultsObserver())
        viewModel.currentResultsList.observe(viewLifecycleOwner, getSpeechRecognitionResultsObserver())
    }

    private fun getSpeechRecognizerStartedObserver() = Observer<Boolean> {
        buttonDirectSpeechRecognizerStart.isEnabled = !it
        buttonDirectSpeechRecognizerStop.isEnabled = it
        if (it)
            editTextDirectSpeechRecognizerOutput.text.clear()
    }

    private fun getSpeechRecognizerErrorObserver() = Observer<Pair<Int, String>?> {
        if (it != null)
            Snackbar.make(requireView(), "Error happened! Code: ${it.first.toString()}, Description: ${it.second}", Snackbar.LENGTH_LONG).show()
    }

    private fun getSpeechRecognitionPartialResultsObserver() = getSpeechRecognitionResultsObserver() /*Observer<List<String>> {
        editTextDirectSpeechRecognizerOutput.text.clear()
        it.forEach {currentString ->
            editTextDirectSpeechRecognizerOutput.text = editTextDirectSpeechRecognizerOutput.text.append(" $currentString")
        }
    }*/

    private fun getSpeechRecognitionResultsObserver() = Observer<List<String>> {
        editTextDirectSpeechRecognizerOutput.text.clear()
        it.forEach {currentString ->
            editTextDirectSpeechRecognizerOutput.text = editTextDirectSpeechRecognizerOutput.text.append(" $currentString")
        }
    }

}