package cucerdariancatalin.sdui.presentation.viewModels

import cucerdariancatalin.sdui.data.models.ContextType
import cucerdariancatalin.sdui.data.models.DynamicListRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ContextViewModel() {

    override val context = ContextType.HOME

    override val requestModel = DynamicListRequestModel(
        contextType = context
    )
}