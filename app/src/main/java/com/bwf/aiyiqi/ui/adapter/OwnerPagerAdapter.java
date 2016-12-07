package com.bwf.aiyiqi.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.aiyiqi.ui.fragment.EssenceFragment;
import com.bwf.aiyiqi.ui.fragment.ModelFragment;
import com.bwf.aiyiqi.ui.fragment.NewestFragment;

/**
 * Created by Lizhangfeng on 2016/12/7 0007.
 * Description:
 */

public class OwnerPagerAdapter extends FragmentPagerAdapter {

    public OwnerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = EssenceFragment.newInstance();
                break;
            case 1:
                fragment = NewestFragment.newInstance();

                break;
            case 2:
                fragment = ModelFragment.newInstance();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}



