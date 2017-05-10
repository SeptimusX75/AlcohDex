package io.memetic.alcohdex.feature.entries.model;

import android.databinding.DataBindingUtil;
import android.view.View;

import io.memetic.alcohdex.R;
import io.memetic.alcohdex.core.BaseBindingEpoxyModel;
import io.memetic.alcohdex.databinding.ListItemBeerEntryBinding;
import io.memetic.alcohdex.feature.entries.viewmodel.ListEntryVm;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class BeerListEntryBinder extends BaseBindingEpoxyModel {
    private final Presenter mPresenter;
    private final ListEntryVm mViewModel;

    public BeerListEntryBinder(ListEntryVm viewModel, Presenter presenter) {
        mViewModel = viewModel;
        mPresenter = presenter;
    }

    @Override
    public void bind(View view) {
        ListItemBeerEntryBinding binding = DataBindingUtil.getBinding(view);
        if (binding == null) {
            binding = DataBindingUtil.bind(view);
        }
        binding.setViewModel(mViewModel);
        binding.setPresenter(mPresenter);
        binding.executePendingBindings();
        super.bind(view);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.list_item_beer_entry;
    }

    public interface Presenter {
        void onEntrySelected(BeerEntry entry);
    }
}
