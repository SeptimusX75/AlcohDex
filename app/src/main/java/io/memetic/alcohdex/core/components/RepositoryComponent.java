package io.memetic.alcohdex.core.components;

import javax.inject.Singleton;

import dagger.Component;
import io.memetic.alcohdex.HomeActivity;
import io.memetic.alcohdex.core.modules.RepositoryModule;
import io.memetic.alcohdex.feature.entries.AddEntryFragment;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
@Singleton
@Component(modules = {RepositoryModule.class})
public interface RepositoryComponent {
    void inject(HomeActivity fragment);

    void inject(AddEntryFragment fragment);
}
