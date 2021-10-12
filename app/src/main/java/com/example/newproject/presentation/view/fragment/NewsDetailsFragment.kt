package com.example.newproject.presentation.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.newproject.BaseFragment
import com.example.newproject.R
import com.example.newproject.data.model.ArticleResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.android.synthetic.main.item_news.*

class NewsDetailsFragment : BaseFragment() {

    private var newsDetails: ArticleResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetails = arguments?.getParcelable(NEWS_DETAILS)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        inflater.inflate(R.layout.fragment_news_details, contentContainer, true)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAllData()
        btnSource?.setOnClickListener {
            val i =
                Intent(Intent.ACTION_VIEW, Uri.parse(newsDetails?.url))
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        toolbarProcess()
    }

    private fun setAllData() {

        newsDetails?.let {

            tvTitle?.text = newsDetails?.title
            tvDescDetails?.text = newsDetails?.description
            tvAuthor?.text = newsDetails?.author
            tvContentDetail?.text = newsDetails?.content
            tvDateDetail?.text = newsDetails?.publishedAt

            Picasso.with(mContext)
                .load(newsDetails?.urlToImage)
                .into(ivPosterImage)
        }
    }


    companion object {
        val TAG: String = NewsDetailsFragment::class.java.simpleName
        private const val NEWS_DETAILS = "news_details"

        fun newInstance(
            newsDetails: ArticleResponse?
        ) = run {
            val fragment =
                NewsDetailsFragment()
            fragment.arguments = bundleOf(
                NewsDetailsFragment.NEWS_DETAILS to newsDetails
            )
            return@run fragment
        }
    }
}
