package com.example.newproject.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject.R
import com.example.newproject.presentation.view.fragment.NewsListFragment
import com.example.newproject.core.utils.FragmentUtil
import kotlinx.android.synthetic.main.activity_home.*


// here in this class we init bottom navigation bar and move to main fragment
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        FragmentUtil.replaceFragment(
            this,
            NewsListFragment(),
            true,
            TAG = NewsListFragment.TAG
        )

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
//                R.id.home -> FragmentUtil.replaceFragment(
//                    this,
//                    MovieRecommendationsFragment(),
//                    true,
//                    TAG = MovieRecommendationsFragment.TAG
//                )
                R.id.person -> FragmentUtil.replaceFragment(
                    this,
                    NewsListFragment(),
                    true,
                    TAG = NewsListFragment.TAG
                )
//                R.id.settings->setCurrentFragment(thirdFragment)

            }
            true
        }

    }

}
