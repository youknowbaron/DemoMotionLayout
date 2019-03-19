package com.baron.constrainlayoutxxx

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.motion.MotionLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.baron.constrainlayoutxxx.MainActivity.Companion.LAYOUT_ID
import com.baron.constrainlayoutxxx.MainActivity.Companion.SHOW_PATH

@RequiresApi(Build.VERSION_CODES.LOLLIPOP) // for View#clipToOutline
class MotionActivity : AppCompatActivity() {

    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = intent.getIntExtra(LAYOUT_ID, R.layout.fragment_motion_very_basic)
        setContentView(layout)
        container = findViewById(R.id.motionLayout)

        val debugMode = if (intent.getBooleanExtra(SHOW_PATH, false)) {
            MotionLayout.DEBUG_SHOW_PATH
        } else {
            MotionLayout.DEBUG_SHOW_NONE
        }

        (container as? MotionLayout)?.setDebugMode(debugMode)
    }
}