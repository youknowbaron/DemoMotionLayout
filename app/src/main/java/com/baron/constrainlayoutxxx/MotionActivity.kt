package com.baron.constrainlayoutxxx

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.motion.MotionLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.baron.constrainlayoutxxx.MainActivity.Companion.LAYOUT_ID
import com.baron.constrainlayoutxxx.MainActivity.Companion.SHOW_PATH
import com.baron.constrainlayoutxxx.adapter.ViewPagerAdapter

@RequiresApi(Build.VERSION_CODES.LOLLIPOP) // for View#clipToOutline
class MotionActivity : AppCompatActivity() {

    private lateinit var container: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = intent.getIntExtra(LAYOUT_ID, R.layout.fragment_motion_very_basic)
        setContentView(layout)
        container = findViewById(R.id.motionLayout)

        when (layout) {
            R.layout.fragment_motion_coordinator_layout -> {
                container.findViewById<ImageView>(R.id.back).setOnClickListener {
                    onBackPressed()
                }
            }
            R.layout.fragment_motion_view_pager -> {
                val adapter = ViewPagerAdapter(supportFragmentManager)
                adapter.addPage("Page 1", R.layout.fragment_page)
                adapter.addPage("Page 2", R.layout.fragment_page)
                adapter.addPage("Page 3", R.layout.fragment_page)
                val pager = findViewById<ViewPager>(R.id.pager)
                val tabs = findViewById<TabLayout>(R.id.tabs)
                pager.adapter = adapter
                tabs.setupWithViewPager(pager)
                pager.addOnPageChangeListener(container as ViewPager.OnPageChangeListener)
            }
        }

        val debugMode = if (intent.getBooleanExtra(SHOW_PATH, false)) {
            MotionLayout.DEBUG_SHOW_PATH
        } else {
            MotionLayout.DEBUG_SHOW_NONE
        }

        (container as? MotionLayout)?.setDebugMode(debugMode)
    }

    fun changeState(v: View?) {
        val motionLayout = container as? MotionLayout ?: return
        if (motionLayout.progress > 0.5f) {
            motionLayout.transitionToStart()
        } else {
            motionLayout.transitionToEnd()
        }
    }
}