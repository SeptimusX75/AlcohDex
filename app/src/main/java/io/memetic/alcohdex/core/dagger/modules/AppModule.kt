package io.memetic.alcohdex.core.dagger.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.memetic.alcohdex.App
import io.memetic.alcohdex.R
import javax.inject.Singleton

/**
 * TODO class description

 * @author manuel.silva@mobileforming.com
 * *         4/17/17
 */
@Module
class AppModule(private val mApp: App) {
    private val mResources: Resources = mApp.resources
    private val mSharedPreferences: SharedPreferences = mApp.getSharedPreferences(
            mApp.getString(R.string.preference_file_key), MODE_PRIVATE
    )

    @Provides
    @Singleton
    fun provideContext(): Context {
        return mApp
    }


    @Provides
    @Singleton
    fun provideSharedPrefs(): SharedPreferences {
        return mSharedPreferences
    }

    @Provides
    @Singleton
    fun provideResources(): Resources {
        return mResources
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
