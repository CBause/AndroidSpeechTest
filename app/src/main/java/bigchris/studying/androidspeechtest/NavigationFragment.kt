package bigchris.studying.androidspeechtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class NavigationFragment : Fragment() {
    private lateinit var buttonShowDirectSpeechRecognitionFragment: Button
    private lateinit var buttonShowExternalSpeechRecognitionFragment: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.navigation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonShowDirectSpeechRecognitionFragment = view.findViewById(R.id.buttonNavigateToDirectSpeechRecognitionFragment)
        buttonShowExternalSpeechRecognitionFragment = view.findViewById(R.id.buttonNavigateToExternalSpeechRecognitionFragment)
        setupShowDirectButton()
        setupShowExternalButton()
    }

    fun setupShowDirectButton() {
        buttonShowDirectSpeechRecognitionFragment.setOnClickListener {
            it.isEnabled = false
            buttonShowExternalSpeechRecognitionFragment.isEnabled = true
            MainFragmentController.replaceCurrentFragment(requireActivity().supportFragmentManager, MainFragmentController.FRAGMENTTAGS.DIRECTSPEECHRECOGNITIONFRAGMENT)
        }
    }

    fun setupShowExternalButton() {
        buttonShowExternalSpeechRecognitionFragment.setOnClickListener {
            it.isEnabled = false
            buttonShowDirectSpeechRecognitionFragment.isEnabled = true
            MainFragmentController.replaceCurrentFragment(requireActivity().supportFragmentManager, MainFragmentController.FRAGMENTTAGS.EXTERNALSPEECHRECOGNITIONFRAGMENT)
        }
    }


}