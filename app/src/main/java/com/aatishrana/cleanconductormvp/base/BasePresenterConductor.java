package com.aatishrana.cleanconductormvp.base;


import android.app.Activity;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bluelinelabs.conductor.Controller;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public abstract class BasePresenterConductor<P extends Presenter<V>, V> extends Controller
{
    private static final String TAG = "BasePresenterConductor";
    private static final int LOADER_ID = 101;
    private P presenter;

    @Override
    protected void onActivityStarted(@NonNull Activity activity)
    {
        super.onActivityResumed(activity);
        Log.i(TAG, "onActivityResumed-" + tag());

        Loader<P> loader = ((AppCompatActivity) activity).getSupportLoaderManager().getLoader(loaderId());
        if (loader == null)
        {
            initLoader((AppCompatActivity) activity);
        } else
        {
            this.presenter = ((PresenterLoader<P>) loader).getPresenter();
            onPresenterCreatedOrRestored(presenter);
        }
    }

    private void initLoader(final AppCompatActivity activity)
    {
        // LoaderCallbacks as an object, so no hint regarding loader will be leak to the subclasses.
        activity.getSupportLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>()
        {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args)
            {
                Log.i(TAG, "onCreateLoader-" + tag());
                return new PresenterLoader<>(activity, getPresenterFactory(), tag());
            }

            @Override
            public void onLoadFinished(android.support.v4.content.Loader<P> loader, P data)
            {
                Log.i(TAG, "onLoadFinished-" + tag());
                BasePresenterConductor.this.presenter = data;
                onPresenterCreatedOrRestored(presenter);
            }

            @Override
            public void onLoaderReset(android.support.v4.content.Loader<P> loader)
            {
                Log.i(TAG, "onLoaderReset-" + tag());
                BasePresenterConductor.this.presenter = null;
            }

        });
    }

    @Override
    protected void onAttach(@NonNull View view)
    {
        super.onAttach(view);
        Log.i(TAG, "onAttach-" + tag());
        presenter.onViewAttached(getPresenterView());
    }

    @Override
    protected void onDetach(@NonNull View view)
    {
        presenter.onViewDetached();
        super.onDetach(view);
        Log.i(TAG, "onDetach-" + tag());
    }


    /**
     * String tag use for log purposes.
     */
    @NonNull
    protected abstract String tag();

    /**
     * Instance of {@link PresenterFactory} use to create a Presenter when needed. This instance should
     * not contain {@link android.app.Activity} context reference since it will be keep on rotations.
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Hook for subclasses that deliver the {@link Presenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected abstract void onPresenterCreatedOrRestored(@NonNull P presenter);

    /**
     * Override in case of fragment not implementing Presenter<View> interface
     */
    @NonNull
    protected V getPresenterView()
    {
        return (V) this;
    }


    protected int loaderId()
    {
        return LOADER_ID;
    }
}
