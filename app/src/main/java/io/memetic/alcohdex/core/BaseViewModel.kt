package io.memetic.alcohdex.core

import android.content.Context
import android.content.res.Resources
import android.databinding.BaseObservable
import io.memetic.alcohdex.ComponentRegistry
import io.memetic.alcohdex.core.interfaces.ViewModel
import javax.inject.Inject

abstract class BaseViewModel : BaseObservable(), ViewModel {
    @Inject
    var context: Context? = null
        internal set
    @Inject
    var resources: Resources? = null
        internal set

    init {
        ComponentRegistry.getInstance().appComponent.inject(this)
    }
}
