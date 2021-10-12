package com.example.newproject.presentation.view.fragment

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newproject.BaseFragment
import com.example.newproject.R
import com.example.newproject.core.utils.FragmentUtil
import com.example.newproject.data.model.ArticleResponse
import com.example.newproject.presentation.view.adapter.NewsListAdapter
import com.example.newproject.presentation.view.adapter.PaginationListener
import com.example.newproject.presentation.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private val newsList: ArrayList<ArticleResponse> = ArrayList()
    private val newsListViewModel: NewsListViewModel by viewModel()
    private var newsListAdapter: NewsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        inflater.inflate(R.layout.fragment_news_list, contentContainer, true)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsList("")
        etSearch?.setOnEditorActionListener(TextView.OnEditorActionListener() { textView: TextView, i: Int, keyEvent: KeyEvent ->
            if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                if (!textView.text.toString().isNullOrEmpty()) {
                    getNewsList(textView.text.toString())
                }
            }
            return@OnEditorActionListener false
        })
    }

    override fun onResume() {
        super.onResume()
        toolbarProcess()
    }

    override fun toolbarProcess() {
        super.toolbarProcess()
        toolbar.hideBackIcon(activity as AppCompatActivity)
    }

    private fun adapterProcess() {
        newsListAdapter = NewsListAdapter(
            mContext,
            newsList,
            onItemClicked = { resultsItem: ArticleResponse? ->
                FragmentUtil.replaceFragment(
                    activity as AppCompatActivity,
                    NewsDetailsFragment.newInstance(resultsItem),
                    true,
                    TAG = NewsDetailsFragment.TAG
                )
            })
        rvNewsList?.adapter = newsListAdapter
    }

    private fun getNewsList(query: String) {
        newsList?.clear()
        pbLoader.visibility = View.VISIBLE
        newsListViewModel.callGetNewsList(query)
            .observe(this, Observer {
                pbLoader.visibility = View.GONE
                if (!it?.articles.isNullOrEmpty()) {
                    newsList.addAll(it?.articles ?: ArrayList())
                    adapterProcess()
                } else {
                    Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            })
    }

    companion object {
        val TAG: String = NewsListFragment::class.java.simpleName
    }

    override fun onRefresh() {
        TODO("Not yet implemented")
    }
}