package com.example.newproject

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newproject.data.model.ArticleResponse
import com.example.newproject.data.network.NewsAPIController
import com.example.newproject.data.repository.NewsRepository
import com.example.newproject.presentation.di.networkModule
import com.example.newproject.presentation.di.repositoryModule
import com.example.newproject.presentation.di.viewModelModule
import com.example.newproject.presentation.viewmodel.NewsListViewModel
import junit.framework.TestCase
import org.junit.*
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class NewsViewModelTest : TestCase() {

    private lateinit var newsViewModel: NewsListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
        startKoin {
            androidContext(context)
            module { listOf(networkModule, viewModelModule, repositoryModule) }
        }
        newsViewModel = NewsListViewModel(NewsRepository(NewsAPIController))
    }

    @Test
    fun testNewsViewModel() {
        val result = newsViewModel.callGetNewsList("bitcoin").getOrAwaitForValue()?.articles?.find {
            !it.content.isNullOrEmpty() && !it.description.isNullOrEmpty()
        }
        assert(result != null)
    }
}