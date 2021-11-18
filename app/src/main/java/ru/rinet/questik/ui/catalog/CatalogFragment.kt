package ru.rinet.questik.ui.catalog

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.catalog_fragment.*
import ru.rinet.questik.R
import ru.rinet.questik.ui.base.BaseFragment
import ru.rinet.questik.ui.catalog.jobs.JobsFragment
import ru.rinet.questik.ui.catalog.material.MaterialFragment

class CatalogFragment : BaseFragment(R.layout.catalog_fragment) {
    lateinit var mViewPager: ViewPager
    lateinit var mTabLayout: TabLayout

    override fun onResume() {
        super.onResume()
        initFields()
        setupViewPager(mViewPager)

    }

    private fun initFields() {
        mViewPager = viewpager
        mTabLayout = tab_layout
    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(JobsFragment(), "Работы")
        adapter.addFragment(MaterialFragment(), "Материалы")
        mViewPager.setAdapter(adapter)
        mTabLayout.setupWithViewPager(mViewPager)
    }
}