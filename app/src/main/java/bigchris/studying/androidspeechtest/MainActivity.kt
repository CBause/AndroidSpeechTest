package bigchris.studying.androidspeechtest

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), Tagged {
    override val TAG = "MAINACTIVITY"
    private lateinit var mainFrameLayout: FrameLayout
    private val mainViewModel by viewModels<MainViewModel> {getViewModelFactory(true)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFrameLayout()
    }

    fun initFrameLayout() {
        mainFrameLayout = findViewById(R.id.frameLayoutMain)
        MainFragmentController.setInitialFragment(supportFragmentManager)
    }

}