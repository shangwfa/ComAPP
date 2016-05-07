package com.shangwf.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shangwf.app.databinding.DbActivityMainBinding;
import com.shangwf.app.utils.CornerUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DbActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.db_activity_main);
        binding.setBackground(CornerUtils.cornerDrawable(getResources().getColor(R.color.colorAccent),5));
    }
}
