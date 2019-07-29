package com.rahmanarifofficial.mypik_pusatinformasikampus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FavoritePagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {
    private val fragments = ArrayList<androidx.fragment.app.Fragment>()
    private val titles = ArrayList<String>()

    override fun getItem(position: Int): androidx.fragment.app.Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    fun addFragment(fragment: androidx.fragment.app.Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

}