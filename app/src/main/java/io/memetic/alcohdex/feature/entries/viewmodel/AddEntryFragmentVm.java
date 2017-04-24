package io.memetic.alcohdex.feature.entries.viewmodel;

import android.annotation.SuppressLint;
import android.databinding.Bindable;

import java.util.Calendar;

import io.memetic.alcohdex.BR;
import io.memetic.alcohdex.core.BaseViewModel;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;

import static android.text.format.DateUtils.FORMAT_SHOW_DATE;
import static android.text.format.DateUtils.FORMAT_SHOW_YEAR;
import static android.text.format.DateUtils.formatDateTime;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/23/17
 */
public class AddEntryFragmentVm extends BaseViewModel {

    private final Calendar mCalendar;
    private BeerEntry mEntry;

    public AddEntryFragmentVm(BeerEntry entry, Calendar calendar) {
        mEntry = entry;
        mCalendar = calendar;
        mCalendar.setTimeInMillis(mEntry.getTimestamp());
    }

    @Bindable
    public BeerEntry getEntry() {
        return mEntry;
    }

    @Bindable
    public String getDateText() {
        return formatDateTime(getContext(), mEntry.getTimestamp(),
                FORMAT_SHOW_DATE | FORMAT_SHOW_YEAR);
    }

    @SuppressLint("WrongConstant")
    @Bindable
    public DateTuple getDateTuple() {
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);

        return new DateTuple(day, month, year);
    }

    public void setDateTuple(DateTuple dateTuple) {
        mCalendar.set(dateTuple.year, dateTuple.month, dateTuple.day);
        mEntry.setTimestamp(mCalendar.getTimeInMillis());

        notifyPropertyChanged(BR.dateText);
    }

    public static class DateTuple {
        public final int day, month, year;

        public DateTuple(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    public interface Presenter {
        void onImageChangeRequest();

        void onChangeDate(DateTuple dateTuple);
    }
}
