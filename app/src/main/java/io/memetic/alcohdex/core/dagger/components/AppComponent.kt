package io.memetic.alcohdex.core.dagger.components

import dagger.Component
import io.memetic.alcohdex.HomeActivity
import io.memetic.alcohdex.core.BaseViewModel
import io.memetic.alcohdex.core.dagger.modules.AppModule
import io.memetic.alcohdex.data.EntryRepository
import io.memetic.alcohdex.feature.entries.AddEntryActivity
import io.memetic.alcohdex.feature.entries.AddEntryFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, EntryRepository::class))
interface AppComponent {
    fun inject(repository: EntryRepository)

    fun inject(fragment: HomeActivity)

    fun inject(fragment: AddEntryFragment)

    fun inject(activity: AddEntryActivity)

    fun inject(viewModel: BaseViewModel)
}
