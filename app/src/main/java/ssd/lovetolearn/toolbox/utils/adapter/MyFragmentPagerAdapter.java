package ssd.lovetolearn.toolbox.utils.adapter;

import android.support.v4.app.*;
import java.util.*;
import ssd.lovetolearn.toolbox.activity.*;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter
{
    private String[] mTitles = new String[]{"首页", "发现", "进货单","我的"};

	private List<Fragment> list;
	
    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.list = list;
    }
    
    
    
    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
    /*
	private Fragment newFragment(Fragment f) {
		if (f == null) {
			f = new Fragment();
		}
		return f;
	}*/
    
    
}
