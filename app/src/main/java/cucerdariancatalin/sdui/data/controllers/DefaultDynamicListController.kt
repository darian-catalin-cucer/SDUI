package cucerdariancatalin.sdui.data.controllers

import cucerdariancatalin.sdui.data.api.TooltipPreferencesApi
import cucerdariancatalin.sdui.data.models.ComponentItemModel
import cucerdariancatalin.sdui.data.factories.base.DynamicListFactory
import com.javi.render.processor.data.enums.RenderType
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DefaultDynamicListController @Inject constructor(
    override val delegates: MutableSet<@JvmSuppressWildcards DynamicListFactory>,
    override val defaultDispatcher: CoroutineDispatcher,
    override val tooltipPreferencesApi: TooltipPreferencesApi,
): DynamicListComposeController() {

    override fun getMapComponents(): List<ComponentItemModel> = data

    override fun getMapSkeletons(): List<RenderType> = skeletons
}