package io.memetic.alcohdex.entries;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.memetic.alcohdex.R;
import io.memetic.alcohdex.databinding.FragmentAddEntryBinding;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         3/19/17
 */
public class AddEntryFragment extends Fragment {

    private FragmentAddEntryBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_entry, container, false);
        return binding.getRoot();
    }
}
