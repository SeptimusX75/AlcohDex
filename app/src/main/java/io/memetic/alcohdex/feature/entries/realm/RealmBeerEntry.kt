package io.memetic.alcohdex.feature.entries.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

/**
 * TODO class description

 * @author manuel.silva@mobileforming.com
 * *         5/22/17
 */
open class RealmBeerEntry : RealmObject() {
    companion object Fields {
        const val primaryKey = "id"
        const val nameField = "name"
        const val dateField = "date"
    }
    @Required @PrimaryKey var id: String? = null
    var name: String? = null
    var brewery: String? = null
    var imageUri: String? = null
    var rating: Float = 0.toFloat()
    var date: Date? = null
}
