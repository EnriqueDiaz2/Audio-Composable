package figueroa.enrique.audiocomposable.data.base

import androidx.room3.Database
import androidx.room3.RoomDatabase
import figueroa.enrique.audiocomposable.data.db.HistorialDao
import figueroa.enrique.audiocomposable.data.db.PresetsDao
import figueroa.enrique.audiocomposable.data.model.Historial
import figueroa.enrique.audiocomposable.data.model.Preset

@Database(entities = [Preset::class, Historial::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun presetsDao(): PresetsDao
    abstract fun historialDao(): HistorialDao
}