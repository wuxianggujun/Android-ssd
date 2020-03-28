package ssd.lovetolearn.toolbox.activity;
import android.graphics.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.appcompat.*;
import android.support.v7.widget.*;
import java.util.*;
import ssd.lovetolearn.toolbox.utils.adapter.*;

public class JianHuangShi extends AppCompatActivity
{
    
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    
	private List<Fragment> fragments;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianhuangshi);
        toolbar=findViewById(R.id.toolbar);
		getFragment();
        initViews();
    }

	private void getFragment() {
		fragments = new ArrayList<>();
		fragments.add(new MyFragment());
		fragments.add(new Fragment_2());
		fragments.add(new Fragment_3());
		fragments.add(new Fragment_4());
	}
	
    private void initViews() {
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        mViewPager=findViewById(R.id.viewpager);
        myFragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        
        
        //将tab和Viewpage结合在一起
        mTabLayout=findViewById(R.id.main_tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        
        //指定tab的位置
        one=mTabLayout.getTabAt(0);
        two=mTabLayout.getTabAt(1);
        three=mTabLayout.getTabAt(2);
        four=mTabLayout.getTabAt(3);
        
    }
    
    
}
