package com.aatishrana.cleanconductormvp.presenter;

import android.util.Log;

import com.aatishrana.cleanconductormvp.base.PresenterFactory;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public class SamplePresenterFactory implements PresenterFactory<SamplePresenter>
{
    private final String TAG = "SamplePresenterFactory";
    private final String title;

    public SamplePresenterFactory(String title)
    {
        this.title = title;
        Log.i(TAG, "Constructor");
    }

    @Override
    public SamplePresenter create()
    {
        Log.i(TAG, "create");
        return new SamplePresenter(title);
    }
}
