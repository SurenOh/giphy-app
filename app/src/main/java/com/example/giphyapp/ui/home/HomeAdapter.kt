package com.example.giphyapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphyapp.model.GiphyModel
import com.example.giphyapp.databinding.ItemGifBinding

typealias OnImageClickCallBack = (String) -> Unit
typealias LoadMore = (Int) -> Unit

class HomeAdapter(private val items: ArrayList<GiphyModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: ItemGifBinding

    private var isLoading = false

    var onClickImage: OnImageClickCallBack? = null
    var loadMore: LoadMore? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= items.size.minus(10) && !isLoading) {
            isLoading = true
            loadMore?.invoke(items.size)
        }
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: ArrayList<GiphyModel>) {
        items.clear()
        isLoading = false
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: ArrayList<GiphyModel>) {
        val position = items.size
        items.addAll(newItems)
        isLoading = false
        notifyItemInserted(position)
    }

    inner class ViewHolder(private val _binding: ItemGifBinding) : RecyclerView.ViewHolder(_binding.root) {

        fun bind(item: GiphyModel) = with(_binding) {
            userName.text = item.userName.takeIf { it.isNotBlank() } ?: NO_NAME
            title.text = item.title.takeIf { it.isNotBlank() } ?: NO_TITLE
            Glide.with(userName.context)
                .load(item.gifUrl)
                .into(imageGif)
            date.text = item.date
            itemView.setOnClickListener { onClickImage?.invoke(item.gifUrl) }
        }
    }

    companion object {
        const val NO_NAME = "No name"
        const val NO_TITLE = "No Title"
    }
}