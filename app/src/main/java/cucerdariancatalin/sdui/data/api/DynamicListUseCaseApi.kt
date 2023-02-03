package cucerdariancatalin.sdui.data.api

import cucerdariancatalin.sdui.data.actions.DynamicListAction
import cucerdariancatalin.sdui.data.models.DynamicListRequestModel
import kotlinx.coroutines.flow.Flow

interface DynamicListUseCaseApi {

    suspend fun get(
        page: Int,
        requestModel: DynamicListRequestModel,
        withSkeletons: Boolean = true
    ): Flow<DynamicListAction>
}