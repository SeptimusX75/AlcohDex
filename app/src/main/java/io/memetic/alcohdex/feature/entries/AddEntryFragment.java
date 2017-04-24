package io.memetic.alcohdex.feature.entries;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.UUID;

import javax.inject.Inject;

import io.memetic.alcohdex.ComponentRegistry;
import io.memetic.alcohdex.R;
import io.memetic.alcohdex.data.EntryRepository;
import io.memetic.alcohdex.databinding.FragmentAddEntryBinding;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.memetic.alcohdex.feature.entries.viewmodel.AddEntryFragmentVm;

import static io.memetic.alcohdex.feature.entries.viewmodel.AddEntryFragmentVm.DateTuple;
import static io.memetic.alcohdex.feature.entries.viewmodel.AddEntryFragmentVm.Presenter;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         3/19/17
 */
public class AddEntryFragment extends Fragment implements Presenter {

    @Inject
    EntryRepository mRepository;

    public static final String KEY_ENTRY_ID = "KEY_ENTRY_ID";
    private FragmentAddEntryBinding binding;
    private BeerEntry mEntry;
    private ParcelUuid mUuid;
    private AddEntryFragmentVm mViewModel;

    public static AddEntryFragment newInstance(ParcelUuid uuid) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_ENTRY_ID, uuid);

        AddEntryFragment fragment = new AddEntryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ComponentRegistry.getInstance().getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mUuid = getArguments().getParcelable(KEY_ENTRY_ID);
        if (mUuid != null) {
            mEntry = mRepository.getEntryById(mUuid.getUuid());
        }
        Calendar calendar = Calendar.getInstance();
        if (mEntry == null) {
            mEntry = new BeerEntry();
            mEntry.setUuid(new ParcelUuid(UUID.randomUUID()));
            mEntry.setTimestamp(calendar.getTimeInMillis());
        }
        mViewModel = new AddEntryFragmentVm(mEntry, calendar);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_entry, container, false);
        binding.setViewModel(mViewModel);
        binding.setPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_add_entry_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDone:
                mRepository.addEntry(mEntry);
                mRepository.commitEntries();
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onImageChangeRequest() {
        Toast.makeText(getContext(), "Show image upload options", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeDate(DateTuple dateTuple) {
        DatePickerDialog dateDialog = new DatePickerDialog(
                getContext(), R.style.AppTheme_Dialog,
                (view, year, month, dayOfMonth) -> {
                    DateTuple tuple = new DateTuple(dayOfMonth, month, year);
                    mViewModel.setDateTuple(tuple);
                },
                dateTuple.year, dateTuple.month, dateTuple.day
        );
        dateDialog.show();

    }
}
