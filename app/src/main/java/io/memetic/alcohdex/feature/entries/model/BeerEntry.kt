package io.memetic.alcohdex.feature.entries.model

import android.databinding.Bindable
import android.net.Uri
import android.os.ParcelUuid

import io.memetic.alcohdex.BR
import io.memetic.alcohdex.core.BaseModel

/**
 * TODO class description
 * @author manuel.silva@mobileforming.com
 * *         3/7/17
 */
class BeerEntry : BaseModel() {
    lateinit var uuid: ParcelUuid

    @get:Bindable
    var name: String? = null
        set(name) {
            field = name
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    var brewery: String? = null
        set(brewery) {
            field = brewery
            notifyPropertyChanged(BR.brewery)
        }
    @get:Bindable
    var imageUri: Uri? = null
        set(imageUri) {
            field = imageUri
            notifyPropertyChanged(BR.imageUri)
        }
    @get:Bindable
    var rating: Float = 0.toFloat()
        set(rating) {
            field = rating
            notifyPropertyChanged(BR.rating)
        }
    @get:Bindable
    var timestamp: Long = 0
        set(timestamp) {
            field = timestamp
            notifyPropertyChanged(BR.timestamp)
        }
}
