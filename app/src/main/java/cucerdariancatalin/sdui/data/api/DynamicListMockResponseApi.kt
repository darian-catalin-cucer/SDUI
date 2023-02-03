package cucerdariancatalin.sdui.data.api

import cucerdariancatalin.sdui.data.models.DataContentModel

interface DynamicListMockResponseApi {

    suspend fun getJsonDataFromAsset(): DataContentModel
}