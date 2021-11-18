package ru.rinet.questik.ui.base

import android.view.View
import androidx.fragment.app.Fragment


open class BaseFragment(layout:Int) : Fragment(layout) {

    lateinit var mRootView: View

    override fun onStart() {
        super.onStart()
    }
}