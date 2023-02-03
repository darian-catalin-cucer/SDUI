package cucerdariancatalin.sdui.data.models

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import cucerdariancatalin.sdui.data.actions.ScrollAction
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseState

data class ComponentInfo(
    val windowWidthSizeClass: WindowWidthSizeClass,
    val showCaseState: ShowCaseState,
    val scrollAction: ((ScrollAction) -> Unit)? = null
)
