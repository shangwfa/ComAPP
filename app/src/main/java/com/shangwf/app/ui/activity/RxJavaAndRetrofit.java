package com.shangwf.app.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.shangwf.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxJavaAndRetrofit extends Activity {
    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_and_retrofit);
        ButterKnife.bind(this);

    } @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    //进行网络请求
    private void getMovie(){

    }
}
