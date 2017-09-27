package com.aatishrana.cleanconductormvp.base;

import android.content.Context;

import android.support.v4.content.Loader;
import android.util.Log;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public class PresenterLoader<T extends Presenter> extends Loader<T>
{
    private final PresenterFactory<T> factory;
    private final String tag;
    private T presenter;

    PresenterLoader(Context context, PresenterFactory<T> factory, String tag) {
        super(context);
        this.factory = factory;
        this.tag = tag;
    }

    @Override
    protected void onStartLoading() {
        Log.i("PresenterLoader", "onStartLoading-" + tag);

        // if we already own a presenter instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.i("PresenterLoader", "onForceLoad-" + tag);

        // Create the Presenter using the Factory
        presenter = factory.create();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
        Log.i("PresenterLoader", "deliverResult-" + tag);
    }

    @Override
    protected void onStopLoading() {
        Log.i("PresenterLoader", "onStopLoading-" + tag);
    }

    @Override
    protected void onReset() {
        Log.i("PresenterLoader", "onReset-" + tag);
        if (presenter != null) {
            presenter.onDestroyed();
            presenter = null;
        }
    }

    public T getPresenter() {
        return presenter;
    }
}
