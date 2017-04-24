package io.memetic.alcohdex.core;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;

import javax.inject.Inject;

import io.memetic.alcohdex.ComponentRegistry;
import io.memetic.alcohdex.core.interfaces.ViewModel;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/7/17
 */
public abstract class BaseViewModel extends BaseObservable implements ViewModel {
    @Inject
    Context mContext;
    @Inject
    Resources mResources;

    {
        ComponentRegistry.getInstance().getAppComponent().inject(this);
    }

    public Context getContext() {
        return mContext;
    }

    public Resources getResources() {
        return mResources;
    }
}
