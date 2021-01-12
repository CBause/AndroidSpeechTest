package bigchris.studying.androidspeechtest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import bigchris.studying.androidspeechtest.externalspeechrecognition.ExternalSpeechRecognitionFragment
import bigchris.studying.androidspeechtest.directspeechrecognition.DirectSpeechRecognitionFragment
import java.lang.IllegalArgumentException

object MainFragmentController {
    val frameLayoutId = R.id.frameLayoutMain
    val initialFragmentClass = FRAGMENTTAGS.EXTERNALSPEECHRECOGNITIONFRAGMENT
    enum class FRAGMENTTAGS {
        DIRECTSPEECHRECOGNITIONFRAGMENT,
        EXTERNALSPEECHRECOGNITIONFRAGMENT
    }

    private fun createFragment(fragmentTag: FRAGMENTTAGS) = with(fragmentTag) {
        when(fragmentTag) {
            FRAGMENTTAGS.DIRECTSPEECHRECOGNITIONFRAGMENT -> DirectSpeechRecognitionFragment()
            FRAGMENTTAGS.EXTERNALSPEECHRECOGNITIONFRAGMENT -> ExternalSpeechRecognitionFragment()
            else -> throw IllegalArgumentException("Undefined or unknown fragment")
        }
    }

    private fun getStoppedFragmentOrNull(fragmentManager: FragmentManager, fragmentTag: FRAGMENTTAGS) : Fragment? = with(fragmentManager) {
        this.findFragmentByTag(fragmentTag.toString())
    }

    fun setInitialFragment(fragmentManager: FragmentManager) {
        fragmentManager.run {
            beginTransaction()
                    .replace(frameLayoutId, createFragment(initialFragmentClass))
                    .commit()
        }
    }

    fun replaceCurrentFragment(fragmentManager: FragmentManager, fragmentTag: FRAGMENTTAGS) {
        val nextActiveFragment: Fragment = getStoppedFragmentOrNull(fragmentManager, fragmentTag) ?: createFragment(fragmentTag)
        fragmentManager.run {
            beginTransaction()
                    .replace(frameLayoutId, nextActiveFragment)
                    .commit()
        }
    }


}