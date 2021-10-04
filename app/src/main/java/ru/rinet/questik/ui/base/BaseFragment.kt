package ru.rinet.questik.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup




open class BaseFragment(layout:Int) : Fragment(layout) {

    lateinit var mRootView: View

    override fun onStart() {
        super.onStart()

    }
}