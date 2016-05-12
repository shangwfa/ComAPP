package com.shangwf.app.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.marlonmafra.android.widget.SegmentedTab;
import com.shangwf.app.R;
import com.shangwf.app.adapter.SubTabAdapter;
import com.shangwf.app.ui.fragment.CustomFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SegmentedTabActivity extends AppCompatActivity {
    @Bind(R.id.segmentedTab)
    SegmentedTab segmentedTab;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private SubTabAdapter subTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity_segmented_tab);
        ButterKnife.bind(this);
        List<Fragment> fragmentList=new ArrayList<>();
        List<String> titles=new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            fragmentList.add(CustomFragment.createFragment("Tab " + (i + 1)));
            titles.add("Tab " + (i + 1));
        }

        this.subTabAdapter = new SubTabAdapter(getSupportFragmentManager(), this, fragmentList);
        this.viewPager.setAdapter(this.subTabAdapter);
        this.segmentedTab.setupWithViewPager(this.viewPager);
        this.segmentedTab.setup(titles);
    }
}
