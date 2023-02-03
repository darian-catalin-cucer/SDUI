package cucerdariancatalin.sdui.data.factories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.javi.render.processor.annotations.factory.AdapterFactory
import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.data.factories.base.DynamicListFactory
import cucerdariancatalin.sdui.data.models.ComponentInfo
import cucerdariancatalin.sdui.data.models.ComponentItemModel
import cucerdariancatalin.sdui.presentation.components.profile.ProfileComponentScreenView
import cucerdariancatalin.sdui.presentation.components.profile.ProfileModel
import cucerdariancatalin.sdui.presentation.components.profile.profileHeight
import javax.inject.Inject

@AdapterFactory
class ProfileFactory @Inject constructor(

): DynamicListFactory {

    override val renders: List<RenderType> = listOf(RenderType.PROFILE)

    @Composable
    override fun CreateComponent(
        modifier: Modifier,
        component: ComponentItemModel,
        componentInfo: ComponentInfo
    ) {
        val model = remember {
            derivedStateOf { component.resource as ProfileModel }
        }

        ProfileComponentScreenView(
            model = model.value,
            isExpandedScreen = componentInfo.windowWidthSizeClass == WindowWidthSizeClass.Expanded
        )
    }

    @Composable
    override fun CreateSkeleton() {
        Box(
          modifier = Modifier
              .testTag("skeleton")
              .height(profileHeight)
              .background(MaterialTheme.colors.onPrimary)
        )
    }
}