package com.aatishrana.cleanconductormvp.presenter;

import android.util.Log;

import com.aatishrana.cleanconductormvp.base.Presenter;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public class SamplePresenter implements Presenter<SampleView>
{
    private final String TAG = "SamplePresenter";
    private final String title;
    private SampleView view;
    private int count = 0;

    public SamplePresenter(String title)
    {
        this.title = title;
        Log.i(TAG, "Constructor");
    }

    @Override
    public void onViewAttached(SampleView view)
    {
        Log.i(TAG, "onViewAttached");
        this.view = view;
        this.count++;
        this.view.showMessage(title + ". View attached " + count + " times");
    }

    @Override
    public void onViewDetached()
    {
        Log.i(TAG, "onViewDetached");
        this.view = null;
    }

    @Override
    public void onDestroyed()
    {
        Log.i(TAG, "onDestroyed");
        // Nothing to clean up
    }
}
