package io.memetic.alcohdex.core.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.memetic.alcohdex.App;
import io.memetic.alcohdex.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/17/17
 */
@Module
public class AppModule {

    private final App mApp;
    private final Resources mResources;
    private SharedPreferences mSharedPreferences;

    public AppModule(App app) {
        mApp = app;
        mResources = app.getResources();
        mSharedPreferences = mApp.getSharedPreferences(
                mApp.getString(R.string.preference_file_key), MODE_PRIVATE
        );
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp;
    }


    @Provides
    @Singleton
    public SharedPreferences provideSharedPrefs() {
        return mSharedPreferences;
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return mResources;
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
