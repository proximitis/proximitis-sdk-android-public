package cz.proximitis.sdkSample.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityLayout = activityLayout
        if (activityLayout != 0) {
            setContentView(activityLayout)
        }
    }

    @get:LayoutRes
    abstract val activityLayout: Int
}