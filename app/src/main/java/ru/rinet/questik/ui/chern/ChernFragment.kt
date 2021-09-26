package ru.rinet.questik.ui.chern

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.rinet.questik.R

class ChernFragment : Fragment() {

    companion object {
        fun newInstance() = ChernFragment()
    }

    private lateinit var viewModel: ChernViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chern_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChernViewModel::class.java)
        // TODO: Use the ViewModel
    }

}