package com.meksconway.covid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.meksconway.covid.R
import com.meksconway.covid.common.extensions.autoNotify
import com.meksconway.covid.data.model.summary.Countries
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountriesVH>() {

//    private val contentList = arrayListOf<Countries>()
    var contentList: List<Countries> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.countryCode == n.countryCode }
    }
    private val callbackSubject = PublishSubject.create<Countries>()
    fun getCallbackSubject(): Observable<Countries> = callbackSubject

//    fun setItems(list: List<Countries>) {
//        contentList.clear()
//        contentList.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_countries, parent, false)
        return CountriesVH(view)
    }

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: CountriesVH, position: Int) {
        holder.bind(contentList[position])
        holder.itemView.clicks()
            .throttleFirst(4, TimeUnit.SECONDS)
            .map {
                contentList[position]
            }
            .subscribe(callbackSubject)

    }

    inner class CountriesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val countryText = itemView.findViewById<TextView>(R.id.txtTitle)

        fun bind(model: Countries) {
            countryText?.text = model.country
        }

    }

}