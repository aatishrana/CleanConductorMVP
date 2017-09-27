package com.aatishrana.cleanconductormvp.base;

/**
 * Created by Aatish Rana on 27-Sep-17.
 */

public interface PresenterFactory<T extends Presenter>
{
    T create();
}
