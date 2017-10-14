package org.andriodtown.testmusicplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by user on 2017-10-11.
 */

public class ListPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragList;

    public ListPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
