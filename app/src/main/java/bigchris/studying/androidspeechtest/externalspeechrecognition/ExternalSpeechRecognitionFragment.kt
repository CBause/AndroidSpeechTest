package bigchris.studying.androidspeechtest.externalspeechrecognition

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.speech.RecognizerResultsIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import bigchris.studying.androidspeechtest.MainViewModel
import bigchris.studying.androidspeechtest.R
import bigchris.studying.androidspeechtest.Tagged
import bigchris.studying.androidspeechtest.getViewModelFactory
import bigchris.studying.speechrecognitionresultresolver.SpeechRecognitionResultResolverFactory
import com.google.android.material.snackbar.Snackbar

class ExternalSpeechRecognitionFragment : Fragment(), Tagged {
    override val TAG = "EXTERNALSPEECHRECOGNITIONFRAGMENT"
    private val viewModel by viewModels<ExternalSpeechRecognitionViewModel> {getViewModelFactory()}
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}
    private lateinit var buttonExternalSpeechRecognizerStart: ImageButton
    private lateinit var editTextExternalSpeechRecognizerOutput: EditText
    private val receiver = TestBroadcastReceiver()

    inner class TestBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i(TAG, intent?.toString()!!)
        }
    }

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
        buttonExternalSpeechRecognizerStart.setOnClickListener {
            startActivity(viewModel.getExternalSpeechRecognitionIntent())
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter()
        filter.addAction(RecognizerResultsIntent.ACTION_VOICE_SEARCH_RESULTS)
        requireActivity().registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(receiver)
    }

}