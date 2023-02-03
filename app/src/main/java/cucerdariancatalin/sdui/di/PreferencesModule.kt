package cucerdariancatalin.sdui.di

import cucerdariancatalin.sdui.data.api.TooltipPreferencesApi
import cucerdariancatalin.sdui.domain.impl.TooltipPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun bindPreferencesService(
        impl: TooltipPreferencesImpl
    ): TooltipPreferencesApi
}