package figueroa.enrique.audiocomposable.data.db

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import figueroa.enrique.audiocomposable.data.model.Preset
import kotlinx.coroutines.flow.Flow

@Dao
interface PresetsDao {
    @Query("SELECT * FROM presets ORDER BY id DESC")
    fun obtenerPresets(): Flow<List<Preset>>

    @Insert
    suspend fun insertPreset(preset: Preset): Long

    @Update
    suspend fun actualizarPreset(preset: Preset)

    @Delete
    suspend fun eliminarPreset(preset: Preset)
}
