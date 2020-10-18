package ru.rinet.questik.ui.main.project.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragment;
import ru.rinet.questik.ui.main.project.fragment.itog.ProjectItogFragment;
import ru.rinet.questik.ui.main.project.fragment.job.ProjectDetailJobFragment;
import ru.rinet.questik.ui.main.project.fragment.material.ProjectDetailMaterialFragment;

public class DetailProjectPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;

    public DetailProjectPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ProjectDetailFragment.newInstance();
            case 1:
                return ProjectDetailJobFragment.newInstance();
            case 2:
                return ProjectDetailMaterialFragment.newInstance();
            case 3:
                return ProjectItogFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}


