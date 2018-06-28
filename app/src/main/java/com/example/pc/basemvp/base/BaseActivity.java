package com.example.pc.basemvp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.pc.basemvp.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundleData(savedInstanceState);
        initRootView(savedInstanceState);
        loadData(savedInstanceState);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.transition.slide_from_right, R.transition.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.transition.slide_from_left, R.transition.slide_to_right);
    }

    protected abstract void bundleData(Bundle savedInstanceState);

    protected abstract void initRootView(Bundle savedInstanceState);

    protected abstract void loadData(Bundle savedInstanceState);
}
