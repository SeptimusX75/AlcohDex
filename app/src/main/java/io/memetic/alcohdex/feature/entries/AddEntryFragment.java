package io.memetic.alcohdex.feature.entries;

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

import java.util.UUID;

import javax.inject.Inject;

import io.memetic.alcohdex.ComponentRegistry;
import io.memetic.alcohdex.R;
import io.memetic.alcohdex.data.EntryRepository;
import io.memetic.alcohdex.databinding.FragmentAddEntryBinding;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         3/19/17
 */
public class AddEntryFragment extends Fragment {

    @Inject
    EntryRepository mRepository;

    public static final String KEY_ENTRY_ID = "KEY_ENTRY_ID";
    private FragmentAddEntryBinding binding;
    private BeerEntry mEntry;
    private ParcelUuid mUuid;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mUuid = getArguments().getParcelable(KEY_ENTRY_ID);
        if (mUuid != null) {
            mEntry = mRepository.getEntryById(mUuid.getUuid());
        }
        if (mEntry == null) {
            mEntry = new BeerEntry(UUID.randomUUID());
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_entry, container, false);
        binding.setEntry(mEntry);

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
}
