package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.adapter.OnBoardingViewPageAdapter
import com.example.myapplication.model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {
    var onBoardingViewPageAdapter: OnBoardingViewPageAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0
    private var sharePreferences: SharedPreferences? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        if (restoredPrefData()) {
            val i = Intent(applicationContext, HomeActivity::class.java)
            startActivity(i)
            finish()
        }

        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabIndicator)
        next = findViewById(R.id.next)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()

        onBoardingData.add(
            OnBoardingData(
                "Fresh Food",
                "You will get the fast food",
                R.drawable.oboarding1
            )
        )
        onBoardingData.add(
            OnBoardingData(
                "Fast Delivery",
                "Fast delivery with fast food",
                R.drawable.oboarding2
            )
        )
        onBoardingData.add(
            OnBoardingData(
                "Easy payment",
                "You can payment online gateway",
                R.drawable.oboarding3
            )
        )

        setOnBoardingViewPageAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        next?.setOnClickListener() {
            if (position < onBoardingData.size) {
                position++
                onBoardingViewPager!!.currentItem = position

            }
            if (position == onBoardingData.size) {
                savePrefData()
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position

                if (tab.position == onBoardingData.size - 1) {
                    next!!.text = "Get Started"
                } else {
                    next!!.text = "Next"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    private fun savePrefData() {
        sharePreferences = applicationContext.getSharedPreferences("Pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharePreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restoredPrefData(): Boolean {
        sharePreferences = applicationContext.getSharedPreferences("Pref", Context.MODE_PRIVATE)
        return sharePreferences!!.getBoolean("isFirstTimeRun", false)
    }

    private fun setOnBoardingViewPageAdapter(onBoardingData: List<OnBoardingData>) {
        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPageAdapter = OnBoardingViewPageAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPageAdapter

        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }
}