package cucerdariancatalin.sdui.data.factories

import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import cucerdariancatalin.sdui.MainActivity
import cucerdariancatalin.sdui.data.models.ComponentInfo
import cucerdariancatalin.sdui.data.models.ComponentItemModel
import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.presentation.components.card.CardsModel
import cucerdariancatalin.sdui.presentation.ui.components.showCase.rememberShowCaseState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class CardsFactoryTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var factory: CardsFactory

    private val componentItemModel by lazy {
        ComponentItemModel(
            render = RenderType.CARDS.name,
            index =  0,
            resource = CardsModel(
                title = String(),
                cardElements = emptyList()
            )
        )
    }

    @Before
    fun setUp() {
        hiltTestRule.inject()
        factory = CardsFactory()
    }

    @Test
    fun createComponentShouldHaveCardsComponentViewScreen() {

        composeTestRule.activity.setContent {
            factory.CreateComponent(
                modifier = Modifier,
                component = componentItemModel,
                componentInfo = ComponentInfo(
                    windowWidthSizeClass = calculateWindowSizeClass(composeTestRule.activity).widthSizeClass,
                    showCaseState = rememberShowCaseState()
                )
            )
        }

        composeTestRule
            .onNodeWithTag("cards_component")
            .assertExists()
    }

    @Test
    fun createSkeletonShouldHaveSkeleton() {

        composeTestRule.activity.setContent {
            factory.CreateSkeleton()
        }

        composeTestRule
            .onNodeWithTag("skeleton")
            .assertExists()
    }

    @Test
    fun renderNameShouldBe_CARDS() {
        assert(factory.renders.contains(RenderType.CARDS))
    }
}