package com.udacity.notepad.util

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.view.View

class SpaceItemDecoration : ItemDecoration {
    private lateinit var context: Context
    private var dimenRes: Int = 0
    private var space: Int = 0

    constructor(context: Context, dimenRes: Int, space: Int) {
        this.context = context
        this.dimenRes = dimenRes
        this.space = context.resources.getDimensionPixelOffset(dimenRes)
        this.space = space
    }

    private constructor(space: Int) {
        throw RuntimeException()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        when (getOrientation(parent)) {
            LinearLayoutManager.VERTICAL -> if (position != 0) outRect.top = space
            LinearLayoutManager.HORIZONTAL -> if (position != 0) outRect.left = space
        }
    }

    private fun getOrientation(parent: RecyclerView): Int {
        val lm = parent.layoutManager
        return if (lm is LinearLayoutManager) {
            lm.orientation
        } else -1
    }
}