package cucerdariancatalin.sdui.data.models

import cucerdariancatalin.sdui.data.factories.base.DynamicListFactory
import cucerdariancatalin.sdui.data.listeners.DynamicListComponentListener

data class DynamicListElement(
    val factory: DynamicListFactory?,
    val componentItemModel: ComponentItemModel
)