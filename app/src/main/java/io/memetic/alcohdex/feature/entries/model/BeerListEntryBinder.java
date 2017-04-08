package io.memetic.alcohdex.feature.entries.model;

import android.databinding.DataBindingUtil;
import android.view.View;

import io.memetic.alcohdex.R;
import io.memetic.alcohdex.core.BaseBindingEpoxyModel;
import io.memetic.alcohdex.databinding.ListItemBeerEntryBinding;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class BeerListEntryBinder extends BaseBindingEpoxyModel {
    private final BeerEntry mEntry;

    public BeerListEntryBinder(BeerEntry entry) {
        mEntry = entry;
    }

    @Override
    public void bind(View view) {
        ListItemBeerEntryBinding binding = DataBindingUtil.getBinding(view);
        if (binding == null) {
            binding = DataBindingUtil.bind(view);
        }
        binding.setEntry(mEntry);
        binding.executePendingBindings();
        super.bind(view);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.list_item_beer_entry;
    }
}
