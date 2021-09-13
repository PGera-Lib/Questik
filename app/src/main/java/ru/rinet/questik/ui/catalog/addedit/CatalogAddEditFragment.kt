package ru.rinet.questik.ui.catalog.addedit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.rinet.questik.R

class CatalogAddEditFragment : Fragment() {

    companion object {
        fun newInstance() = CatalogAddEditFragment()
    }

    private lateinit var viewModel: CatalogAddEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.catalog_add_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CatalogAddEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}