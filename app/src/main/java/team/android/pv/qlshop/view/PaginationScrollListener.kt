package team.android.pv.qlshop.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

abstract class PaginationScrollListener
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    var item = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        Log.d("PPPP", visibleItemCount.toString() + "\n")
        Log.d("PPPP", totalItemCount.toString() + "\n")
        Log.d("PPPP", firstVisibleItemPosition.toString() + "\n" + isLastPage().toString() + isLoading().toString())



        if (item + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
            loadMoreItems(true)
        }

    }

    abstract fun loadMoreItems(b: Boolean)
}