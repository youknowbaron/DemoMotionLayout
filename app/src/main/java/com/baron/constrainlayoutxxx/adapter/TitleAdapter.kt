package com.baron.constrainlayoutxxx.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.baron.constrainlayoutxxx.MainActivity
import com.baron.constrainlayoutxxx.MotionActivity
import com.baron.constrainlayoutxxx.R

class TitleAdapter(private val dataSet: Array<TitleAdapter.TitleItem>) :
    RecyclerView.Adapter<TitleAdapter.ViewHolder>() {

    data class TitleItem(val title: String, val description : String, val layout : Int = 0, val activity : Class<*> = MotionActivity::class.java) {
        constructor(title: String, description: String, activity : Class<*> = MotionActivity::class.java) : this(title, description, 0, activity)
    }

    class ViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        var title = layout.findViewById(R.id.title) as TextView
        var description = layout.findViewById(R.id.description) as TextView
        var layoutFileId = 0
        var activity : Class<*>? = null

        init {
            layout.setOnClickListener { v ->
                val context = v?.context as MainActivity
                activity?.let {
                    context.start(it, layoutFileId)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): TitleAdapter.ViewHolder {
        val row = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_title, parent, false) as ConstraintLayout
        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.description.text = dataSet[position].description
        holder.layoutFileId = dataSet[position].layout
        holder.activity = dataSet[position].activity
    }

    override fun getItemCount() = dataSet.size
}