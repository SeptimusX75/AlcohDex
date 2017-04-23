package io.memetic.alcohdex.core.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import io.memetic.alcohdex.HomeActivity;
import io.memetic.alcohdex.core.BaseViewModel;
import io.memetic.alcohdex.core.dagger.modules.AppModule;
import io.memetic.alcohdex.data.EntryRepository;
import io.memetic.alcohdex.feature.entries.AddEntryActivity;
import io.memetic.alcohdex.feature.entries.AddEntryFragment;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/16/17
 */
@Singleton
@Component(modules = {AppModule.class, EntryRepository.class})
public interface AppComponent {
    void inject(EntryRepository repository);

    void inject(HomeActivity fragment);

    void inject(AddEntryFragment fragment);

    void inject(AddEntryActivity activity);

    void inject(BaseViewModel viewModel);
}
