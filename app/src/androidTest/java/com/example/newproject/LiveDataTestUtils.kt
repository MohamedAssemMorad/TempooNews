package com.example.newproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


fun <T> LiveData<T>.getOrAwaitForValue(): T {

    var data: T? = null
    val latch = CountDownLatch(2)

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            this@getOrAwaitForValue.removeObserver(this)
            latch.countDown()
        }
    }

    this.observeForever(observer)

    try {
        if (latch.await(30, TimeUnit.SECONDS)) {
            throw TimeoutException("LiveData never gets its value")
        }
    }
    finally {
        this.removeObserver(observer)
    }
    return data as T
}