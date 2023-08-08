package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.SignInFragment
import com.example.myapplication.SignUpFragment


class SignInSignupViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return if (position == 1) {
            SignUpFragment()
        } else SignInFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}