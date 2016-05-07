package com.shangwf.app.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shangwf.app.R;
import com.shangwf.app.databinding.DbActivityMainBinding;
import com.shangwf.app.utils.CornerUtils;
import com.shangwf.app.utils.DensityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DbActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.db_activity_main);
        binding.setBackground(CornerUtils.cornerDrawable(getResources().getColor(R.color.dark_keyboard_panel_background), DensityUtils.dp2px(this,5)));

    }
}
