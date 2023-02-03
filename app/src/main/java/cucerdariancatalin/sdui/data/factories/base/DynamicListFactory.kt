package cucerdariancatalin.sdui.data.factories.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.data.models.ComponentInfo
import cucerdariancatalin.sdui.data.models.ComponentItemModel

interface DynamicListFactory {

    /**
     * List of compatible renders.
     */
    val renders: List<RenderType>

    /**
     * This render should show a show case.
     */
    val hasShowCaseConfigured: Boolean
        get() = false

    /**
     * Create composable view.
     */
    @Composable
    fun CreateComponent(
        modifier: Modifier,
        component: ComponentItemModel,
        componentInfo: ComponentInfo
    )

    /**
     * Create skeleton for current composable view.
     */
    @Composable
    fun CreateSkeleton()
}