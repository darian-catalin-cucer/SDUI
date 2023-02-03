package cucerdariancatalin.sdui.di

import cucerdariancatalin.sdui.data.api.DynamicListControllerApi
import cucerdariancatalin.sdui.data.api.DynamicListMockResponseApi
import cucerdariancatalin.sdui.data.api.DynamicListRenderProcessorApi
import cucerdariancatalin.sdui.domain.impl.DynamicListControllerImpl
import cucerdariancatalin.sdui.domain.impl.DynamicListMockResponseImpl
import cucerdariancatalin.sdui.domain.impl.DynamicListRenderProcessorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DynamicListWiringModule {

    @Binds
    @Singleton
    abstract fun bindDynamicListController(
        impl: DynamicListControllerImpl
    ): DynamicListControllerApi

    @Binds
    @Singleton
    abstract fun bindDynamicListMockResponse(
        impl: DynamicListMockResponseImpl
    ): DynamicListMockResponseApi

    @Binds
    @Singleton
    abstract fun bindDynamicListRenderProcessor(
        impl: DynamicListRenderProcessorImpl
    ): DynamicListRenderProcessorApi
}