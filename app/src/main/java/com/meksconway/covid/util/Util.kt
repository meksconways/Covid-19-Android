package com.meksconway.covid.util

import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.covid.data.model.homecontent.HomeContentType

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

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
                    Log.d("***position",position.toString())
                    Log.d("***before",beforePreventionItemCount.toString())
                    when {
                        (position - beforePreventionItemCount) % 3 == 0 -> {
                            left = spaceHeight
                        }
                        (position - beforePreventionItemCount) % 3 == 2 -> {
                            right = spaceHeight
                        }
                        else -> {
                            left = spaceHeight / 2
                            right = spaceHeight / 2
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

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()