package cucerdariancatalin.sdui.domain.useCases

import com.javi.render.processor.data.enums.RenderType
import cucerdariancatalin.sdui.data.actions.DynamicListAction
import cucerdariancatalin.sdui.data.api.DynamicListControllerApi
import cucerdariancatalin.sdui.data.api.DynamicListUseCaseApi
import cucerdariancatalin.sdui.data.models.DynamicListRequestModel
import cucerdariancatalin.sdui.di.IODispatcher
import cucerdariancatalin.sdui.domain.database.AppDatabase
import cucerdariancatalin.sdui.domain.database.skeletons.SkeletonsEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DynamicListUseCaseImpl @Inject constructor(
    @IODispatcher val ioDispatcher: CoroutineDispatcher,
    private val controller: DynamicListControllerApi,
    private val database: AppDatabase
): DynamicListUseCaseApi {

    override suspend fun get(
        page: Int,
        requestModel: DynamicListRequestModel,
        withSkeletons: Boolean
    ): Flow<DynamicListAction> {

        return controller
            .get(page, requestModel)
            .onEach {
                // Save skeletons
                if (it is DynamicListAction.SuccessAction) {
                    database.skeletonsDao().saveSkeletonsByContext(
                        SkeletonsEntity(
                            context = requestModel.contextType.source,
                            renders = (it.container.headers + it.container.bodies).map {
                                    component -> RenderType.valueOf(component.render.uppercase())
                            }
                        )
                    )
                }
            }
            .onStart {

                if (withSkeletons) {
                    val skeletonContext = withContext(Dispatchers.IO) {
                        database.skeletonsDao()
                            .provideSkeletonsByContext(requestModel.contextType.source)
                    }

                    skeletonContext?.let {
                        emit(DynamicListAction.SkeletonAction(it.renders))
                    } ?: kotlin.run {
                        emit(DynamicListAction.LoadingAction)
                    }
                }
            }
            .catch {
                println(it)
                emit(DynamicListAction.ErrorAction(it))
            }.flowOn(ioDispatcher)
    }
}