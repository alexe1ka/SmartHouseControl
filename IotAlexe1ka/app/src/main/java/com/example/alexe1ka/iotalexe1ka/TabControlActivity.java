package com.example.alexe1ka.iotalexe1ka;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.alexe1ka.iotalexe1ka.data.DataBaseHelper;
import com.example.alexe1ka.iotalexe1ka.fragments.ControlButtonFragment;
import com.example.alexe1ka.iotalexe1ka.fragments.InformationFragment;
import com.example.alexe1ka.iotalexe1ka.fragments.VisionFragment;
import com.example.alexe1ka.iotalexe1ka.model.ReplyToRequest;
import com.jjoe64.graphview.GraphView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.example.alexe1ka.iotalexe1ka.ConstRequest.GET_HUM;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.GET_TEMP;
import static com.example.alexe1ka.iotalexe1ka.ConstRequest.getUrl;

/**
 * Created by alexe1ka on 02.01.2017.
 */

public class TabControlActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String ipAddr;
    private DataBaseHelper mMyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabcontrol);
        ipAddr = getIntent().getExtras().getString("ipAddr");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        //listener's
        mMyDb = new DataBaseHelper(getApplicationContext());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("OnPageScrolled" + position, String.valueOf(position));
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("OnPageSelected" + position, String.valueOf(position));
                TextView mHum = (TextView) findViewById(R.id.humViewFrag);
                TextView mTemp = (TextView) findViewById(R.id.tempViewFrag);

                if (position == 1) {
                    //REQUEST ONE
                    AsyncRequestToEsp getTemp = new AsyncRequestToEsp(getApplicationContext());
                    ReplyToRequest reqT = null;
                    try {
                        reqT = getTemp.execute(getUrl(ipAddr, GET_TEMP)).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    mTemp.setText(reqT.getTemperature());
                    mMyDb.insertData(mTemp.getText().toString(), getDateTime());

                    //REQUEST TWO
                    AsyncRequestToEsp getHum = new AsyncRequestToEsp(getApplicationContext());
                    ReplyToRequest reqH = null;
                    try {
                        reqH = getHum.execute(getUrl(ipAddr, GET_HUM)).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    mHum.setText(reqH.getHumidity());

                    GraphView graphView = (GraphView) findViewById(R.id.graph);
                    graphView.removeAllSeries();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("OnPageSelected" + state, String.valueOf(state));
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ControlButtonFragment(), getString(R.string.tab1));
        adapter.addFragment(new InformationFragment(), getString(R.string.tab2));
        adapter.addFragment(new VisionFragment(), getString(R.string.tab3));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public String getAddr() {
        return ipAddr;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
