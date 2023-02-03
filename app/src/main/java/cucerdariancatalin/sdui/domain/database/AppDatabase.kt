package cucerdariancatalin.sdui.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cucerdariancatalin.sdui.domain.database.converters.SkeletonConverter
import cucerdariancatalin.sdui.domain.database.skeletons.SkeletonsDao
import cucerdariancatalin.sdui.domain.database.skeletons.SkeletonsEntity

@Database(entities = [SkeletonsEntity::class], version = 1, exportSchema = false)
@TypeConverters(SkeletonConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun skeletonsDao(): SkeletonsDao
}