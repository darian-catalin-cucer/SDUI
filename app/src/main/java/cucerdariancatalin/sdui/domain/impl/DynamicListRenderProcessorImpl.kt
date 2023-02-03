package cucerdariancatalin.sdui.domain.impl

import cucerdariancatalin.sdui.data.api.DynamicListRenderProcessorApi
import cucerdariancatalin.sdui.data.renders.base.DynamicListRender
import javax.inject.Inject

class DynamicListRenderProcessorImpl @Inject constructor(
    private val renders: MutableSet<@JvmSuppressWildcards DynamicListRender<*>>
): DynamicListRenderProcessorApi {

    override suspend fun <T> processResource(render: String, resource: T?): Any? {
        return renders.firstOrNull {
            it.renders.any { renderValue -> renderValue.value == render }
        }?.resolve(render, resource) ?: resource
    }
}