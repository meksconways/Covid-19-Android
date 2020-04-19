package com.meksconway.covid.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.covid.data.model.homecontent.HomeContentType

class HomeFragmentRecyclerViewItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    private var beforePreventionItemCount: Int = 0

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {

            val position = parent.getChildAdapterPosition(view)
            val adapter = parent.adapter
            when (adapter?.getItemViewType(position)) {
                HomeContentType.PREVENTION.ordinal -> {
                    when {
                        (position - beforePreventionItemCount) % 3 == 0 -> {
                            left = spacing
                        }
                        (position - beforePreventionItemCount) % 3 == 2 -> {
                            right = spacing
                        }
                        else -> {
                            left = spacing / 2
                            right = spacing / 2
                        }
                    }
                }
                else -> {
                    beforePreventionItemCount += 1
                }
            }
        }
    }
}
