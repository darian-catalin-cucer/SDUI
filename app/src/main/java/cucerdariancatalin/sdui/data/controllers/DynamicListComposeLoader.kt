package cucerdariancatalin.sdui.data.controllers

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import cucerdariancatalin.sdui.data.actions.ContextViewAction
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseState

abstract class DynamicListComposeLoader {

    @Suppress("LongParameterList")
    @Composable
    abstract fun <T: DynamicListComposeController> DynamicListScreen(
        bodyAdapterController: T,
        headerAdapterController: T,
        action: ContextViewAction?,
        widthSizeClass: WindowWidthSizeClass,
        showCaseState: ShowCaseState,
        bodyListState: LazyListState
    )
}