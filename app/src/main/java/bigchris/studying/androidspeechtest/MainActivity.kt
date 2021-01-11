package bigchris.studying.androidspeechtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var mainFrameLayout: FrameLayout
    private val mainViewModel : MainViewModel by viewModels() {ViewModelFactory()}
    private val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.i("MAINACTIVITY", it.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        initFrameLayout()
    }

    fun initFrameLayout() {
        mainFrameLayout = findViewById(R.id.frameLayoutMain)
        MainFragmentController.setInitialFragment(supportFragmentManager)

    }

}