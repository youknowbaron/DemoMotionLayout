package com.baron.constrainlayoutxxx

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.CompoundButton
import com.baron.constrainlayoutxxx.adapter.TitleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    private var doShowPaths = true
    lateinit var adapter: RecyclerView.Adapter<*>

    private val dataSet: Array<TitleAdapter.TitleItem> = arrayOf(
        TitleAdapter.TitleItem(
            "1. Very Simple",
            "Vuốt qua vuốt về cho vui, không vui thì thôi.",
            R.layout.fragment_motion_very_basic
        ),
        TitleAdapter.TitleItem(
            "2. Very Simple Too",
            "Cũng giống cái trên, nhưng cách làm khác (dùng ConstraintSet).",
            R.layout.fragment_motion_very_basic2
        ),
        TitleAdapter.TitleItem(
            "3. Key Frame",
            "Bẻ là phải cong.",
            R.layout.fragment_motion_key_frame
        ),
        TitleAdapter.TitleItem(
            "4. Key Frame",
            "Đường cong uốn lượn.",
            R.layout.fragment_motion_key_frame_cycle
        ),
        TitleAdapter.TitleItem(
            "5. Key Frame Interpolation",
            "Vuốt nhẹ một phát, mát cả đời.",
            R.layout.fragment_motion_key_frame_interpolation
        ),
        TitleAdapter.TitleItem(
            "6. CoordinatorLayout Example",
            "Một con vịt xòe ra hai cái cánh.",
            R.layout.fragment_motion_coordinator_layout
        ),
        TitleAdapter.TitleItem(
            "7. DrawerLayout Example",
            "Cũng là vịt nhưng vẫn xòe hai cái cánh.",
            R.layout.fragment_motion_drawer_layout
        ),
        TitleAdapter.TitleItem(
            "8. ViewPager Example",
            "Đua xe với cô không?",
            R.layout.fragment_motion_view_pager
        ),
        TitleAdapter.TitleItem(
            "9. Complex Motion Example",
            ".",
            R.layout.fragment_motion_reveal
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = TitleAdapter(dataSet)
        rcvTitle.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }

        showPaths.setOnCheckedChangeListener(this)
        showPaths.isChecked = true
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        doShowPaths = isChecked
    }

    fun start(activity: Class<*>, layoutFileId: Int) {
        val intent = Intent(this, activity).apply {
            putExtra(LAYOUT_ID, layoutFileId)
            putExtra(SHOW_PATH, doShowPaths)
        }
        startActivity(intent)
    }

    companion object {
        const val LAYOUT_ID = "layout_file_id"
        const val SHOW_PATH = "show_path"
    }
}
