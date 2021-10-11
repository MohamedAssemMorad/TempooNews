package com.example.newproject.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.R
import com.example.newproject.data.model.ArticleResponse
import com.squareup.picasso.Picasso

class NewsListAdapter(
    val context: Context,
    private val newsList: ArrayList<ArticleResponse>?,
    val onItemClicked: (newsResponse: ArticleResponse?) -> Unit
) :
    RecyclerView.Adapter<NewsListAdapter.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(newsList?.get(position))

        holder.itemView.setOnClickListener {
            onItemClicked(newsList?.get(position))
        }
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ArticleResponse?) {

            val newsTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
            val newsDesc: AppCompatTextView = itemView.findViewById(R.id.tvDesc)
            val newsPoster: AppCompatImageView = itemView.findViewById(R.id.ivPoster)

            newsTitle.text = item?.title
            newsDesc.text = item?.description
            Picasso.with(context).load(item?.urlToImage.toString())
                .into(newsPoster)
        }
    }

    companion object {
        val TAG: String = NewsListAdapter::class.java.simpleName
    }
}