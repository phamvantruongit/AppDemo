package team.android.pv.qlshop.view

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by admin on 10/11/2017.
 */

class LoadMoreScroll(
    internal var layoutManager: RecyclerView.LayoutManager,
    internal var loadMoreScroll: ILoadMoreScroll
) : RecyclerView.OnScrollListener() {
    private var item_first = 0
    private var sum_item = 0
    private val item_load_before = 6

    internal var changes = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        sum_item = layoutManager.itemCount

        if (layoutManager is LinearLayoutManager) {
            item_first = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        } else if (layoutManager is GridLayoutManager) {
            item_first = (layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

        }

        if (changes != sum_item) {
            changes = item_first + item_load_before
            if (sum_item == item_first + item_load_before) {
                loadMoreScroll.loadMore(true)
            }
        }


    }

    interface ILoadMoreScroll {
        fun loadMore(isScroll: Boolean)
    }
}