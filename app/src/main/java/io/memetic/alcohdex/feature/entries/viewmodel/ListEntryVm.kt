package io.memetic.alcohdex.feature.entries.viewmodel

import android.databinding.Bindable
import android.text.format.DateUtils.FORMAT_NUMERIC_DATE
import android.text.format.DateUtils.formatDateTime
import io.memetic.alcohdex.core.BaseViewModel
import io.memetic.alcohdex.feature.entries.model.BeerEntry

class ListEntryVm(val entry: BeerEntry) : BaseViewModel() {

    val dateText: String
        @Bindable
        get() = formatDateTime(context, entry.timestamp, FORMAT_NUMERIC_DATE)

    val name: String
        @Bindable
        get() = entry.brewery

    val brewery: String
        @Bindable
        get() = entry.brewery

    val ratingText: String
        @Bindable
        get() = entry.rating.toString()
}
