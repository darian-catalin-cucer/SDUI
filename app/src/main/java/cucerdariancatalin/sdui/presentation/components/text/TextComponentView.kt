package cucerdariancatalin.sdui.presentation.components.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cucerdariancatalin.sdui.R
import cucerdariancatalin.sdui.data.models.showCase.ShowCaseStrategy
import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.data.models.showCase.ShapeType
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseState
import cucerdariancatalin.sdui.presentation.ui.components.showCase.ShowCaseStyle
import cucerdariancatalin.sdui.presentation.ui.components.showCase.TooltipView
import cucerdariancatalin.sdui.presentation.ui.components.showCase.asShowCaseTarget
import cucerdariancatalin.sdui.presentation.ui.components.showCase.rememberShowCaseState
import cucerdariancatalin.sdui.presentation.ui.theme.Typography

@Composable
fun TextComponentView(
    modifier: Modifier,
    componentIndex: Int,
    showCaseState: ShowCaseState,
    text: String
) {
    Text(
        text = text,
        style = Typography.h6,
        color = MaterialTheme.colors.secondary,
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp)
            .asShowCaseTarget(
                index = componentIndex,
                style = ShowCaseStyle.Default.copy(
                    shapeType = ShapeType.RECTANGLE,
                    cornerRadius = 5.dp,
                    withAnimation = false
                ),
                content = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TooltipView(text = stringResource(R.string.tooltip_text))

                        Text(
                            text = stringResource(R.string.tooltip_text_subtitle),
                            color = Color.White,
                            style = Typography.h5,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                strategy = ShowCaseStrategy(onlyUserInteraction = true),
                key = RenderType.TEXT.value,
                state = showCaseState
            )
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewHeaderComponentView() {
    TextComponentView(
        modifier = Modifier,
        0,
        rememberShowCaseState(),
        "Hello"
    )
}