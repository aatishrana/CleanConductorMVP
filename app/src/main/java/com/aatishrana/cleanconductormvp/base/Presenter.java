package com.aatishrana.cleanconductormvp.base;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public interface Presenter<V>
{
    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
