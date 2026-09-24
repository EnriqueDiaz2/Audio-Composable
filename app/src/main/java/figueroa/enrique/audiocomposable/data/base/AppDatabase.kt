package figueroa.enrique.audiocomposable.data.base

import androidx.room3.Database
import androidx.room3.RoomDatabase
import figueroa.enrique.audiocomposable.data.model.Preset
import figueroa.enrique.audiocomposable.data.model.Historial
import figueroa.enrique.audiocomposable.data.db.PresetsDao
import figueroa.enrique.audiocomposable.data.db.HistorialDao
import figueroa.enrique.audiocomposable.data.db.ParametersDao
import figueroa.enrique.audiocomposable.data.model.Parameters

@Database(entities = [Preset::class, Historial::class, Parameters::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun presetDao(): PresetsDao
    abstract fun historialDao(): HistorialDao
    abstract fun parametersDao(): ParametersDao
}