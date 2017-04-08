package io.memetic.alcohdex.core.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.memetic.alcohdex.data.EntryRepository;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
@Module
public class RepositoryModule {
    @Provides
    @Singleton
    EntryRepository providesRepository() {
        return EntryRepository.getInstance();
    }
}
