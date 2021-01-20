package bigchris.studying.androidspeechtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class NavigationFragment : Fragment(), Tagged {
    override val TAG = "NAVIGATIONFRAGMENT"
    private lateinit var buttonShowDirectSpeechRecognitionFragment: Button
    private lateinit var buttonShowSpeechRecognitionFragment: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.navigation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonShowDirectSpeechRecognitionFragment = view.findViewById(R.id.buttonNavigateToDirectSpeechRecognitionFragment)
        buttonShowSpeechRecognitionFragment = view.findViewById(R.id.buttonNavigateToSpeechRecognitionFragment)
        setupShowDirectSpeechRecognitionButton()
        setupShowSpeechRecognitionButton()
    }

    private fun enableAllButtons() {
        buttonShowDirectSpeechRecognitionFragment.isEnabled = true
        buttonShowSpeechRecognitionFragment.isEnabled = true
    }

    private fun initiateNavigation(view: View, fragmentTag: MainFragmentController.FRAGMENTTAGS) {
        enableAllButtons()
        view.isEnabled = false
        MainFragmentController.replaceCurrentFragment(requireActivity().supportFragmentManager, fragmentTag)
    }

    fun setupShowDirectSpeechRecognitionButton() {
        buttonShowDirectSpeechRecognitionFragment.setOnClickListener {
            initiateNavigation(it, MainFragmentController.FRAGMENTTAGS.DIRECTSPEECHRECOGNITIONFRAGMENT)
        }
    }

    fun setupShowSpeechRecognitionButton() {
        buttonShowSpeechRecognitionFragment.setOnClickListener {
            initiateNavigation(it, MainFragmentController.FRAGMENTTAGS.SPEECHRECOGNITIONFRAGMENT)
        }
    }


}