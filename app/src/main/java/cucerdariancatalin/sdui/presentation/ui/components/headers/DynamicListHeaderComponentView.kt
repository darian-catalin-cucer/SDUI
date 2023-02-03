package cucerdariancatalin.sdui.presentation.ui.components.headers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import cucerdariancatalin.sdui.data.models.ContextType
import cucerdariancatalin.sdui.data.models.showCase.ShapeType
import cucerdariancatalin.sdui.data.models.showCase.ShowCaseStrategy
import cucerdariancatalin.sdui.presentation.ui.components.BackButtonComponentView
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseStyle
import cucerdariancatalin.sdui.presentation.ui.components.showCase.TooltipView
import cucerdariancatalin.sdui.presentation.ui.components.showCase.asShowCaseTarget
import cucerdariancatalin.sdui.presentation.ui.components.showCase.rememberShowCaseState
import cucerdariancatalin.sdui.presentation.ui.theme.DynamicListComposeTheme
import cucerdariancatalin.sdui.presentation.ui.theme.Typography
import cucerdariancatalin.sdui.presentation.viewModels.HeaderViewModel

/**
 * Define header design by context
 */
@Composable
fun DynamicListHeaderComponentView(
    title: String,
    contextType: ContextType,
    bodyLazyListState: LazyListState? = null,
    bodyLazyGridState: LazyGridState? = null,
    viewModel: HeaderViewModel = hiltViewModel()
) {

    val icon = if (viewModel.isHome()) Icons.Default.Star else Icons.Default.ArrowBack

    when (contextType) {

        ContextType.BANNER_DETAIL -> {
            SimpleHeaderView(
                title = title,
                icon = icon,
                onIconClick = {
                    viewModel.handleIconClick()
                }
            )
        }

        ContextType.HOME, ContextType.CARD_DETAIL -> {
            HeaderWithImageView(
                title = title,
                bodyLazyListState = bodyLazyListState,
                bodyLazyGridState = bodyLazyGridState,
                icon = icon,
                onIconClick = {
                    viewModel.handleIconClick()
                }
            )
        }
    }
}

@Composable
fun SimpleHeaderView(
    title: String,
    icon: ImageVector,
    onIconClick: () -> Unit
) {
    val showCaseState = rememberShowCaseState()

    Column {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight(),
        ) {
            val (backRef, titleRef) = createRefs()

            BackButtonComponentView(
                modifier = Modifier
                    .constrainAs(backRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .asShowCaseTarget(
                        index = 0,
                        style = ShowCaseStyle.Default.copy(shapeType = ShapeType.CIRCLE),
                        content = {
                            TooltipView(text = "Aquí puedes dar back")
                        },
                        strategy = ShowCaseStrategy(onlyUserInteraction = true),
                        key = "back-button",
                        state = showCaseState
                    ),
                onClick = onIconClick,
                iconColor = MaterialTheme.colors.secondary,
                icon = icon
            )

            Text(
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(backRef.top)
                    bottom.linkTo(backRef.bottom)
                    start.linkTo(backRef.end, 10.dp)
                },
                text = title,
                style = Typography.h6,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHeaderComponentView() {
    DynamicListComposeTheme {
        DynamicListHeaderComponentView(
            title = "Hello from the header view of DynamicList",
            contextType = ContextType.HOME,
            rememberLazyListState()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSimpleHeaderComponentView() {
    DynamicListComposeTheme {
        DynamicListHeaderComponentView(
            title = "Hello from the header view of DynamicList",
            contextType = ContextType.HOME,
            rememberLazyListState()
        )
    }
}