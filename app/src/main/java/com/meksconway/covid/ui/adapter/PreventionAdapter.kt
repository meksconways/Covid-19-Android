package com.meksconway.covid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.meksconway.covid.R
import com.meksconway.covid.data.model.homecontent.PreventionModel

class PreventionAdapter : RecyclerView.Adapter<PreventionAdapter.PreventionVH>() {

    private val contentList = arrayListOf<PreventionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreventionVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_prevention, parent, false)
        return PreventionVH(view)
    }

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: PreventionVH, position: Int) {
        holder.bind(contentList[position])
    }

    fun setItems(content: List<PreventionModel>) {
        contentList.clear()
        contentList.addAll(content)
        notifyDataSetChanged()
    }

    class PreventionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val lottieView = itemView
            .findViewById<LottieAnimationView>(R.id.lottieView)
        private val titleText = itemView.findViewById<TextView>(R.id.txtTitle)

        fun bind(model: PreventionModel) {
            titleText?.text = model.title
            lottieView?.setAnimation(model.rawName)
            lottieView?.playAnimation()
        }

    }


}