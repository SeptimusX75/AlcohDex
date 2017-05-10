package io.memetic.alcohdex.feature.entries.viewmodel;

import android.databinding.Bindable;

import io.memetic.alcohdex.core.BaseViewModel;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;

import static android.text.format.DateUtils.FORMAT_NUMERIC_DATE;
import static android.text.format.DateUtils.formatDateTime;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/24/17
 */
public class ListEntryVm extends BaseViewModel {
    private BeerEntry mEntry;

    public ListEntryVm(BeerEntry entry) {
        mEntry = entry;
    }

    @Bindable
    public String getDateText() {
        return formatDateTime(getContext(), mEntry.getTimestamp(), FORMAT_NUMERIC_DATE);
    }

    @Bindable
    public BeerEntry getEntry() {
        return mEntry;
    }

    @Bindable
    public String getName() {
        return mEntry.getBrewery();
    }

    @Bindable
    public String getBrewery() {
        return mEntry.getBrewery();
    }

    @Bindable
    public String getRatingText() {
        return String.valueOf(mEntry.getRating());
    }
}
