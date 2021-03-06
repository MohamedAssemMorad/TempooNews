package com.example.newproject

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newproject.data.network.NewsAPIController
import com.example.newproject.data.repository.NewsRepository
import com.example.newproject.presentation.di.networkModule
import com.example.newproject.presentation.di.repositoryModule
import com.example.newproject.presentation.di.viewModelModule
import com.example.newproject.presentation.viewmodel.NewsListViewModel
import junit.framework.TestCase
import kotlinx.coroutines.MainCoroutineDispatcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : TestCase() {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.example.newproject", appContext.packageName)
//    }

    private lateinit var newsViewModel: NewsListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Before
//    override fun setUp() {
//        super.setUp()
//
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        startKoin {
//            androidContext(context)
//            module { listOf(networkModule, viewModelModule, repositoryModule) }
//        }
//        newsViewModel = NewsListViewModel(NewsRepository(NewsAPIController))
//    }

    @Test
    fun testNewsViewModel() {
        val context = ApplicationProvider.getApplicationContext<Context>()
//        startKoin {
//            androidContext(context)
//            module { listOf(networkModule, viewModelModule, repositoryModule) }
//        }
        newsViewModel = NewsListViewModel(NewsRepository(NewsAPIController))

        val result = newsViewModel.callGetNewsList("bitcoin").getOrAwaitForValue()?.articles?.find {
            !it.content.isNullOrEmpty() && !it.description.isNullOrEmpty()
        }

        assertTrue(result.toString(), true)
    }
}