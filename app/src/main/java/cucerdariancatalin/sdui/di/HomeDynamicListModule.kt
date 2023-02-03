package cucerdariancatalin.sdui.di

import com.google.gson.Gson
import cucerdariancatalin.sdui.data.api.TooltipPreferencesApi
import cucerdariancatalin.sdui.data.controllers.DefaultDynamicListController
import cucerdariancatalin.sdui.data.factories.base.DynamicListFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object HomeDynamicListModule {

    @Provides
    fun provideDefaultAdapterController(
        delegates: MutableSet<@JvmSuppressWildcards DynamicListFactory>,
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
        tooltipPreferencesApi: TooltipPreferencesApi
    ): DefaultDynamicListController {
        return DefaultDynamicListController(
            delegates,
            defaultDispatcher,
            tooltipPreferencesApi
        )
    }

    @Provides
    fun provideGson() = Gson()
}