package cucerdariancatalin.sdui.presentation.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cucerdariancatalin.sdui.R
import cucerdariancatalin.sdui.data.models.showCase.ShapeType
import cucerdariancatalin.sdui.data.models.showCase.ShowCaseStrategy
import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.destinations.CardScreenDestination
import cucerdariancatalin.sdui.presentation.components.common.CardItemVIew
import cucerdariancatalin.sdui.presentation.components.common.TitleDecoratedView
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseState
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseStyle
import cucerdariancatalin.sdui.presentation.ui.components.showCase.TooltipView
import cucerdariancatalin.sdui.presentation.ui.components.showCase.asShowCaseTarget
import cucerdariancatalin.sdui.presentation.ui.components.showCase.rememberShowCaseState
import cucerdariancatalin.sdui.presentation.viewModels.CardsViewModel

const val CARD_COMPONENT_SCREEN_TAG = "card_component_screen"
const val CARD_COMPONENT_TAG = "card_component"

@Composable
fun CardsComponentViewScreen(
    modifier: Modifier,
    data: CardsModel,
    componentIndex: Int,
    showCaseState: ShowCaseState,
    viewModel: CardsViewModel = hiltViewModel(),
) {
    CardsComponentView(
        modifier = modifier.testTag(CARD_COMPONENT_SCREEN_TAG),
        data = data,
        componentIndex = componentIndex,
        showCaseState = showCaseState
    ) { title, images ->
        viewModel.navigateToCardsDetail(
            CardScreenDestination(title, images.toTypedArray())
        )
    }
}

@Composable
fun CardsComponentView(
    modifier: Modifier,
    data: CardsModel,
    componentIndex: Int,
    showCaseState: ShowCaseState,
    onNavigateToDetail:(String, List<String>) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {

        TitleDecoratedView(
            text = data.title
        )

        LazyRow(
            modifier = Modifier.testTag(CARD_COMPONENT_TAG),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        ) {
            itemsIndexed(
                items = data.cardElements
            ) { index, item ->

                val modifierCard = if (index == 0) {
                    Modifier.asShowCaseTarget(
                        index = componentIndex,
                        style = ShowCaseStyle.Default.copy(
                            shapeType = ShapeType.RECTANGLE,
                            cornerRadius = 16.dp,
                            withAnimation = false
                        ),
                        content = {
                            TooltipView(text = stringResource(R.string.tooltip_cards))
                        },
                        strategy = ShowCaseStrategy(firstToHappen = true),
                        key = RenderType.CARDS.value,
                        state = showCaseState
                    )
                } else Modifier

                CardItemVIew(
                    modifier = modifierCard,
                    title = item.title,
                    images = item.images
                ) {
                    onNavigateToDetail.invoke(item.title,
                        item.images.map { it.imageURL })
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCardsComponentView() {
    CardsComponentView(
        data = CardsModel(
            cardElements = listOf(
                CardElement("Hola", images = emptyList())
            ),
            title = "Title",
        ),
        componentIndex = 0,
        showCaseState = rememberShowCaseState(),
        modifier = Modifier
    ) { _, _ -> }
}