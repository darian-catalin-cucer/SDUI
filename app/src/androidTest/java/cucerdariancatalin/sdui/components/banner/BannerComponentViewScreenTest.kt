package cucerdariancatalin.sdui.components.banner

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import cucerdariancatalin.sdui.MainActivity
import cucerdariancatalin.sdui.presentation.components.banner.BANNER_IMAGE_SCREEN_TEST_TAG
import cucerdariancatalin.sdui.presentation.components.banner.BannerComponentViewScreen
import cucerdariancatalin.sdui.presentation.components.banner.BannerModel
import cucerdariancatalin.sdui.presentation.ui.components.showCase.rememberShowCaseState
import cucerdariancatalin.sdui.presentation.ui.theme.DynamicListComposeTheme
import cucerdariancatalin.sdui.presentation.viewModels.BannerViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BannerComponentViewScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        val viewModel = composeTestRule.activity.viewModels<BannerViewModel>().value
        composeTestRule.activity.setContent {
            DynamicListComposeTheme {
                BannerComponentViewScreen(
                    modifier = Modifier,
                    model = BannerModel(String()),
                    componentIndex = 0,
                    showCaseState = rememberShowCaseState(),
                    viewModel = viewModel
                )
            }
        }
    }

    @Test
    fun bannerScreenShouldHaveComponentView() {
        composeTestRule
            .onNodeWithTag(BANNER_IMAGE_SCREEN_TEST_TAG, useUnmergedTree = true)
            .assertExists("BannerComponentViewScreen does has not have a BannerComponentView!")
    }
}