package com.victoweng.giphy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.victoweng.giphy.R
import com.victoweng.giphy.dataModels.search.GifResponse
import com.victoweng.giphy.dataModels.search.data.GifItem
import kotlinx.android.synthetic.main.gif_item.view.*

class GifAdapter(val glideManager: RequestManager) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val gifList = mutableListOf<GifItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return GifViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gifList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GifViewHolder -> {
                holder.onbind(gifList[position], glideManager)
            }
        }
    }

    fun setGifList(response: GifResponse) {
        gifList.clear()
        gifList.addAll(response.data!!)
        notifyDataSetChanged()
    }

    class GifViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val gifView = view.gif_view
        val titleView = view.title

        fun onbind(gifItem: GifItem, glideManager: RequestManager) {
            glideManager.asGif()
                .load(gifItem.images.fixed_height.url)
                .into(gifView)
            titleView.setText(gifItem.title)
        }
    }

}