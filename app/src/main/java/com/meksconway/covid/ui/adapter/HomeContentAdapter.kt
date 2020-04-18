package com.meksconway.covid.ui.adapter

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.button.MaterialButton
import com.meksconway.covid.R
import com.meksconway.covid.data.model.homecontent.*
import com.meksconway.covid.util.px


class HomeContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contentList = arrayListOf<HomeContent>()

    override fun getItemId(position: Int): Long {
        return contentList[position].contentId
    }

    override fun getItemViewType(position: Int): Int {
        return when (contentList[position].contentType) {
            HomeContentType.HEADER -> HomeContentType.HEADER.ordinal
            HomeContentType.PREVENTION -> HomeContentType.PREVENTION.ordinal
            HomeContentType.YOUR_OWN_TEST -> HomeContentType.YOUR_OWN_TEST.ordinal
            HomeContentType.TITLE -> HomeContentType.TITLE.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {

            HomeContentType.HEADER.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_header, parent, false)
                HeaderVH(view)
            }
            HomeContentType.PREVENTION.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_prevention, parent, false)
                PreventionVH(view)
            }
            HomeContentType.YOUR_OWN_TEST.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_your_own_test, parent, false)
                OwnTestVH(view)
            }
            HomeContentType.TITLE.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_title, parent, false)
                TitleVH(view)
            }

            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val model = contentList[position]) {
            is HomeContentHeaderModel -> {
                (holder as? HeaderVH)?.bind(model)
                holder.itemView.setOnClickListener {
                }
            }
            is PreventionModel -> {
                (holder as? PreventionVH)?.bind(model)
                holder.itemView.setOnClickListener {

                }
            }

            is HomeContentTitleModel -> {
                (holder as? TitleVH)?.bind(model)
                holder.itemView.setOnClickListener {

                }
            }

            is YourOwnTestModel -> {
                (holder as? OwnTestVH)?.bind(model)
                holder.itemView.setOnClickListener {
                }
            }
        }
    }

    fun setItems(list: List<HomeContent>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }


    class HeaderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleText = itemView.findViewById<TextView>(R.id.txtTitle)
        private val contentText = itemView.findViewById<TextView>(R.id.txtContent)
        private val btnCall = itemView.findViewById<MaterialButton>(R.id.btnCall)
        private val btnSendSMS = itemView.findViewById<MaterialButton>(R.id.btnSendSMS)

        init {
            itemView.viewTreeObserver?.addOnGlobalLayoutListener {
                val buttonHeight = btnCall.height
                btnCall.cornerRadius = buttonHeight / 2
                btnSendSMS.cornerRadius = buttonHeight / 2
            }
        }


        fun bind(model: HomeContentHeaderModel) {
            titleText?.text = model.headerText
            contentText?.text = model.contentText
        }

    }

    class TitleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleText = itemView.findViewById<TextView>(R.id.txtTitle)

        fun bind(model: HomeContentTitleModel) {
            titleText?.text = model.title
        }
    }

    class PreventionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val lottieView = itemView
            .findViewById<LottieAnimationView>(R.id.lottieView)
        private val titleText = itemView.findViewById<TextView>(R.id.txtTitle)
        private val card = itemView.findViewById<View>(R.id.card)

        fun bind(model: PreventionModel) {
            titleText?.text = model.title
            lottieView?.setAnimation(model.rawName)
            lottieView?.playAnimation()

            card.viewTreeObserver.addOnGlobalLayoutListener {
                val height = card.height
                val drawable = GradientDrawable()
                drawable.cornerRadius = (height / 2).toFloat()
                drawable.colors = intArrayOf(
                    ContextCompat.getColor(itemView.context, R.color.colorPrimary),
                    ContextCompat.getColor(itemView.context, R.color.colorPrimary)
                )
                card.background = drawable
            }


        }

    }

    class OwnTestVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleText = itemView.findViewById<TextView>(R.id.txtTitle)
        private val contentText = itemView.findViewById<TextView>(R.id.txtContent)
        private val card = itemView.findViewById<FrameLayout>(R.id.cardView)

        fun bind(model: YourOwnTestModel) {
            titleText?.text = model.titleText
            contentText?.text = model.contentText
            val gradientDrawable = GradientDrawable()
            gradientDrawable.orientation =  GradientDrawable.Orientation.TL_BR
            gradientDrawable.cornerRadius = 16.px.toFloat()
            gradientDrawable.colors = intArrayOf(
                ContextCompat.getColor(card.context,R.color.purpleLight),
                ContextCompat.getColor(card.context,R.color.purpleMiddle),
                ContextCompat.getColor(card.context,R.color.colorPrimary)
            )

            val alphaColor = ColorUtils.setAlphaComponent(ContextCompat.getColor(card.context,R.color.purpleLight), 120)
            val list = ColorStateList(arrayOf(intArrayOf()), intArrayOf(alphaColor))
            val rippleDrawable = RippleDrawable(list, gradientDrawable, null)

            card.background = rippleDrawable


        }
    }

}