package io.memetic.alcohdex;

import android.databinding.Bindable;

import io.memetic.alcohdex.core.BaseViewModel;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/16/17
 */
public class HomeViewModel extends BaseViewModel {
    private boolean hasEntries;

    @Bindable
    public boolean isEntryAvailable() {
        return hasEntries;
    }

    public void setEntryAvailable(boolean hasEntries) {
        this.hasEntries = hasEntries;
        notifyPropertyChanged(BR.entryAvailable);
    }
}
