package com.aatishrana.cleanconductormvp;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aatishrana.cleanconductormvp.base.BasePresenterConductor;
import com.aatishrana.cleanconductormvp.base.PresenterFactory;
import com.aatishrana.cleanconductormvp.presenter.SamplePresenter;
import com.aatishrana.cleanconductormvp.presenter.SamplePresenterFactory;
import com.aatishrana.cleanconductormvp.presenter.SampleView;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public class SampleController extends BasePresenterConductor<SamplePresenter, SampleView> implements SampleView
{
    private static final String TAG = "SampleController";
    private TextView title;

    private SamplePresenter presenter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container)
    {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.controller_home, container, false);
        title = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void showMessage(String text)
    {
        Log.i(TAG, "showMessage");
        this.title.setText(text);
    }

    @NonNull
    @Override
    protected String tag()
    {
        return TAG;
    }

    @NonNull
    @Override
    protected PresenterFactory<SamplePresenter> getPresenterFactory()
    {
        Log.i(TAG, "getPresenterFactory");
        return new SamplePresenterFactory(TAG);
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull SamplePresenter presenter)
    {
        Log.i(TAG, "onPresenterCreatedOrRestored");
        this.presenter = presenter;
    }
}
