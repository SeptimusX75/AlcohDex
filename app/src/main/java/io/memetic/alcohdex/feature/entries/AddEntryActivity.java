package io.memetic.alcohdex.feature.entries;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import io.memetic.alcohdex.R;
import io.memetic.alcohdex.databinding.ActivityAddEntryBinding;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         3/7/17
 */
public class AddEntryActivity extends AppCompatActivity {
    public static final String TAG_ADD_ENTRY_FRAGMENT = "FRAGMENT_TAG_ADD_ENTRY";
    public static final String EXTRA_ENTRY_ID = AddEntryFragment.KEY_ENTRY_ID;
    private ActivityAddEntryBinding binding;
    private AddEntryFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_entry);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ParcelUuid uuid = getIntent().getParcelableExtra(EXTRA_ENTRY_ID);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            fragment = AddEntryFragment.newInstance(uuid);
            transaction.add(binding.fragmentTarget.getId(), fragment, TAG_ADD_ENTRY_FRAGMENT);
            transaction.commit();
        } else {
            fragment = (AddEntryFragment) getSupportFragmentManager()
                    .findFragmentByTag(TAG_ADD_ENTRY_FRAGMENT);
        }
    }
}
