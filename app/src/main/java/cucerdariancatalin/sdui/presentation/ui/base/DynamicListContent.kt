@file:OptIn(ExperimentalFoundationApi::class)

package cucerdariancatalin.sdui.presentation.ui.base

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import cucerdariancatalin.sdui.data.actions.ScrollAction
import cucerdariancatalin.sdui.data.models.ComponentInfo
import cucerdariancatalin.sdui.data.models.DynamicListElement
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseState
import cucerdariancatalin.sdui.presentation.ui.utils.rememberMetricsStateHolder

@Suppress("LongParameterList", "FunctionNaming")
@Composable
fun DynamicListScreen(
    content: List<DynamicListElement>,
    listState: LazyListState,
    widthSizeClass: WindowWidthSizeClass,
    showCaseState: ShowCaseState,
    withVerticalPadding: Boolean = true,
    onAction: ((ScrollAction) -> Unit)? = null
) {

    val metricsStateHolder = rememberMetricsStateHolder()

    // Reporting scrolling state from compose should be done from side effect to prevent recomposition.
    LaunchedEffect(metricsStateHolder, listState) {
        snapshotFlow { listState.isScrollInProgress }.collect { isScrolling ->
            if (isScrolling) {
                metricsStateHolder.state?.putState("DynamicListScreen", "Scrolling")
            } else {
                metricsStateHolder.state?.putState("DynamicListScreen", "Scrolling stop")
            }
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .wrapContentHeight()
            .testTag("dynamic-list-container"),
        state = listState,
        contentPadding = PaddingValues(
            bottom = if (content.isNotEmpty() && withVerticalPadding) 16.dp else 0.dp,
            top = if (withVerticalPadding) 16.dp else 0.dp
        )
    ) {
        items(
            items = content,
            key = { it.componentItemModel.index },
            contentType = { it.componentItemModel.render }
        ) {
            it.factory?.CreateComponent(
                modifier = Modifier
                    .animateItemPlacement(),
                component = it.componentItemModel,
                componentInfo = ComponentInfo(
                    scrollAction = onAction,
                    windowWidthSizeClass = widthSizeClass,
                    showCaseState = showCaseState
                )
            )
        }
    }
}