package com.meksconway.covid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.covid.R
import com.meksconway.covid.data.model.summary.Countries

class CountriesAdapter(private val callback: (Countries) -> Unit) :
    RecyclerView.Adapter<CountriesAdapter.CountriesVH>() {

    private val contentList = arrayListOf<Countries>()

    fun setItems(list: List<Countries>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_countries, parent, false)
        return CountriesVH(view)
    }

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: CountriesVH, position: Int) {
        holder.bind(contentList[position])
        holder.itemView.setOnClickListener {
            callback.invoke(contentList[position])
        }
    }

    inner class CountriesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val countryText = itemView.findViewById<TextView>(R.id.txtTitle)

        fun bind(model: Countries) {
            countryText?.text = model.country
        }

    }

}