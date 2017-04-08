package io.memetic.alcohdex.feature.entries;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

    public static final String KEY_ENTRY = "key_entry";
    private FragmentAddEntryBinding binding;
    private BeerEntry mEntry;
    private EntryRepository mRepository;

    public static AddEntryFragment newInstance(BeerEntry entry) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_ENTRY, entry);

        AddEntryFragment fragment = new AddEntryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mRepository = EntryRepository.getInstance();

        mEntry = getArguments().getParcelable(KEY_ENTRY);

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
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
