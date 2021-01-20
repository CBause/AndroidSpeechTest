package bigchris.studying.androidspeechtest.directspeechrecognition

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
    private val permissionRequestRecordAudioLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it)
            viewModel.startSpeechRecognition()
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
        startViewModelObservations()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.removeSpeechRecognizerController()
    }


    private fun setupClickListeners() {
        setStartOnClickListener()
        setStopOnClickListener()
        setClearTextViewOnClickListener()
    }

    private fun checkRecordAudioPermission() =
        (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)

    private fun setStartOnClickListener() {
        buttonDirectSpeechRecognizerStart.setOnClickListener {
            if (checkRecordAudioPermission())
                viewModel.startSpeechRecognition()
            else
                permissionRequestRecordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    private fun setStopOnClickListener() {
        buttonDirectSpeechRecognizerStop.setOnClickListener {
            viewModel.stopSpeechRecognition()
        }
    }

    private fun setClearTextViewOnClickListener() {
        buttonClearTextView.setOnClickListener {
            if (this::editTextDirectSpeechRecognizerOutput.isInitialized)
                editTextDirectSpeechRecognizerOutput.text.clear()
        }
    }

    private fun startViewModelObservations() {
        viewModel.speechRecognitionError.observe(viewLifecycleOwner, getSpeechRecognizerErrorObserver())
        viewModel.currentResultsList.observe(viewLifecycleOwner, getSpeechRecognitionResultsObserver())
        viewModel.currentPartialResultsList.observe(viewLifecycleOwner, getSpeechRecognitionPartialResultsObserver())
        viewModel.speechRecognizerStarted.observe(viewLifecycleOwner, getSpeechRecognizerStartedObserver())
    }

    private fun getSpeechRecognizerErrorObserver() = Observer<Pair<Int, String>?> {
        if (it != null)
            Snackbar.make(requireView(), "Error occurred! Code: ${it.first.toString()}, Description: ${it.second
            }", Snackbar.LENGTH_SHORT).show()
    }

    private fun getSpeechRecognitionResultsObserver() = Observer<List<String>> {
        editTextDirectSpeechRecognizerOutput.text.clear()
        it.forEach {currentString ->
            editTextDirectSpeechRecognizerOutput.text = editTextDirectSpeechRecognizerOutput.text.append("$currentString")
        }
    }

    private fun getSpeechRecognitionPartialResultsObserver() = getSpeechRecognitionResultsObserver()

    private fun getSpeechRecognizerStartedObserver() = Observer<Boolean> {
        buttonDirectSpeechRecognizerStart.isEnabled = !it
        buttonDirectSpeechRecognizerStop.isEnabled = it
        if (it)
            editTextDirectSpeechRecognizerOutput.text.clear()
    }

}