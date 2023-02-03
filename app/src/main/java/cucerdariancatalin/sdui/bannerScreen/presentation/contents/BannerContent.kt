package cucerdariancatalin.sdui.bannerScreen.presentation.contents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cucerdariancatalin.sdui.data.models.ContextType
import cucerdariancatalin.sdui.presentation.components.common.ImageComponentView
import cucerdariancatalin.sdui.presentation.ui.components.headers.DynamicListHeaderComponentView

@Composable
fun BannerContent(
    imageURL: String
) {
    Box {

        ImageComponentView(
            modifier = Modifier.fillMaxSize(),
            imageURL = imageURL
        )

        DynamicListHeaderComponentView(
            title = "Esto es un banner",
            contextType = ContextType.BANNER_DETAIL
        )
    }
}

@Composable
@Preview
fun PreviewBannerContent() {
    BannerContent(
        "Hello",
    )
}