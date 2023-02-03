package cucerdariancatalin.sdui.data.actions

import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.data.models.DynamicListContainer

sealed class DynamicListAction {
    /**
     * Show loader view
     */
    object LoadingAction : DynamicListAction()

    /**
     * Show error view
     */
    class ErrorAction(val exception: Throwable): DynamicListAction()

    /**
     * Show data from response
     */
    class SuccessAction(val container: DynamicListContainer): DynamicListAction()

    /**
     * Show skeleton
     */
    class SkeletonAction(val renderTypes: List<RenderType>): DynamicListAction()
}